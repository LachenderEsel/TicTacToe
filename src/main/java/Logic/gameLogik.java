package Logic;

import GUI.UI_GameTicTacToe;

public class gameLogik {

    private char turn = 'X';

    private boolean gameOver = false;

    private String field [][] = new String[2][2];

    public void newGamestart(){
        gameOver = false;
        turn = 'X';

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++){
                field[y][x] = "";
            }
        }
    }

    public void changeTurn(){
        if (turn == 'X') {
            turn = 'O';
        } else {
            turn = 'X';
        }
        // hier müssen wir dem User sagen, dass der Gegner dran ist oder er selbst
        //ui_Game_Title.updateMessage("Now " + turn + " Player");
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
                    if (field[y][x].isEmpty()){
                        System.out.println("nope");
                        return;
                    }
                }
            }
            gameOver = true;

            System.out.println("draw!");

            //Hier müssen die Clients wieder informiert werden
            //ui_Game_Title.updateMessage("It is a draw");
            //ui_Game_Title.showButton();
        }
    }

    private void checkTopRightToBottomLeft() {
        if (!gameOver){
            if (field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].isEmpty()) {
                String winner = field[0][2];
                gameIsOver(winner);
                System.out.println("Won: " + winner);
            }
        }
    }

    private void checkTopLeftToBottomRight() {
        if (!gameOver){
            if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].isEmpty()) {
                String winner = field[0][0];
                gameIsOver(winner);
                System.out.println("Won: " + winner);
            }
        }
    }

    private void checkCols() {
        if (!gameOver) {
            for (int x = 0; x < 3; x++) {
                if (field[0][x].equals(field[1][x]) && field[0][x].equals(field[2][x]) && !field[0][x].isEmpty()) {
                    String winner = field[0][x];
                    gameIsOver(winner);
                    System.out.println("Won: " + winner);
                }
            }
        }
    }

    private void checkRow() {
        if (!gameOver) {
            for (int y = 0; y < 3; y++) {
                if (field[y][0].equals(field[y][1]) && field[y][0].equals(field[y][2]) && !field[y][0].isEmpty()) {
                    String winner = field[y][0];
                    gameIsOver(winner);
                    System.out.println("Won: " + winner);
                }
            }
        }
    }

    private void gameIsOver(String winner){
        gameOver = true;

        //Server informiert Clienten
        //ui_Game_Title.updateMessage("Winner is " + winner);
        //ui_Game_Title.showButton();
    }

}

