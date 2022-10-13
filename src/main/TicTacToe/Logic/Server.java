package Logic;
import java.rmi.RemoteException;

/**
 *
 */
public class Server {

    /**
     * Constructor
     */
    public Server () { }

    /**
     * Method is used by the main method to start the server.
     */
    public void serverStart (){
        try {


        } catch (Exception ex){
            System.err.println("Server exception: " + ex.toString());
            ex.printStackTrace();
        }
    }
}
