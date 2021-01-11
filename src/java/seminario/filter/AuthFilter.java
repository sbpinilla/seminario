/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seminario.filter;

import java.io.IOException;

import javax.servlet.annotation.WebFilter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.xhtml"})
public class AuthFilter implements Filter {

    private FilterConfig filterConfig = null;

    private static final String AJAX_REDIRECT_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
            + "<partial-response><redirect url=\"%s\"></redirect></partial-response>";

    public AuthFilter() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

         res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
            res.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            res.setDateHeader("Expires", 0); // Proxies.

        
        HttpSession ses = req.getSession(false);
        String reqURI = req.getRequestURI();

        boolean loggedIn = (ses != null) && (ses.getAttribute("USUARIO") != null);
        boolean initRequest = req.getRequestURI().equals(req.getContextPath() + "/");
        boolean loginRequest = req.getRequestURI().equals(req.getContextPath() + "/faces/login.xhtml");
        boolean landingRequest = req.getRequestURI().equals(req.getContextPath() + "/faces/landing.xhtml");
        
        
        if (loggedIn || initRequest || loginRequest || landingRequest ) {
            System.out.println("valido");
            
            chain.doFilter(request, response);
            
        }else{
            res.sendRedirect(req.getContextPath() + "/faces/login.xhtml"); 
                        System.out.println("no valido");
        }
             
        
       
    }

    @Override
    public void destroy() {
    }

}
