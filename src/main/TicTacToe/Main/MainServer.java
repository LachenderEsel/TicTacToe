package Main;

import Logic.Server;

/**
 * Entry point to start the server.
 *
 * Prequisites:
 *      The RMI Interface should be started before.
 */
public class MainServer {
    /**
     *
     * @param args
     */
    public static void main (String args[]){
        Server server = new Server();
        server.serverStart();
    }
}
