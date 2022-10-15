package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


import java.io.IOException;

public class UI_Game {

    private String clientName;
    private String serverName;
    private String gameID;
    Stage stage;
    private UI_GameTitle title;
    private UI_GameTicTacToe game;

    public UI_Game (String clientName, String serverName, String gameID, Stage stage) {
        this.clientName = clientName;
        this.serverName = serverName;
        this.gameID = gameID;
        this.stage = stage;
    }

    public void start() {

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, UI_Window.WINDOW_WIDTH, UI_Window.WINDOW_HEIGHT);
        initLayout(root);
        stage.setScene(scene);
        stage.show();
    }
    
    private void initLayout(BorderPane root){
        initTitle(root);
        initGame(root);
    }

    private void initTitle(BorderPane root) {
        title = new UI_GameTitle();
        title.setButtonOnAction(startNewGame());
        root.getChildren().add(UI_GameTitle.getStackPane());
    }

    private EventHandler<ActionEvent> startNewGame() {
        return new  EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ae){
                UI_GameTitle.hideButton();
                UI_GameTitle.updateMessage("X beginns");
                System.out.println("New Game!");
                game.newGamestart();
            }
        };
    }

    private void initGame(BorderPane root) {
        game = new UI_GameTicTacToe(title);
        root.getChildren().add(game.getStackPane());
    }


}