package Models;

public class Account_multi {
	 private int accountId; // primary key
	  private float balance;  // not null
	  private AccountStatus status;
	  private AccountType type;
	  private User Owner;
	  private User Owner2;

public Account_multi() {
		super();
		// TODO Auto-generated constructor stub
	}
public Account_multi(int accountId, float balance, AccountStatus status, AccountType type, User owner,User Owner2) {
	super();
	this.accountId = accountId;
	this.balance = balance;
	this.status = status;
	this.type = type;
	Owner = owner;
	this.Owner2=Owner2;
}

public Account_multi(float balance, AccountStatus status, AccountType type, User owner) {
	super();
	this.balance = balance;
	this.status = status;
	this.type = type;
	Owner = owner;
}

public int getAccountId() {
	return accountId;
}
public void setAccountId(int accountId) {
	this.accountId = accountId;
}
public float getBalance() {
	return balance;
}
public void setBalance(float balance) {
	this.balance = balance;
}
public AccountStatus getStatus() {
	return status;
}
public void setStatus(AccountStatus status) {
	this.status = status;
}
public AccountType getType() {
	return type;
}
public void setType(AccountType type) {
	this.type = type;
}
public User getOwner() {
	return Owner;
}
public void setOwner(User owner) {
	Owner = owner;
}
public boolean Check_Id(int Forgein) {
	if (this.Owner.getUserId()==Forgein) {
		return true;
	}
	return false;
}
public int WantStatus() {
	int x = this.status.getStatusId();
	return x;
}
public int WantType() {
	int x = this.type.getTypeId();
	return x;
}
public int WantOwner() {
	int x = this.Owner.getUserId();
	return x;
}
public float Withdraw(float amount, int ForgeinID) {
	if(this.Check_Id(ForgeinID)) {
	this.setBalance(this.getBalance() + amount);
	return(amount);
	}
	System.out.println("i'm sorry i can't do that dave");
	return amount;
}
public double Deposit(float amount, int ForgeinID) {
	if(this.Check_Id(ForgeinID)) {
	this.setBalance(this.getBalance() + amount);
	return(this.getBalance());
	}
	System.out.println("i'm sorry i can't do that dave");
	return amount;
}
public double Transfer(float amount,int ForgeinID, Account AccountAlt) {
	if(this.Check_Id(ForgeinID)) {
	this.setBalance(this.getBalance() + amount);
	AccountAlt.setBalance(AccountAlt.getBalance() + amount);
	return(amount);
	}
	System.out.println("i'm sorry i can't do that dave");
	return amount;
}
public User getOwner2() {
	return Owner2;
}
public void setOwner2(User owner2) {
	Owner2 = owner2;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((Owner == null) ? 0 : Owner.hashCode());
	result = prime * result + ((Owner2 == null) ? 0 : Owner2.hashCode());
	result = prime * result + accountId;
	result = prime * result + Float.floatToIntBits(balance);
	result = prime * result + ((status == null) ? 0 : status.hashCode());
	result = prime * result + ((type == null) ? 0 : type.hashCode());
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Account_multi other = (Account_multi) obj;
	if (Owner == null) {
		if (other.Owner != null)
			return false;
	} else if (!Owner.equals(other.Owner))
		return false;
	if (Owner2 == null) {
		if (other.Owner2 != null)
			return false;
	} else if (!Owner2.equals(other.Owner2))
		return false;
	if (accountId != other.accountId)
		return false;
	if (Float.floatToIntBits(balance) != Float.floatToIntBits(other.balance))
		return false;
	if (status == null) {
		if (other.status != null)
			return false;
	} else if (!status.equals(other.status))
		return false;
	if (type == null) {
		if (other.type != null)
			return false;
	} else if (!type.equals(other.type))
		return false;
	return true;
}
@Override
public String toString() {
	return "Account_multi [accountId=" + accountId + ", balance=" + balance + ", status=" + status + ", type=" + type
			+ ", Owner=" + Owner + ", Owner2=" + Owner2 + "]";
}


}
