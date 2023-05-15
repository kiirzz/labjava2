package Models;

public class Client {
    private int clientID;
    private String clientName;
    private String clientPhoneNumber;
    private String clientMail;
    private double clientBudget;
    private String clientPower;

    /*setter*/
    public void setClientInfo(String clientName, String clientPhoneNumber, String clientMail, String power)
    {
        this.clientName = clientName;
        this.clientPhoneNumber = clientPhoneNumber;
        this.clientMail = clientMail;
        this.clientPower = power;
        this.clientBudget = 0;
    }

    public void setClientID(int id) {
        this.clientID = id;
    }

    public void purchaseMoney(double budget) { this.clientBudget -= budget; }

    public void rechargeMoney(double budget) { this.clientBudget += budget; }

    /*getter*/
    public int getClientID() { return clientID; }

    public String getClientName() { return clientName; }

    public String getClientPhoneNumber() { return clientPhoneNumber; }

    public String getClientMail() { return clientMail; }

    public Double getClientBudget() { return clientBudget; }

    public String getClientPower() { return clientPower; }

    /**/

}
