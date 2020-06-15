package stax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import element.Actor;
import element.Channel;
import element.Programme;

public class Appli {

	// question 1.1
	public String getChannel(StaxChannel staxChannel) {
		String result = "";
		for (Channel channel : staxChannel.getChannelList()) {
			result = result + channel.getDisplayName() + "\n";
		}
		return result;
	}

	// question 1.2
	public String getDays(StaxProgramme staxProgramme) {
		String result = "";
		HashSet<String> set = new HashSet<String>();
		for (Programme programme : staxProgramme.getProgrammeList()) {
			set.add(programme.getDate().substring(0, 4) + "/" + 
					programme.getDate().substring(4,6) + "/" + 
					programme.getDate().substring(6, 8) + "\n");
		}
		for (String s : set) {
			result = result + s;
		}
		return result;
	}

	// question 1.3
	public String getProgrammes(StaxProgramme staxProgramme, String date, String channelGiven) {
		ArrayList<Programme> find = new ArrayList<Programme>();
		for (Programme programme : staxProgramme.getProgrammeList()) {
			if (programme.getChannel().equals(channelGiven)) {
				if (programme.getDate().equals(date)) {
					find.add(programme);
				}
			}
		}
		String result = "";
		for (Programme p : find) {
			result = result + p.getTitle() + "\n";
		}
		return result;
	}

	// question 1.4
	public String allProgrammes(StaxProgramme staxProgramme, String title) {
		String result = title + ": " + "\n";
		for (Programme programme : staxProgramme.getProgrammeList()) {
			if (programme.getTitle().equals(title)) {
				result = result + "Channel: " + programme.getChannel() + "\n";
				result = result + "Category: " + programme.getCategory() + "\n";
				for (String director : programme.getDirectorList()) {
					result = result + "Director: " + director + "\n";
				}
				for (String actor : programme.getActorGuestList()) {
					result = result + "ActorOrGuest: " + actor + "\n";
				}
				for (String presenter : programme.getPresenterList()) {
					result = result + "Presenter: " + presenter + "\n";
				}
				for (String writer : programme.getWriterList()) {
					result = result + "Writer: " + writer + "\n";
				}
				if (programme.getYear() != 0) {
					result = result + "Year: " + programme.getYear() + "\n";
				}
				break;
			}
		}
		for (Programme programme : staxProgramme.getProgrammeList()) {
			if (programme.getTitle().equals(title)) {
				result += "----------------------------------------------" + "\n";
				result = result + "Date: " + programme.getDate().substring(0, 4) + "/" + 
						 programme.getDate().substring(4,6) + "/" + 
						 programme.getDate().substring(6, 8) + "\n";
				result = result + "StartTime: " + programme.getStartTime().substring(0, 2) + ":" +
						 programme.getStartTime().substring(2, 4) + ":" +
						 programme.getStartTime().substring(4, 6) + "\n";
				result = result + "StopTime: " + programme.getStopTime().substring(0, 2) + ":" +
						 programme.getStopTime().substring(2, 4) + ":" +
						 programme.getStopTime().substring(4, 6) + "\n";
			}
		}
		return result;
	}

	// question 1.5
	public String programmeShowing(StaxProgramme staxProgramme, String time) {
		ArrayList<Programme> find = new ArrayList<Programme>();
		for (Programme programme : staxProgramme.getProgrammeList()) {
			if (time.substring(0, 8).equals(programme.getDate())) {
				if (Integer.parseInt(time.substring(8, 14)) >= Integer.parseInt(programme.getStartTime())
						&& Integer.parseInt(time.substring(8, 14)) <= Integer.parseInt(programme.getStopTime())) {
					find.add(programme);
				}
			}
		}
		String result = "";
		for (Programme p : find) {
			result = result + p.getTitle() + "\n";
		}
		return result;
	}

	// question 1.6
	public String getFilms(StaxProgramme staxProgramme, String name) {
		HashSet<String> find = new HashSet<String>();
		for (Programme programme : staxProgramme.getProgrammeList()) {
			for (String acts : programme.getActorGuestList()) {
				if (acts.equals(name)) {
					find.add(programme.getTitle());
					break;
				}
			}
			for (String acts : programme.getDirectorList()) {
				if (acts.equals(name)) {
					find.add(programme.getTitle());
					break;
				}
			}
		}
		String result = "";
		for (String s : find) {
			result = result + s + "\n";
		}
		return result;
	}

	// question 2.1
	public String actorsByFreq(StaxProgramme staxProgramme) {
		String result = "";
		staxProgramme.storeProgramme();
		// HashMap<String, Actor> actors = new HashMap<String, Actor>();
		ArrayList<Actor> actors = new ArrayList<Actor>();
		for (Programme programme : staxProgramme.lighterProList(staxProgramme.getProgrammeList())) {
			for (String actor : programme.getActorGuestList()) {
				for (Actor a : actors) {
					if (a.getName().equals(actor)) {
						a.setProgrammes(programme);
						break;
					}
				}
				Actor act = new Actor(actor);
				act.setProgrammes(programme);
				actors.add(act);
			}
		}
		for (int i = 0; i < actors.size(); i++) {
			for (int j = 1; j < actors.size() - i; j++) {
				if (actors.get(j - 1).getNumber() < actors.get(j).getNumber()) {
					Actor a = actors.get(j);
					actors.set(j, actors.get(j - 1));
					actors.set(j - 1, a);
				}
			}
		}
		for (Actor a : actors) {
			result = result + a.getName() + ": " + a.getNumber() + "\n";
		}
		return result;
	}

	// question 2.2
	public String nbProOneDay(StaxProgramme staxProgramme) {
		Map<String, ArrayList<Programme>> map1 = staxProgramme.sortDate(staxProgramme.getProgrammeList());
		Iterator<String> it1 = map1.keySet().iterator();
		String result = "";
		while (it1.hasNext()) {
			String date = it1.next();
			result = result + "Date: " + date.substring(0, 4) + "/" +  
					 date.substring(4,6) + "/" + 
					 date.substring(6, 8) + ": " + "\n";
			Map<String, ArrayList<Programme>> map2 = staxProgramme.sortCatecory(map1.get(date));
			Iterator<String> it2 = map2.keySet().iterator();
			while (it2.hasNext()) {
				String category = it2.next();
				result = result + category + ": ";
				int number = 0;
				for (Programme programme : map2.get(category)) {
					number += 1;
					// System.out.println(programme.getStartTime()+"---"+programme.getStopTime()+ "
					// "+programme.getTitle());
				}
				result = result + number + "\n";
			}
			result += "----------------------------------------------" + "\n";
		}
		return result;
	}

	// question 2.3
	public String nbProCsa(StaxProgramme staxProgramme) {
		Map<String, ArrayList<Programme>> map1 = staxProgramme
				.sortChannel(staxProgramme.lighterProList(staxProgramme.getProgrammeList()));
		Iterator<String> it1 = map1.keySet().iterator();
		String result = "";
		while (it1.hasNext()) {
			String channel = it1.next();
			result = result + channel + " : " + "\n";
			Map<String, ArrayList<Programme>> map2 = staxProgramme.sortCsa(map1.get(channel));
			Iterator<String> it2 = map2.keySet().iterator();
			while (it2.hasNext()) {
				String csa = it2.next();
				result = result + csa + ": ";
				int number = 0;
				for (Programme programme : map2.get(csa)) {
					number += 1;
					// System.out.println(programme.getStartTime()+"---"+programme.getStopTime()+ "
					// "+programme.getTitle());
				}
				result = result + number + "\n";
			}
			result += "----------------------------------------------" + "\n";
		}
		return result;
	}

	// question 2.4
	public String chlDecrease(StaxProgramme staxProgramme) {
		String result = "";
		int sum = 0;
		ArrayList<Programme> proWithYear = new ArrayList<Programme>();
		for (Programme p : staxProgramme.lighterProList(staxProgramme.getProgrammeList())) {
			if (p.getYear() != 0) {
				proWithYear.add(p);
			}
		}
		Map<String, ArrayList<Programme>> map1 = staxProgramme.sortChannel(proWithYear);
		HashMap<String, Float> map2 = new HashMap<String, Float>();
		Iterator<String> it1 = map1.keySet().iterator();
		while (it1.hasNext()) {
			String channel = it1.next();
			for (Programme p : map1.get(channel)) {
				sum += p.getYear();
			}
			int size = map1.get(channel).size();
			float avg = (float) sum / size;
			map2.put(channel, avg);
			sum = 0;
		}
		ArrayList<Map.Entry<String, Float>> list = new ArrayList<Entry<String, Float>>(map2.entrySet());
		for (int i = 0; i < list.size(); i++) {
			for (int j = 1; j < list.size() - i; j++) {
				if (list.get(j - 1).getValue() < list.get(j).getValue()) {
					Map.Entry<String, Float> entry = list.get(j);
					list.set(j, list.get(j - 1));
					list.set(j - 1, entry);
				}
			}
		}
		for (Map.Entry<String, Float> en : list) {
			result = result + "Channel: " + en.getKey() + "\n" + "Seniority: " + en.getValue() + "\n";
			result += "----------------------------------------------" + "\n";
		}
		return result;
	}

	public static void main(String[] args) {
		Appli app = new Appli();
		StaxChannel test1 = new StaxChannel();
		StaxProgramme test2 = new StaxProgramme();
		test1.storeChannel();
		test2.storeProgramme();
		// System.out.println(test2.);
		// System.out.println(app.getChannel(test1));
		// System.out.println(app.getDays(test2));
		// System.out.println(app.getProgrammes(test2,"20180504","Arte"));
		// System.out.println(app.allProgrammes(test2, "Arte journal junior"));
		// System.out.println(app.getFilms(test2,"Sarah Suco"));
		// System.out.println(app.programmeShowing(test2,"20180504210000"));
		// System.out.println(app.actorsByFreq(test2));
		// System.out.println(app.nbProOneDay(test2));
		// System.out.println(app.nbProCsa(test2));
		// System.out.println(app.chlDecrease(test2));
	}
}
