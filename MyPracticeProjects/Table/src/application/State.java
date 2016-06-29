package application;

public class State
{
	private String a;
	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	private String b;

	public State(String string, String string2)
	{
		a = string;
		b = string2;
	}
}