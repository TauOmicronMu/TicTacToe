import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Constantly wait for and interpret messages from the given client.
 * 
 * @author TauOmicronMu
 *
 */
public class InFromClient extends Thread {

	private ObjectInputStream fromClient;
	private ConnectedClientData connectedClientData;
	private String clientName;

	public InFromClient(ObjectInputStream fromClient, ConnectedClientData connectedClientData, String clientName) {
		this.fromClient = fromClient;
		this.connectedClientData = connectedClientData;
		this.clientName = clientName;

		this.run();
	}

	public void run() {
		while (true) {
			Message message = null;
			try {
				message = (Message) fromClient.readObject();
			} 
			catch (ClassNotFoundException e) {
				Constants.errorAndEnd("Error reading message from client (ClassNotFound Exception). ");
			} 
			catch (IOException e) {
				Constants.errorAndEnd("Error reading message from client (I/O Exception). ");
			}

			MessageType messageType = message.getMessageCommand();
			switch(messageType) {
			case PLAYERDISCONNECT :
				/*
				 * Remove the player record from the connectedClientData and
				 * forward the PLAYERDISCONNECT message to all other
				 * connected clients.
				 */
				connectedClientData.removeClient((String) message.getData());
				for(String client : connectedClientData.getConnectedClients()) {
					connectedClientData.addClientMessage(client, message);
				}
				break;
			case PLAYERJOINED :
				/*
				 * The server will never receive this message.
				 */
				break;
			case CLIENTLIST :
				/*
				 * The server will never receive this message.
				 */
				break;
			}
		}
	}
}