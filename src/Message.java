import java.io.Serializable;
import java.util.Date;

/**
 * Abstract implementation of a Message.
 * @author TauOmicronMu
 */
public class Message implements Serializable {

	/**
	 * Default serial ID.
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * The time at the point of Message creation.
	 */
	private final Date timestamp;
	
	/*
	 * The nickname of the client that the Message is being
	 * sent from.
	 */
	private final String fromClient;
	
	/*
	 * The command detailing what to do with the data -
	 * for example, "EndGame".
	 */
	private final String messageCommand;
	
	/*
	 * Holds data specific to the type of message to be sent. 
	 */
	private final Object data;
	
	public Message(String fromClient, String messageCommand, Object data) {
		
		this.fromClient = fromClient;
		this.messageCommand = messageCommand;
		this.data = data;
		
		this.timestamp = new Date();
	}

	/**
	 * Returns the fromClient attribute of the Message.
	 * @return The nickname of the client that the Message is being sent from.
	 */
	public String getFromClient() {
		return fromClient;
	}
    
	/**
	 * Returns the messageCommand attribute of the Message.
	 * @return The command detailing what to do with the data.
	 */
	public String getMessageCommand() {
		return messageCommand;
	}

	/**
	 * Returns the data attribute of the Message.
	 * @return The data held within the Message.
	 */
	public Object getData() {
		return data;
	}

	/**
	 * Returns the time at the point of Message creation.
	 * @return the timestamp attribute of the Message.
	 */
	public Date getTimestamp() {
		return timestamp;
	}

}
