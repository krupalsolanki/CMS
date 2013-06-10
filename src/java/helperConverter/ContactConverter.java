/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helperConverter;


import entities.Contacts;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Walter
 */
@FacesConverter("contact")
public class ContactConverter implements Converter {
    
    // @Inject
    //EntityReader entityReader = new EntityReader();
    Session session = null;

    public ContactConverter() {
        SessionFactory sessionFactory = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
    }
    
    
    
    @Override 
    public Contacts getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {  
        
        
        if (submittedValue!=null) {  
            System.out.println("I am in con");
             try { 
                 org.hibernate.Transaction tx = session.beginTransaction();
                 String str = submittedValue;
                 int contact_id = Integer.parseInt(str);
                 Contacts contacts;
                 Query query = session.createQuery("select c from Contacts c where c.contactId=:contactId");
                 query.setParameter("contactId", contact_id);
                 System.out.println("I am in converter");
                 contacts = (Contacts) query.uniqueResult();
                 return contacts;
             } 
             catch(Exception exception) {      
                 exception.printStackTrace();
             }  
         }  
  
        return null;  
    }  
  
    @Override    
    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {  
        
        Contacts c = (Contacts) value;
        return String.valueOf(c.getContactId());  
         
    }  

}
