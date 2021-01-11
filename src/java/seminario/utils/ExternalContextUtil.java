/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package seminario.utils;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Sergio
 */
public class ExternalContextUtil {
    
    
    public static  ExternalContext getExternalContext(){
        
    ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
    return context;
    }
    
     public static  FacesContext getExternalInstance(){
        
    FacesContext context = FacesContext.getCurrentInstance();
    return context;
    }
     
     public static String getExternalContextPath(){
         
     String contextPath = ((HttpServletRequest) getExternalContext().getRequest()).getContextPath();
     return  contextPath;
     }
     
    
}
