import java.util.*;

public class Main {

    private static List<Clients> userList = new ArrayList<>();
    private static String currentUser = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Clients client = new Clients(1, "genericUser", "Boris", "Deyanov",
                                        1234, "genericPass", false, 0.0);
        userList.add(client);
        
        mainLoop:
        while(true) {
            logInLoop:
            do {
                userLogInMenu();
                String logScreenInput = scanner.nextLine().trim();
                if (logScreenInput.equals("3")) {
                    System.out.println("Thank you for using National Bank!");
                    return;
                }
                switch (logScreenInput) {
                    case "1":
                        int logAttempts = 0;
                        do {
                            userLogMenu();
                            String username = scanner.nextLine();
                            System.out.println("Enter password:");
                            String password = scanner.nextLine();
                            if (checkCredentials(username, password)) {
                                currentUser = username;
                                System.out.println("Welcome!\n");
                                break;
                            } else {
                                if (containsUser(username)) {
                                    logAttempts++;
                                    System.out.println("Wrong username or password!\n");
                                    if (logAttempts >= 4) {
                                        System.out.println("Too many log in attempts!");
                                        return;
                                    }
                                } else {
                                    System.out.println("Username does not exist.\n");
                                }
                            }
                        } while (true);
                        break logInLoop;
                    case "2":
                        do {
                            userLogMenu();
                            String usernameReg = scanner.nextLine();
                            if (containsUser(usernameReg)) {
                                System.out.println("Username is already taken!\n");
                                continue;
                            }
                            System.out.println("Enter first and surname:");
                            String[] names = scanner.nextLine().split("\\s+");
                            System.out.println("Enter last four digits of account:");
                            int lastFour = Integer.parseInt(scanner.nextLine());
                            String passwordReg = "";
                            while (true) {
                                System.out.println("Enter password:");
                                String passwordOne = scanner.nextLine();
                                System.out.println("Re-enter password:");
                                String passwordTwo = scanner.nextLine();
                                if (!passwordTwo.equals(passwordOne)) {
                                    System.out.println("Passwords do not match!\n");
                                } else {
                                    passwordReg = passwordOne;
                                    break;
                                }
                            }
                            Clients clientReg = new Clients(userList.size() + 1, usernameReg
                                    , names[0], names[1], lastFour, passwordReg, false, 0.0);
                            userList.add(clientReg);
                            System.out.println("Thank you for registering!\n");
                            continue logInLoop;
                        } while (true);
                    default:
                        System.out.println("Invalid selection!");
                        break;
                }
            } while (true);

            do {
                mainMenu();
                String mainInput = scanner.nextLine().trim();
                if (mainInput.equals("6")) {
                    System.out.println("Thank you for using National Bank!");
                    return;
                }
                int userInd = userIndex(currentUser);

                switch (mainInput) {
                    case "1":
                        System.out.println("Enter deposit amount:");
                        double depositAmount = Double.parseDouble(scanner.nextLine());
                        userList.get(userInd).setBalance(depositAmount);
                        System.out.printf("$%.2f deposited successfully for user %s.%n%n", depositAmount, currentUser);
                        break;
                    case "2":
                        System.out.println("Enter amount to withdraw:");
                        double withdrawAmount = Double.parseDouble(scanner.nextLine());
                        if (userList.get(userInd).getBalance() < withdrawAmount) {
                            System.out.println("Withdraw failed - insufficient funds.\n");
                        } else {
                            double currentBalance = userList.get(userInd).getBalance();
                            userList.get(userInd).setBalance(currentBalance - withdrawAmount);
                            System.out.printf("$%.2f withdrawn successfully from user %s.%n%n", withdrawAmount, currentUser);
                        }
                        break;
                    case "3":
                        System.out.println("Statement balance for " + currentUser + " as of now:");
                        System.out.printf("$%.2f%n%n", userList.get(userInd).getBalance());
                        break;
                    case "4":
                        System.out.println("Enter username of recipient:");
                        String recipientUser = scanner.nextLine();
                        System.out.println("Enter recipient name separated by spaces:");
                        String[] recipientName = scanner.nextLine().trim().split("\\s+");
                        System.out.println("Enter transfer amount:");
                        double transferAmount = Double.parseDouble(scanner.nextLine());

                        if (containsUser(recipientUser)){
                            int recipientIndex = userIndex(recipientUser);
                            int currentUserIndex = userIndex(currentUser);
                            double recipientBalance = userList.get(recipientIndex).getBalance();
                            double currentUserBalance = userList.get(currentUserIndex).getBalance();

                            if (userList.get(recipientIndex).getFirstName().equals(recipientName[0])
                                    && userList.get(recipientIndex).getLastName().equals(recipientName[1])){
                                if (userList.get(currentUserIndex).getBalance() >= transferAmount){
                                    userList.get(currentUserIndex).setBalance(currentUserBalance - transferAmount);
                                    userList.get(recipientIndex).setBalance(recipientBalance + transferAmount);
                                    System.out.printf("%.2f transferred successfully to %s.%n%n", transferAmount, recipientUser);
                                } else {
                                    System.out.println("Transfer failed - insufficient funds!");
                                }
                            } else {
                                System.out.println("Username and names entered do not match!");
                            }
                        } else {
                            System.out.println("User does not exist in our database!");
                        }
                        break;
                    case "5":
                        System.out.printf("%s successfully logged out!%n", currentUser);
                        continue mainLoop;
                    default:
                        System.out.println("Invalid selection.\n");
                }
            } while (true);
        }
    }

    public static void mainMenu(){
        System.out.println("-----------National Bank----------");
        System.out.println("Select from the following options:");
        System.out.println(" 1. Deposit");
        System.out.println(" 2. Withdraw");
        System.out.println(" 3. View Balance");
        System.out.println(" 4. Transfer Funds");
        System.out.println(" 5. Logout");
        System.out.println(" 6. Exit");
        System.out.println("----------------------------------");
    }

    public static void userLogInMenu(){
        System.out.println("-----------National Bank----------");
        System.out.println("Select from the following options:");
        System.out.println(" 1. User Login");
        System.out.println(" 2. Register New User");
        System.out.println(" 3. Exit");
        System.out.println("----------------------------------");
    }

    public static void userLogMenu(){
        System.out.println("-----------National Bank----------");
        System.out.println("Enter username:");
    }

    public static boolean containsUser(String username){
        for (Clients client : userList){
            if (client.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

    public static int userIndex(String username){
        for (int i = 0; i < userList.size(); i++){
            if (userList.get(i).getUsername().equals(username)){
                return i;
            }
        }
        return -1;
    }

    public static boolean checkCredentials(String username, String password){
        if (containsUser(username)){
            for (Clients client : userList){
                if (client.getUsername().equals(username) && client.getPass().equals(password)){
                    return true;
                }
            }
        }
        return false;
    }
}
