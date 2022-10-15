package Main;

import Logic.Server;

/**
 * Entry point to start the server.
 * Prequisites:
 *      The RMI Interface should be started before.
 * @author Pascal
 */
public class MainServer {
    /**
     * Start the server of the tic tac toe game.
     * @param args arguments
     */
    public static void main (String args[]){
        Server server = new Server();
        server.serverStart();
    }
}
