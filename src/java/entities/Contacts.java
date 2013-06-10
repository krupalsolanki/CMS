package entities;
// Generated Jun 5, 2013 5:07:43 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Contacts generated by hbm2java
 */
public class Contacts  implements java.io.Serializable {


     private Integer contactId;
     private String firstName;
     private String lastName;
     private String email;
     private String phoneNo;
     private String companyName;
     private String companyLoc;
     private String designation;
     private String linkedInUrl;
     private String notes;
     private String addedBy;
     private Set interestbridges = new HashSet(0);

    public Contacts() {
    }

	
    public Contacts(String phoneNo, String linkedInUrl, String notes) {
        this.phoneNo = phoneNo;
        this.linkedInUrl = linkedInUrl;
        this.notes = notes;
    }
    public Contacts(String firstName, String lastName, String email, String phoneNo, String companyName, String companyLoc, String designation, String linkedInUrl, String notes, String addedBy, Set interestbridges) {
       this.firstName = firstName;
       this.lastName = lastName;
       this.email = email;
       this.phoneNo = phoneNo;
       this.companyName = companyName;
       this.companyLoc = companyLoc;
       this.designation = designation;
       this.linkedInUrl = linkedInUrl;
       this.notes = notes;
       this.addedBy = addedBy;
       this.interestbridges = interestbridges;
    }
   
    public Integer getContactId() {
        return this.contactId;
    }
    
    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNo() {
        return this.phoneNo;
    }
    
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    public String getCompanyName() {
        return this.companyName;
    }
    
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getCompanyLoc() {
        return this.companyLoc;
    }
    
    public void setCompanyLoc(String companyLoc) {
        this.companyLoc = companyLoc;
    }
    public String getDesignation() {
        return this.designation;
    }
    
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public String getLinkedInUrl() {
        return this.linkedInUrl;
    }
    
    public void setLinkedInUrl(String linkedInUrl) {
        this.linkedInUrl = linkedInUrl;
    }
    public String getNotes() {
        return this.notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public String getAddedBy() {
        return this.addedBy;
    }
    
    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }
    public Set getInterestbridges() {
        return this.interestbridges;
    }
    
    public void setInterestbridges(Set interestbridges) {
        this.interestbridges = interestbridges;
    }




}


