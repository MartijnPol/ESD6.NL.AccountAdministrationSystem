package main.dao.implementation;

import main.dao.InvoiceDao;
import main.dao.JPA;
import main.domain.Invoice;

import javax.ejb.Stateless;

/**
 * @author Thom van de Pas on 4-4-2018
 */
@Stateless
@JPA
public class InvoiceDaoImpl extends GenericDaoJPAImpl<Invoice> implements InvoiceDao {

    public InvoiceDaoImpl() {

    }
}
