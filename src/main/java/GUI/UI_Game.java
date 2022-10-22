package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


import java.io.IOException;

public class UI_Game extends Application {
    private String clientName;
    private String serverName;
    private String gameID;
    Stage stage;
    private UI_GameTitle title;
    private UI_GameTicTacToe game;

    public void start(Stage stage) {

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
        root.getChildren().add(UI_GameTitle.getStackPane());
    }

    private void initGame(BorderPane root) {
        game = new UI_GameTicTacToe(title);
        root.getChildren().add(game.getStackPane());
    }

    public void main() {
        Application.launch();
    }
}