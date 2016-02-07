import java.util.ArrayList;
import java.util.HashMap;

/**
 * Hash map holding nickname : ClientData.
 * @author TauOmicronMu
 */
public class ConnectedClientData {
    
	/**
	 * Map containing key : value pairs corresponding to:
	 * E.g.
	 * 
	 * Nickname | Client Data
	 * ----------------------
	 *          |
	 *          |
	 *          |
	 *          |
	 */
	private HashMap<String, ClientData> connectedClients;
	
	/**
	 * Creates a new instance of the ConnectedClientData class.
	 */
	public ConnectedClientData() {
	    this.connectedClients = new HashMap<>();	
	}
	
	/**
	 * Returns the ClientData stored for a given nickname.
	 * @param nickname The nickname of the client.
	 * @return The ClientData stored for the client with the given nickname.
	 */
	public ClientData getClientData(String nickname) {
		return this.connectedClients.get(nickname);
	}
	
	/**
	 * Increments the score of the client specified by the nickname.
	 * @param nickname Increments the score of the client specified by the nickname.
	 */
	public synchronized void incrementClientScore(String nickname) {
	    this.connectedClients.get(nickname).incrementScore();
	}
	
	/**
	 * Adds the message, message, to the queue of the client specified by the nickname.
	 * @param nickname The nickname of the client.
	 * @param message The message to add to the client's queue.
	 */
	public synchronized void addClientMessage(String nickname, Message message) {
		this.connectedClients.get(nickname).addMessage(message);
	}
	
	public synchronized Message getFirstClientMessage(String nickname) {
		return this.connectedClients.get(nickname).getFirstMessage();
	}
	
	/**
	 * Creates a new blank client record.
	 * @param nickname The nickname of the new client.
	 */
	public void addNewClient(String nickname) {
		for(String name : getConnectedClients()) {
			if(nickname == name) {
				Constants.errorAndEnd("Server Error : Attempted to create a duplicate record.");
			}
		}
		this.connectedClients.put(nickname, new ClientData());
	}
	
	/**
	 * Return an ArrayList containing the nicknames of all connected clients.
	 * @return An ArrayList<String> containing the nicknames of all connected clients.
	 */
	public ArrayList<String> getConnectedClients() {
		ArrayList<String> currentlyConnected = new ArrayList<String>();
		for(String nickname : this.connectedClients.keySet()) {
			currentlyConnected.add(nickname);
		}
		return currentlyConnected;
	}
	
}
