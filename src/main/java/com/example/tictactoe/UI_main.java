package com.example.tictactoe;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


import java.io.IOException;

public class UI_main extends Application {

    private UI_Title title;
    private UI_Game game;
    @Override
    public void start(Stage stage) throws IOException {
        System.out.println("test");

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, UI_field.WINDOW_WIDTH, UI_field.WINDOW_HEIGHT);
        initLayout(root);
        stage.setScene(scene);
        stage.show();
    }
    
    private void initLayout(BorderPane root){
        initTitle(root);
        initGame(root);
    }

    private void initTitle(BorderPane root) {
        title = new UI_Title();
        title.setButtonOnAction(startNewGame());
        root.getChildren().add(UI_Title.getStackPane());
    }

    private EventHandler<ActionEvent> startNewGame() {
        return new  EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ae){
                UI_Title.hideButton();
                UI_Title.updateMessage("Game Started");
                game.newGamestart();
            }
        };
    }

    private void initGame(BorderPane root) {
        game = new UI_Game(title);
        root.getChildren().add(game.getStackPane());
    }

    public static void main(String[] args) {
        launch();
    }
}