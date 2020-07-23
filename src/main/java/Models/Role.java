package Models;

public class Role {
	  private int roleId; // primary key
	  private String role; // not null, unique
	  

		public Role() {
		super();
	}
		public Role(int roleId, String role) {
		super();
		this.roleId = roleId;
		this.role = role;
	}
		public Role(String role) {
		super();
		this.role = role;
	}
		public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
		public boolean Chelf_Privileges_Employee() {
			if((this.getRole())=="Admin" || this.getRole()=="Employee") {
			return true;	
			}
			return false;
			}
		public boolean Chelf_Privileges_Admin() {
			if((this.getRole())=="Admin") {
			return true;	
			}
			return false;
			}
		//admin/empolee comands
		//retreive account
		public Account Retrieve_Foreign_Account(Account Foreign) {
			return(Foreign);
		}
		public User Retrieve_Foreign_User(User Foreign) {
			return(Foreign);
		}
		//modify user, Admin only
		public void Adm_Modify_Pass(User Foreign, String Password) {
			
				Foreign.setPassword(Password);
			
			//System.out.println("i'm sorry i can't do that dave");
		}
		public void Adm_Modify_Uname(User Foreign, String Uname) {
			
				Foreign.setUsername(Uname);
			
			//System.out.println("i'm sorry i can't do that dave");
		}
		public void Adm_Modify_Email(User Foreign, String Email) {
			
				Foreign.setEmail(Email);
			
			//System.out.println("i'm sorry i can't do that dave");
		}
		public void Adm_Modify_Fname(User Foreign, String Fname) {
				Foreign.setPassword(Fname);
			
			//System.out.println("i'm sorry i can't do that dave");
		}
		public void Adm_Modify_Lname(User Foreign, String Lname) {
				Foreign.setPassword(Lname);
			
			//System.out.println("i'm sorry i can't do that dave");
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((role == null) ? 0 : role.hashCode());
			result = prime * result + roleId;
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
			Role other = (Role) obj;
			if (role == null) {
				if (other.role != null)
					return false;
			} else if (!role.equals(other.role))
				return false;
			if (roleId != other.roleId)
				return false;
			return true;
		}
		@Override
		public String toString() {
			return "Role [roleId=" + roleId + ", role=" + role + "]";
		}
		
}
