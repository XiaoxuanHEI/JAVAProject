package element;

import java.util.ArrayList;

public class Programme {
	private String date;
	private String startTime;
	private String stopTime;
	private String channel;
	private String title;
	private ArrayList<String> directorList = new ArrayList<String>();
	private ArrayList<String> actorGuestList = new ArrayList<String>();
	private ArrayList<String> presenterList = new ArrayList<String>();
	private ArrayList<String> writerList = new ArrayList<String>();
	private String category;
	private String csa;
	private int year;

	public Programme() {
		year = 0;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getStopTime() {
		return stopTime;
	}

	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArrayList<String> getDirectorList() {
		return directorList;
	}

	public void setDirectorList(String director) {
		directorList.add(director);
	}

	public ArrayList<String> getActorGuestList() {
		return actorGuestList;
	}

	public void setActorGuestList(String actor) {
		actorGuestList.add(actor);
	}

	public ArrayList<String> getPresenterList() {
		return presenterList;
	}

	public void setPresenterList(String presenter) {
		presenterList.add(presenter);
	}

	public ArrayList<String> getWriterList() {
		return writerList;
	}

	public void setWriterList(String writer) {
		writerList.add(writer);
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCsa() {
		return csa;
	}

	public void setCsa(String csa) {
		this.csa = csa;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
}
