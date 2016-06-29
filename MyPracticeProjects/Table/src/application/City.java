package application;

public class City
{
	private State s1;
	public State getS1() {
		return s1;
	}

	public void setS1(State s1) {
		this.s1 = s1;
	}

	public String getS2() {
		return s2;
	}

	public void setS2(String s2) {
		this.s2 = s2;
	}

	private String s2;

	public City(State sp, String string)
	{
		s1 = sp;
		s2 = string;
	}
}