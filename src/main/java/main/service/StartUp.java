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
    @Inject
    private TariffService tariffService;

    public StartUp() {

    }

    @PostConstruct
    public void initData() {
        Address address = new Address("Nijverheidsweg", "25a", "5071NL", "Udenhout", "Nederland");
        Address address2 = new Address("Tilburgseweg", "12", "5074FK", "Tilburg", "Nederland");
        Owner owner1 = ownerService.createOrUpdate(new Owner("Henk", "van der Pol", new Date(), address));
        Owner owner2 = ownerService.createOrUpdate(new Owner("Frits", "Jansen", new Date(), address2));
        Car car1 = new Car("FD-83-TT", owner1);
        Car car2 = carService.createOrUpdate(new Car("KO-PX-12", owner2));
//        Car car3 = carService.create(new Car("FW-739-S", owner1));
        car1.setCarTrackerId(1L);
        car2.setCarTrackerId(2L);
//        car3.setCarTrackerId(3L);
        carService.createOrUpdate(car1);
        carService.createOrUpdate(car2);

        User smolders = new User("smolders", "1234", "smolders@gmail.com");

        UserGroup userGroup = new UserGroup("Regular");
        userGroup.addUser(smolders);
        userService.createOrUpdate(smolders);
        this.userGroupService.create(userGroup);

        Ownership ownership = new Ownership();
        ownership.setOwner(owner1);
        ownership.setCar(car1);
        this.ownershipService.createOrUpdate(ownership);

        Ownership ownership2 = new Ownership();
        ownership2.setOwner(owner2);
        ownership2.setCar(car2);
        this.ownershipService.createOrUpdate(ownership2);

        Invoice invoice = new Invoice();
        invoice.setInvoiceNr(20180001L);
        invoice.setPaymentStatus(PaymentStatus.OPEN);
        invoice.setPeriod(new Date());
        invoice.setTotalAmount(new BigDecimal(250.30));
        invoice.setOwnership(ownership);
        this.invoiceService.createOrUpdate(invoice);

        Invoice invoice2 = new Invoice();
        invoice2.setInvoiceNr(20180002L);
        invoice2.setPaymentStatus(PaymentStatus.PAID);
        invoice2.setPeriod(new Date());
        invoice2.setTotalAmount(new BigDecimal(121.12));
        invoice2.setOwnership(ownership);
        this.invoiceService.createOrUpdate(invoice2);

        Invoice invoice3 = new Invoice();
        invoice3.setInvoiceNr(20180003L);
        invoice3.setPaymentStatus(PaymentStatus.OPEN);
        invoice3.setPeriod(new Date());
        invoice3.setTotalAmount(new BigDecimal(166.25));
        invoice3.setOwnership(ownership2);
        this.invoiceService.createOrUpdate(invoice3);

        Tariff tariff1 = new Tariff();
        tariff1.setTariffInEuro(0.11);
        tariff1.setRidingDuringRushHour(true);
        this.tariffService.create(tariff1);

        Tariff tariff2 = new Tariff();
        tariff2.setTariffInEuro(0.05);
        tariff2.setRidingDuringRushHour(false);
        this.tariffService.create(tariff2);
        this.invoiceService.createOrUpdate(invoice3);

    }
}
