package web.core.helper;

import org.primefaces.context.RequestContext;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * @author Thom van de Pas on 28-3-2018
 */
public final class FrontendHelper {

    private static FacesContext getContext() {
        return FacesContext.getCurrentInstance();
    }

    private static RequestContext getPFContext() {
        return RequestContext.getCurrentInstance();
    }

    public static void displayWarningSmallMessage(String title, String message) {
        if (title == null) title = "Let op";
        displaySmallMessage(title, message, FacesMessage.SEVERITY_WARN);
    }

    public static void displayWarningSmallMessage(String message) {
        displayWarningSmallMessage(null, message);
    }

    public static void displaySuccessSmallMessage(String title, String message) {
        if (title == null) title = "Succes";
        displaySmallMessage(title, message, FacesMessage.SEVERITY_INFO);
    }

    public static void displaySuccessSmallMessage(String message) {
        displaySuccessSmallMessage(null, message);
    }

    public static void defaultMessage() {
        displaySuccessSmallMessage(null, "Opgeslagen");
    }

    public static void displayErrorSmallMessage(String title, String message) {
        if (title == null) title = "Fout";
        displaySmallMessage(title, message, FacesMessage.SEVERITY_ERROR);
    }

    public static void displayErrorSmallMessage(String message) {
        String title = "Fout";
        displaySmallMessage(title, message, FacesMessage.SEVERITY_ERROR);
    }

    public static void displaySmallMessage(String title, String message, FacesMessage.Severity severityType) {
        getContext().addMessage("growlMessage", new FacesMessage(severityType, title, message));
        getPFContext().update("defaultForm:growl");
    }
}
