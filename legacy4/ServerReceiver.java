
import java.net.*;
import java.io.*;

// Gets messages from client and puts them in a queue, for another
// thread to forward to the appropriate client.

public class ServerReceiver extends Thread {
	private String myClientsName;
	private BufferedReader myClient;
	private ClientTable clientTable;
	private ClientList clientList;

	public ServerReceiver(String n, BufferedReader c, ClientTable t, ClientList list) {
		myClientsName = n;
		myClient = c;
		clientTable = t;
		clientList = list;
	}

	public void run() {
		try {
			while (true) {
				String command1 = myClient.readLine();
				String command2 = myClient.readLine();

				switch (command1) {
				case "playwith":
					break;
				case "quit":
					try {
					    this.clientList.removeClient(myClient.readLine());
					    return;
					}
					catch (NullPointerException e) {
						//Catch the null pointer exceptions.
					}
				case "end":
					break;
				case "list":
					Message msg1 = new Message(myClientsName, this.clientList.toString());
					MessageQueue recipientsQueue1 = clientTable.getQueue(myClientsName);
					if (recipientsQueue1 != null)
						recipientsQueue1.offer(msg1);
					//TODO : Work out how to display this in the client.
					break;
				default:
					if (command1 != null && command2 != null) {
						Message msg = new Message(myClientsName, command1);
						MessageQueue recipientsQueue = clientTable.getQueue(command2);
						if (recipientsQueue != null)
							recipientsQueue.offer(msg);
						else
							System.err.println("Message for unexistent client " + command2 + ": " + command1);
					} else {
						myClient.close();
						return;
					}
					break;
				}
			}
		} catch (IOException e) {
			System.err.println("Something went wrong with the client " + myClientsName + " " + e.getMessage());
			// No point in trying to close sockets. Just give up.
			// We end this thread (we don't do System.exit(1)).
		}
	}
}
