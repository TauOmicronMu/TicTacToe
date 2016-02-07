import java.io.IOException;
import java.io.ObjectOutputStream;

/**
	 * Relay messages, in order, to a specific client from their MessageQueue.
	 * @author TauOmicronMu
	 *
	 */
	public class OutToClient extends Thread {
		
		private String clientName;
		private ConnectedClientData connectedClientData;
		private ObjectOutputStream toClient;
		
		public OutToClient(String clientName, ConnectedClientData connectedClientData, ObjectOutputStream toClient) {
			this.clientName = clientName;
			this.connectedClientData = connectedClientData;
			this.toClient = toClient;
		}
		
		public void run() {
			while(true) {
				//Grab the first message in the queue.
				Message message = this.connectedClientData.getClientData(clientName).getFirstMessage();

				//Put the message into the output stream.
				try {
					this.toClient.writeObject(message);
					//Make sure that the message reaches the client.
					this.toClient.flush();
				} 
				catch (IOException e) {
					Constants.errorAndEnd("Something went wrong writing/flushing a client message to the Output Stream (I/O Exception).");
				}
			}
		}
	}
	