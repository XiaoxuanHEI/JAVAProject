package stax;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import element.Channel;

public class StaxChannel {
	Channel channel = null;
	String value = null;
	
	private ArrayList<Channel> channelList = new ArrayList<Channel>();
	public ArrayList<Channel> getChannelList() {
		return channelList;
	}

	public void storeChannel() {
		try {
			// create an instance of XMLInputFactory
			XMLInputFactory factory = XMLInputFactory.newInstance();
			// create an instance of XMLStreamReader through factory
			XMLStreamReader reader = factory.createXMLStreamReader(new FileReader("src/tvguide.xml"));
			int event;
			while (reader.hasNext()) {
				event = reader.next();
				if (event == XMLStreamReader.START_ELEMENT) {
					// get the attributes of "channel"
					if (reader.getLocalName().equals("channel")) {
						// create an instance of channel
						channel = new Channel();
						// System.out.print("<"+reader.getLocalName()+">");
						for (int i = 0; i < reader.getAttributeCount(); i++) {
							channel.setId(reader.getAttributeValue(i));
							// System.out.println(reader.getAttributeLocalName(i) + ": " +
							// reader.getAttributeValue(i));
						}
					}
					/*
					 * if(reader.getLocalName().equals("display-name")){
					 * System.out.print(reader.getLocalName() + ": "); }
					 */
					if (reader.getLocalName().equals("icon")) {
						for (int i = 0; i < reader.getAttributeCount(); i++) {
							channel.setSrc(reader.getAttributeValue(i));
							// System.out.println(reader.getAttributeLocalName(i) + ": " +
							// reader.getAttributeValue(i));
						}
					}
					// stop storing when meet "programme"
					if (reader.getLocalName().equals("programme")) {
						break;
					}
				}

				// store characters
				else if (event == XMLStreamReader.CHARACTERS) {
					if (!reader.isWhiteSpace()) {
						value = reader.getText();
						// System.out.println(value);
					}
				}
				// store every channel to channelList
				else if (event == XMLStreamReader.END_ELEMENT) {
					if (reader.getLocalName().equals("channel")) {
						channelList.add(channel);
						channel = null;
						// System.out.println("---------------------------------");
					} else if (reader.getLocalName().equals("display-name")) {
						channel.setDisplayName(value);
					}
				}
			}
		} catch (FactoryConfigurationError | XMLStreamException | IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * public static void main(String[] args) throws Exception { // TODO
	 * Auto-generated method stub StaxChannel test = new StaxChannel();
	 * test.test1(); System.out.println(test.getChannelList().size()); }
	 */
}
