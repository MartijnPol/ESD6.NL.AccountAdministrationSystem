package main.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import main.dao.InvoiceDao;
import main.dao.JPA;
import main.domain.Invoice;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Thom van de Pas on 4-4-2018
 */
@Stateless
public class InvoiceService {

    @Inject
    @JPA
    private InvoiceDao invoiceDao;

    public Invoice createOrUpdate(Invoice invoice) {
        return this.invoiceDao.createOrUpdate(invoice);
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

        Format formatter = new SimpleDateFormat("MMMM");
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

    public void setInvoiceDao(InvoiceDao invoiceDao) {
        this.invoiceDao = invoiceDao;
    }

    /**
     * Generate invoice pdf file.
     * The OpenPDF library is used for generating the file.
     *
     * @param invoice Invoice containing all the necessary data
     */
    public void generateInvoicePdf(Invoice invoice) {
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream("C:/Users/Gebruiker/Documents/Development/invoice.pdf"));
            document.open();

            document.addTitle("Factuur" + invoice.getInvoiceNr());
            document.addCreationDate();
            document.addAuthor("RekeningRijden AAS");

            Paragraph paragraph = new Paragraph("Factuur: " + invoice.getInvoiceNr());
            paragraph.setSpacingAfter(10);

            document.add(paragraph);

            document.add(getAddressTable(invoice));

            document.add(getTariffTable());

            document.add(getTotalAmountTable());
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }

        document.close();
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

    private PdfPTable getTotalAmountTable() {
        PdfPTable table = new PdfPTable(3);

        table.getDefaultCell().setPadding(5);
        table.getDefaultCell().setBorder(0);
        table.getDefaultCell().setBorderWidthTop(1);

        table.addCell("");
        table.addCell("");
        table.addCell("€10,00");

        return table;
    }
}
