package helperConverter;

import entities.Category;
import entities.Contactlist;
import entities.Contacts;
import entities.Employee;
import entities.Interestbridge;
import entities.Interests;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ContactHelper {

    String email = null;
    Session session = null;
    public static List<Contacts> cont;

    public ContactHelper() {
        //this.session = HibernateUtil.getSessionFactory().getCurrentSession();
        SessionFactory sessionFactory = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.beginTransaction();
        System.out.println("opening session");
    }

    public List getContacts() {
//        openSession();
        List<Contacts> contactList = null;
        try {
            Query q = session.createQuery("from Contacts as c ");
            contactList = (List<Contacts>) q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        closeSession();
        return contactList;
    }

    public List getDistinctCompanies() {
//        openSession();
        List<String> contactList = null;
        try {

            Query q = session.createQuery("select distinct c.companyName from Contacts as c ");
            contactList = (List<String>) q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        closeSession();
        return contactList;
    }

    public List getDistinctLocations() {
//        openSession();
        List<String> contactList = null;
        try {

            Query q = session.createQuery("select distinct c.companyLoc from Contacts as c ");
            contactList = (List<String>) q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        closeSession();
        return contactList;
    }

    public List getDistinctDesignations() {
//        openSession();
        List<String> contactList = null;
        try {

            Query q = session.createQuery("select distinct c.designation from Contacts as c ");
            contactList = (List<String>) q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        closeSession();
        return contactList;
    }

    public List getDistinctInterests() {
//        openSession();
        List<String> interestList = null;
        try {

            Query q = session.createQuery("select distinct i.interestName from Interests as i ");
            interestList = (List<String>) q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        closeSession();
        return interestList;
    }

    public List getUpdatedContacts(List<Contacts> names, List<String> companies, List<String> locations, List<String> designations, List<String> interests) {
//        openSession();
        List<Contacts> contactList = null;

        /*String string = conts.get(0);
         String[] name = string.split(" ");
         String firstn = name[0];
         String lastn = name[1];*/
        try {
            /*
             org.hibernate.Transaction tx = session.beginTransaction();
             Query q = session.createQuery("from Contacts as c where c.firstName=:firstName");
             System.out.println(conts.get(0).getFirstName());
             q.setParameter("firstName", conts.get(0).getFirstName());
             contactList = (List<Contacts>) q.list();
             System.out.println(conts.get(0));
             */
            String first_name_array = "";
            String company_array = "";
            String location_array = "";
            String designation_array = "";
            String interest_array = "";


            if (names != null) {
                for (int i = 0; i < names.size(); i++) {
                    if (i != names.size() - 1) {
                        first_name_array = first_name_array + "'" + names.get(i).getFirstName() + "'" + ",";
                    } else {
                        first_name_array = first_name_array + "'" + names.get(i).getFirstName() + "'";
                    }
                }
            } else {
                first_name_array = "select c.firstName from Contacts as c ";
            }

            if (companies != null) {
                for (int i = 0; i < companies.size(); i++) {
                    if (i != companies.size() - 1) {
                        company_array = company_array + "'" + companies.get(i) + "'" + ",";
                    } else {
                        company_array = company_array + "'" + companies.get(i) + "'";
                    }
                }
            } else {
                company_array = "select distinct c.companyName from Contacts as c ";
            }

            if (locations != null) {
                System.out.println("Loc:" + locations.get(0));
                for (int i = 0; i < locations.size(); i++) {
                    if (i != locations.size() - 1) {
                        location_array = location_array + "'" + locations.get(i) + "'" + ",";
                    } else {
                        location_array = location_array + "'" + locations.get(i) + "'";
                    }
                }
            } else {
                location_array = "select distinct c.companyLoc from Contacts as c";
            }

            if (designations != null) {
                for (int i = 0; i < designations.size(); i++) {
                    if (i != designations.size() - 1) {
                        designation_array = designation_array + "'" + designations.get(i) + "'" + ",";
                    } else {
                        designation_array = designation_array + "'" + designations.get(i) + "'";
                    }
                }
            } else {
                designation_array = "select distinct c.designation from Contacts as c";
            }

            if (interests != null) {
                for (int i = 0; i < interests.size(); i++) {
                    if (i != interests.size() - 1) {
                        interest_array = interest_array + "'" + interests.get(i) + "'" + ",";
                    } else {
                        interest_array = interest_array + "'" + interests.get(i) + "'";
                    }
                }
            } else {
                interest_array = "select distinct c.designation from Contacts as c";
            }

            Query q;

            if (interests != null) {
                q = session.createQuery("select distinct c from Contacts as c, Interests i , Interestbridge ib where ib.interests = i and ib.contacts = c and c.firstName in (" + first_name_array + ") and c.companyName in (" + company_array + ") and c.companyLoc in (" + location_array + ") and c.designation in (" + designation_array + ") and i.interestName in (" + interest_array + ")");
                System.out.println("interest :" + interest_array + "::::::first : " + first_name_array);
            } else {
                q = session.createQuery("from Contacts as c where c.firstName in (" + first_name_array + ") and c.companyName in (" + company_array + ") and c.companyLoc in (" + location_array + ") and c.designation in (" + designation_array + ")");
            }
            //System.out.println(name_array);
            //q.setParameter("firstName", name_array);
            contactList = (List<Contacts>) q.list();
            //System.out.println(contactList);

        } catch (Exception e) {
            e.printStackTrace();
        }
//        closeSession();
        return contactList;
    }
    boolean flag = false;

    public String addContact(String firstName, String lastName, String email, String mobNo, String comName, String comLoc, String designation, String url, String addedBy, String notes, List<String> selectedInterests) {

        this.email = email;
        System.out.println("addContact()");
//        openSession();
        System.out.println("Inside add");
        Contacts contact = new Contacts();
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setEmail(email);
        contact.setPhoneNo(mobNo);
        contact.setCompanyName(comName);
        contact.setCompanyLoc(comLoc);
        contact.setDesignation(designation);
        contact.setLinkedInUrl(url);

        Category catg = new Category();
        catg.setCategoryId(1);
        contact.setCategory(catg);   // category id from the category table and login
//        contact.setAddedBy(addedBy);
//        contact.setNotes(notes);
        List<String> InterestsInTable = getDistinctInterests();
        List<String> temp = null;
        if (selectedInterests != null) {
            temp = new ArrayList<String>(selectedInterests.size());
            temp.addAll(selectedInterests);
            temp.removeAll(InterestsInTable);

            for (int i = 0; i < temp.size(); i++) {
                Interests ints = new Interests();
                ints.setInterestName(temp.get(i).toLowerCase());
                session.beginTransaction();
                session.save(ints);
            }
        }
        System.out.println("Phone No : " + mobNo);
        boolean check = false;
        System.out.println("check1.." + firstName);
        if (firstName != null && lastName != null && email != null && comName != null && comLoc != null && designation != null) {
            session.save(contact);
            if (selectedInterests != null) {
                addInterestBridge(selectedInterests);
            }
            addContactList(email);
            System.out.println("save in the database..");
            check = true;
        }

        if (check) {
//            closeSession();
            flag = true;
            return "success";
        } else {
//            closeSession();
            return "error";
        }

    }

    public String addInterestBridge(List<String> intList) {

        System.out.print(email);
        try {
            String hql_query = "select c from Contacts c where c.email='" + email + "'";
            Query query = (Query) session.createQuery(hql_query);
            //prepare statement
            List<Contacts> conList = (List<Contacts>) query.list();
            int conId = 0;
            for (Contacts c : conList) {
                conId = c.getContactId();
            }
            Contacts c = new Contacts();
            c.setContactId(conId);
            System.out.println("Contact Id is " + c.getContactId());
            List<String> temp = intList;
            //System.out.println("temp size " + temp.size());
            List<Interests> intId;
            for (int i = 0; i < temp.size(); i++) {
                Interests ints = new Interests();
                hql_query = "select i from Interests i where i.interestName='" + temp.get(i) + "'";
                query = (Query) session.createQuery(hql_query);
                intId = (List<Interests>) query.list();
                System.out.println("interest Id is " + intId);
                Interestbridge ib = new Interestbridge();
                ib.setContacts(c);
                ib.setInterests(intId.get(0));
                System.out.println("done..");
                session.save(ib);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    public String addContactList(String email) {

        System.out.print(email);
        try {
            String hql_query = "select c from Contacts c where c.email='" + email + "'";
            Query query = (Query) session.createQuery(hql_query);
            //prepare statement
            List<Contacts> conList = (List<Contacts>) query.list();
            int conId = 0;
            for (Contacts c : conList) {
                conId = c.getContactId();
            }
            Employee emp = new Employee();
            emp.setEmpId(1);
            Contacts c = new Contacts();
            c.setContactId(conId);
            Contactlist cl = new Contactlist();
            cl.setEmployee(emp);
            cl.setContacts(c);

            session.save(cl);
            session.getTransaction().commit();
            System.out.println("Contact Id is " + c.getContactId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "success";
    }

    public List<Contacts> getContactsDetails() {
        List<Contacts> contactList = new ArrayList<Contacts>();
        try {
//            openSession();
            String hql_query = "from Contacts";
            Query query = (Query) session.createQuery(hql_query);
            //prepare statement
            contactList = (List<Contacts>) query.list();
            System.out.println(contactList);
            System.out.println("reading value");
            for (Contacts cl : contactList) {
                System.out.println("check1..");
                System.out.println(cl.getFirstName());
            }
            System.out.println("Done..");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
//            closeSession();
        }
        return contactList;
    }

    public boolean editSelectedContact(String email, String firstName, String lastName, String comName, String comLoc, String phoneNo, String designation) {

//        openSession();
        Query query = session.createQuery("update Contacts set firstName = :firstName , lastName = :lastName , companyName = :companyName , companyLoc = :companyLoc , phoneNo = :phoneNo , designation = :designation where email = :email ");

        System.out.println("Yeh hai first name " + firstName + " | " + lastName + " | " + comName + " | " + comLoc + " | " + phoneNo + " | " + email);
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        query.setParameter("companyName", comName);
        query.setParameter("companyLoc", comLoc);
        query.setParameter("phoneNo", phoneNo);
        query.setParameter("designation", designation);
        query.setParameter("email", email);

        int result = query.executeUpdate();

//        closeSession();
        return true;

    }

    public boolean deleteSelectedContact(String email) {

//        openSession();
        System.out.println("Email in hiber :" + email);
        Query query = session.createQuery("delete from Contacts c where c.email = :email ");
        query.setParameter("email", email);

        int result = query.executeUpdate();

//        closeSession();
        return true;

    }

    public boolean doesEmailExist(String email) {

        Query q = session.createQuery("select c from Contacts c where c.email='" + email + "'");

        if (q.list().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public String getInterestsForUser(String email) {
//        openSession();
        Query q = session.createQuery("select i.interestName from Interests i , Contacts c, Interestbridge ib where c.email='" + email + "' and ib.contacts = c and ib.interests = i");
        List<String> interestList = q.list();
        String intlist = "";
        for (int i = 0; i < interestList.size(); i++) {
            intlist = intlist + interestList.get(i) + " | ";
        }
//        closeSession();
        return intlist;
    }

    public boolean verifyUser(String username, String password) {
        List<Employee> empl = new ArrayList<Employee>();
        System.out.println("i am here login helper");
        String email = username + "@compassitesinc.com";
        
        try {
            String hql_query = "select e from Employee e where e.empEmailId = '" + email + "'";
            Query query = (Query) session.createQuery(hql_query);
            //prepare statement
            List<Employee> empList = (List<Employee>) query.list();
            String checkEmail = "";
            String checkPassword = "";
                checkEmail = empList.get(0).getEmpEmailId();
                checkPassword = empList.get(0).getPassword();
            
            System.out.println("email "+ checkEmail);
            System.out.println("password "+ checkPassword);
            System.out.println("email "+ username);
            System.out.println("password "+ password);
            if (checkEmail.equals(email)) {
                if (checkPassword.equals(password)) {
                    System.out.println("sadsd");
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
        
    }

//    public void openSession() {
//        SessionFactory sessionFactory = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
//        session = sessionFactory.openSession();
//        session.beginTransaction();
//        System.out.println("opening session");
//    }
//    public void closeSession() {
//        session.getTransaction().commit();
//        session.flush();
//        session.close();
//    }
    @Override
    protected void finalize() throws Throwable {
        try {
            System.out.println("close session..");
            session.getTransaction().commit();
            session.flush();
            session.close();
        } finally {
            super.finalize();
        }
    }
}
