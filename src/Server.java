import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Class holding all of the logic for the Server.
 * 
 * USAGE : java Server
 * 
 * >> Create an instance of ConnectedClientData. >> Attempt to open a
 * ServerSocket, which should loop forever and poll new clients. >> When a new
 * Client connects, create two threads (ServerSender and ServerReceiver), and
 * save the instances of these, alongside the other Client data, in a ClientData
 * object, which should be added to a record inside of ConnectedClientData.
 * 
 * @author TauOmicronMu
 *
 */
public class Server {

	public static void main(String[] args) {
		ConnectedClientData connectedClientData = new ConnectedClientData();
	
		ServerSocket serverSocket = null;
	
		try {
			serverSocket = new ServerSocket(Constants.PORT);
		} 
		catch(IOException e) {
			Constants.errorAndEnd("Something went wrong opening the ServerSocket (IO Exception). Please try again.");
		}
		
		while(true) {
			try {
		        Socket client = serverSocket.accept();
			}
			catch(IOException e) {
				Constants.errorAndEnd("Something went wrong accepting a client connection (IO Exception). Please try again.");
			}
		}
	}
}
