package Models;

public class ChangeString {
	String s;
	String st;
	int u;
	public ChangeString() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChangeString(String s, String st,int u) {
		super();
		this.s = s;
		this.st = st;
		this.u = u;
	}
	public String getS() {
		return s;
	}
	public void setS(String s) {
		this.s = s;
	}
	public String getSt() {
		return st;
	}
	public void setSt(String st) {
		this.st = st;
	}
	public int getu() {
		return u;
	}
	public void setu(int u) {
		this.u = u;
	}
}
