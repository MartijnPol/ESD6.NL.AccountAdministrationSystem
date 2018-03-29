package web.bean.login;

import main.domain.User;
import main.service.UserService;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * @author Thom van de Pas on 23-3-2018
 */
@Named(value = "loginBean")
@ViewScoped
public class LoginBean implements Serializable {

    @Inject
    private UserService userService;

    @Inject
    private SessionBean sessionBean;

    private String username;
    private String password;

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            request.login(this.username, this.password);
        } catch (ServletException e) {
            e.printStackTrace();
        }

        User loggedInUser = this.userService.findbyUsername(request.getRemoteUser());
        this.sessionBean.setLoggedInUser(loggedInUser);

        boolean isRegular = request.isUserInRole("RegularRole");

        if (isRegular) {
            return "profile.xhtml?faces-redirect=true";
        }
        return "";
    }

    //<editor-fold desc="Getters/Setters">
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //</editor-fold>
}
