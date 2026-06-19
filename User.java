public class User {
    String name;
    String address;
    String phone;
    String password;
    Account account;

    public User(String name, String address, String phone, String password, Account account) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.password = password;
        this.account = account;
    }
}
