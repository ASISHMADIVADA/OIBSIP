import java.util.ArrayList;

public class Bank {

    private Account account;
    private ArrayList<Transaction> history;

    public Bank() {
        account = new Account("ASHU123", "1234", 50000);
        history = new ArrayList<>();
    }

    public Account getAccount() {
        return account;
    }

    public ArrayList<Transaction> getHistory() {
        return history;
    }

    public void addTransaction(String type, double amount) {
        history.add(new Transaction(type, amount));
    }
}