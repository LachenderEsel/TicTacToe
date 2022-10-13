package Logic;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Pascal
 */
public class LogicServer implements TicTacToeAService {

    /**
     * Constructor
     */
    public LogicServer(){

    }

    /**
     *
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
}
