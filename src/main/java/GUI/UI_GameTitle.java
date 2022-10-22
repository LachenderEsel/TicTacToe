package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class UI_GameTitle {
    private static StackPane pane;
    private static Label name;
    private static Label id;
    private static TextField nameField;
    private static TextField idField;

    private static Label message;
    private static Button play;
    private static Button update;

    public UI_GameTitle(){

        pane = new StackPane();
        pane.setMinSize(UI_Window.WINDOW_WIDTH, UI_Window.TITLE_HEIGHT);
        pane.setTranslateX(UI_Window.WINDOW_WIDTH / 2);
        pane.setTranslateY(UI_Window.TITLE_HEIGHT / 2);

        name = new Label("Client Name:");
        name.setMinSize(UI_Window.WINDOW_WIDTH, UI_Window.TITLE_HEIGHT);
        name.setFont(Font.font(20));
        name.setAlignment(Pos.TOP_LEFT);
        name.setTranslateY(10);
        name.setTranslateX(10);
        pane.getChildren().add(name);

        id = new Label("GameID:");
        id.setMinSize(UI_Window.WINDOW_WIDTH, UI_Window.TITLE_HEIGHT);
        id.setFont(Font.font(20));
        id.setAlignment(Pos.TOP_RIGHT);
        id.setTranslateY(10);
        id.setTranslateX(-40);
        pane.getChildren().add(id);

        nameField = new TextField("set name");
        nameField.setMinSize(120, 30);
        nameField.setTranslateY(-45);
        nameField.setTranslateX(-80);
        pane.getChildren().add(nameField);

        idField = new TextField("set GameID");
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

        play = new Button("Start");
        play.setMinSize(65,30);
        play.setTranslateY(50);
        play.setTranslateX(-55);
        play.setOnMouseClicked(event -> {
            UI_GameTitle.hideButton();
            UI_GameTitle.updateMessage("X beginns");
            System.out.println("New Game!");
            UI_GameTicTacToe.newGamestart();
        });
        pane.getChildren().add(play);

        update = new Button("Connect ");
        update.setMinSize(85,30);
        update.setTranslateY(50);
        update.setTranslateX(35);
        update.setOnMouseClicked(event -> {
            //connect
        });
        pane.getChildren().add(update);
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
