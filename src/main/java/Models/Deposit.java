package Models;

public class Deposit {
	public int id;
	public float amount;
	
	
	public Deposit() {
		super();
		}
	public Deposit(int id, float amount) {
		super();
		this.id = id;
		this.amount = amount;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
