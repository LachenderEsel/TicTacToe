package Logic;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author Pascal
 */
public class LogicServer implements TicTacToeAService {
    private HashMap<String, String> player;


    /**
     * Constructor
     */
    public LogicServer(){
        player = new HashMap<String, String>();
    }

    /**
     * Server accepts client's game registration and waits for the next
     * client to create a game between them
     * @param clientName Name of the current client
     * @return A map with three keys
     * @throws RemoteException if something went wrong
     */
    @Override
    public HashMap<String, String> findGame(String clientName) throws RemoteException {


        return null;
    }

    /**
     *
     * @param x value of the coordinate x
     * @param y value of the coordinate y
     * @param gameId ID of the current game
     * @return A String based of the action
     * @throws RemoteException if something went wrong
     */
    @Override
    public String makeMove(int x, int y, String gameId) throws RemoteException {
        return null;
    }

    /**
     *
     * @param gameId ID of the current game
     * @return updated Arraylist with all moves in the game
     * @throws RemoteException if something went wrong
     */
    @Override
    public ArrayList<String> fullUpdate(String gameId) throws RemoteException {
        return null;
    }

    /**
     * Decide which player starts in the game randomly.
     * @param client_1 the first client
     * @param client_2 the second client
     * @return the client with the first move
     */
    private String startPlayer(String client_1, String client_2) {
        Random rand = new Random();
        int client1;
        int client2;
        do {
            client1 = rand.nextInt(100);
            client2 = rand.nextInt(100);
        } while (client1 == client2);

        if (client1 < client2){
            return client_2;
        } else {
            return client_1;
        }
    }
}
