public class PlayerJoinedMessage extends Message {

	/**
	 * Default generated SID.
	 */
	private static final long serialVersionUID = -4151429789261821782L;

	public PlayerJoinedMessage(String sender, String newPlayer) {
		super(sender, MessageType.PLAYERJOINED, newPlayer);
	}
}