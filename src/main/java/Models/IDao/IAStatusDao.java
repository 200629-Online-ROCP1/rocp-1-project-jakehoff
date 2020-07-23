package Models.IDao;
import Models.AccountStatus;
import java.util.List;

public interface IAStatusDao {
		
		public List<AccountStatus> findAll();
		public AccountStatus findById(int id);
		public boolean addStatus(AccountStatus a);
}
