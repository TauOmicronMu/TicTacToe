import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;


/**
 * Class holding all of the logic for the client.
 * 
 * USAGE : java Client nickname hostname port
 * 
 * >> Check if args is the correct length,
 * >> Attempt to open a socket to hostname:port,
 * >> If the socket is successfully opened, create 
 *    input and output streams to/from the server.
 * >> Sends the requested nickname to the server.
 * >> Receives a final nickname from the server.
 * 
 * @author TauOmicronMu
 *
 */
public class Client {

	public static void main(String[] args) {
		//Keep track of the state of the client.
		ClientState clientState = new ClientState(clientList);
		
	    //Check correct usage.
		if(args.length != 3) {
	    	Constants.errorAndEnd("Invalid usage of args, Usage : java Client nickname hostname port");
	    }	    
		
		Socket socket = null;
		//Attempt to open a socket to the server at hostname:port
		try {
		    socket = new Socket(args[1], Integer.parseInt(args[2]));
		}
		catch (Exception e) {
			Constants.errorAndEnd("Something went wrong opening a socket. Was your port an integer?");
		}
		
		ObjectInputStream fromServer = null;
		ObjectOutputStream toServer = null;
		//Attempt to open an input and output stream to/from the server.
		/*
		 * ALWAYS OPEN OUTPUT BEFORE INPUT BECAUSE IT WON'T WORK OTHERWISE,
		 * BECAUSE JAVA HAS REALLY WEIRD QUIRKS.
		 */
		try {
			System.out.println("Started opening I/O Streams.");
		
			toServer = new ObjectOutputStream(socket.getOutputStream());
			System.out.println("Opened ObjectOutputStream");
			
			fromServer = new ObjectInputStream(socket.getInputStream());
			System.out.println("Opened ObjectInputStream");
			
			toServer.flush();
		}
		catch (Exception e){
		    Constants.errorAndEnd("Something went wrong opening and flushing the I/O Stream.");
		}
		
		//Attempt to send our nickname to the server. The server will respond to us, telling us what our nickname is.
		try {
			toServer.writeObject(args[0]);
			System.out.println("Wrote nickname to Output Stream.");
		} 
		catch (IOException e) {
			Constants.errorAndEnd("Something went wrong sending the nickname to the server.");
		}
		
		//Try to read back and store my final username.
		String finalClientName = null;
		try {
	        finalClientName = (String) fromServer.readObject();
	        System.out.println("My final username is : " + finalClientName );
		}
		catch (Exception e) {
			Constants.errorAndEnd("Error receiving final username from Server.");
		}
		
		//Create and start an OutToServer thread that handles all user input and data flow to the Server.
		
		OutToServer toServerThread = new OutToServer(clientList, toServer);
		
		toServerThread.start();
		
		/*
		 * Handles all input from the server.
		 */
		while(true); //Sit the client here for a while...
	}
	
	/**
	 * Shared resource to keep track of things required by the Client and all associated Threads.
	 * @author TauOmicronMu
	 *
	 */
	public class ClientState {
		private boolean inGame;
		private boolean myTurn;
		private ArrayList<String> clientList;
		
		public ClientState() {
			this.setInGame(false);
			this.setMyTurn(false);
			
			this.clientList = new ArrayList<String>();
		}

		public boolean isInGame() {
			return inGame;
		}

		public void setInGame(boolean inGame) {
			this.inGame = inGame;
		}

		public boolean isMyTurn() {
			return myTurn;
		}

		public void setMyTurn(boolean myTurn) {
			this.myTurn = myTurn;
		}

        public void addToList(String client) {
        	this.clientList.add(client);
        }
        
        public void removeFromList(String client) {
        	this.clientList.remove(this.clientList.indexOf(client));
        }
        
        public ArrayList<String> getClients() {
        	return this.clientList;
        }
		
	}
	
    /**
     * Keeps track of client input, and sends these to the server.
     * @author TauOmicronMu
     *
     */
	public class OutToServer extends Thread {
		
		private ClientState clientState;
		private ObjectOutputStream toServer;
		
		public OutToServer(ClientState clientState, ObjectOutputStream toServer) {
			this.clientState = clientState;
			this.toServer = toServer;
			
			this.run();
		}
		
		public void run() {
			/*
			 * >> Wait for client input
			 * >> Depending on input, either:
			 * >> >> Perform a local task (E.g. list)
			 * >> >> Send data to server (E.g. playwith)
			 */
		}
	}
	
	
	
	
	
	
	
	/*
	public static class ConnectedClientList extends JFrame {
		private JPanel backPlate;
		private JList clientList;
		private ArrayList<String> clients;
		
		public ConnectedClientList(ArrayList<String> clients) {
			this.clients = clients;
			setTitle("TicTacToe Lobby");
			//TODO : Set size to fit.
			setBackground(Color.BLUE);
			
			backPlate = new JPanel();
			backPlate.setLayout( new BorderLayout() );
			getContentPane().add(this.backPlate);
			
			String[] connectedClientArray = new String[this.clients.size()];
			connectedClientArray = (String[]) this.clients.toArray();
			
			clientList = new JList<String>(connectedClientArray);
			backPlate.add(clientList, BorderLayout.CENTER);
		}
	}
	*/
	
}
