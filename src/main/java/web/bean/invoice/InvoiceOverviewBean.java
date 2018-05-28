package web.bean.invoice;

import main.domain.Invoice;
import main.service.InvoiceService;
import org.primefaces.event.SelectEvent;
import web.bean.BaseBean;
import web.core.helper.FrontendHelper;
import web.core.helper.RedirectHelper;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @author Thom van de Pas on 4-4-2018
 */
@Named
@ViewScoped
public class InvoiceOverviewBean extends BaseBean {

    @Inject
    private InvoiceService invoiceService;

    private List<Invoice> invoices;
    private List<Invoice> filteredInvoices;
    private Invoice selectedInvoice;

    @Override
    @PostConstruct
    public void init() {
        this.invoices = this.invoiceService.findAll();
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public List<Invoice> getFilteredInvoices() {
        return filteredInvoices;
    }

    public void setFilteredInvoices(List<Invoice> filteredInvoices) {
        this.filteredInvoices = filteredInvoices;
    }

    /**
     * This method is fired when a row is selected in the data table.
     * The user is redirected to the invoice page where master details are displayed.
     *
     * @param event Select event from data table
     */
    public void onRowSelect(SelectEvent event) {
        Invoice selectedInvoice = (Invoice) event.getObject();
        RedirectHelper.redirect("/pages/invoice/invoice.xhtml?invoiceNr=" + selectedInvoice.getInvoiceNr());
    }

    /**
     * Delete an invoice from the database.
     * When invoice represents a null value a error message is pushed to the front-end.
     *
     * @param invoice Invoice that should be deleted
     */
    public void deleteInvoice(Invoice invoice) {
        if (invoice != null) {
            invoiceService.delete(invoice);
            this.invoices = invoiceService.findAll();
            FrontendHelper.displaySuccessSmallMessage("Factuur is succesvol verwijderd!");
        } else {
            FrontendHelper.displayErrorSmallMessage("Er ging iets mis.", "Probeer het opnieuw.");
        }
    }

    /**
     * Generate pdf file for an invoice.
     *
     * @param invoice Invoice that should be used for generation
     */
    public void generateInvoicePdf(Invoice invoice) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        ByteArrayOutputStream byteArrayOutputStream = invoiceService.generateInvoicePdf(invoice);

        httpServletResponse.setHeader("Expires", "0");
        httpServletResponse.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        httpServletResponse.setHeader("Pragma", "public");
        httpServletResponse.setHeader("Content-disposition", "inline; filename=pathfinder.pdf");

        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.setContentLength(byteArrayOutputStream.size());

        try {
            OutputStream outputStream = httpServletResponse.getOutputStream();
            byteArrayOutputStream.writeTo(outputStream);
            outputStream.flush();
            outputStream.close();
            FacesContext.getCurrentInstance().renderResponse();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setSelectedInvoice(Invoice selectedInvoice) {
        this.selectedInvoice = selectedInvoice;
    }

    public Invoice getSelectedInvoice() {
        return selectedInvoice;
    }
}
