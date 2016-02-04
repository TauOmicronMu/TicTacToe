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
		        
		        ObjectInputStream fromClient = new ObjectInputStream(client.getInputStream());
		        
		        //Get the client's name.
		        String clientName = fromClient.readUTF();
		        
		        //TODO : Check whether or not the name is in use, and if it is, 
		        //       generate a number recursively, and append it to the name.
		    
		        //TODO : Create 2 threads for the client.
		        ServerSender toClient = new ServerSender();
		        ServerReceiver fromClient = new ServerReceiver();
		        
		        //Create a "Record" in ConnectedClientData for the new client.
		        connectedClientData.addNewClient(clientName, toClient, fromClient);
		    
			}
			catch(IOException e) {
				Constants.errorAndEnd("Something went wrong accepting a client connection (IO Exception). Please try again.");
			}
		}
	}
	
	/**
	 * Continuously reads from the clientdata for a particular client,
	 * forwarding each Message to the client in order.
	 * @author TauOmicronMu
	 *
	 */
	public static class ServerSender {
		
		private ClientData clientdata;
		private ObjectOutputStream client;
		
		public ServerSender(ClientData clientData, ObjectOutputStream client) {
			this.clientdata = clientdata;
			this.client = client;
		}
		
		public void run() {
			while(true) {
				Message message = this.clientdata.getFirstMessage();
				client.writeObject(message);
			}
		}
	}
	
	/**
	 * Gets messages from client and puts them in a queue, for another
	 * thread to forward to the appropriate client.
	 * @author TauOmicronMu
	 *
	 */
	public static class ServerReceiver {
	    
		private String myClientsName;
		private ObjectInputStream client;
		private ConnectedClientData clientData;
		
		public ServerReceiver(String myClientsName, ObjectInputStream client, ConnectedClientData clientData) {
			this.myClientsName = myClientsName;
			this.client = client;
			this.clientData = clientData;
		}
		
		public void run() {
			try {
				// Read the next message in the input stream to the server.
				Message message = this.client.readObject();
				// If the message is null, kill the process.
				if(message != null){
					//TODO : Handle messages here.
				}
				else {
					client.close();
					Constants.errorAndEnd("Message was null in input stream for " + myClientsName);
				}
			}
			catch (IOException e) {
				Constants.errorAndEnd("Something went wrong with the client (I/O Exception).");
			}
		}
	}
}
