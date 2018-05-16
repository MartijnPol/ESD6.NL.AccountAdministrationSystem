package main.service;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 * @author Thom van de Pas on 8-3-2018
 */
@Singleton
@Startup
public class StartUp {

    @Inject
    private CarService carService;
    @Inject
    private CarTrackerService carTrackerService;
    @Inject
    private InvoiceService invoiceService;
    @Inject
    private OwnershipService ownershipService;
    @Inject
    private OwnerService ownerService;
    @Inject
    private RDWService rdwService;
    @Inject
    private TariffService tariffService;
    @Inject
    private UserGroupService userGroupService;
    @Inject
    private UserService userService;

    public StartUp() {

    }

    @PostConstruct
    public void initData() {
//        Address address = new Address("Nijverheidsweg", "25a", "5071NL", "Udenhout", "Nederland");
//        Address address2 = new Address("Tilburgseweg", "12", "5074FK", "Tilburg", "Nederland");
//        Owner owner1 = ownerService.createOrUpdate(new Owner("Henk", "van der Pol", new Date(), address));
//        Owner owner2 = ownerService.createOrUpdate(new Owner("Frits", "Jansen", new Date(), address2));
//
//        Ownership ownership = new Ownership();
//        ownership.setOwner(owner1);
//
//        Ownership ownership2 = new Ownership();
//        ownership2.setOwner(owner2);
//
//        Car car1 = carService.createOrUpdate(new Car("08-SK-PX", ownership));
//        Car car2 = carService.createOrUpdate(new Car("00-01-ES", ownership2));
//
//        ownership.setCar(car1);
//        ownership2.setCar(car2);
//        this.ownershipService.createOrUpdate(ownership);
//        this.ownershipService.createOrUpdate(ownership2);
//
//        CarTracker carTracker = carTrackerService.createOrUpdate(new CarTracker("NLD1", "Tesla"));
//        CarTracker carTracker2 = carTrackerService.createOrUpdate(new CarTracker("NLD2", "Volvo"));
//
//        car1.setCurrentCarTracker(carTracker);
//        car2.setCurrentCarTracker(carTracker2);
//        carTracker.setCar(car1);
//        carTracker2.setCar(car2);
//
//        carTrackerService.createOrUpdate(carTracker);
//        carTrackerService.createOrUpdate(carTracker2);
//
//        carService.createOrUpdate(car1);
//        carService.createOrUpdate(car2);
//
//        User smolders = new User("smolders", "1234", "smolders@gmail.com");
//
//        UserGroup userGroup = new UserGroup("Regular");
//        userGroup.addUser(smolders);
//        userService.createOrUpdate(smolders);
//        this.userGroupService.create(userGroup);
//
//        Invoice invoice = new Invoice();
//        invoice.setInvoiceNr(20181L);
//        invoice.setPaymentStatus(PaymentStatus.OPEN);
//        invoice.setPeriod(new Date());
//        invoice.setTotalAmount(new BigDecimal(250.30));
//        invoice.setOwnership(ownership);
//        this.invoiceService.createOrUpdate(invoice);
//
//        Invoice invoice2 = new Invoice();
//        invoice2.setInvoiceNr(20182L);
//        invoice2.setPaymentStatus(PaymentStatus.PAID);
//        invoice2.setPeriod(new Date());
//        invoice2.setTotalAmount(new BigDecimal(121.12));
//        invoice2.setOwnership(ownership);
//        this.invoiceService.createOrUpdate(invoice2);
//
//        Invoice invoice3 = new Invoice();
//        invoice3.setInvoiceNr(20183L);
//        invoice3.setPaymentStatus(PaymentStatus.OPEN);
//        invoice3.setPeriod(new Date());
//        invoice3.setTotalAmount(new BigDecimal(166.25));
//        invoice3.setOwnership(ownership2);
//        this.invoiceService.createOrUpdate(invoice3);
//
//        Tariff tariff1 = new Tariff(0.07, false);
//
//        Map<String, Double> carLabels = new HashMap<>();
//        carLabels.put("A", -20.0);
//        carLabels.put("B", -15.0);
//        carLabels.put("C", -10.0);
//        carLabels.put("D", 0.0);
//        carLabels.put("E", 10.0);
//        carLabels.put("F", 20.0);
//        carLabels.put("G", 30.0);
//
//        Map<String, Double> carFuels = new HashMap<>();
//        carFuels.put("Diesel", 20.0);
//        carFuels.put("Benzine", 10.0);
//        carFuels.put("LPG", 0.0);
//        carFuels.put("Electric", 10.0);
//        carFuels.put("Waterstof", 20.0);
//
//        tariff1.setCarLabels(carLabels);
//        tariff1.setCarFuels(carFuels);
//
//        this.tariffService.createOrUpdate(tariff1);
    }
}
