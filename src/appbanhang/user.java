package appbanhang;

public class user {
	public class Account {
	    // Thuộc tính private (đóng gói dữ liệu)
	    private int id;
	    private String name;
	    private double balance;
	    private String cccd;

	    // Constructor mặc định
	    public Account() {
	        id = 0;
	        name = "";
	        balance = 0;
	        cccd = "";
	    }

	    // Constructor có tham số
	    public Account(int id, String name, double balance, String cccd) {
	        this.id = id;
	        this.name = name;
	        this.balance = balance;
	        this.cccd = cccd;
	    }

	    // Getter và Setter
	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public double getBalance() {
	        return balance;
	    }

	    public void setBalance(double balance) {
	        this.balance = balance;
	    }

	    public String getCccd() {
	        return cccd;
	    }

	    public void setCccd(String cccd) {
	        this.cccd = cccd;
	    }

	    // Gửi tiền
	    public void deposit(double amount) {
	        if (amount > 0) {
	            balance += amount;
	            System.out.println("Deposit successful! Current balance: " + balance);
	        } else {
	            System.out.println("Invalid amount!");
	        }
	    }

	    // Rút tiền
	    public void withdraw(double amount) {
	        if (amount > balance) {
	            System.out.println("That amount exceeds your current balance.");
	        } else if (amount <= 0) {
	            System.out.println("Invalid amount!");
	        } else {
	            balance -= amount;
	            System.out.println("Withdraw successful! Current balance: " + balance);
	        }
	    }

	    // Hiển thị thông tin tài khoản
	    public void display() {
	        System.out.println("Id: " + id);
	        System.out.println("Name: " + name);
	        System.out.println("CCCD: " + cccd);
	        System.out.println("Balance: " + balance);
	    }
	}
}
