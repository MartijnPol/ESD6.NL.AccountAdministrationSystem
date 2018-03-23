package main.boundary.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * @author Thom van de Pas on 8-3-2018
 */
@ApplicationPath("api")
public class ApplicationConfig extends Application {

    public ApplicationConfig()
        {
//            BeanConfig beanConfig = new BeanConfig();
//            beanConfig.setVersion("2.0.0");
//            beanConfig.setSchemes(new String[]{"http"});
//            beanConfig.setHost("localhost:8080");
//            beanConfig.setBasePath("/AccountAdministrationSystem/api");
//            beanConfig.setResourcePackage("rest");
//            beanConfig.setPrettyPrint(true);
//            beanConfig.setScan(true);
        }

}
