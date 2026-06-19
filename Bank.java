import java.util.*;
import java.io.*;

public class Bank {
    Map<Integer, User> users = new HashMap<>();

    public Bank() {
        loadUsers();
    }

    public User registerUser(String name, String address, String phone, String password, double deposit) {
        Account acc = new Account(deposit);
        User user = new User(name, address, phone, password, acc);
        users.put(acc.accountNumber, user);
        saveUser(user);

        System.out.println("Registration Successful!");
        System.out.println("Account Number: " + acc.accountNumber);
        return user;
    }

    public User login(int accNo, String password) {
        User user = users.get(accNo);
        if (user != null && user.password.equals(password)) return user;
        return null;
    }

    public void transfer(int fromAcc, int toAcc, double amount) {
        User sender = users.get(fromAcc);
        User receiver = users.get(toAcc);

        if (sender == null || receiver == null) {
            System.out.println("Invalid Account!");
            return;
        }

        if (sender.account.withdraw(amount)) {
            receiver.account.deposit(amount);
            System.out.println("Transfer Successful!");
        } else {
            System.out.println("Insufficient Balance!");
        }
    }

    private void saveUser(User user) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("users.txt", true))) {
            bw.write(user.account.accountNumber + "," +
                     user.name + "," +
                     user.address + "," +
                     user.phone + "," +
                     user.password + "," +
                     user.account.balance);
            bw.newLine();
        } catch (Exception e) {
            System.out.println("Error saving user!");
        }
    }

    private void loadUsers() {
        try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                int accNo = Integer.parseInt(data[0]);
                String name = data[1];
                String address = data[2];
                String phone = data[3];
                String password = data[4];
                double balance = Double.parseDouble(data[5]);

                Account acc = new Account(balance);
                acc.accountNumber = accNo;

                User user = new User(name, address, phone, password, acc);
                users.put(accNo, user);
            }
        } catch (Exception e) {
            System.out.println("No previous data found.");
        }
    }
}
