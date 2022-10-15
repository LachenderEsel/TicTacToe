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
    private final int fontSize;
    private final int fontSizeHeader;
    //private GUIClient guiClient;
    private String client;

    /**
     * Constructor
     */
    public UI_signIn() {
        font = "Arial";
        fontSize = 12;
        fontSizeHeader = 20;
        //client = new Client();
    }

    /**
     *
     * @throws Exception
     */
    public void stop() throws Exception {
        System.out.println("Programm wurde beendet.");
    }

    /**
     *
     * @throws Exception
     */
    public void init() throws Exception {
        System.out.println("Programm wurde gestartet.");
    }


    /**
     * .
     * @param
     */
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Distributed systems");
        StackPane pane = new StackPane();

        /* Set label */
        final Label labelClientName = new Label("Set name");
        labelClientName.setFont(new Font(font, fontSizeHeader));

        /* Textfield */
        TextField nameField = new TextField();
        nameField.setPrefWidth(350);
        nameField.setPromptText("Geben Sie einen Namen ein");

        /* Set label */
        final Label lableServerName = new Label("Set server name");
        lableServerName.setFont(new Font(font, fontSizeHeader));

        /* Textfield */
        TextField serverField = new TextField();
        nameField.setPrefWidth(350);
        nameField.setPromptText("Geben Sie einen Servernamen ein");

        /* Set label */
        final Label labelGameID = new Label("Vorhandene Game ID eintragen");
        labelGameID.setFont(new Font(font, fontSizeHeader));

        /* Textfield */
        TextField GameID = new TextField();
        nameField.setPrefWidth(350);
        nameField.setPromptText("Geben Sie eine Game ID ein");

        /* Buttons */
        Button ok_button = new Button();
        ok_button.setText("OK");
        ok_button.setOnAction( ok -> {
            try {/*
                String clientID = nameField.getText() + "@" + InetAddress.getLocalHost().getHostName();
                client.setClientID(clientID);
                String serverName = serverField.getText();
                client.setServername(serverName);
                guiClient = new GUIClient(font, fontSize, fontSizeHeader, client);
                guiClient.startClient(new Stage());
                //primaryStage.close();
                */
            } catch (Exception e) {
                System.err.println("Exception: " + e.toString());
                e.printStackTrace();
            }
        });

        /* Button exit */
        Button closeButton = new Button();
        closeButton.setText("Close");
        closeButton.setOnAction(closeProgramm -> {
            Platform.exit();
        });

        HBox buttons = new HBox();
        buttons.getChildren().addAll(ok_button, closeButton);
        buttons.setSpacing(20);

        VBox vertical = new VBox();
        vertical.getChildren().addAll(labelClientName, nameField, lableServerName, serverField, labelGameID, GameID, buttons);
        vertical.setPadding(new Insets(15, 15, 15, 15));
        vertical.setSpacing(15);

        pane.getChildren().addAll(vertical);
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }

    /**
     * .
     * Dies ist ein Test
     */
    public void main() {
        Application.launch();
    }

}
