import java.util.Scanner;

public class ATM {

    private Bank bank;
    private Scanner sc;

    public ATM() {
        bank = new Bank();
        sc = new Scanner(System.in);
    }

    public void start() {

        int attempts = 3;

        while (attempts > 0) {

            System.out.print("Enter User ID: ");
            String id = sc.nextLine();

            System.out.print("Enter PIN: ");
            String pin = sc.nextLine();

            if (id.equals(bank.getAccount().getUserId())
                    && pin.equals(bank.getAccount().getPin())) {

                System.out.println("\nLogin Successful!\n");

                menu();
                return;
            }

            attempts--;
            System.out.println("Invalid User ID or PIN.");
            System.out.println("Attempts Left: " + attempts);
        }

        System.out.println("\nAccess Denied.");
    }

    private void menu() {

        while (true) {

            System.out.println("\n========== ATM MENU ==========");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Balance");
            System.out.println("6. Quit");

            System.out.print("Enter Choice: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {

                case 1:
                    history();
                    break;

                case 2:
                    withdraw();
                    break;

                case 3:
                    deposit();
                    break;

                case 4:
                    transfer();
                    break;

                case 5:
                    balance();
                    break;

                case 6:
                    System.out.println("Thank you for using ATM.");
                    return;

                default:
                    System.out.println("Invalid Choice.");

            }

        }

    }

    private void history() {

        if (bank.getHistory().isEmpty()) {
            System.out.println("No Transactions.");
            return;
        }

        System.out.println("\nTransaction History");

        for (Transaction t : bank.getHistory()) {
            System.out.println(t);
        }

    }

    private void withdraw() {

        System.out.print("Enter Amount: ");

        double amount = Double.parseDouble(sc.nextLine());

        if (bank.getAccount().withdraw(amount)) {

            bank.addTransaction("Withdraw", amount);

            System.out.println("Withdrawal Successful.");

        } else {

            System.out.println("Insufficient Funds.");

        }

    }

    private void deposit() {

        System.out.print("Enter Amount: ");

        double amount = Double.parseDouble(sc.nextLine());

        bank.getAccount().deposit(amount);

        bank.addTransaction("Deposit", amount);

        System.out.println("Deposit Successful.");

    }

    private void transfer() {

        System.out.print("Enter Receiver Account ID: ");

        String receiver = sc.nextLine();

        System.out.print("Enter Amount: ");

        double amount = Double.parseDouble(sc.nextLine());

        if (bank.getAccount().withdraw(amount)) {

            bank.addTransaction("Transfer to " + receiver, amount);

            System.out.println("Transfer Successful.");

        } else {

            System.out.println("Insufficient Funds.");

        }

    }

    private void balance() {

        System.out.println("Current Balance : ₹" + bank.getAccount().getBalance());

    }

}