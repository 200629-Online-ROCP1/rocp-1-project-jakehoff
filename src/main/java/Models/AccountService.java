package Models;

import java.util.List;

import Models.IDao.IAStatusDao;
import Models.IDao.IUserDao;
import Models.dao.AStatusDao;
import Models.dao.ATypeDao;
import Models.dao.Userdao;

public class AccountService {
	private final IAccountDAO dao = new accountDao();
	private final IUserDao dao1 = new Userdao();
	private final IAStatusDao dao2 = new AStatusDao();
	private final ATypeDao dao3 = new ATypeDao();
	public final Withdraw w = new Withdraw();
	Account ac;
	public List <Account> findAll(){
		return dao.findAll();
	}
	public Account findById(int id) {
		return dao.findById(id);
	}
	public AccountStatus MatchStatus(Account s) {
		int x= s.WantStatus();
		return(dao2.findById(x));
	}
	public AccountType MatchType(Account t) {
		int y =t.WantType();
		return(dao3.findById(y));

	}
	public User MatchOwner(Account u) {
		int z = u.WantOwner();
		return (dao1.findById(z));
	}
	public float Withdraw(float i, Account z) {
		ac = dao.findById(z.getAccountId());
		return dao.Withdraw(i,ac);
	}
	public float Deposit(float i, Account a) {
		return dao.Deposit(i,a);

	}
	public float Transfer(float i, Account a, Account b) {
		return dao.Transfer(i,a,b);

	}
	public boolean addAccount(Account a) {		
		List<Account> list = findAll();
		
		for(Account ac: list) {
			if(ac.getOwner().equals(a.getOwner())&&ac.getAccountId()==a.getAccountId() && ac.getStatus().equals(a.getStatus())&&ac.getType().equals(a.getType())) {
				return false;
			}
		}
		User theOwner = this.MatchOwner(a);
		AccountType theType = this.MatchType(a);
		AccountStatus theStatus = this.MatchStatus(a);
		boolean b = dao.addAccount(a,theOwner,theStatus,theType);
		return(b);
	}
}
