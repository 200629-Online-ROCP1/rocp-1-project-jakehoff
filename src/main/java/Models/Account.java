package Models;

public class Account {
	 private int accountId; // primary key
	  private float balance;  // not null
	  private static AccountStatus status;
	  private static AccountType type;
	  private static User Owner;

public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
public Account(int accountId, float balance, AccountStatus status, AccountType type, User owner) {
	super();
	this.accountId = accountId;
	this.balance = balance;
	this.status = status;
	this.type = type;
	Owner = owner;
}

public Account(float balance, AccountStatus status, AccountType type, User owner) {
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

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((Owner == null) ? 0 : Owner.hashCode());
	result = prime * result + accountId;
	long temp;
	temp = Double.doubleToLongBits(balance);
	result = prime * result + (int) (temp ^ (temp >>> 32));
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
	Account other = (Account) obj;
	if (Owner == null) {
		if (other.Owner != null)
			return false;
	} else if (!Owner.equals(other.Owner))
		return false;
	if (accountId != other.accountId)
		return false;
	if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
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
	return "Account [accountId=" + accountId + ", balance=" + balance + ", status=" + status + ", type=" + type
			+ ", Owner=" + Owner + "]";
}





}
