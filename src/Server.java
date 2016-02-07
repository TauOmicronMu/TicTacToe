import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Class holding all of the logic for the Server.
 * 
 * USAGE : java Server
 * 
 * @author TauOmicronMu
 *
 */
public class Server {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		//Create a new ConnectedClientData
		ConnectedClientData connectedClients = new ConnectedClientData();
		
		//Attempt to open a ServerSocket on port Constants.PORT.
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(Constants.PORT);
		}
		catch (Exception e) {
			Constants.errorAndEnd("Something went wrong opening the ServerSocket on port : " + Constants.PORT + " .");
		}
	  
		//Wait for a client to connect.
		while(true) {
			Socket client = serverSocket.accept();
		    System.out.println("Client connected.");
			
		    //Debugging 
		    //System.out.println("Client : " + client);
			
		    //Grab the I/O Streams from the client.
			ObjectInputStream fromClient = null;
			ObjectOutputStream toClient = null;
		    
			/*
			 * ALWAYS OPEN OUTPUT BEFORE INPUT BECAUSE IT WON'T WORK OTHERWISE,
			 * BECAUSE JAVA HAS REALLY WEIRD QUIRKS.
			 */
			try {
				System.out.println("Started trying to get IO streams.");
				
				toClient = new ObjectOutputStream(client.getOutputStream());
			    System.out.println("Got ObjectOutputStream.");
			    
				fromClient = new ObjectInputStream(client.getInputStream());
			    System.out.println("Got ObjectInputStream.");
			    
			    /*
			     * Debugging
			     */
			    //toClient.write("Hello".getBytes());
			    //toClient.flush();
			}
			catch (Exception e) {
				Constants.errorAndEnd("Something went wrong opening and flushing the streams to/from the client.");
			}
			
			//Try reading the client's nickname from the input stream.
			String clientName = null;
			try {
			    clientName = (String) fromClient.readObject();
			    System.out.println("Successfully read Client's nickname from stream : " + clientName);
			}
			catch (Exception e) {
			    Constants.errorAndEnd("Error reading clientName in Server - not a string.");
			}

			//Allocate a unique username to the client.
			String finalClientName = Constants.getUnusedUsername(clientName, connectedClients);
			
			//Relay the final username back to the client.
			try {
				toClient.writeObject(finalClientName);
				toClient.flush();
			}
			catch (Exception e) {
				Constants.errorAndEnd("Something went wrong sending finalClientName to the Client.");
			}
			
			System.out.println("Final client name : " + finalClientName);
			
			//Create a new record in connectedClients with the new client's name.
			connectedClients.addNewClient(finalClientName);
	
			
	    	//TODO : Relay a PLAYERJOINED message to all clients except this client.
		   
			//TODO : Relay the current list of connected players to the new client.
			
		    //TODO : Create 2 new threads for sending/receiving to/from the client.
			
		}
 	}
	
	/**
	 * Constantly wait for and interpret messages from the given client.
	 * @author TauOmicronMu
	 *
	 */
	public class InFromClient extends Thread {
		
	}
}
