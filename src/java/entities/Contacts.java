package entities;
// Generated Jun 24, 2013 4:38:10 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Contacts generated by hbm2java
 */
@Entity
@Table(name="contacts"
    ,catalog="contactmanagementsystem"
)
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
     private Set<Interestbridge> interestbridges = new HashSet<Interestbridge>(0);
     private Set<Contactlist> contactlists = new HashSet<Contactlist>(0);

    public Contacts() {
    }

	
    public Contacts(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public Contacts(String firstName, String lastName, String email, String phoneNo, String companyName, String companyLoc, String designation, String linkedInUrl, Set<Interestbridge> interestbridges, Set<Contactlist> contactlists) {
       this.firstName = firstName;
       this.lastName = lastName;
       this.email = email;
       this.phoneNo = phoneNo;
       this.companyName = companyName;
       this.companyLoc = companyLoc;
       this.designation = designation;
       this.linkedInUrl = linkedInUrl;
       this.interestbridges = interestbridges;
       this.contactlists = contactlists;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="contactId", unique=true, nullable=false)
    public Integer getContactId() {
        return this.contactId;
    }
    
    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }
    
    @Column(name="firstName", nullable=false, length=30)
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    @Column(name="lastName", nullable=false, length=30)
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    @Column(name="email", length=78)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Column(name="phoneNo", length=20)
    public String getPhoneNo() {
        return this.phoneNo;
    }
    
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    
    @Column(name="companyName", length=100)
    public String getCompanyName() {
        return this.companyName;
    }
    
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    @Column(name="companyLoc", length=50)
    public String getCompanyLoc() {
        return this.companyLoc;
    }
    
    public void setCompanyLoc(String companyLoc) {
        this.companyLoc = companyLoc;
    }
    
    @Column(name="designation", length=120)
    public String getDesignation() {
        return this.designation;
    }
    
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    
    @Column(name="linkedInUrl", length=256)
    public String getLinkedInUrl() {
        return this.linkedInUrl;
    }
    
    public void setLinkedInUrl(String linkedInUrl) {
        this.linkedInUrl = linkedInUrl;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="contacts")
    public Set<Interestbridge> getInterestbridges() {
        return this.interestbridges;
    }
    
    public void setInterestbridges(Set<Interestbridge> interestbridges) {
        this.interestbridges = interestbridges;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="contacts")
    public Set<Contactlist> getContactlists() {
        return this.contactlists;
    }
    
    public void setContactlists(Set<Contactlist> contactlists) {
        this.contactlists = contactlists;
    }




}


