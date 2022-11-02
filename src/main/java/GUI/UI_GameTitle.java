
package GUI;

import Logic.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.concurrent.ExecutionException;

public class UI_GameTitle {
    private static StackPane pane;
    private static Label name;
    private static Label id;
    private static TextField nameField;
    private static TextField idField;

    private static Label message;
    private static Button play;
    private static Button update;
    private Client client;

    public UI_GameTitle(){

        client = new Client();

        pane = new StackPane();
        pane.setMinSize(UI_Window.WINDOW_WIDTH, UI_Window.TITLE_HEIGHT);
        pane.setTranslateX(UI_Window.WINDOW_WIDTH / 2);
        pane.setTranslateY(UI_Window.TITLE_HEIGHT / 2);

        name = new Label("Set Name:");
        name.setMinSize(UI_Window.WINDOW_WIDTH, UI_Window.TITLE_HEIGHT);
        name.setFont(Font.font(20));
        name.setAlignment(Pos.TOP_LEFT);
        name.setTranslateY(10);
        name.setTranslateX(10);
        pane.getChildren().add(name);

        id = new Label("Set GameID:");
        id.setMinSize(UI_Window.WINDOW_WIDTH, UI_Window.TITLE_HEIGHT);
        id.setFont(Font.font(20));
        id.setAlignment(Pos.TOP_RIGHT);
        id.setTranslateY(10);
        id.setTranslateX(-40);
        pane.getChildren().add(id);

        nameField = new TextField("");
        nameField.setMinSize(120, 30);
        nameField.setTranslateY(-45);
        nameField.setTranslateX(-80);
        pane.getChildren().add(nameField);

        idField = new TextField("");
        idField.setMinSize(120, 30);
        idField.setTranslateY(-45);
        idField.setTranslateX(80);
        pane.getChildren().add(idField);

        message = new Label("Tick Trick Track");
        message.setMinSize(UI_Window.WINDOW_WIDTH, UI_Window.TITLE_HEIGHT);
        message.setFont(Font.font(26));
        message.setAlignment(Pos.CENTER);
        message.setTranslateY(10);
        pane.getChildren().add(message);

        update = new Button("Connect ");
        update.setMinSize(85,30);
        update.setTranslateY(50);
        update.setOnMouseClicked(event -> {
            start();
        });
        pane.getChildren().add(update);
    }

    public void start(){
        updateMessage("Connecting...");
        try {
            client.connect("lab28.mailcluster.haw-hamburg.de/127.0.1.1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (idField.getText().equals("")){
            updateMessage("Looking for a Game...");
            if(client.findGame(nameField.getText())){
                idField.setEditable(false);
                idField.setText(Integer.toString(client.getGameID()));
                client.getGameMoves();
            } else {
                updateMessage("no Game found!");
            }
        }
        updateMessage("Connected");
    }


    public static StackPane getStackPane() {
        return pane;
    }

    public static void updateMessage(String str){
        message.setText(str);
    }

    public void showButton() {
        play.setVisible(true);
    }

    public static void hideButton(){
        play.setVisible(false);
    }
}