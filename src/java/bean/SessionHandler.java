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

/**
 *
 * @author Walter
 */
@ManagedBean
@SessionScoped
public class SessionHandler {

    private String username;
    protected String addedBy;
    HiberManage hiber;
    ContactHelper helper;
    
    boolean verifyUserFlag = false;
    
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
    
    public boolean getVerifyUserFlag() {
        return verifyUserFlag;
    }
    
    public void setVerifyUserFlag(boolean verifyUserFlag) {
        this.verifyUserFlag = verifyUserFlag;
    }
   
    public SessionHandler() {
        helper = new ContactHelper();
    }
    
    public String login() {
        
        System.out.println("i am here login session");
        System.out.println("username "+username);
        verifyUserFlag =  helper.verifyUser(username);
        
        if(verifyUserFlag) {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            Map<String, Object> sessionMap = externalContext.getSessionMap();
            sessionMap.put("username", username);
            addedBy = (String) sessionMap.get("username");
            System.out.println("Session :" + addedBy);
            return "success";
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL,"Login Failed.","Please retype username and password.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        return "fail";
    }
    
}
