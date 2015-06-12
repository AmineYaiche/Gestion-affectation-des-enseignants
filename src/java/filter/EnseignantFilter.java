/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import managedBeans.enseignant.EnseignantSession;

/**
 *
 * @author amine
 */
public class EnseignantFilter implements Filter {
    
    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public EnseignantFilter() {
    }    
    
    
   

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req   =   (HttpServletRequest)request;
        HttpServletResponse resp =   (HttpServletResponse)response; 
        
        EnseignantSession e = (EnseignantSession) req.getSession().getAttribute("enseignantSession");
        System.out.println("----------(-(-(-(-(-(-(-----------------");
        if(e != null && e.getId() > 0)
            chain.doFilter(request, response);
        else
            resp.sendRedirect(req.getServletContext().getContextPath());
            
    }

    /**
     * Return the filter configuration object for this filter.
     */
    

    /**
     * Destroy method for this filter
     */
    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {        
       
    }

   
    
    
}
