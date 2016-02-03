import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Class holding all of the logic for the client.
 * 
 * USAGE : java Client nickname hostname port
 * 
 * >> Check if args is the correct length,
 * >> Attempt to open a socket to hostname:port,
 * >> If the socket is successfully opened, create 
 *    input and output streams to/from the server.
 * >> Spin up two threads (ClientSender & ClientReceiver).
 * 
 * @author TauOmicronMu
 *
 */
public class Client {
 
	public static void main(String[] args) {
		
		/*
		 * Check that args is the right length.
		 * If it isn't, tell the user and exit the program.
		 */
		if(args.length != 3){
		    Constants.errorAndEnd("Invalid usage of Client. Correct Usage: java Client nickname hostname port");
		}
		
		String nickname = args[0];
		String hostname = args[1];
		
		/*
		 * Initialises port and assigns a default value to the port to stop Java from crying.
		 */
		int port = 4444;
		
		try {
		    port = Integer.parseInt(args[2]);   	
		}
		catch(Exception e) {
			Constants.errorAndEnd("Invalid port - Ports must be integers.");
		}
		
		Socket socket = null;
		
		try {
			socket = new Socket(hostname, port);
		}
		catch(IOException e) {
			Constants.errorAndEnd("Something went wrong opening the socket to the server (IO Exception). Please try again.");
		}
		
		ObjectOutputStream toServer = null;
		ObjectInputStream fromServer = null;
		
		try {
			toServer = new ObjectOutputStream(socket.getOutputStream());
			fromServer = new ObjectInputStream(socket.getInputStream());
		}
		catch(IOException e){
			Constants.errorAndEnd("Something went wrong opening the I/O Streams (I/O Exception). Please try again.");
		}
	}
}
