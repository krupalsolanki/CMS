/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Contacts;
import entities.Employee;
import helperConverter.ContactHelper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.mail.MessagingException;
import mail.SendEmail;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
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
    private ContactHelper helper = new ContactHelper();
    Map<String, Object> sessionMap;

    public Map<String, Object> getSessionMap() {
        return sessionMap;
    }

    public void setSessionMap(Map<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }

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
        count = 0;
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
    List<Contacts> contactList = null;

    public String sendContent() throws MessagingException, UnsupportedEncodingException, IOException {
        openSession();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mail Sent", "Your Mail has been sent.");
        FacesContext.getCurrentInstance().addMessage(null, message);

        String htmltext = content.replaceAll("\n", "<br/>");


        SendEmail se = new SendEmail(subject);
        String s[] = to.split(",");
        String from = "default";
        int totalsent = 0;
        totalsent = s.length;
        
        for (int i = 0; i < totalsent; i++) {
            System.out.println(s[i]);
            Query q = session.createQuery("select c from Contacts c where c.email ='" + s[i] + "'");
            System.out.println("check 1...");
            contactList = (List<Contacts>) q.list();
            System.out.println("contact list size :" + contactList.size());
            String firstName = contactList.get(0).getFirstName();
            String lastName = contactList.get(0).getLastName();
            System.out.println("first " + firstName + " - " + lastName);
//            String notes = contactList.get(0).getNotes();
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            sessionMap = externalContext.getSessionMap();
            System.out.println("sessonnnnnnnnnnnnnnn  " + sessionMap.get("type") + " " + sessionMap.get("empid"));
            if (sessionMap.get("type").equals("admin")) {
                from = helper.getNameForEmail(s[i]);
            } else {
//                ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                sessionMap = externalContext.getSessionMap();
                int id = Integer.parseInt(sessionMap.get("empid").toString());
                String hql_query1;
                hql_query1 = "select e from Employee e where e.empId=" + id;
                Query query1 = (Query) session.createQuery(hql_query1);
                List<Employee> cont = (List<Employee>) query1.list();
                String fName = cont.get(0).getFirstName();
                String lName = cont.get(0).getLastName();
                from = fName + " " + lName;
                System.out.println("name " + from);
            }

            se.composeSend(s[i], htmltext, contactList, from);
        }
        if(totalsent > 0)
        {
            try {
                String toCall = "http://54.225.23.238/mailsent.php?count=" +totalsent+"&subject=" +subject;
                URL url = new URL(toCall);
                URLConnection urlConn = url.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                System.out.println("Total Sent : " +totalsent);
                in.close();
            } catch (MalformedURLException ex) {
                Logger.getLogger(mailBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        sentFlag = true;
        to=null;
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

            if (progress >= 100) {
                progress = 100;
                count = 0;
            }

        }
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }
}
