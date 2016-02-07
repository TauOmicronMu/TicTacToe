import java.util.ArrayList;

@SuppressWarnings("serial")
public class ClientListMessage extends Message {

	public ClientListMessage(String sender, ArrayList<String> data) {
		super(sender, MessageType.CLIENTLIST, data);
	}
}
