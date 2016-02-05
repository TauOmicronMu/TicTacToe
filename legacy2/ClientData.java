import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import example.ServerReceiver;
import example.ServerSender;

/**
 * Object to hold a client's data.
 * @author TauOmicronMu
 */
public class ClientData {

	private int score;
	private boolean busy;
	
	private final Server.ServerSender toClient;
	private final Server.ServerReceiver fromClient;
	
	private BlockingQueue<Message> messages;
	
	public ClientData(Server.ServerSender toClient, Server.ServerReceiver fromClient) {
		this.setScore(0);
		this.setBusy(false);
		this.setMessages(new LinkedBlockingQueue<>());
		
		this.toClient = toClient;
		this.fromClient = fromClient;
	}

	public synchronized int getScore() {
		return score;
	}

	public synchronized void setScore(int score) {
		this.score = score;
	}
	
	public void incrementScore() {
		this.score++;
	}

	public boolean isBusy() {
		return busy;
	}

	public synchronized void setBusy(boolean busy) {
		this.busy = busy;
	}

	public Message getFirstMessage() {
	    return this.messages.remove();	
	}
	
	public BlockingQueue<Message> getMessages() {
		return messages;
	}

	public void setMessages(BlockingQueue<Message> messages) {
		this.messages = messages;
	}
	
	public void addMessage(Message message) {
		this.messages.add(message);
	}

	public Server.ServerSender getToClient() {
		return toClient;
	}

	public Server.ServerReceiver getFromClient() {
		return fromClient;
	}
}

