import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
     * Keeps track of client input, and sends these to the server.
     * @author TauOmicronMu
     *
     */
	public class OutToServer extends Thread {
		
		private ClientState clientState;
		private ObjectOutputStream toServer;
		private Scanner scanner;
		private String clientName;
		
		public OutToServer(ClientState clientState, ObjectOutputStream toServer, String clientName) {
			this.clientState = clientState;
			this.toServer = toServer;
			this.clientName = clientName;
 
			this.scanner = new Scanner(System.in);
		}
		
		public void run() {
			/*
			 * >> Wait for client input
			 * >> Depending on input, either:
			 * >> >> Perform a local task (E.g. list)
			 * >> >> Send data to server (E.g. playwith)
			 */
            
		    while(true) {
		    	String command = this.scanner.nextLine();
		    	switch(command) {
		    	case "list" :
		    		System.out.println("---------- Clients : " + this.clientState.howManyClients() + " ----------");
		    		//Print out the currently connected clients.
		            for(String client : this.clientState.getClients()) {
		            	System.out.println(">>   " + client);
		            }
		            System.out.println("---------------------------------");
		    		break;
		    	case "quit" :
		    		Message message = new PlayerDisconnectMessage(this.clientName, this.clientName);
		    		try {
						this.toServer.writeObject(message);
						this.toServer.flush();
					} catch (IOException e) {
						Constants.errorAndEnd("Error writing/flushing PLAYERDISCONNECT Message to Output Stream (I/O Exception). ");
					}
		    		System.exit(0);
		    		break;
		    	case "playwith" :
		    		String recipient = scanner.nextLine();
		    		
		    		//Check if the recipient even exists.
		    		if(this.clientState.getClients().contains(recipient)) {
			    		PlayRequestMessage playmessage = new PlayRequestMessage(this.clientName, recipient);
			    		try {
			    			this.toServer.writeObject(playmessage);
			    			this.toServer.flush();
			    		}
			    		catch (IOException e) {
			    			Constants.errorAndEnd("Error writing/flushing a PLAYREQUEST Message to Output Stream (I/O Exception). ");
			    		}
		    		}
		    		else {
		    			System.out.println(recipient + " is not currently connected.");
		    		}
		    		//TODO : Send a "PLAYREQUEST Message"
		    		break;
		    	case "end" :
		    		break;
		    	default : 
		    		//Handle PLAYREQUEST Messages.
		    		if(this.clientState.isInvited()) {
		    		    switch (command.toLowerCase()) {
		    		    case ("y") :
		    		    	this.clientState.setInvited(false);
		    		        //TODO : Send a PLAYRESPONSE Message back to the client.
		    		    	break;
		    		    case ("yes") :
		    		    	this.clientState.setInvited(false);
		    		        //TODO : Send a PLAYRESPONSE Message back to the client.
		    		    case ("n") :
		    		    	this.clientState.setInvited(false);
		    		        //TODO : Send a PLAYRESPONSE Message back to the client.
		    		    	break;
		    		    case ("no") :
		    		    	this.clientState.setInvited(false);
		    		        //TODO : Send a PLAYRESPONSE Message back to the client.
		    		        break;
		    		    default :
		    		    	System.out.println("That wasn't a valid option. Please respond with yes or no.");
		    		    }
		    		}
		    	}
		    }
		}
	}