package Main;

import GUI.UI_Game;


/**
 *
 * @author Pascal
 */
public class MainClient {
    /**
     * Start the client of the tic tac toe game.
     * @param args arguments
     */
    public static void main (String args[]){
        UI_Game client = new UI_Game();
        client.main();
    }
}