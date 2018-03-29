package main.service;

import main.domain.Car;
import main.domain.Owner;
import main.domain.User;
import main.domain.UserGroup;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
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
    private OwnerService ownerService;
    @Inject
    private UserGroupService userGroupService;
    @Inject
    private UserService userService;

    public StartUp() {

    }

    @PostConstruct
    public void initData() {
        Owner owner1 = ownerService.create(new Owner("Henk", "van der Pol", new Date()));
        Owner owner2 = ownerService.create(new Owner("Frits", "Jansen", new Date()));
        Car car1 = carService.create(new Car("FD-83-TT", owner1));
        Car car2 = carService.create(new Car("KO-PX-12", owner2));
        Car car3 = carService.create(new Car("FW-739-S", owner1));
        car1.setCarTrackerId(1L);
        car2.setCarTrackerId(2L);
        car3.setCarTrackerId(3L);

        User smolders = new User("smolders", "1234", "smolders@gmail.com");

        UserGroup userGroup = new UserGroup("Regular");
        userGroup.addUser(smolders);
        userService.create(smolders);
        this.userGroupService.create(userGroup);
    }
}
