package stax;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import element.Channel;
import element.Programme;

public class StaxProgramme {
	Programme programme = null;
	String value = null;
	private ArrayList<Programme> programmeList = new ArrayList<Programme>();

	public ArrayList<Programme> getProgrammeList() {
		return programmeList;
	}

	public void storeProgramme() {
		try {
			XMLInputFactory factory = XMLInputFactory.newInstance();
			XMLStreamReader reader = factory.createXMLStreamReader(new FileReader("src/tvguide.xml"));
			int event;
			while (reader.hasNext()) {
				event = reader.next();
				if (event == XMLStreamReader.START_ELEMENT) {
					if (reader.getLocalName().equals("programme")) {
						programme = new Programme();
						for (int i = 0; i < reader.getAttributeCount(); i++) {
							if (i == 0) {
								programme.setDate(reader.getAttributeValue(i).substring(0, 8));
								programme.setStartTime(reader.getAttributeValue(i).substring(8, 14));
							}
							if (i == 1) {
								programme.setStopTime(reader.getAttributeValue(i).substring(8, 14));
							}
							if (i == 3) {
								StaxChannel staxChannel = new StaxChannel();
								staxChannel.storeChannel();
								for (Channel channel : staxChannel.getChannelList()) {
									if (channel.getId().equals(reader.getAttributeValue(i)))
										programme.setChannel(channel.getDisplayName());
								}
							}

							// System.out.println(reader.getAttributeLocalName(i) + ": " +
							// reader.getAttributeValue(i));
						}

					}
				} else if (event == XMLStreamReader.CHARACTERS) {
					if (!reader.isWhiteSpace()) {
						value = reader.getText();
					}
				} else if (event == XMLStreamReader.END_ELEMENT) {
					if (reader.getLocalName().equals("title")) {
						programme.setTitle(value);
					}
					if (reader.getLocalName().equals("director")) {
						programme.setDirectorList(value);
					}
					if (reader.getLocalName().equals("actor") | reader.getLocalName().equals("guest")) {
						if (value.indexOf("(") == -1) {
							programme.setActorGuestList(value);
						} else
							programme.setActorGuestList(value.substring(0, value.indexOf("(")));
					}
					if (reader.getLocalName().equals("presenter")) {
						programme.setPresenterList(value);
					}
					if (reader.getLocalName().equals("writer")) {
						programme.setWriterList(value);
					}
					if (reader.getLocalName().equals("category")) {
						programme.setCategory(value);
					}
					if (reader.getLocalName().equals("rating")) {
						programme.setCsa(value);
					}
					if (reader.getLocalName().equals("date")) {
						programme.setYear(Integer.parseInt(value));
					}
					if (reader.getLocalName().equals("programme")) {
						programmeList.add(programme);
						/*
						 * System.out.println("Date: " + programme.getDate());
						 * System.out.println("Start Time: " + programme.getStartTime());
						 * System.out.println("Stop Time: " + programme.getStopTime());
						 * System.out.println("Channel: " + programme.getChannel());
						 * System.out.println("Title: " + programme.getTitle());
						 * System.out.println(programme.getDirectorList().size());
						 * System.out.println(programme.getActorList().size());
						 * System.out.println("Category: " + programme.getCategory());
						 * System.out.println(programme.getCsa());
						 * System.out.println("--------------------------------------");
						 */
						programme = null;
					}
				}
			}
		} catch (FactoryConfigurationError | XMLStreamException | IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Programme> lighterProList(ArrayList<Programme> programmeList) {
		HashMap<String, Programme> map = new HashMap<String, Programme>();
		ArrayList<Programme> lighter = new ArrayList<Programme>();
		for (Programme programme : programmeList) {
			map.put(programme.getTitle(), programme);
		}
		Iterator<Entry<String, Programme>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Programme> entry = it.next();
			lighter.add(entry.getValue());
		}
		return lighter;
	}

	public Map<String, ArrayList<Programme>> sortCatecory(ArrayList<Programme> programmeList) {
		TreeMap<String, ArrayList<Programme>> cateMap = new TreeMap<String, ArrayList<Programme>>();
		for (Programme programme : programmeList) {
			if (cateMap.containsKey(programme.getCategory())) {
				ArrayList<Programme> a1 = cateMap.get(programme.getCategory());
				a1.add(programme);
			} else {
				ArrayList<Programme> a2 = new ArrayList<Programme>();
				a2.add(programme);
				cateMap.put(programme.getCategory(), a2);
			}
		}
		return cateMap;
	}

	public Map<String, ArrayList<Programme>> sortDate(ArrayList<Programme> programmeList) {
		TreeMap<String, ArrayList<Programme>> dateMap = new TreeMap<String, ArrayList<Programme>>();
		for (Programme programme : programmeList) {
			if (dateMap.containsKey(programme.getDate())) {
				ArrayList<Programme> a1 = dateMap.get(programme.getDate());
				a1.add(programme);
			} else {
				ArrayList<Programme> a2 = new ArrayList<Programme>();
				a2.add(programme);
				dateMap.put(programme.getDate(), a2);
			}
		}
		return dateMap;
	}

	public Map<String, ArrayList<Programme>> sortChannel(ArrayList<Programme> programmeList) {
		TreeMap<String, ArrayList<Programme>> channelMap = new TreeMap<String, ArrayList<Programme>>();
		for (Programme programme : programmeList) {
			if (channelMap.containsKey(programme.getChannel())) {
				ArrayList<Programme> a1 = channelMap.get(programme.getChannel());
				a1.add(programme);
			} else {
				ArrayList<Programme> a2 = new ArrayList<Programme>();
				a2.add(programme);
				channelMap.put(programme.getChannel(), a2);
			}
		}
		return channelMap;
	}

	public Map<String, ArrayList<Programme>> sortCsa(ArrayList<Programme> programmeList) {
		TreeMap<String, ArrayList<Programme>> csaMap = new TreeMap<String, ArrayList<Programme>>();
		for (Programme programme : programmeList) {
			if (csaMap.containsKey(programme.getCsa())) {
				ArrayList<Programme> a1 = csaMap.get(programme.getCsa());
				a1.add(programme);
			} else {
				ArrayList<Programme> a2 = new ArrayList<Programme>();
				a2.add(programme);
				csaMap.put(programme.getCsa(), a2);
			}
		}
		return csaMap;
	}

	// public static void main(String[] args) {
	// StaxProgramme test2 = new StaxProgramme();
	// test2.storeProgramme();
	// }
}
