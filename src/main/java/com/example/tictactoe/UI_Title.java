package com.example.tictactoe;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

public class UI_Title {
    private static StackPane pane;
    private static Label message;
    private static Button play;

    public UI_Title(){
        pane = new StackPane();
        pane.setMinSize(UI_field.WINDOW_WIDTH, UI_field.TITLE_HEIGHT);
        pane.setTranslateX(UI_field.WINDOW_WIDTH / 2);
        pane.setTranslateY(UI_field.TITLE_HEIGHT / 2);

        message = new Label("Tick Trick Track");
        message.setMinSize(UI_field.WINDOW_WIDTH, UI_field.TITLE_HEIGHT);
        message.setFont(Font.font(26));
        message.setAlignment(Pos.CENTER);
        message.setTranslateY(-20);
        pane.getChildren().add(message);

        play = new Button("Start");
        play.setMinSize(135, 30);
        play.setTranslateY(20);
        pane.getChildren().add(play);
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

    public void setButtonOnAction(EventHandler<ActionEvent> onAction) {
        play.setOnAction(onAction);
    }
}
