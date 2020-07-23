package Models;

public class Transfer {
	public int id1;
	public int id2;
	public float amount;

public Transfer() {
		super();
	}
public Transfer(int id, int id2, float amount) {
	super();
	this.id1 = id;
	this.id2 = id2;
	this.amount = amount;
}
public int getId1() {
	return id1;
}
public void setId1(int id) {
	this.id1 = id;
}
public int getId2() {
	return id2;
}
public void setId2(int id) {
	this.id2 = id;
}
public float getAmount() {
	return amount;
}
public void setAmount(float amount) {
	this.amount = amount;
}

}
