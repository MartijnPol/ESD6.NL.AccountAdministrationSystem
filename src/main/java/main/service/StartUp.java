package main.service;

import main.domain.*;
import main.domain.enums.PaymentStatus;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Thom van de Pas on 8-3-2018
 */
@Singleton
@Startup
public class StartUp {

    @Inject
    private CarService carService;
    @Inject
    private InvoiceService invoiceService;
    @Inject
    private OwnershipService ownershipService;
    @Inject
    private OwnerService ownerService;
    @Inject
    private UserGroupService userGroupService;
    @Inject
    private UserService userService;

    public StartUp() {

    }

    @PostConstruct
    public void initData() {
        Address address = new Address("Nijverheidsweg", "25a", "5071NL", "Udenhout", "Nederland");
        Address address2 = new Address("Tilburgseweg", "12", "5074FK", "Tilburg", "Nederland");
        Owner owner1 = ownerService.create(new Owner("Henk", "van der Pol", new Date(), address));
        Owner owner2 = ownerService.create(new Owner("Frits", "Jansen", new Date(), address2));
        Car car1 = carService.create(new Car("FD-83-TT", owner1));
        Car car2 = carService.create(new Car("KO-PX-12", owner2));

        User smolders = new User("smolders", "1234", "smolders@gmail.com");

        UserGroup userGroup = new UserGroup("Regular");
        userGroup.addUser(smolders);
        userService.create(smolders);
        this.userGroupService.create(userGroup);

        Ownership ownership = new Ownership();
        ownership.setOwner(owner1);
        ownership.setCar(car1);
        this.ownershipService.create(ownership);

        Ownership ownership2 = new Ownership();
        ownership2.setOwner(owner2);
        ownership2.setCar(car2);
        this.ownershipService.create(ownership2);

        Invoice invoice = new Invoice();
        invoice.setPaymentStatus(PaymentStatus.OPEN);
        invoice.setPeriod(new Date());
        invoice.setTotalAmount(new BigDecimal(250.30));
        invoice.setOwnership(ownership);
        this.invoiceService.create(invoice);

        Invoice invoice2 = new Invoice();
        invoice2.setPaymentStatus(PaymentStatus.PAID);
        invoice2.setPeriod(new Date());
        invoice2.setTotalAmount(new BigDecimal(121.12));
        invoice2.setOwnership(ownership);
        this.invoiceService.create(invoice2);

        Invoice invoice3 = new Invoice();
        invoice3.setPaymentStatus(PaymentStatus.OPEN);
        invoice3.setPeriod(new Date());
        invoice3.setTotalAmount(new BigDecimal(166.25));
        invoice3.setOwnership(ownership2);
        this.invoiceService.create(invoice3);
    }
}
