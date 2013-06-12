package bean;

import helperConverter.ContactHelper;
import entities.Contacts;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.UnselectEvent;

/**
 *
 * @author Walter
 */
@ManagedBean
@RequestScoped
public class HiberManage {

    SessionHandler sessionhandler;
    private String username;
    private String addedBy;
    
    private String firstName;
    private String lastName;
    private String email;
    private String mobNo;
    private String comName;
    private String comLoc;
    private String designation;
    private String url;
    private String notes;
    
    protected String searchValue;
    protected String searchName;
    protected String searchLocation;
    protected String searchInterest;
     
    
    List<Contacts> contacts;
    public List<Contacts> conts;
    
    ContactHelper helper;
    private int recordCount = 20;
    private Contacts current;
    private int selectedItemIndex;
    
    private List<String> distinctCompanies;
    private List<String> distinctLocations;
    private List<String> distinctDesignations;
    private List<String> distinctInterests;
    
    
    protected List<Contacts> selectedNames;
    private List<String> selectedCompanies;
    private List<String> selectedLocations;
    private List<String> selectedDesignations;
    private List<String> selectedInterests;
    private List<String> selectedCities;
    
    public boolean interestflag = false;
    public boolean isContactAddedFlag = false;
    public int doesEmailExistFlag;

    public static String contactsToSend;
    
    private List<String> images;
    
    // The following are the getter and setter methods for the properties
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobNo() {
        return mobNo;
    }

    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getComLoc() {
        return comLoc;
    }

    public void setComLoc(String comLoc) {
        this.comLoc = comLoc;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public String getSearchInterest() {
        return searchInterest;
    }

    public void setSearchInterest(String searchInterest) {
        this.searchInterest = searchInterest;
    }

    public String getSearchLocation() {
        return searchLocation;
    }

    public void setSearchLocation(String searchLocation) {
        this.searchLocation = searchLocation;
    }

    public List<String> getDistinctCompanies() {
        return distinctCompanies;
    }

    public void setDistinctCompanies(List<String> distinctCompanies) {
        this.distinctCompanies = distinctCompanies;
    }

    public List<String> getDistinctLocations() {
        return distinctLocations;
    }

    public void setDistinctLocations(List<String> distinctLocations) {
        this.distinctLocations = distinctLocations;
    }

    public List<String> getDistinctDesignations() {
        return distinctDesignations;
    }

    public void setDistinctDesignations(List<String> distinctDesignations) {
        this.distinctDesignations = distinctDesignations;
    }

    public List<String> getDistinctInterests() {
        return distinctInterests;
    }

    public void setDistinctInterests(List<String> distinctInterests) {
        this.distinctInterests = distinctInterests;
    }
    
    public List<Contacts> getConts() {
        return conts;
    }

    public void setConts(List<Contacts> conts) {
        this.conts = conts;
    }

        public List<Contacts> getSelectedNames() {
        return selectedNames;
    }

    public void setSelectedNames(List<Contacts> selectedNames) {
        this.selectedNames = selectedNames;
    }

    public List<String> getSelectedCompanies() {
        return selectedCompanies;
    }

    public void setSelectedCompanies(List<String> selectedCompanies) {
        this.selectedCompanies = selectedCompanies;
    }

    public List<String> getSelectedLocations() {
        return selectedLocations;
    }

    public void setSelectedLocations(List<String> selectedLocations) {
        this.selectedLocations = selectedLocations;
    }
    
    public List<String> getSelectedDesignations() {
        return selectedDesignations;
    }

    public void setSelectedDesignations(List<String> selectedDesignations) {
        this.selectedDesignations = selectedDesignations;
    }

    public List<String> getSelectedInterests() {
        return selectedInterests;
    }

    public void setSelectedInterests(List<String> selectedInterests) {
        this.selectedInterests = selectedInterests;
    }

    public List<String> getSelectedCities() {
        return selectedCities;
    }

    public void setSelectedCities(List<String> selectedCities) {
        this.selectedCities = selectedCities;
    }
    
    public boolean isInterestflag() {
        return interestflag;
    }

    public void setInterestflag(boolean interestflag) {
        this.interestflag = interestflag;
    }
    
    public static String getContactsToSend() {
        return contactsToSend;
    }

    public void setContactsToSend(String contactsToSend) {
        this.contactsToSend = contactsToSend;
    }

    public String getAddedBy() {
        addedBy = sessionhandler.addedBy;
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }
    
    public int getdoesEmailExistFlag() {
        return doesEmailExistFlag;
    }

    public void setdoesEmailExistFlag(int doesEmailExistFlag) {
        this.doesEmailExistFlag = doesEmailExistFlag;
    }

    public boolean getIsContactAddedFlag() {
        return isContactAddedFlag;
    }

    public void setIsContactAddedFlag(boolean isContactAddedFlag) {
        this.isContactAddedFlag = isContactAddedFlag;
    }
        
    public HiberManage() {
        helper = new ContactHelper();
        conts = helper.getContacts();
        distinctCompanies = helper.getDistinctCompanies();
        distinctDesignations = helper.getDistinctDesignations();
        distinctLocations = helper.getDistinctLocations();
        distinctInterests = helper.getDistinctInterests();
    }

    public Contacts getSelected() {
        if (current == null) {
            current = new Contacts();
            selectedItemIndex = -1;
        }
        return current;
    }

    public List<Contacts> getContacts() {
        System.out.println("I am in hibermanage");

        if (selectedLocations != null || selectedNames != null || selectedCompanies != null || selectedDesignations != null || selectedInterests != null) {
            contacts = helper.getUpdatedContacts(selectedNames, selectedCompanies, selectedLocations, selectedDesignations, selectedInterests);
            System.out.println(contacts);
        } else if (contacts == null) {
            contacts = helper.getContacts();
        }

        this.setFieldsToNull();
        return contacts;
    }

    public void setFieldsToNull() {
        selectedNames = null;
        selectedCompanies = null;
        selectedDesignations = null;
        selectedLocations = null;
    }

    public List<Contacts> completeNames(String query) {
        List<Contacts> suggestions = new ArrayList<Contacts>();

        for (Contacts p : conts) {
            if (p.getFirstName().toLowerCase().startsWith(query.toLowerCase()) || p.getLastName().toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(p);
            }
        }
        return suggestions;
    }

    public List<String> completeCompany(String query) {
        List<String> suggestions = new ArrayList<String>();

        for (String p : distinctCompanies) {
            if (p.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(p);
                System.out.println(p);
            }
        }
        return suggestions;
    }

    public List<String> completeLocation(String query) {
        List<String> suggestions = new ArrayList<String>();

        for (String p : distinctLocations) {
            if (p.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(p);
                System.out.println(p);
            }
        }
        return suggestions;
    }

    public List<String> completeDesignation(String query) {
        List<String> suggestions = new ArrayList<String>();

        for (String p : distinctDesignations) {
            if (p.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(p);
                System.out.println(p);
            }
        }
        return suggestions;
    }
    
    public List<String> completeCities(String query) {
        List<String> suggestions = new ArrayList<String>();

        for (String p : distinctLocations) {
            if (p.toLowerCase().contains(query.toLowerCase())) {
                suggestions.add(p.toLowerCase());
            }
        }
        if (suggestions.isEmpty()) {
            interestflag = true;
            suggestions.add(query.toLowerCase());
        }
        return suggestions;
    }

    public List<String> completeInterestsForAdd(String query) {
        List<String> suggestions = new ArrayList<String>();

        for (String p : distinctInterests) {
            if (p.toLowerCase().contains(query.toLowerCase())) {
                suggestions.add(p.toLowerCase());
            }
        }
        if (suggestions.isEmpty()) {
            interestflag = true;
            suggestions.add(query.toLowerCase());
        }
        return suggestions;
    }
    
    public List<String> completeInterestsForSearch(String query) {
        List<String> suggestions = new ArrayList<String>();

        for (String p : distinctInterests) {
            if (p.toLowerCase().contains(query.toLowerCase())) {
                suggestions.add(p.toLowerCase());
            }
        }
        return suggestions;
    }

    //The following method is used for sending the emails to the mail page
    
    public String getEmailIds() {
        contactsToSend = "";
        for (Contacts c : contacts) {
            if (contacts.indexOf(c) == contacts.size() - 1) {
                contactsToSend = contactsToSend + c.getEmail();
            } else {
                contactsToSend = contactsToSend + c.getEmail() + ",";
            }
        }
        mailBean.setEmails(contactsToSend);
        if (contacts != null) {
            return "Success";
        } else {
            return "fail";
        }
    }
    
    public String addContact() {
        String temp = helper.addContact(firstName, lastName, email, mobNo, comName, comLoc, designation, url, addedBy, notes, selectedInterests);

        if (temp == "success") {
            isContactAddedFlag = true;
        }

        firstName = null;
        lastName = null;
        email = null;
        mobNo = null;
        comName = null;
        comLoc = null;
        designation = null;
        url = null;
        notes = null;
        selectedInterests = null;

        return temp;
    }

//    public boolean isContactAdded() {
//        return flag;
//    }

    public void handleUnselect(UnselectEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unselected:" + event.getObject().toString(), null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    // code for email validation against db
    public void validateEmail(String email) {
        System.out.println(email);
        if (helper.doesEmailExist(email)) {
            doesEmailExistFlag = 1;
            FacesContext.getCurrentInstance().addMessage("myform:email", new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Email Already Exists: Please try again."));
        } else {
            doesEmailExistFlag = 0;
        }

    }

    public String getInterestsForUser(String email) {

        return helper.getInterestsForUser(email);

    }

    public void showAdddedDetails() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("The contact has been Added."));
    }

    
    //code for images in login
    
    public List<String> imageSwitch() {
        images = new ArrayList<String>();
        
        images.add("resources/images/handshake1.png");
        images.add("resources/images/logo.png");
        return images;
    }
    // The following methods are used for display of data and navigation of pages
    
    void recreateModel() {
        contacts = null;
    }

    public String searchContact() {
        return "";
    }

    public String prepareView() {

        return "browse";
    }
}
