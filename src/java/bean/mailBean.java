/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Contacts;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.mail.MessagingException;
import mail.SendEmail;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.component.growl.Growl;

/**
 *
 * @author Disha
 */
@ManagedBean(name = "mailBean")
@RequestScoped
public class mailBean {

    public String content;
    public static String to;
    public String subject;
    private List<Contacts> selectedContacts;

    public mailBean() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<Contacts> getSelectedContacts() {
        return selectedContacts;
    }

    /**
     * Set the value of selectedContacts
     *
     * @param selectedContacts new value of selectedContacts
     */
    public void setSelectedContacts(List<Contacts> selectedContacts) {
        this.selectedContacts = selectedContacts;
    }

    public String sendContent() throws MessagingException, UnsupportedEncodingException {
        
//         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Sending Mail!", "Please Wait!"));  
        
//        FacesContext context = FacesContext.getCurrentInstance();  
//        context.addMessage(null, new FacesMessage("Successful", "Your mail has beent sent.")); 
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mail Sent", "Your Mail has been sent.");
        FacesContext.getCurrentInstance().addMessage(null, message);
FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        String htmltext = content.replaceAll("\n", "<br/>");
        SendEmail se = new SendEmail(subject);
        String s[] = to.split(",");

        for (int i = 0; i < s.length; i++) {
            System.out.println(s[i]);
          //  se.composeSend(s[i], htmltext);
        }

        sentFlag = true;
        return "success";
    }

    public static void setEmails(String searched) {
        to = searched;
    }

    public String can() {
        System.out.println("hahahhaahahahah");
        return "can";
    }
    public boolean sentFlag = false;

    public boolean isMailSent() {
        return sentFlag;
    }
}
