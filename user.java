/**
 * I declare that this code was written by me.
 * I will not copy or allow others to copy my code.
 * I understand that copying code is considered as plagiarism.
 *
 * Vernon Ong, 14 Jul 2022 12:31:26 am
 */

package atm;

public class user {
	
	private String user;
	private int id;
	private double balance;
	
	public user(String user, int id, double balance) {
		this.user = user;
		this.id = id;
		this.balance = balance;
	}

	public String getUser() {
		return user;
	}

	public int getId() {
		return id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
}
