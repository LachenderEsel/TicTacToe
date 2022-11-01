package Logic;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Just a client
 * @author Pascal
 */
public class Client {

    private final int REGPORT = 1099; //registry port

    private int gameID;
    private String clientID;
    private String opponent;
    private String nextPlayer;

    private ArrayList<String> gameMoves;
    private TicTacToeAService ticTacToeService;

    /**
     * Constructor
     */
    public Client ()
    {
        gameMoves = new ArrayList<>();
    }

    /**
     * Connect to a specific host
     * @param hostName Name of the host
     */
    public void connect(String hostName)
    {
        int port = REGPORT;
        System.out.println("Connect to -> " + hostName);
        try {
            Registry registry = LocateRegistry.getRegistry(hostName, port);
            ticTacToeService = (TicTacToeAService) registry.lookup("TicTacToeAService");

            System.out.println("Connected to -> " + hostName);
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    public boolean reJoinGame(int gameID, String clientID)
    {
        this.gameID = gameID;
        this.clientID = clientID;

        try {
            gameMoves = ticTacToeService.fullUpdate(Integer.toString(gameID));
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        String player1 = gameMoves.get(0).substring(0, gameMoves.get(0).indexOf(":"));
        String player2 = gameMoves.get(1).substring(0, gameMoves.get(1).indexOf(":"));
        System.out.println(player1 + " : " + player2); // zur testzwecke
        //if player does not exist
        if(player1.equals(clientID) && player2.equals(clientID))
        {
            System.err.println("Cannot recognize the current player!");
            return false;
        }

        // Rejoin as a waiter or a mover
        if(gameMoves.get(gameMoves.size() -1).substring(0, gameMoves.get(gameMoves.size() -1).indexOf(":")).equals(clientID))
        {
            System.out.println("Rejoined and wait.");
            waitAndUpdateRemote();
        }
        else
        {
            System.out.println("Rejoined and move.");
        }
        return true;
    }   /**
     * find/start a new game
     * @param clientID ID of the client
     * @return true if a new game was started. False if no game was found
     */
    public boolean findGame(String clientID)
    {
        this.clientID = clientID;
        try {
            HashMap<String, String> map = ticTacToeService.findGame(clientID);

            //if no opponent was found
            if(map.get("First Move").equals("no_opponent_found"))
            {
                System.out.println("No opponent was found in 30 seconds.");
                return false;
            }

            gameID = Integer.parseInt(map.get("Game ID"));
            opponent = map.get("Opponent Name");
            nextPlayer = map.get("First Move");

            System.out.println("An valid game was found with the following information.");
            System.out.println("Game ID: " + gameID + ", \nOpponent name: " + opponent + ", \nMove: " + nextPlayer + ".");

            //If opponent needs to play
            if (Objects.equals(nextPlayer, "opponent_move"))
            {
                waitAndUpdateRemote();
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return true;
    }


    /**
     *
     */
    public void waitAndUpdateRemote()
    {
        try
        {
            while (ticTacToeService.fullUpdate(Integer.toString(gameID)).size() <= gameMoves.size())
            {
                Thread.sleep(1000);
            }
            gameMoves = ticTacToeService.fullUpdate(Integer.toString(gameID));
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * Get the current game ID
     * @return Game ID
     */
    public int getGameID()
    {
        return gameID;
    }

    /**
     * Get the current array list with all moves.
     * @return All moves
     */
    public ArrayList<String> getGameMoves()
    {
        return gameMoves;
    }

    /**
     * Adds a new move to the array list with the moves
     * @param x param x
     * @param y param y
     */
    public void addMove(int x, int y)
    {
        gameMoves.add(clientID + ": " + x + "," + y);
    }

    /**
     * make a move
     * @param x coordinate x
     * @param y coordinate y
     * @return state of the client
     */
    public String makeMove(int x, int y)
    {
        try {
            String answer = ticTacToeService.makeMove(x, y, Integer.toString(gameID));

            // If u won the game
            if (answer == "you_win")
            {
                return "You Win";
            }

            //If u lost the game
            if(answer == "you_lose")
            {
                return "You lose";
            }

            if(answer == "invalid_move")
            {
                return "Invalid move";
            }

            if (answer == "opponent_gone")
            {
                clientID = null;
                opponent = null;
                gameID = Integer.parseInt(null);
                gameMoves = null;
                nextPlayer = null;

                return "Timeout";
            }

            // Check current move
            if(x <= 2 && y <= 2 && x >= 0 && y >= 0 && gameID > 0)
            {
                gameMoves.add(clientID + ": " + x + "," + y);
                return "Your Turn";
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return "Why???";
    }
}