
package it.Filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.jsf"})
public class AuthFilter implements Filter {

    public AuthFilter() {
    }
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {   
        try {

            // verifica se la variabile sessione è impostata
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
            res.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            res.setDateHeader("Expires", 0); // Proxies.
            HttpSession ses = req.getSession(false);
            //  permette all'utente di procedere se ci sono i presupposti
            String reqURI = req.getRequestURI();
            if (reqURI.indexOf("/login.jsf") >= 0 || (ses != null && ses.getAttribute("username") != null)
                    || reqURI.indexOf("/public/") >= 0 || reqURI.contains("javax.faces.resource")) {
                chain.doFilter(request, response);
            } else // utente non loggato ma tenta di accedere ad una pagina non permessa viene rimandato alla pagina di login
            {
                res.sendRedirect(req.getContextPath() + "/login.jsf");  // L'utente anonimo viene rimandato alla pagina di login
            }
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }    
    
    }
    
    @Override
    public void destroy() {
    }
}
