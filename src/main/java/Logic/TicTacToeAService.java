package Logic;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.HashMap;
import java.rmi.RemoteException;

/**
 *
 * @author  Pascal
 */
public interface TicTacToeAService extends Remote {
    /**
     *      Returns a Map with three keys:
     *      "Game ID", "Opponent Name", and "First Move"
     *      Each key maps to a String containing the respective value.
     *      * Game ID and Opponent Name can be any reasonable Strings.
     *      * FirstMove is a String from:
     *      ["your_move", "opponent_move", "no_opponent_found"]
     *      Steps:
     *      - if game is waiting for player:
     *      * assign player to game
     *      * randomly chose who begins
     *      * write info into state
     *      * notify both players and return the Triplet
     *      - else:
     *      * create a new game in a "waiting-for-player" state
     *      > block on condition variable
     *      > if timeout:
     *      - return [0, "", "no_opponent_found"]
     *      > if opponent joins:
     *      - read data from state
     *      - return [GameId, Name, FirstMove]
     * @param clientName Name of the current client
     * @return a found game
     * @throws RemoteException if something went wrong with the stub
     */
    HashMap<String, String> findGame(String clientName) throws RemoteException;


    /**
     *      Returns a String from ["game_does_not_exist", "invalid_move", "opponent_gone", "you_win", "you_lose", "x,y"]
     *      Grid: 0,0 is on the top left
     *      Steps:
     *      - if game with `gameId` exists:
     *      * if move is invalid:
     *      > "invalid_move"
     *      * if move ends game:
     *      > return "you_win" | "you_lose"
     *      * else:
     *      > signal condition variable
     *      > block on condition variable
     *      > if timeout:
     *      - return "opponent_gone"
     *      > if opponent makes a move:
     *      - return "x,y"
     *      - else:
     *      * return "game_does_not_exist"
     * @param x value of the coordinate x
     * @param y value of the coordinate y
     * @param gameId ID of the current game
     * @return A String based of the action
     * @throws RemoteException if something went wrong with the stub
     */
    String makeMove(int x, int y, String gameId) throws RemoteException;


    /**
     *      Returns a list with all moves in the game with ID gameId.
     *      Each String has the pattern "name: x,y".
     * @param gameId ID of the current game
     * @return updated Arraylist with all moves in the game
     * @throws RemoteException if something went wrong with the stub
     */
    ArrayList<String> fullUpdate(String gameId) throws RemoteException;
}
