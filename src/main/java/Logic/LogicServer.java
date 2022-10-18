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

    private ArrayList<String> clients;
    private String [] gameMoves;
    private String logPath;
    private Logger logger;
    private FileHandler fh;
    private SimpleFormatter formatter;
    private int timeout;

    private int gameID;
    private GameLogic game;


    /**
     * Constructor
     */
    public LogicServer(int timeout){
        this.timeout = timeout;
        clients = new ArrayList<String>();
        gameMoves = new String[9];
        logPath = System.getProperty("user.dir");
        initLogger();
        gameID = 0;
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
        addClientToList(clientName);
        HashMap<String, String> map = new HashMap<>();


        if(game == null)
        {
            gameID++;
            game = new GameLogic(gameID, clientName, startPlayer());
            try{
                game.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }

            map.put("Game ID", game.getGameID());
            map.put("Opponent name", game.getClient(1));
            map.put("First Move", firstMove(clientName));

        }
        else
        {
            map.put("Game ID", game.getGameID());
            map.put("Opponent name", game.getClient(0));
            map.put("First Move", firstMove(clientName));
            game.addClient(clientName);
            game.notify();
        }
        return map;
    }

    /**
     *
     * @param clientName
     * @return
     */
    private String firstMove(String clientName)
    {
        if (game.getClient(startPlayer()) == clientName)
        {
            return "your_move";
        }
        return "opponent_move";
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
        createLogfile(gameId, "FullUpdate wurde getriggert");
        ArrayList<String> update = new ArrayList<String>();

        for (int i = 0; i < gameMoves.length; i++){
            update.add(gameMoves[i]);
        }

        return update;
    }

    /**
     * Decide which player starts in the game randomly.
     * @return the client with the first move
     */
    private int startPlayer() {
        Random rand = new Random();
        return rand.nextInt(2);
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
