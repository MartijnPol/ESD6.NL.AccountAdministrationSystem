package web.filter;

import org.omnifaces.filter.HttpFilter;
import org.omnifaces.util.Servlets;
import web.bean.login.SessionBean;

import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Thom van de Pas on 23-3-2018
 */
@WebFilter(urlPatterns = {"/*"})
public class LoginFilter extends HttpFilter {

    @Inject
    private SessionBean sessionBean;

    // list URL that want to exclude from authentication filter
    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList("/login.xhtml", "/register.xhtml")
    ));

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse res, HttpSession session, FilterChain chain)
            throws ServletException, IOException {
        if (this.sessionBean == null || this.sessionBean.getLoggedInUser() == null) {
            String loginUrl = req.getContextPath() + "/login.xhtml";
            String path = req.getRequestURI().substring(req.getContextPath().length())
                    .replaceAll("[/]+$", "");

            // check if user already logged or not, session for authenticate user was on this getRemoteUser()
            boolean loggedIn = (req.getRemoteUser() != null);
            // check if the URL was appointed to login URL or not
            boolean loginRequest = req.getRequestURI().equals(loginUrl);
            boolean resourceRequest = Servlets.isFacesResourceRequest(req);
            // check if the URL was allowed or not
            boolean allowedUrl = ALLOWED_PATHS.contains(path);

            if (loggedIn || loginRequest || resourceRequest || allowedUrl) {
                if (!resourceRequest) { // Prevent browser from caching restricted resources
                    Servlets.setNoCacheHeaders(res);
                }

                chain.doFilter(req, res); // So, just continue request.
            } else {
                Servlets.facesRedirect(req, res, loginUrl);
            }
        }
        chain.doFilter(req, res);
    }
}