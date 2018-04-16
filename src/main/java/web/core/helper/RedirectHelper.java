package web.core.helper;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Thom van de Pas on 29-3-2018
 */
public final class RedirectHelper {

    public static void redirect(String relativeUrl) {
        String url = getContextPath() + relativeUrl;

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getContextPath() {
        return ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getContextPath();
    }
}
