package web.bean.invoice;

import io.swagger.models.auth.In;
import main.domain.Invoice;
import main.domain.enums.PaymentStatus;
import main.service.InvoiceService;
import org.primefaces.event.SelectEvent;
import web.bean.BaseBean;
import web.core.helper.FrontendHelper;
import web.core.helper.RedirectHelper;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
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

    public PaymentStatus[] getPaymentStatuses() {
        return PaymentStatus.values();
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

    public void onRowSelect(SelectEvent event) {
        Invoice selectedInvoice = (Invoice) event.getObject();
        RedirectHelper.redirect("/pages/invoice/invoice.xhtml?invoiceId=" + selectedInvoice.getId());
    }


    public void setSelectedInvoice(Invoice selectedInvoice) {
        this.selectedInvoice = selectedInvoice;
    }

    public Invoice getSelectedInvoice() {
        return selectedInvoice;
    }
}
