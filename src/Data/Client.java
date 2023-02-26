package Data;

public class Client {
    private String clientName;
    private String clientPhoneNumber;
    private String clientMail;
    private double clientBudget;

    /*setter*/
    public void setClientInfo(String clientName, String clientPhoneNumber, String clientMail)
    {
        this.clientName = clientName;
        this.clientPhoneNumber = clientPhoneNumber;
        this.clientMail = clientMail;
    }

    public void setClientBudget(double clientBudget) { this.clientBudget = clientBudget; }

    /*getter*/
    public String getClientName() { return clientName; }

    public String getClientPhoneNumber() { return clientPhoneNumber; }

    public String getClientMail() { return clientMail; }

    public Double getClientBudget() { return clientBudget; }

    /**/

}
