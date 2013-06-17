/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Contacts;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.mail.MessagingException;
import mail.SendEmail;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Disha
 */
@ManagedBean(name = "mailBean")
@RequestScoped
public class mailBean implements Serializable {

    /**
     * @return the count
     */
    public static int getCount() {
        return count;
    }

    /**
     * @param aCount the count to set
     */
    public static void setCount(int aCount) {
        count = aCount;
    }
    Session session = null;
    public String content;
    public static String to;
    public String subject;
    private List<Contacts> selectedContacts;

    public void openSession() {
        SessionFactory sessionFactory = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        System.out.println("opening session");
    }

    public void closeSession() {
        session.getTransaction().commit();
        session.flush();
        session.close();
    }
    public mailBean() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTo() {
        count=0;
        return to;
    }

    public void setTo(String to) {
        mailBean.to = to;
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

    public void setSelectedContacts(List<Contacts> selectedContacts) {
        this.selectedContacts = selectedContacts;
    }
    List<String> contactList = null;
    public String sendContent() throws MessagingException, UnsupportedEncodingException {
        openSession();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mail Sent", "Your Mail has been sent.");
        FacesContext.getCurrentInstance().addMessage(null, message);

        String htmltext = content.replaceAll("\n", "<br/>");
        
     
        SendEmail se = new SendEmail(subject);
        String s[] = to.split(",");
        

        for (int i = 0; i < s.length; i++) {
            System.out.println(s[i]);
            Query q = session.createQuery("select c.firstName from Contacts c where c.email ='"+s[i]+"'");
            contactList = (List<String>) q.list();
            System.out.println("contact list size :"+contactList.size());
            String firstName = contactList.get(0);
            
           se.composeSend(s[i], htmltext, firstName);
        }

        sentFlag = true;
        return "success";
    }

    public void onComplete() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mail Sent", "Your Mail has been sent."));
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
    /**
     * @return the progress
     */
    private Integer progress;
    private static int count;

    public Integer getProgress() {
        
        if (count == 0) {
            progress = 0;
            count++;
        } else {
            progress = count;
            
            progress = progress + (int) (Math.random() * 35);
            count = progress;
            
            if (progress >=  100) {
                progress = 100;
                count = 0;
                
            }

        }
        return progress;
    }

    /**
     * @param progress the progress to set
     */
    public void setProgress(Integer progress) {
        this.progress = progress;
    }
}
