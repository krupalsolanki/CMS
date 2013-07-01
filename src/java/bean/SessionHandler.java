/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import helperConverter.ContactHelper;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;



/**
 *
 * @author Walter
 */
@ManagedBean
@SessionScoped
public class SessionHandler{

    private String username;
    private String password;
    protected String addedBy;
    HiberManage hiber;
    ContactHelper helper;
    
    String verifyUserFlag = "";

    Map<String, Object> sessionMap;
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getVerifyUserFlag() {
        return verifyUserFlag;
    }

    public void setVerifyUserFlag(String verifyUserFlag) {
        this.verifyUserFlag = verifyUserFlag;
    }

    public Map<String, Object> getSessionMap() {
        return sessionMap;
    }

    public void setSessionMap(Map<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }
    
   
   
    public SessionHandler() {
        helper = new ContactHelper();
    }
    
    public String logout()
    {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        sessionMap = externalContext.getSessionMap();
        System.out.println(sessionMap.get("username"));
        sessionMap.remove("username");
        System.out.println("**************************************");
        System.out.println("LOGOUT");
        System.out.println("**************************************");
        System.out.println(sessionMap.get("username"));
        
        if(sessionMap.get("username")==null)
        {
            return "logout";
        }
        else
            return "fail";
    }
    
    
    public String login() {
        
        System.out.println("i am here login session");
        System.out.println("username "+username);
        System.out.println("password "+password);
        verifyUserFlag =  helper.verifyUser(username,password);

        if(verifyUserFlag.equals("admin") || verifyUserFlag.equals("user")) {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            sessionMap = externalContext.getSessionMap();
            sessionMap.put("username", username);
           
            
            addedBy = (String) sessionMap.get("username");
            System.out.println("Session :" + addedBy);
            
            
            if(verifyUserFlag.equals("admin"))
            {     System.out.println("Inside Admin");
                return "admin";
            }  
            else
                return "user";
            }
        
        
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL,"Login Failed.","Please retype username and password.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        return "fail";
    }
    

    
    
}
