package Models;

public class Withdraw {
public int id;
public float amount;

public Withdraw() {
	super();
}
public Withdraw(int id, float amount) {
	super();
	this.id = id;
	this.amount = amount;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public float getAmount() {
	return amount;
}
public void setAmount(float amount) {
	this.amount = amount;
}

}
