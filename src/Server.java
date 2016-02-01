import java.io.*;
import java.net.*;

/**
 * Class to hold all server logic.
 * @author TauOmicronMu
 *
 */
public class Server {

	/**
	 * Run a new server.
	 * @param args No arguments taken.
	 */
	public static void main(String[] args) {
		
		/*
		 * Declare a ServerSocket (default = null), to stop java from
		 * crying.
		 */
		ServerSocket serverSocket = null;
		
		/*
		 * Attempt to initialise the ServerSocket with the port 
		 * defined in Constants.java. Failing this, print an
		 * error and exit.
		 */
		try {
		    serverSocket = new ServerSocket(Constants.PORT);
		}
		catch(Exception e) {
			Constants.errorAndEnd("Unable to start the server.", Constants.SERVER_START_ERROR);
		}
		
		/*
		 * Wait for a client to connect, and then attempt to create a new
		 * socket, input stream and output stream for the client.
		 */
		while(true) {
            try {
				Socket client = serverSocket.accept();
				ObjectInputStream fromClient = new ObjectInputStream(client.getInputStream());
				ObjectOutputStream toClient = new ObjectOutputStream(client.getOutputStream()); 
			} 
            catch (IOException e) {
				Constants.errorAndEnd("Unable to handle client.", Constants.CLIENT_HANDLE_ERROR);
			}
        }
	}
}