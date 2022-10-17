package Logic;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author Pascal
 */
public class LogicServer implements TicTacToeAService {
    private HashMap<String, String> player;
    private ArrayList<String> clients;
    private String [] gameMoves;
    private String logPath;
    private Logger logger;
    private FileHandler fh;
    private SimpleFormatter formatter;
    private int timeout;
    private boolean gameRunning;
    private int gameID;


    /**
     * Constructor
     */
    public LogicServer(int timeout){
        this.timeout = timeout;
        player = new HashMap<String, String>();
        clients = new ArrayList<String>();
        gameMoves = new String[9];
        logPath = System.getProperty("user.dir");
        initLogger();
        gameRunning = false;
        gameID = -1;
        Thread thread = new Thread (() -> {
           while (true) {
               checkGame();
           }
        });
    }

    /**
     * Check if the game is still running and create a new game if the old one is over
     */
    public void checkGame(){
        if (!gameRunning && clients.size() >= 2) {

        }
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
        // Check if the given client is available
        addClientToList(clientName);





        //"Game ID", "Opponent Name", and "First Move"
        return player;
    }
    /**
     *      Returns a Map with three keys:
     *      "Game ID", "Opponent Name", and "First Move"
     *      Each key maps to a String containing the respective value.
     *      * Game ID and Opponent Name can be any reasonable Strings.
     *      * FirstMove is a String from:
     *      ["your_move", "opponent_move", "no_opponent_found"]
     *      */






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
        createLogfile(gameId, "FullUpdate wurde getriggert");
        ArrayList<String> update = new ArrayList<String>();

        for (int i = 0; i < gameMoves.length; i++){
            update.add(gameMoves[i]);
        }

        return update;
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

    /**
     * A client will be added to the list if not available
     * @param clientName Name of the current client
     */
    private void addClientToList(String clientName){
        if (clients.size() == 0){
            clients.add(clientName); //siehe Klassenvariable
        } else {
            boolean clientAvailable = false;

            //check if the client is already available in the list
            for (int i = 0; i < clients.size(); i++){
                if (clients.get(i) == clientName) {
                    clientAvailable = true;
                }
            }

            //Add the new client if not available in the current list
            if(clientAvailable){
                clients.add(clientName);
            }
        }
    }

    /**
     * Initialize the logger to create a logfile during the runtime
     */
    private void initLogger () {
        logger = Logger.getLogger("Logfile");
        try {
            fh = new FileHandler(logPath);
            logger.addHandler(fh);
            formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.setUseParentHandlers(false);
        logger.info("Server started");
    }

    /**
     * Record everything in the logfile
     * @param gameID ID of the current game
     * @param message message
     */
    private void createLogfile (String gameID, String message) {
       // if (clientID != null && message != null) {
       //     logger.info("Client: " + clientID + " hat die folgende Nachricht gesendet:\n" + message + "\n");
       // } else {
       //     logger.info("Client: " + clientID + " hat eine Nachrichtenanfrage gestellt.\n");
       // }
    }

    /**
     * Empty the array for old game moves
     */
    private void emptyGameMoves(){
        Arrays.fill(gameMoves, null);
    }

}
