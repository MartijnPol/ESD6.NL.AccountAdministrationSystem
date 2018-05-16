package main.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import main.dao.InvoiceDao;
import main.dao.JPA;
import main.domain.Car;
import main.domain.Ownership;
import main.domain.Invoice;
import main.domain.Tariff;
import main.utils.StringHelper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * @author Thom van de Pas on 4-4-2018
 */
@Stateless
public class InvoiceService {

    @Inject
    @JPA
    private InvoiceDao invoiceDao;

    @Inject
    private TariffService tariffService;

    public Invoice createOrUpdate(Invoice invoice) {
        if (invoice.getInvoiceNr() == null) {
            return this.invoiceDao.create(invoice);
        } else {
            return this.invoiceDao.update(invoice);
        }
    }

    public void delete(Invoice invoice) {
        this.invoiceDao.delete(invoice);
    }

    public List<Invoice> findAll() {
        return this.invoiceDao.findAll();
    }

    /**
     * Finds an Invoice by its id.
     *
     * @param id The id that represents the Invoice
     * @return Invoice that matches te provided id.
     */
    public Invoice findById(Long id) {
        if (id != null) {
            return this.invoiceDao.findById(id);
        }
        return null;
    }

    /**
     * Gets the name of the month from a Date.
     *
     * @param date is the whole date from which the month name has to be shown.
     * @return a String of the month name.
     */
    public String getMonthName(Date date) {
        String monthName;

        Locale loc = new Locale.Builder().setLanguage("nl").setRegion("NL").build();
        Format formatter = new SimpleDateFormat("MMMM", loc);
        monthName = formatter.format(date);

        return monthName;
    }

    /**
     * Finds an Invoice by its number.
     *
     * @param invoiceNr is the number of the Invoice to be found.
     * @return the found Invoice or null if the number is unknown.
     */
    public Invoice findByInvoiceNr(Long invoiceNr) {
        if (invoiceNr != null) {
            return this.invoiceDao.findByInvoiceNr(invoiceNr);
        }
        return null;
    }

    /**
     * Find the first Invoice that exists in the database.
     *
     * @return The found Invoice or null if there was nothing retrieved from the database.
     */
    public Invoice findFirstInvoice() {
        return this.invoiceDao.findFirstInvoice();
    }

    /**
     * Find last used invoice number.
     *
     * @return Long invoice number.
     */
    public Long findLastInvoiceNr() {
        return this.invoiceDao.findLastInvoiceNr();
    }

    /**
     * Get the next invoice number that should be used.
     *
     * @param lastUsedInvoiceNr Last used invoice number.
     * @return Next Long invoice number that should be used as identifier.
     */
    public Long getNextInvoiceNr(Long lastUsedInvoiceNr) {
        if (lastUsedInvoiceNr != null) {
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);

            String invoiceNrText = Objects.toString(lastUsedInvoiceNr);
            String currentYearText = Objects.toString(currentYear);
            String replacedInvoiceNr = invoiceNrText.replace(currentYearText, "");
            Long subtractedNr = 1L;

            if (!StringHelper.isEmpty(replacedInvoiceNr)) {
                subtractedNr = Long.valueOf(replacedInvoiceNr) + 1;
            }

            String nextInvoiceNrText = currentYearText + subtractedNr.toString();

            return Long.valueOf(nextInvoiceNrText);

        }
        return null;
    }

    //<editor-fold defaultstate="collapsed" desc="Invoice amount generation methods">

    /**
     * Total invoice amount is calculated based on different factors.
     * The amount is returned as a BigDecimal which is rounded half up.
     * Rounding mode to round towards "nearest neighbor" unless both neighbors are equidistant, in which case round up.
     *
     * @param ownership Ownership used to retrieve data from
     * @return Total amount to pay in BigDecimal format
     */
    public BigDecimal generateTotalInvoiceAmount(Ownership ownership) {
        Car car = ownership.getCar();
        Tariff tariff = tariffService.findById(1L);

        if (tariff != null) {
            double mainTariff = tariff.getTariffInEuro();
            double economicalAddition = getEconomicalAddition(car, tariff);
            double carFuelAddition = getCarFuelAddition(car, tariff);

            BigDecimal result = BigDecimal.valueOf(mainTariff + (mainTariff * economicalAddition) + (mainTariff * carFuelAddition));
            return preventNegativeAmount(result);
        }

        return BigDecimal.ZERO;
    }

    /**
     * Get the economical addition for a given car.
     * The carlabel is retrieved from the RDW data and the matching addition is searched in the tariff entity.
     * When this search return empty a default addition of 0.0 is returned.
     *
     * @param car    Car that should be checked
     * @param tariff Tariff used for calculation
     * @return Addition based on the economical label of the car when there is no label found 0.0 is returned
     */
    public double getEconomicalAddition(Car car, Tariff tariff) {
        String economicalLabel = car.getRdwData().getZuinigheidslabel();
        Double addition = 0.0;

        if (!StringHelper.isEmpty(economicalLabel) && tariff.getCarLabels().get(economicalLabel) != null) {
            addition = tariff.getCarLabels().get(economicalLabel);
        }

        return addition;
    }

    /**
     * Get the car fuel addition for a given car.
     * The car fuel is retrieved form the RDWFuel data and the matching addition is searched in the tariff entity.
     * When this search return empty a default addition of 0.0 is returned.
     *
     * @param car    Car that should be checked
     * @param tariff Tariff used for calculation
     * @return Addition based on car fuel when there is no fuel found 0.0 is returned
     */
    public double getCarFuelAddition(Car car, Tariff tariff) {
        String carFuel = car.getRdwFuelData().getBrandstof_omschrijving();
        Double addition = 0.0;

        if (!StringHelper.isEmpty(carFuel) && tariff.getCarFuels().get(carFuel) != null) {
            addition = tariff.getCarFuels().get(carFuel);
        }

        return addition;
    }

    /**
     * Prevent negative invoice amounts.
     * When amount is evaluated as negative BigDecimal.ZERO is returned.
     *
     * @param amount Amount to check for negative value
     * @return Amount or BigDecimal.ZERO when amount is evaluated as negative
     */
    public BigDecimal preventNegativeAmount(BigDecimal amount) {
        BigDecimal roundedAmount = amount.setScale(2, RoundingMode.HALF_UP);

        if (roundedAmount.compareTo(BigDecimal.ZERO) < 0) {
            roundedAmount = BigDecimal.ZERO;
        }

        return roundedAmount;
    }

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="PDF generation methods">

    /**
     * Generate invoice pdf file.
     * The OpenPDF library is used for generating the file.
     *
     * @param invoice Invoice containing all the necessary data
     */
    public ByteArrayOutputStream generateInvoicePdf(Invoice invoice) {
        Document document = new Document();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();

            document.addTitle("Factuur" + invoice.getInvoiceNr());
            document.addCreationDate();
            document.addAuthor("RekeningRijden AAS");

            Paragraph paragraph = new Paragraph("Factuur: " + invoice.getInvoiceNr());
            paragraph.setSpacingAfter(10);

            document.add(paragraph);

            document.add(getAddressTable(invoice));

            document.add(getTariffTable());

            document.add(getTotalAmountTable(invoice));
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        document.close();
        return byteArrayOutputStream;
    }

    /**
     * Get the address table that is placed at the top section in the invoice.
     * This table contains owner and car details.
     *
     * @param invoice Invoice containing all the necessary data
     * @return Address table containing car and owner details
     */
    private PdfPTable getAddressTable(Invoice invoice) {
        PdfPTable table = new PdfPTable(2);
        table.getDefaultCell().setBorder(0);

        table.addCell(getAddressParagraph(invoice.getOwnership().getOwner().getFullName(),
                invoice.getOwnership().getOwner().getAddress().getStreet(),
                invoice.getOwnership().getOwner().getAddress().getStreetNr(),
                invoice.getOwnership().getOwner().getAddress().getPostalCode(),
                invoice.getOwnership().getOwner().getAddress().getCity()));

        table.addCell(getCarParagraph(invoice.getOwnership().getCar().getLicensePlate(),
                invoice.getOwnership().getCar().getRdwData().getMerk(),
                invoice.getOwnership().getCar().getRdwData().getVoertuigsoort()));

        table.setSpacingAfter(10);

        return table;
    }

    /**
     * Get the address table cell.
     *
     * @param fullname   Owners first name and last name
     * @param street     Name of the street
     * @param streetNr   House number
     * @param postalCode Postal code
     * @param city       City the owners lives
     * @return Table cell
     */
    private PdfPCell getAddressParagraph(String fullname, String street, String streetNr, String postalCode, String city) {
        Paragraph addressParagraph = new Paragraph();

        addressParagraph.add(fullname);
        addressParagraph.add(Chunk.NEWLINE);
        addressParagraph.add(street + " " + streetNr);
        addressParagraph.add(Chunk.NEWLINE);
        addressParagraph.add(postalCode + " " + city);

        PdfPCell cell = new PdfPCell();
        cell.setBorder(0);
        cell.addElement(addressParagraph);

        return cell;
    }

    /**
     * Get the car table cell.
     *
     * @param licensePlate License plate that represents the car
     * @param brand        Brand of the car
     * @param model        Car model
     * @return Table cell
     */
    private PdfPCell getCarParagraph(String licensePlate, String brand, String model) {
        Paragraph carParagraph = new Paragraph();

        carParagraph.add(licensePlate);
        carParagraph.add(Chunk.NEWLINE);
        carParagraph.add(brand);
        carParagraph.add(Chunk.NEWLINE);
        carParagraph.add(model);

        PdfPCell cell = new PdfPCell();
        cell.setBorder(0);
        cell.addElement(carParagraph);

        return cell;
    }

    /**
     * Get the tariff table.
     * This table shows per day how much the customer needs to pay.
     *
     * @return Tariff table
     */
    private PdfPTable getTariffTable() {
        PdfPTable table = new PdfPTable(3);

        table.getDefaultCell().setPadding(5);
        Font boldFont = new Font(Font.HELVETICA, -1.0F, Font.BOLD);

        table.addCell(new Phrase("Datum", boldFont));
        table.addCell(new Phrase("Dag van de week", boldFont));
        table.addCell(new Phrase("Bedrag", boldFont));

        table.setHeaderRows(1);

        table.addCell("25-04-2018");
        table.addCell("Woensdag");
        table.addCell("€5,00");
        table.addCell("26-04-2018");
        table.addCell("Donderdag");
        table.addCell("€5,00");

        return table;
    }

    /**
     * Get the total amount that should be payed.
     *
     * @param invoice Invoice used to retrieve data from
     * @return Total amount table
     */
    private PdfPTable getTotalAmountTable(Invoice invoice) {
        PdfPTable table = new PdfPTable(3);

        table.getDefaultCell().setPadding(5);
        table.getDefaultCell().setBorder(0);
        table.getDefaultCell().setBorderWidthTop(1);

        table.addCell("");
        table.addCell("");
        table.addCell("€" + invoice.getTotalAmount().setScale(2, RoundingMode.HALF_UP));

        return table;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">

    public void setTariffService(TariffService tariffService) {
        this.tariffService = tariffService;
    }


    public void setInvoiceDao(InvoiceDao invoiceDao) {
        this.invoiceDao = invoiceDao;
    }

    //</editor-fold>
}
