package helperConverter;

import bean.SessionHandler;
import entities.Contactlist;
import entities.Contactrelation;
import entities.Contacts;
import entities.Employee;
import entities.Interestbridge;
import entities.Interests;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ContactHelper {

    Map<String, Object> sessionMap;
    String email = null;
    Session session = null;
    public static List<Contacts> cont;
    private int sessionEmpId;

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

    public List getMyContacts(String empid) {
        List<Contacts> contactList = null;

        int empIdInt = Integer.parseInt(empid);
        try {

            Query q3 = session.createQuery("from Contacts c where c.contactId in (Select cl.contacts.contactId from Contactlist cl where cl.employee.empId=" + empIdInt + ")");
            //Query q3 = session.createQuery("from Contacts c where c.contactId in (Select cl from Contactlist cl where cl.Employee.empId=1)");
            contactList = (List<Contacts>) q3.list();
            System.out.println("contactList" + contactList.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return contactList;
    }
    boolean flag = false;

    public String addContact(String firstName, String lastName, String email, String mobNo, String comName, String comLoc, String designation, String url, String notes, List<String> selectedInterests, String nickName, int categoryId) {

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

        //Category catg = new Category();
        //catg.setCategoryId(Integer.parseInt(category));
        //contact.setCategory(catg);   // category id from the category table

        List<String> InterestsInTable = getDistinctInterests();
        List<String> temp = null;
        session.beginTransaction();
        if (selectedInterests != null) {
            temp = new ArrayList<String>(selectedInterests.size());
            temp.addAll(selectedInterests);
            temp.removeAll(InterestsInTable);

            for (int i = 0; i < temp.size(); i++) {
                Interests ints = new Interests();
                ints.setInterestName(temp.get(i).toLowerCase());
                session.save(ints);
            }
        }
        System.out.println("Phone No : " + mobNo);
        boolean check = false;
        System.out.println("check1.." + firstName);
        if (categoryId == 1) {
            if (firstName != null && lastName != null && email != null) {
                session.save(contact);
                if (selectedInterests != null) {
                    addInterestBridge(selectedInterests);
                }


                addContactList(email, nickName, categoryId);
                System.out.println("save in the database..");
                check = true;
            }
        } else {
            if (firstName != null && lastName != null && email != null && comName != null && comLoc != null && designation != null) {
                session.save(contact);
                if (selectedInterests != null) {
                    addInterestBridge(selectedInterests);
                }


                addContactList(email, nickName, categoryId);
                System.out.println("save in the database..");
                check = true;
            }
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

    public String addContactList(String email, String nickName, int categoryId) {

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
            System.out.println("Contact Id is " + c.getContactId());
            addContactRelation(conId, nickName, categoryId);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "success";
    }

    public String addContactRelation(int conId, String nickName, int categoryId) {
        try {

            String hql_query = "select c from Contactlist c where c.contacts.contactId=" + conId;
            Query query = (Query) session.createQuery(hql_query);
            //prepare statement
            List<Contactlist> conList = (List<Contactlist>) query.list();
            int conListId = 0;
            for (Contactlist c : conList) {
                conListId = c.getContactListId();
            }
            Contactlist cl = new Contactlist();
            cl.setContactListId(conListId);

            Contactrelation cr = new Contactrelation();
            cr.setNickName(nickName);
            cr.setCategoryId(categoryId);
            cr.setContactlist(cl);
            session.save(cr);
            session.getTransaction().commit();
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
        session.beginTransaction();
        Query query = session.createSQLQuery("update Contacts set firstName = :firstName , lastName = :lastName , companyName = :companyName , companyLoc = :companyLoc , phoneNo = :phoneNo , designation = :designation where email = :email ");

        System.out.println("Yeh hai first name " + firstName + " | " + lastName + " | " + comName + " | " + comLoc + " | " + phoneNo + " | " + email);
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        query.setParameter("companyName", comName);
        query.setParameter("companyLoc", comLoc);
        query.setParameter("phoneNo", phoneNo);
        query.setParameter("designation", designation);
        query.setParameter("email", email);

        int result = query.executeUpdate();
        System.out.println("update executed");
//        closeSession();
        session.getTransaction().commit();;
        return true;

    }

    public boolean deleteSelectedContact(String email) {

        session.beginTransaction();
//        openSession();
        System.out.println("Email in hiber :" + email);
        Query query = session.createSQLQuery("delete from Contacts where email = :email ");
        query.setParameter("email", email);


        int result = query.executeUpdate();
        session.getTransaction().commit();

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

    public boolean doesEmployeeEmailExist(String email) {

        Query q = session.createQuery("select e from Employee e where e.empEmailId='" + email + "'");

        if (q.list().isEmpty()) {
            System.out.println("list is empty");
            return false;
        } else {
            System.out.println("list is not empty");
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

    public List verifyUser(String username, String password) {
        List<String> sessionList = new ArrayList<String>();

        System.out.println("i am here login helper");
        String emailForLogin = username + "@compassitesinc.com";
        String md5Password = null;

        try {
            //Create MessageDigest object for MD5
            MessageDigest digest = MessageDigest.getInstance("MD5");
            //Update input string in message digest
            digest.update(password.getBytes(), 0, password.length());
            //Converts message digest value in base 16 (hex)
            md5Password = new BigInteger(1, digest.digest()).toString(16);

            String hql_query = "select e from Employee e where e.empEmailId = '" + emailForLogin + "'";
            Query query = (Query) session.createQuery(hql_query);
            //prepare statement
            List<Employee> empList = (List<Employee>) query.list();
            String checkEmail = "";
            String checkPassword = "";
            int checkType = 0;


//            System.out.println("email " + checkEmail);
//            System.out.println("password " + checkPassword);
//            System.out.println("email " + username);
//            System.out.println("password " + password);
            if (empList.size() > 0) {
                checkPassword = empList.get(0).getPassword();
                if (checkPassword.equals(md5Password)) {
                    checkType = empList.get(0).getType();
                    String empIdForSession = empList.get(0).getEmpId().toString();
                    sessionList.add(empIdForSession);
                    if (checkType == 1) {
                        // Go to admin Page
                        sessionList.add("admin");
                        return sessionList;
                    } else {
                        sessionList.add("user");
                        return sessionList;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        sessionList.add("0");
        sessionList.add("false");
        return sessionList;

    }

    public String addNewUser(String email, String password, String firstName, String lastName) {

        if (!doesEmployeeEmailExist(email)) {
            String md5 = null;
            try {
                //Create MessageDigest object for MD5
                MessageDigest digest = MessageDigest.getInstance("MD5");
                //Update input string in message digest
                digest.update(password.getBytes(), 0, password.length());
                //Converts message digest value in base 16 (hex)
                md5 = new BigInteger(1, digest.digest()).toString(16);

            } catch (NoSuchAlgorithmException e) {

                e.printStackTrace();
            }
            Employee em = new Employee();
            em.setEmpEmailId(email);
            em.setPassword(md5);
            em.setFirstName(firstName);
            em.setLastName(lastName);
            em.setType(2);


            session.beginTransaction();
            session.save(em);
            session.getTransaction().commit();
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            sessionMap = externalContext.getSessionMap();
            sessionMap.put("username", email);
            SessionHandler sh = new SessionHandler();
            email = email.replaceAll("@compassitesinc.com", "");
            System.out.println("email after replacement is " + email);
            sh.setAddedBy((String) sessionMap.get("email"));
            System.out.println("Session :" + email);

            return "user";
        } else {

            return "fail";
        }
    }

    public String getNameForEmail(String emailToSend) {
        System.out.println(" getNameForEmail --- check1 " + emailToSend);
        String hql_query = "select c from Contacts c where c.email='" + emailToSend + "'";
        Query query = (Query) session.createQuery(hql_query);
        List<Contacts> con = (List<Contacts>) query.list();
        int contactId = con.get(0).getContactId();
        System.out.println(" getNameForEmail --- check2 " + contactId);
        String hql_query2 = "select cl from Contactlist cl where cl.contacts.contactId='" + contactId + "'";
        Query query2 = (Query) session.createQuery(hql_query2);
        List<Contactlist> conList = (List<Contactlist>) query2.list();
        System.out.println("check 3 ");
        String firstName = null;
        String lastName = null;
        String name = null;
        if (!conList.isEmpty()) {
            System.out.println("inside if conlist ");
            firstName = conList.get(0).getEmployee().getFirstName();
            lastName = conList.get(0).getEmployee().getLastName();
            name = firstName + " " + lastName;
            System.out.println("name " + name);
        } else {
            System.out.println("inside else conlist ");
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            sessionMap = externalContext.getSessionMap();
            int id = Integer.parseInt(sessionMap.get("empid").toString());
            String hql_query1 ;
            hql_query1 = "select e from Employee e where e.empId=" + id;
            Query query1 = (Query) session.createQuery(hql_query1);
            List<Employee> cont = (List<Employee>) query1.list();
            String fName = cont.get(0).getFirstName();
            String lName = cont.get(0).getLastName();
            name = fName + " " + lName;
            System.out.println("name " + name);
        }
        return name;
    }

    public int getEmpID(String username) {
//        Query q1 = session.createQuery("Select e from Employee e where e.empEmailId='" +username +"@compassitesinc.com'");
//        System.out.println(q1);
//        List<Employee> empIdList = (List<Employee>) q1.list();
//        for(Employee e : empIdList)
//        {
//                sessionEmpId = e.getEmpId();
//                System.out.println("****" +sessionEmpId);
//        } 
        return sessionEmpId;
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
            //sssession.getTransaction().commit();
            session.flush();
            session.close();
        } finally {
            super.finalize();
        }
    }
}
