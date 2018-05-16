package main.utils;

import main.service.InvoiceService;
import org.eclipse.persistence.config.SessionCustomizer;
import org.eclipse.persistence.internal.databaseaccess.Accessor;
import org.eclipse.persistence.internal.sessions.AbstractSession;
import org.eclipse.persistence.sequencing.Sequence;
import org.eclipse.persistence.sessions.Session;

import javax.inject.Inject;
import java.util.Vector;

public class InvoiceNrGenerator extends Sequence implements SessionCustomizer {

    @Inject
    InvoiceService invoiceService;

    public InvoiceNrGenerator() {
    }

    public InvoiceNrGenerator(String name) {
        super(name);
    }

    @Override
    public void customize(Session session) throws Exception {
        InvoiceNrGenerator invoiceNrGenerator = new InvoiceNrGenerator("invoicenr");
        session.getLogin().addSequence(invoiceNrGenerator);
    }

    @Override
    public boolean shouldAcquireValueAfterInsert() {
        return false;
    }

    @Override
    public boolean shouldUseTransaction() {
        return false;
    }

    @Override
    public Object getGeneratedValue(Accessor accessor, AbstractSession abstractSession, String s) {
        Long lastInvoiceNr = this.invoiceService.findLastInvoiceNr();
        return this.invoiceService.getNextInvoiceNr(lastInvoiceNr);
    }

    @Override
    public Vector getGeneratedVector(Accessor accessor, AbstractSession abstractSession, String s, int i) {
        return null;
    }

    @Override
    public void onConnect() {

    }

    @Override
    public void onDisconnect() {

    }
}
