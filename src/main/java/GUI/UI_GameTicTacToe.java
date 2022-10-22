package GUI;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class UI_GameTicTacToe {

    private StackPane pane;
    private UI_GameTitle ui_Game_Title;

    private Tile[][] tiles = new Tile[3][3];


    private char turn = 'X';
    private boolean gameOver = false;

    public UI_GameTicTacToe(UI_GameTitle ui_Game_Title){
        this.ui_Game_Title = ui_Game_Title;
        pane = new StackPane();
        pane.setMinSize(UI_Window.WINDOW_WIDTH, UI_Window.GAME_HEIGHT);
        pane.setTranslateX(UI_Window.WINDOW_WIDTH / 2);
        pane.setTranslateY((UI_Window.GAME_HEIGHT / 2 ) + UI_Window.TITLE_HEIGHT);

        addAllTiles();
    }

    private void addAllTiles() {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++){
                Tile tile = new Tile();
                tile.getStackPane().setTranslateX((x * 100) - 100);
                tile.getStackPane().setTranslateY((y * 100) - 100);
                pane.getChildren().add(tile.getStackPane());
                tiles[y][x] = tile;
            }
        }
    }

    public static void newGamestart(){
        //getten wir vom Server
    }

    public void changeTurn(){
        if (turn == 'X') {
            turn = 'O';
        } else {
            turn = 'X';
        }
        ui_Game_Title.updateMessage("Now " + turn + " Player");
    }

    public StackPane getStackPane() {
        return pane;
    }

    private class Tile{

        private StackPane pane;
        private Label label;

        public Tile(){
            pane = new StackPane();
            pane.setMinSize(100,100);

            Rectangle border = new Rectangle();
            border.setWidth(100);
            border.setHeight(100);
            border.setFill(Color.TRANSPARENT);
            border.setStroke(Color.BLACK);
            pane.getChildren().add(border);

            label = new Label("");
            label.setAlignment(Pos.CENTER);
            label.setFont(Font.font(24));
            pane.getChildren().add(label);

            pane.setOnMouseClicked(event -> {
                if (label.getText().isEmpty() && !gameOver) {
                    label.setText(getTurn());
                    changeTurn();
                    checkWinner();
                }
            });
        }

        public String getTurn(){
            return String.valueOf(turn);
        }

        public void checkWinner(){
            //server sagt uns was los ist
        }

        public StackPane getStackPane() {
            return pane;
        }

    }
}
