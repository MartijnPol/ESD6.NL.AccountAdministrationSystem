package web.filter;

import web.bean.login.SessionBean;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Thom van de Pas on 23-3-2018
 */
@WebFilter(urlPatterns = {"/*", "/AccountAdministrationSystem/*"})
public class LoginFilter implements Filter {

    @Inject
    private SessionBean sessionBean;

    public void init(FilterConfig filterConfig) throws ServletException {
        // Don't need an implementation
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (sessionBean == null || sessionBean.getLoggedInUser() == null) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            HttpSession session = request.getSession(false);
            String loginURI = request.getContextPath() + "/login.xhtml";

            boolean loggedIn = session != null && session.getAttribute("user") != null;
            boolean loginRequest = request.getRequestURI().equals(loginURI);

            if (loggedIn || loginRequest) {
                filterChain.doFilter(request, response);
            } else {
                response.sendRedirect(loginURI);
            }
        }
    }

    public void destroy() {
        // No implementation needed
    }
}
