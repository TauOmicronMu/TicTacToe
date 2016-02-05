import java.util.ArrayList;

public class ClientList {
	private ArrayList<String> clients;
	
	public ClientList() {
		this.clients = new ArrayList<String>();
	}
	
	public void addClient(String nickname) {
		this.clients.add(nickname);
	}
	
	public void removeClient(String nickname) {
		try {
	        this.clients.remove(this.clients.indexOf(nickname));
		}
		catch (Exception e) {
			System.err.println("Client disconnected from Server.");
		}
	}
	
	public ArrayList<String> getClients() {
		return this.clients;
	}
	
	public String toString() {
		return this.clients.toString();
	}
}
