/**
 package GUI;
 import javafx.application.Application;
 import javafx.application.Platform;
 import javafx.geometry.Insets;
 import javafx.scene.Scene;
 import javafx.scene.control.Button;
 import javafx.scene.control.Label;
 import javafx.scene.control.TextField;
 import javafx.scene.layout.HBox;
 import javafx.scene.layout.StackPane;
 import javafx.scene.layout.VBox;
 import javafx.scene.text.Font;
 import javafx.stage.Stage;
 public class UI_signIn extends Application{
 private final String font;
 private final int fontSizeHeader;
 private String client;
 /**
 * Constructor
 public UI_signIn() {
 font = "Arial";
 fontSizeHeader = 20;
 }
 public void stop() {
 System.out.println("Programm wurde beendet.");
 }
 public void init() {
 System.out.println("Programm wurde gestartet.");
 }
 public void start(Stage primaryStage) {
 primaryStage.setTitle("Distributed systems");
 StackPane pane = new StackPane();
 final Label labelClientName = new Label("Set name");
 labelClientName.setFont(new Font(font, fontSizeHeader));
 TextField nameField = new TextField();
 nameField.setPrefWidth(350);
 nameField.setPromptText("Geben Sie einen Namen ein");
 final Label lableServerName = new Label("Set server name");
 lableServerName.setFont(new Font(font, fontSizeHeader));
 TextField serverField = new TextField();
 serverField.setPrefWidth(350);
 serverField.setPromptText("Geben Sie einen Servernamen ein");
 final Label labelGameID = new Label("Vorhandene Game ID eintragen");
 labelGameID.setFont(new Font(font, fontSizeHeader));
 TextField gameID = new TextField();
 gameID.setPrefWidth(350);
 gameID.setPromptText("Geben Sie eine Game ID ein");
 Button ok_button = new Button();
 ok_button.setText("OK");
 ok_button.setOnAction( ok -> {
 try {
 //get values from text fields
 String clientName = nameField.getText();
 String serverName = serverField.getText();
 String idGame = gameID.getText();
 //create a new stage
 Stage secondStage = new Stage();
 //Check if the required fields are filled
 if (!clientName.isEmpty() && !serverName.isEmpty()) {
 UI_Game client = new UI_Game(clientName, serverName, idGame, secondStage);
 client.start();
 //primaryStage.close();
 }
 } catch (Exception e) {
 System.err.println("Exception: " + e);
 e.printStackTrace();
 }
 });
 Button closeButton = new Button();
 closeButton.setText("Close");
 closeButton.setOnAction(closeProgramm -> {
 Platform.exit();
 });
 HBox buttons = new HBox();
 buttons.getChildren().addAll(ok_button, closeButton);
 buttons.setSpacing(20);
 VBox vertical = new VBox();
 vertical.getChildren().addAll(labelClientName, nameField, lableServerName, serverField, labelGameID, gameID, buttons);
 vertical.setPadding(new Insets(15, 15, 15, 15));
 vertical.setSpacing(15);
 pane.getChildren().addAll(vertical);
 primaryStage.setScene(new Scene(pane));
 primaryStage.show();
 }
 public void main() {
 Application.launch();
 }
 }
 */