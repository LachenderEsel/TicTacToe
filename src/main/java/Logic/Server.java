package Logic;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Pascal
 */
public class Server {
    private int timeInSec; //timeout time
    int port;
    private boolean rmiStarted;

    /**
     * Constructor
     */
    public Server (String time) {
        timeInSec = Integer.parseInt(time);
        port = 8080;
        rmiStarted = false;
    }

    /**
     * The server will be started.
     * A new server with a stub will be created and linked to a registry.
     */
    public void serverStart (){
        try {
            //Let the user know that the server will start
            System.out.println("***** Start server *****");

            InetAddress ip = InetAddress.getLocalHost();
            String hostname = ip.getHostName();

            if(!rmiStarted)
            {
                java.rmi.registry.LocateRegistry.createRegistry(port);
                rmiStarted = true;
            }
            java.rmi.registry.Registry registry = java.rmi.registry.LocateRegistry.getRegistry(port);
            try
            {
                registry.unbind("TicTacToeAService");
            }
            catch (Exception e)
            {

            }

            // Create the Server and export the object of the implement class.
            // The remote object "obj" will be exported to the stub
            LogicServer obj = new LogicServer();
            TicTacToeAService stub = (TicTacToeAService) UnicastRemoteObject.exportObject(obj, 8080);

            // Bind the stub in the registry
            //Registry registry = LocateRegistry.getRegistry();
            registry.rebind("TicTacToeAService", stub);
            //System.setProperty(ip.toString(), "141.22.27.107");

            //Let the user know if the Server is ready
            System.err.println("***** The Server with the ip: ''" + ip + "'' and Hostname: ''" + hostname + "'' is ready! *****");

        } catch (RemoteException | UnknownHostException e) {
            System.err.println("***** Server start failed. --> Exception: " + e);
            e.printStackTrace();
        }
    }
}
