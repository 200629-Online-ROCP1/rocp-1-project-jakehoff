package Models;

public class intrest {
	public float p;
	public float r;
	public float t;
	public float f;
	
	
	public intrest() {
		super();
		// TODO Auto-generated constructor stub
	}


	public intrest(float p, float r, float t) {
		super();
		this.p = p;
		this.r = r;
		this.t = t;
	}


	//Simple Interest = (P × R × T)/100
	public float calcintrest(float p,float r,float t) {
		f=(p*r*t)/100f;
		return f;
	}


	public float getP() {
		return p;
	}


	public void setP(float p) {
		this.p = p;
	}


	public float getR() {
		return r;
	}


	public void setR(float r) {
		this.r = r;
	}


	public float getT() {
		return t;
	}


	public void setT(float t) {
		this.t = t;
	}

	
}
