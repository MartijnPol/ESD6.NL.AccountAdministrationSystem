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
        Owner owner1 = new Owner("Henk", "van der Pol", new Date(), address);
//        Owner owner2 = ownerService.create(new Owner("Frits", "Jansen", new Date()));
        Car car1 = carService.create(new Car("FD-83-TT", owner1));
//        Car car2 = carService.create(new Car("KO-PX-12", owner2));
//        Car car3 = carService.create(new Car("FW-739-S", owner1));
//        car1.setCarTrackerId(1L);
//        car2.setCarTrackerId(2L);
//        car3.setCarTrackerId(3L);

        User smolders = new User("smolders", "1234", "smolders@gmail.com");

        owner1 = ownerService.create(owner1);

        UserGroup userGroup = new UserGroup("Regular");
        userGroup.addUser(smolders);
        userService.create(smolders);
        this.userGroupService.create(userGroup);

        Ownership ownership = new Ownership();
        ownership.setOwner(owner1);
        ownership.setCar(car1);
        this.ownershipService.create(ownership);

        Invoice invoice = new Invoice();
        invoice.setPaymentStatus(PaymentStatus.OPEN);
        invoice.setPeriod(new Date());
        invoice.setTotalAmount(new BigDecimal(250.30));
        invoice.setOwnership(ownership);
        this.invoiceService.create(invoice);
    }
}
