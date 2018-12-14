package view;

import java.util.Timer;
import java.util.TimerTask;

import controller.AppControl;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * This class is the View in the MVC model
 * 
 * @author DigitalVilla
 *
 */
public class AppView extends Application {

	@Override
	public void start(Stage window) throws Exception {
		window.setScene(new AppUI().getMyScene());
		// window.initStyle(StageStyle.UNDECORATED);
		window.setTitle("Speedometor");
		window.setResizable(false);
		window.toFront();
		window.show();
	}

	/**
	 * This class creates the App GUI window
	 * 
	 * @author DigitalVilla
	 *
	 */
	public class AppUI {
		// Setters and Getters
		private Pane appApp;
		private Label displayText;
		public TextField txtFld;
		private double appW;
		private double appH;
		private Button controlBtn;
		private int counter;
		private ImageView img;
		private ChoiceBox<String> fileSelect;
		private ChoiceBox<String> typeSelect;
		private ChoiceBox<String> sortSelect;
		private String mainTxt;
		private ChoiceBox<String> sizeSelect;
		private AppControl ac;

		public AppUI() {
			ac = new AppControl(this);
			appW = 830;
			appH = 240;
		}

		public Label getDisplayText() {
			return displayText;
		}
		// Method to print to the screen

		/**
		 * This method prints to the GUI screen.
		 * 
		 * @param text
		 *            The text to be printed
		 */
		public void addText(String text) {

			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					displayText.setText(displayText.getText() + text);
				}
			});
		}
		// Method to print to the screen

		/**
		 * This method prints to the GUI screen.
		 * 
		 * @param text
		 *            The text to be printed
		 */
		public void setText(String text) {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					displayText.setText(text);
				}
			});
		}

		/**
		 * This method creates all the JavaFx elements inside the window
		 */
		public void paintScene() {
			appApp = new Pane();
			appApp.setId("appApp");

			// SCREEN DISPLAY
			ScrollPane display = new ScrollPane();
			display.setFitToWidth(true);
			display.setId("appDisplay");
			display.setVvalue(1.0);
			// Setting a horizontal scroll bar is always display
			display.setHbarPolicy(ScrollBarPolicy.NEVER);
			// Setting vertical scroll bar is never displayed.
			display.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
			display.setMaxSize((appW) - 20, appH - 80);
			display.setMinSize((appW) - 20, appH - 80);
			display.setLayoutY(10);
			display.setLayoutX(10);
			mainTxt = "		INSTRUCTIONS:\n	1) Select the DATAFILE to read.";
			mainTxt += "\n	2) Select the COUNTER of items to display";
			mainTxt += "\n	3) Select the PARAMETER to compare by.";
			mainTxt += "\n	4) Select the ALGORITHM to sort the array.";
			mainTxt += "\n	5) Press the BUTTON to start the ride.";

			displayText = new Label();
			displayText.setId("appText");
			displayText.setAlignment(Pos.TOP_LEFT);
			displayText.setWrapText(true);
			// displayText.setMaxSize(appW - 20, appH - 10);
			displayText.setMinHeight(appH - 80);
			displayText.setMaxWidth(appW);
			displayText.setLayoutY(10);
			displayText.setLayoutX(10);
			// Add to display
			display.setContent(displayText);
			display.getStyleClass().add("scroll-bar");

			// CONTROL BAR
			Pane control = new Pane();
			control.setMaxWidth(appW / 3);
			control.setMaxHeight(appH);
			control.setLayoutX(0);
			control.setLayoutY(165);

			fileSelect = new ChoiceBox<String>();
			fileSelect.getItems().addAll("polyfor1", "polyfor2", "polyfor3");
			fileSelect.getStyleClass().add("selector");
			fileSelect.setMinWidth(150);
			fileSelect.setMaxWidth(150);
			fileSelect.setLayoutX(25);
			fileSelect.setLayoutY(30);

			sizeSelect = new ChoiceBox<String>();
			sizeSelect.getItems().addAll("1000", "10000", "100000");
			sizeSelect.getStyleClass().add("selector");
			sizeSelect.setMinWidth(100);
			sizeSelect.setMaxWidth(100);
			sizeSelect.setLayoutX(200);
			sizeSelect.setLayoutY(30);

			typeSelect = new ChoiceBox<String>();
			typeSelect.getItems().addAll("Height", "Volume", "B. Area");
			typeSelect.getStyleClass().add("selector");
			typeSelect.setMinWidth(110);
			typeSelect.setMaxWidth(110);
			typeSelect.setLayoutX(320);
			typeSelect.setLayoutY(30);

			sortSelect = new ChoiceBox<String>();
			sortSelect.getItems().addAll("Quick sort", "Merge sort", "Heap sort", "Java sort", "Insertion sort",
					"Selection sort", "Bubble sort");
			sortSelect.setMinWidth(185);
			sortSelect.setMaxWidth(185);
			sortSelect.getStyleClass().add("selector");
			sortSelect.setLayoutX(450);
			sortSelect.setLayoutY(30);

			controlBtn = new Button();
			controlBtn.setId("appBtn");
			controlBtn.setMinSize(140, 40);
			controlBtn.setLayoutX(appW - 160);
			controlBtn.setLayoutY(25);
			controlBtn.setOnAction(e -> {
				actionBTN();
			});

			startBtn();
			fileSelect.getSelectionModel().selectFirst();
			typeSelect.getSelectionModel().selectFirst();
			sortSelect.getSelectionModel().selectFirst();
			sizeSelect.getSelectionModel().selectFirst();
			control.getChildren().addAll(fileSelect, sizeSelect, typeSelect, sortSelect, controlBtn);
			appApp.getChildren().addAll(control, display);
		}

		private String quoteLoader() {
			String[] phrases = { "PLEASE BE PATIENT... My Gnomes Are Working Hard.",
					"Nobody notices all the GOOD things you do until you don't do them",
					"Always remember that you're unique. Just like everyone else.",
					"The more you weight the harder you are to kidnap.",
					"Dear Math, please grow up and solve your own problems.",
					"The only true connection I have is with my WiFi",
					"My life was put together yesterday, sorry you missed it",
					"The best part of programmig is that my chair swivels.",
					"With great power comes an even greater electricity bill.",
					"When nothing is going right, go left." };

			int rndm = (int) (Math.random() * phrases.length);
			return phrases[rndm];

		}

		private void actionBTN() {
			if (++counter == 1) {
				setText("\n\n		" + quoteLoader());
				resetBtn();
				Timer timer = new Timer();
				timer.schedule(new TimerTask() {
					@Override
					public void run() {
						ac.setParams(getParams());
					}
				}, 2 * 1000);
			} else if (counter == 2) {
				startBtn();
				counter = 0;
			}
		}

		private String getParams() {
			String params = fileSelect.getValue() + ".txt";
			params += "," + typeSelect.getValue().toLowerCase().charAt(0);
			params += "," + sortSelect.getValue().toLowerCase().charAt(0);
			params += "," + sizeSelect.getValue();
			return params;
		}

		private void startBtn() {
			img = new ImageView(this.getClass().getResource("/res/icons/play.png").toExternalForm());
			img.setFitWidth(20);
			img.setPreserveRatio(true);
			controlBtn.setGraphic((img));
			controlBtn.setText(" START");

			fileSelect.setDisable(false);
			typeSelect.setDisable(false);
			sortSelect.setDisable(false);
			sizeSelect.setDisable(false);
			controlBtn.setDisable(false);
			displayText.setText(mainTxt);
		}

		public void resetBtn() {
			img = new ImageView(this.getClass().getResource("/res/icons/refresh.png").toExternalForm());
			img.setFitWidth(20);
			img.setPreserveRatio(true);
			controlBtn.setGraphic((img));
			controlBtn.setText(" RESET");
			fileSelect.setDisable(true);
			typeSelect.setDisable(true);
			sortSelect.setDisable(true);
			sizeSelect.setDisable(true);
			controlBtn.setDisable(true);
		}

		/**
		 * @return returns the Scene of the Interface
		 */
		public Scene getMyScene() {
			paintScene();
			Scene scene = new Scene(appApp, appW, appH);
			scene.setCursor(Cursor.HAND);
			scene.getStylesheets().add(this.getClass().getResource("/view/styles.css").toExternalForm());
			return scene;
		}

		public Button getControlBTN() {
			return controlBtn;
		}
	}

}