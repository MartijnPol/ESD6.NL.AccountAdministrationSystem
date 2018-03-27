package web.bean.login;

import main.domain.User;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

/**
 * @author Thom van de Pas on 23-3-2018
 */
@Named
@SessionScoped
public class SessionBean implements Serializable {

    private User loggedInUser;

    public String logout() {
        loggedInUser = null;

        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        return "/login?faces-redirect=true";
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
