import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Object to hold a client's data.
 * @author TauOmicronMu
 */
public class ClientData {

	private int score;
	private boolean busy;
	
	private BlockingQueue<Message> messages;
	
	private ObjectInputStream fromClient;
	private ObjectOutputStream toClient;
	
	public ClientData() {
		this.setScore(0);
		this.setBusy(false);
		this.setMessages(new LinkedBlockingQueue<>());
		
		this.fromClient = null;
		this.toClient = null;
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
	    try {
			return this.messages.take();
		} 
	    catch (InterruptedException e) {
		    Constants.errorAndEnd("Error getting first message - interrupted.");	
		}	
	    return null;
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
	
	public void addInputStream(ObjectInputStream fromClient) {
		this.fromClient = fromClient;
	}
	
	public void addOutputStream(ObjectOutputStream toClient) {
		this.toClient = toClient;
	}
	
	public ObjectInputStream getInputStream() {
		return this.fromClient;
	}
	
	public ObjectOutputStream getOutputStream() {
		return this.toClient;
	}
}

