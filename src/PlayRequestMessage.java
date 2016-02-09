public class PlayRequestMessage extends Message {
	public PlayRequestMessage(String sender, String data) {
		super(sender, MessageType.PLAYREQUEST, data);
	}
}
