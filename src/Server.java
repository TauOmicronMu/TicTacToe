import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

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
			System.out.println("Client : " + client);
		    //Grab the I/O Streams from the client.
			ObjectInputStream fromClient = null;
			ObjectOutputStream toClient = null;
		    
			try {
				System.out.println("Started trying to get IO streams.");
				toClient = new ObjectOutputStream(client.getOutputStream());
			    System.out.println("Got ObjectOutputStream.");
				fromClient = new ObjectInputStream(client.getInputStream());
			    System.out.println("Got ObjectInputStream.");
			    toClient.write("Hello".getBytes());
			    toClient.flush();
			}
			catch (Exception e) {
				Constants.errorAndEnd("Something went wrong opening and flushing the streams to/from the client.");
			}
			
			String clientName = null;
			try {
			    clientName = (String) fromClient.readObject();
			    System.out.println("Successfully read Client's nickname from stream : " + clientName);
			}
			catch (Exception e) {
			    Constants.errorAndEnd("Error reading clientName in Server - not a string.");
			}

			String finalClientName = Constants.getUnusedUsername(clientName, connectedClients);
			
			System.out.println("Final client name : " + finalClientName);
			
			connectedClients.addNewClient(finalClientName);
		}
 	}	
}
