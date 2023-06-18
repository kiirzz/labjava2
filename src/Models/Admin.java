package Models;

public class Admin {
    protected String ID;
    protected String Name;
    protected String PhoneNumber;
    protected String Mail;
    protected String Password;

    /*setter*/
    public Admin(String id, String Name, String PhoneNumber, String Mail, String Password) {
        this.ID = id;
        this.Name = Name;
        this.PhoneNumber = PhoneNumber;
        this.Mail = Mail;
        this.Password = Password;
    }

    /*getter*/
    public String getID() { return ID; }

    public String getName() { return Name; }

    public String getPhoneNumber() { return PhoneNumber; }

    public String getMail() { return Mail; }

    public String getPassword() { return Password; }

    /**/

}
