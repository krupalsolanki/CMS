
package bean;

public class EkHaiSessionBean implements javax.ejb.SessionBean {

    private String username;
 
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

	public void ejbCreate() {}
	public void ejbActivate() {}
   	public void ejbPassivate() {}
   	public void setSessionContext(javax.ejb.SessionContext ctx) {}
   	public void unsetSessionContext() {}
   	public void ejbRemove() {}

}