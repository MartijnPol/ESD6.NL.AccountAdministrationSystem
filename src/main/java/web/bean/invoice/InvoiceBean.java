package web.bean.invoice;

import main.domain.Invoice;
import main.service.InvoiceService;
import web.bean.BaseBean;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Thom van de Pas on 8-4-2018
 */
@Named
@ViewScoped
public class InvoiceBean extends BaseBean {

    @Inject
    private InvoiceService invoiceService;

    private Invoice invoice;
    private Long invoiceId;

    @Override
    public void init() {
        this.invoice = this.invoiceService.findById(this.invoiceId);
    }

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    //</editor-fold>
}
