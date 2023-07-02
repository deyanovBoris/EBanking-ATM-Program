public class Clients {
    int identifier;
    String username;
    String firstName;
    String lastName;
    int lastFourAccount;
    String pass;
    boolean blockedUser;
    double balance;

    public Clients(int identifier, String username, String firstName,
                   String lastName, int lastFourAccount, String pass, boolean blockedUser, double balance){
        this.identifier = identifier;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastFourAccount = lastFourAccount;
        this.pass = pass;
        this.blockedUser = blockedUser;
        this.balance = balance;
    }

    public Clients() {
    }

    //Getters
    public int getIdentifier() {
        return this.identifier;
    }
    public String getUsername() {
        return this.username;
    }
    public String getFirstName() {
        return this.firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public int getLastFourAccount() {
        return this.lastFourAccount;
    }
    public String getPass() {
        return this.pass;
    }
    public double getBalance() {
        return this.balance;
    }
    public boolean isBlockedUser() {
        return blockedUser;
    }

    //Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    public void setLastFourAccount(int lastFourAccount) {
        this.lastFourAccount = lastFourAccount;
    }
    public void setBlockedUser(boolean blockedUser) {
        this.blockedUser = blockedUser;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
}
