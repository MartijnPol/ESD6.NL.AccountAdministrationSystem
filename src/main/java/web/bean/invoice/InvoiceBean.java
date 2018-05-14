package web.bean.invoice;

import main.domain.Invoice;
import main.domain.enums.PaymentStatus;
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
    private Long invoiceNr;
    private String monthName;
    private PaymentStatus paymentStatus;

    @Override
    public void init() {
        this.invoice = this.invoiceService.findByInvoiceNr(this.invoiceNr);
        if (this.invoice != null) {
            this.monthName = this.invoiceService.getMonthName(this.invoice.getPeriod());
        }
    }

    public void onItemChange(PaymentStatus selectedPaymentStatus) {
        this.paymentStatus = selectedPaymentStatus;
    }

    public void update() {
        if (this.invoice != null && this.paymentStatus != null) {
            this.invoice.setPaymentStatus(this.paymentStatus);
            this.invoiceService.createOrUpdate(this.invoice);
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Long getInvoiceNr() {
        return invoiceNr;
    }

    public void setInvoiceNr(Long invoiceNr) {
        this.invoiceNr = invoiceNr;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    //</editor-fold>
}
