package web.bean.login;

import main.domain.User;
import org.omnifaces.util.Faces;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.ServletException;
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

    /**
     * Logs a user out. Also invalidates the session and redirects the person to the login page.
     * @throws ServletException
     */
    public void logout() throws ServletException {
        this.loggedInUser = null;

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
