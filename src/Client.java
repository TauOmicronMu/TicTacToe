import java.io.*;
import java.net.*;

/**
 * Class to hold all Client logic. 
 * @author TauOmicronMu
 *
 */
public class Client {

	/**
	 * Run a new client. 
	 * @param args To be specified on run: nickname - the nickname for the user; hostname - the server to connect to.
	 *
	 */
	public static void main(String[] args) {
		
		/*
		 * Checks correct usage. If the usage is wrong,
		 * notify the user and exit.
		 */
		if(args.length != 3) {
			Constants.errorAndEnd("Usage : java Client nickname hostname port", Constants.CLIENT_ARGS_ERROR);
		}
		
		/*
		 * Get the information from args. They shouldn't change, so make them final.
		 */
		final String nickname = args[0];
		final String hostname = args[1];
		
		/*
		 * Give a default port because otherwise java will cry about it.
		 */
		int port = Constants.PORT; 
		
		try {
			port = Integer.parseInt(args[2]);
		}
		catch (Exception e) {
			Constants.errorAndEnd(args[2] + " is not a valid port.", Constants.INVALID_PORT_ERROR);
		}
		
		Socket server;
		
		/*
		 * Attempt to connect to the server, and open the input and output streams.
		 * If the host doesn't exist, or isn't running, throw the corresponding errors 
		 * to the user and exit.
		 */
		try {
		    server = new Socket(hostname, port);
		    ObjectInputStream fromServer = new ObjectInputStream(server.getInputStream());
		    ObjectOutputStream toServer = new ObjectOutputStream(server.getOutputStream());	
	    }
		catch (UnknownHostException e) {
			Constants.errorAndEnd("Unknown host: " + hostname, Constants.UNKNOWN_HOST_ERROR);
		}
		catch (IOException e) {
			Constants.errorAndEnd("The server doesn't seem to be running.", Constants.SERVER_NOT_RUNNING_ERROR);
		}
		    
		/*
		 * TODO: send a packet to the server and 'connect' the client.
		 */
	}
}
