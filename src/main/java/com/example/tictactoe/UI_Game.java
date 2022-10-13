package com.example.tictactoe;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class UI_Game {

    StackPane pane;
    UI_main ui_Main;

    public UI_Game(UI_main ui_Main){
        this.ui_Main = ui_Main;
        pane = new StackPane();
        pane.setMinSize(UI_field.WINDOW_WIDTH, UI_field.GAME_HEIGHT);
        pane.setTranslateX(UI_field.WINDOW_WIDTH / 2);
        pane.setTranslateY((UI_field.WINDOW_HEIGHT / 2 ) + UI_field.TITLE_HEIGHT);
    }

    public StackPane getStackPane() {
        return getStackPane();
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
        }
    }
}
