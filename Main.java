import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();

        while (true) {
            System.out.println("\n1. Register\n2. Login\n3. Exit");
            int choice = sc.nextInt();

            if (choice == 1) {
                sc.nextLine();
                System.out.print("Name: ");
                String name = sc.nextLine();

                System.out.print("Address: ");
                String address = sc.nextLine();

                System.out.print("Phone: ");
                String phone = sc.nextLine();

                System.out.print("Password: ");
                String password = sc.nextLine();

                System.out.print("Initial Deposit: ");
                double deposit = sc.nextDouble();

                bank.registerUser(name, address, phone, password, deposit);
            }

            else if (choice == 2) {
                System.out.print("Account Number: ");
                int accNo = sc.nextInt();

                sc.nextLine();
                System.out.print("Password: ");
                String pass = sc.nextLine();

                User user = bank.login(accNo, pass);

                if (user == null) {
                    System.out.println("Invalid Credentials!");
                    continue;
                }

                while (true) {
                    System.out.println("\n1. Deposit\n2. Withdraw\n3. Transfer\n4. Statement\n5. Logout");
                    int opt = sc.nextInt();

                    if (opt == 1) {
                        System.out.print("Amount: ");
                        double amt = sc.nextDouble();
                        user.account.deposit(amt);
                    }

                    else if (opt == 2) {
                        System.out.print("Amount: ");
                        double amt = sc.nextDouble();
                        if (!user.account.withdraw(amt)) {
                            System.out.println("Insufficient Balance!");
                        }
                    }

                    else if (opt == 3) {
                        System.out.print("Receiver Account: ");
                        int to = sc.nextInt();

                        System.out.print("Amount: ");
                        double amt = sc.nextDouble();

                        bank.transfer(user.account.accountNumber, to, amt);
                    }

                    else if (opt == 4) {
                        for (String t : user.account.transactions) {
                            System.out.println(t);
                        }
                    }

                    else break;
                }
            }

            else break;
        }
    }
}
