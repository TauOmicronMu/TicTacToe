import java.util.ArrayList;

/**
	 * Shared resource to keep track of things required by the Client and all associated Threads.
	 * @author TauOmicronMu
	 *
	 */
	public class ClientState {
		private boolean inGame;
		private boolean myTurn;
		private ArrayList<String> clientList;
		
		public ClientState() {
			this.setInGame(false);
			this.setMyTurn(false);
			
			this.clientList = new ArrayList<String>();
		}

		public boolean isInGame() {
			return inGame;
		}

		public void setInGame(boolean inGame) {
			this.inGame = inGame;
		}

		public boolean isMyTurn() {
			return myTurn;
		}

		public void setMyTurn(boolean myTurn) {
			this.myTurn = myTurn;
		}

        public void addToList(String client) {
        	this.clientList.add(client);
        }
        
        public void removeFromList(String client) {
        	this.clientList.remove(this.clientList.indexOf(client));
        }
        
        public ArrayList<String> getClients() {
        	return this.clientList;
        }
        
        public int howManyClients() {
        	return this.clientList.size();
        }
        
        public void setClientList(ArrayList<String> newList) {
        	this.clientList = newList;
        }
		
	}