package Models;

public class ChangeStatus {
	String s;
	int u;
	public ChangeStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChangeStatus(String s,int u) {
		super();
		this.s = s;
		this.u = u;
	}
	public String getS() {
		return s;
	}
	public void setS(String s) {
		this.s = s;
	}
	public int getu() {
		return u;
	}
	public void setu(int u) {
		this.u = u;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((s == null) ? 0 : s.hashCode());
		result = prime * result + u;
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
		ChangeStatus other = (ChangeStatus) obj;
		if (s == null) {
			if (other.s != null)
				return false;
		} else if (!s.equals(other.s))
			return false;
		if (u != other.u)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ChangeStatus [s=" + s + ", u=" + u + "]";
	}
	
}
