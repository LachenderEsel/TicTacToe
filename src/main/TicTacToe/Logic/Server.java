package Logic;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Pascal
 */
public class Server {

    /**
     * Constructor
     */
    public Server () { }

    /**
     * The server will be started.
     * A new server with a stub will be created and linked to a registry.
     */
    public void serverStart (){
        try {
            //Let the user know that the server will start
            System.err.println("***** Start server *****");

            InetAddress ip = InetAddress.getLocalHost();

            // Create the Server and export the object of the implement class.
            // The remote object "serv" will be exported to the stub
            LogicServer serv = new LogicServer();
            TicTacToeAService stub = (TicTacToeAService) UnicastRemoteObject.exportObject(serv, 0);

            // Bind the stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("TicTacToeServer", stub);

            //Let the user know if the Server is ready
            System.err.println("***** The Server with the ip: " + ip + " is ready! *****");

        } catch (UnknownHostException | RemoteException e) {
            System.err.println("***** Server start failed. --> Exception: " + e);
            e.printStackTrace();
        }
    }
}
