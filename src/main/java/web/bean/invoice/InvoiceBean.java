package web.bean.invoice;

import main.domain.Invoice;
import main.domain.enums.PaymentStatus;
import main.service.InvoiceService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Thom van de Pas on 4-4-2018
 */
@Named
@ViewScoped
public class InvoiceBean implements Serializable {

    @Inject
    private InvoiceService invoiceService;

    private List<Invoice> invoices;
    private List<Invoice> filteredInvoices;
    private Invoice invoice;

    @PostConstruct
    public void init() {
        this.invoices = this.invoiceService.getAll();
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


}
