package nielson.c195projectmkn.Models;

/**

 The Contact class represents a person's contact information.
 It contains a contact ID, name, and email.
 */
public class Contact {
    private int contactID;
    private String name;
    private String email;

    /**

     Constructs a new Contact object with the given contact ID, name, and email.
     @param contactID the unique identifier for this contact
     @param name the name of the contact
     @param email the email address of the contact
     */
    public Contact(int contactID, String name, String email) {
        this.contactID = contactID;
        this.name = name;
        this.email = email;
    }
    /**

     Returns the name of the contact.
     @return the name of the contact
     */
    public String getName() {
        return name;
    }
    /**

     Sets the name of the contact.
     @param name the new name for the contact
     */
    public void setName(String name) {
        this.name = name;
    }
    /**

     Returns the email address of the contact.
     @return the email address of the contact
     */
    public String getEmail() {
        return email;
    }
    /**

     Sets the email address of the contact.
     @param email the new email address for the contact
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**

     Returns the unique identifier for this contact.
     @return the unique identifier for this contact
     */
    public int getContactID() {
        return contactID;
    }
    /**

     Sets the unique identifier for this contact.
     @param contactID the new unique identifier for the contact
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }
    /**

     Returns the name of the contact as a string representation of the object.
     @return the name of the contact
     */
    @Override
    public String toString(){
        return name;
    }
}