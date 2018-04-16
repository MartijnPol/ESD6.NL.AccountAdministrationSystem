package web.bean.invoice;

import main.domain.Invoice;
import main.service.InvoiceService;
import web.bean.BaseBean;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;

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

    @Override
    public void init() {
        this.invoice = this.invoiceService.findByInvoiceNr(this.invoiceNr);
        this.monthName = this.invoiceService.getMonthName(this.invoice.getPeriod());
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
    //</editor-fold>
}
