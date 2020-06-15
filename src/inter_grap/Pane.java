package inter_grap;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Pane extends Application {
	private Button buttonChannel;
	private Button buttonDate;
	private Button buttonNb1;
	private Button buttonNb2;
	private Button buttonActor;
	private Button buttonSenior;
	private Label labelGiveDate;
	private Label labelGiveChannel;
	private Label labelGiveTitle;
	private Label labelGiveTime;
	private Label labelGiveName;
	private TextArea showWindow;
	private TextField textFieldDate;
	private TextField textFieldChannel;
	private TextField textFieldTitle;
	private TextField textFieldTime;
	private TextField textFieldName;
	private EventHandler textFieldDateListener;
	private EventHandler textFieldChannelListener;
	private EventHandler textFieldTitleListener;
	private EventHandler textFieldTimeListener;
	private EventHandler textFieldNameListener;
	private EventHandler buttonChannelListener;
	private EventHandler buttonDateListener;
	private EventHandler buttonNb1Listener;
	private EventHandler buttonNb2Listener;
	private EventHandler buttonActorListener;
	private EventHandler buttonSeniorListener;

	public void start(Stage stage) throws Exception {
		GridPane root = new GridPane();
		Scene scene = new Scene(root);
		initListener();
		initGUI(root);
		stage.setTitle("TVguide");
		stage.setScene(scene);
		stage.show();

	}

	public void initListener() {
		buttonChannelListener = new EventHandler() {
			@Override
			public void handle(Event event) {
				if (event.getSource() == buttonChannel) {
					stax.Appli app = new stax.Appli();
					stax.StaxChannel staxChannel = new stax.StaxChannel();
					staxChannel.storeChannel();
					showWindow.setText(app.getChannel(staxChannel));
				}
			}
		};

		buttonDateListener = new EventHandler() {
			@Override
			public void handle(Event event) {
				if (event.getSource() == buttonDate) {
					stax.Appli app = new stax.Appli();
					stax.StaxProgramme staxProgramme = new stax.StaxProgramme();
					staxProgramme.storeProgramme();
					showWindow.setText(app.getDays(staxProgramme));
				}
			}
		};

		buttonNb1Listener = new EventHandler() {
			@Override
			public void handle(Event event) {
				if (event.getSource() == buttonNb1) {
					stax.Appli app = new stax.Appli();
					stax.StaxProgramme staxProgramme = new stax.StaxProgramme();
					staxProgramme.storeProgramme();
					showWindow.setText(app.nbProOneDay(staxProgramme));
				}
			}
		};

		buttonNb2Listener = new EventHandler() {
			@Override
			public void handle(Event event) {
				if (event.getSource() == buttonNb2) {
					stax.Appli app = new stax.Appli();
					stax.StaxProgramme staxProgramme = new stax.StaxProgramme();
					staxProgramme.storeProgramme();
					showWindow.setText(app.nbProCsa(staxProgramme));
				}
			}
		};

		buttonActorListener = new EventHandler() {
			@Override
			public void handle(Event event) {
				if (event.getSource() == buttonActor) {
					stax.Appli app = new stax.Appli();
					stax.StaxProgramme staxProgramme = new stax.StaxProgramme();
					staxProgramme.storeProgramme();
					showWindow.setText(app.actorsByFreq(staxProgramme));
				}
			}
		};

		buttonSeniorListener = new EventHandler() {
			@Override
			public void handle(Event event) {
				if (event.getSource() == buttonSenior) {
					stax.Appli app = new stax.Appli();
					stax.StaxProgramme staxProgramme = new stax.StaxProgramme();
					staxProgramme.storeProgramme();
					showWindow.setText(app.chlDecrease(staxProgramme));
				}
			}
		};

		textFieldChannelListener = new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				if (e.getCode().equals(KeyCode.ENTER)) {
					String channel = textFieldChannel.getText();
					String date = textFieldDate.getText();
					try {
						stax.Appli app = new stax.Appli();
						stax.StaxProgramme staxProgramme = new stax.StaxProgramme();
						staxProgramme.storeProgramme();
						stax.StaxChannel staxChannel = new stax.StaxChannel();
						staxChannel.storeChannel();
						showWindow.setText(app.getProgrammes(staxProgramme, date, channel));
					} catch (Exception exp) {
						textFieldChannel.setText("");
					}
				}
			}
		};

		textFieldDateListener = new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				if (e.getCode().equals(KeyCode.ENTER)) {
					String channel = textFieldChannel.getText();
					String date = textFieldDate.getText();
					try {
						stax.Appli app = new stax.Appli();
						stax.StaxProgramme staxProgramme = new stax.StaxProgramme();
						staxProgramme.storeProgramme();
						stax.StaxChannel staxChannel = new stax.StaxChannel();
						staxChannel.storeChannel();
						showWindow.setText(app.getProgrammes(staxProgramme, date, channel));
					} catch (Exception exp) {
						textFieldDate.setText("");
					}
				}
			}
		};

		textFieldTitleListener = new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				if (e.getCode().equals(KeyCode.ENTER)) {
					String title = textFieldTitle.getText();
					try {
						stax.Appli app = new stax.Appli();
						stax.StaxProgramme staxProgramme = new stax.StaxProgramme();
						staxProgramme.storeProgramme();
						showWindow.setText(app.allProgrammes(staxProgramme, title));

					} catch (Exception exp) {
						textFieldChannel.setText("");
					}
				}
			}
		};

		textFieldTimeListener = new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				if (e.getCode().equals(KeyCode.ENTER)) {
					String time = textFieldTime.getText();
					try {
						stax.Appli app = new stax.Appli();
						stax.StaxProgramme staxProgramme = new stax.StaxProgramme();
						staxProgramme.storeProgramme();
						showWindow.setText(app.programmeShowing(staxProgramme, time));

					} catch (Exception exp) {
						textFieldChannel.setText("");
					}
				}
			}
		};

		textFieldNameListener = new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				if (e.getCode().equals(KeyCode.ENTER)) {
					String name = textFieldName.getText();
					try {
						stax.Appli app = new stax.Appli();
						stax.StaxProgramme staxProgramme = new stax.StaxProgramme();
						staxProgramme.storeProgramme();
						showWindow.setText(app.getFilms(staxProgramme, name));

					} catch (Exception exp) {
						textFieldChannel.setText("");
					}
				}
			}
		};
	}

	public void initGUI(GridPane root) {
		root.setPadding(new Insets(10, 10, 10, 10));
		root.setVgap(10);
		root.setHgap(10);

		showWindow = new TextArea();
		root.add(showWindow, 10, 0, 10, 20);
		buttonChannel = new Button("Tous les chaînes");
		buttonChannel.setMinWidth(200);
		root.add(buttonChannel, 0, 0, 10, 1);
		buttonDate = new Button("Tous les jours");
		buttonDate.setMinWidth(200);
		root.add(buttonDate, 0, 1, 10, 1);
		labelGiveChannel = new Label("Donnez une chaîne");
		root.add(labelGiveChannel, 0, 2, 10, 1);
		textFieldChannel = new TextField();
		root.add(textFieldChannel, 0, 3, 10, 1);
		labelGiveDate = new Label("Donnez un jour");
		root.add(labelGiveDate, 0, 4, 10, 1);
		textFieldDate = new TextField();
		root.add(textFieldDate, 0, 5, 10, 1);
		labelGiveTitle = new Label("Donnez un titre");
		root.add(labelGiveTitle, 0, 6, 10, 1);
		textFieldTitle = new TextField();
		root.add(textFieldTitle, 0, 7, 10, 1);
		labelGiveTime = new Label("Donnez un temps");
		root.add(labelGiveTime, 0, 8, 10, 1);
		textFieldTime = new TextField();
		root.add(textFieldTime, 0, 9, 10, 1);
		labelGiveName = new Label("Donnez un acteur/réalisateur");
		root.add(labelGiveName, 0, 10, 10, 1);
		textFieldName = new TextField();
		root.add(textFieldName, 0, 11, 10, 1);
		buttonActor = new Button("Acteurs par nb d'apparitions");
		buttonActor.setMinWidth(200);
		root.add(buttonActor, 0, 12, 10, 1);
		buttonNb1 = new Button("nb d'émissions(type/jour)");
		buttonNb1.setMinWidth(200);
		root.add(buttonNb1, 0, 13, 10, 1);
		buttonNb2 = new Button("nb d'émissions(csa/chaîne)");
		buttonNb2.setMinWidth(200);
		root.add(buttonNb2, 0, 14, 10, 1);
		buttonSenior = new Button("Chaînes par ancienneté");
		buttonSenior.setMinWidth(200);
		root.add(buttonSenior, 0, 15, 10, 1);

		buttonChannel.setOnAction(buttonChannelListener);
		buttonDate.setOnAction(buttonDateListener);
		textFieldChannel.setOnKeyPressed(textFieldChannelListener);
		textFieldDate.setOnKeyPressed(textFieldDateListener);
		textFieldTitle.setOnKeyPressed(textFieldTitleListener);
		textFieldTime.setOnKeyPressed(textFieldTimeListener);
		textFieldName.setOnKeyPressed(textFieldNameListener);
		buttonNb1.setOnAction(buttonNb1Listener);
		buttonNb2.setOnAction(buttonNb2Listener);
		buttonActor.setOnAction(buttonActorListener);
		buttonSenior.setOnAction(buttonSeniorListener);
	}

	public static void main(String[] args) {
		launch(args);

	}
}
