import java.util.HashMap;

public class TalkingClients {

	private HashMap<String, String> clientmap;
	
    public TalkingClients() {
    	this.clientmap = new HashMap<String, String>();
    }
    
    public void newClient(String nickname) {
    	this.clientmap.put(nickname, null);
    }
    
    public void clientTalkingTo(String talking, String talkingTo) {
    	this.clientmap.put(talking, talkingTo);
    }
    
    public boolean isTalking(String nickname) {
    	return (this.clientmap.get(nickname).equals(null));
    }
    
    public void clientStoppedTo() {
        //TODO
    }
}
