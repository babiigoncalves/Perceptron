package perceptron;

public class Dados {

	double bias;
	double x1;
	double x2;
	int yd;
	int y0;
	
	
	
	public Dados(double x0, double x1, int yd) {
		super();
		this.x1 = x0;
		this.x2 = x1;
		this.yd = yd;
	}
	public double getX0() {
		return x1;
	}
	public void setX0(double x0) {
		this.x1 = x0;
	}
	public double getX1() {
		return x2;
	}
	public void setX1(double x1) {
		this.x2 = x1;
	}
	public int getYd() {
		return yd;
	}
	public void setYd(int yd) {
		this.yd = yd;
	}
	public double getBias() {
		return bias;
	}
	public void setBias(double bias) {
		this.bias = bias;
	}
	public double getX2() {
		return x2;
	}
	public void setX2(double x2) {
		this.x2 = x2;
	}
	public int getY0() {
		return y0;
	}
	public void setY0(int y0) {
		this.y0 = y0;
	}
	
	
}
