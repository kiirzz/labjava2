package Models;

public class Client extends Admin {
    private double clientBudget;

    /*setter*/
    public Client(String id, String Name, String PhoneNumber, String Mail, String Password, double budget)
    {
        super(id, Name, PhoneNumber, Mail, Password);
        this.clientBudget = budget;
    }

    public void purchaseMoney(double budget) { this.clientBudget -= budget; }

    public void rechargeMoney(double budget) { this.clientBudget += budget; }

    public Double getClientBudget() { return clientBudget; }

}
