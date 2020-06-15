package element;

import java.util.ArrayList;

public class Actor {
	private String name;
	private int number;
	private ArrayList<Programme> programmes;

	public Actor(String name) {
		this.name = name;
		number = 0;
		programmes = new ArrayList<Programme>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public ArrayList<Programme> getProgrammes() {
		return programmes;
	}

	public void setProgrammes(Programme programme) {
		programmes.add(programme);
		number++;
	}
}
