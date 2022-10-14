package com.example.tictactoe;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class UI_Game {

    private StackPane pane;
    private UI_Title ui_Title;

    private Tile[][] tiles = new Tile[3][3];

    private char turn = 'X';
    private boolean gameOver = false;

    public UI_Game(UI_Title ui_Title){
        this.ui_Title = ui_Title;
        pane = new StackPane();
        pane.setMinSize(UI_field.WINDOW_WIDTH, UI_field.GAME_HEIGHT);
        pane.setTranslateX(UI_field.WINDOW_WIDTH / 2);
        pane.setTranslateY((UI_field.GAME_HEIGHT / 2 ) + UI_field.TITLE_HEIGHT);

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

    public void newGamestart(){
        gameOver = false;
        turn = 'X';
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++){
                tiles[y][x].setValue(" ");
            }
        }
    }

    public void changeTurn(){
        if (turn == 'X') {
            turn = 'O';
        } else {
            turn = 'X';
        }
        ui_Title.updateMessage("Now " + turn + " Player");

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
            checkRow();
            checkCols();
            checkTopLeftToBottomRight();
            checkTopRightToBottomLeft();
            checkDraw();
        }

        private void checkDraw() {
            if (!gameOver){
                for (int y = 0; y < 3; y++) {
                    for (int x = 0; x < 3; x++){
                        if (tiles[y][x].getValue().isEmpty()){
                            return;
                        }
                    }
                }
            }
            gameOver = true;
            ui_Title.updateMessage("It is a draw");
            ui_Title.showButton();
        }

        private void checkTopRightToBottomLeft() {
            if (!gameOver){
                if (tiles[0][2].getValue().equals(tiles[1][1].getValue()) && tiles[0][2].getValue().equals(tiles[2][0].getValue()) && !tiles[0][2].getValue().isEmpty()) {
                    String winner = tiles[0][2].getValue();
                    gameIsOver(winner);
                }
            }
        }

        private void checkTopLeftToBottomRight() {
            if (!gameOver){
                if (tiles[0][0].getValue().equals(tiles[1][1].getValue()) && tiles[0][0].getValue().equals(tiles[2][2].getValue()) && !tiles[0][0].getValue().isEmpty()) {
                    String winner = tiles[0][0].getValue();
                    gameIsOver(winner);
                }
            }
        }

        private void checkCols() {
            if (!gameOver) {
                for (int x = 0; x < 3; x++) {
                    if (tiles[0][x].getValue().equals(tiles[1][x].getValue()) && tiles[0][x].getValue().equals(tiles[2][x].getValue()) && !tiles[0][x].getValue().isEmpty()) {
                        String winner = tiles[0][x].getValue();
                        gameIsOver(winner);
                    }
                }
            }
        }

        private void checkRow() {
            if (!gameOver) {
                for (int y = 0; y < 3; y++) {
                    if (tiles[y][0].getValue().equals(tiles[y][1].getValue()) && tiles[y][0].getValue().equals(tiles[y][2].getValue()) && !tiles[y][0].getValue().isEmpty()) {
                        String winner = tiles[y][0].getValue();
                        gameIsOver(winner);
                    }
                }
            }
        }

        private void gameIsOver(String winner){
            gameOver = true;
            ui_Title.updateMessage("Winner is " + winner);
            ui_Title.showButton();
        }

        public StackPane getStackPane() {
            return pane;
        }

        public String getValue(){
            return label.getText();
        }

        public void setValue(String value){
            label.setText(value);
        }

    }
}
