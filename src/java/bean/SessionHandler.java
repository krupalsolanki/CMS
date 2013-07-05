/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import helperConverter.ContactHelper;
import java.util.List;
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
public class SessionHandler {

    private String username;
    private String password;
    protected String addedBy;
    HiberManage hiber;
    ContactHelper helper;
    Map<String, Object> sessionMap;
    private int empId;

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

    public Map<String, Object> getSessionMap() {
        return sessionMap;
    }

    public void setSessionMap(Map<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }

    public SessionHandler() {
        System.out.println("i am in session nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
        helper = new ContactHelper();
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        sessionMap = externalContext.getSessionMap();
        System.out.println("sessonnnnnnnnnnnnnnn  " + sessionMap.get("type") + " " + sessionMap.get("empid"));

    }

    public String login() {

        System.out.println("i am here login session");
        System.out.println("username " + username);
        System.out.println("password " + password);
        List<String> verifyUserList = helper.verifyUser(username, password);

        int empIdForSession = Integer.parseInt(verifyUserList.get(0));
        String userType = verifyUserList.get(1);

        if (userType.equals("admin") || userType.equals("user")) {
            System.out.println("Inside");
//            empId = hiber.getSessionEmpId(username);
//            System.out.println(empId);
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            sessionMap = externalContext.getSessionMap();
            sessionMap.put("empid", empIdForSession);

            addedBy = sessionMap.get("empid").toString();
            System.out.println("Session :" + addedBy);
            System.out.println("this is user type " + userType);
            if (userType.equals("admin")) {
                System.out.println("Inside Admin");
                sessionMap.put("type", "admin");
                return "login";
            } else {
                sessionMap.put("type", "user");
                return "login";
            }
        }


        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Login Failed.", "Please retype username and password.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        return "fail";
    }

    public boolean getSessionVariables() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        sessionMap = externalContext.getSessionMap();
        if (sessionMap.get("type").equals("admin")) {
            return true;
        } else {
            return false;
        }
    }

    public String logout() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        sessionMap = externalContext.getSessionMap();
        System.out.println(sessionMap.get("username"));
        sessionMap.remove("username");
        System.out.println("**************************************");
        System.out.println("LOGOUT");
        System.out.println("**************************************");
        System.out.println(sessionMap.get("username"));
        return "logout";
    }
}
