package Models;

import Models.Client;

import java.util.HashMap;

public class Account {
    public HashMap<String, String> account = new HashMap<String, String>();
    private final HashMap<String, Client> accClient = new HashMap<String, Client>();
    private int accountNumber = 0;

    public void addNewAccount(Client client, String pass) {
        this.account.put(client.getClientMail(), pass);
        this.accClient.put(client.getClientMail(), client);
        client.setClientID(this.accountNumber);
        this.accountNumber += 1;
    }

    public boolean checkAccount(String acc) { return this.account.containsKey(acc); }

    public boolean comparePassword(String acc, String pass) {
        if (this.account.containsKey(acc)) {
            return this.account.get(acc).equals(pass);
        }
        return false;
    }

    public Client getClientThroughAcc(String acc) { return this.accClient.get(acc); }
}
