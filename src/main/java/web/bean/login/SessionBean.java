package web.bean.login;

import main.domain.User;
import org.omnifaces.util.Faces;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.Serializable;

import static org.omnifaces.util.Faces.invalidateSession;
import static org.omnifaces.util.Faces.redirect;

/**
 * @author Thom van de Pas on 23-3-2018
 */
@Named
@SessionScoped
public class SessionBean implements Serializable {

    private User loggedInUser;

    public void logout() throws ServletException {
        Faces.logout();
        invalidateSession();
        redirect("login.xhtml?faces-redirect=true");
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
