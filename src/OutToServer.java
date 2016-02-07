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
		
		public OutToServer(ClientState clientState, ObjectOutputStream toServer) {
			this.clientState = clientState;
			this.toServer = toServer;
 
			this.scanner = new Scanner(System.in);
			
			this.run();
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
		    		//Send the server a CLIENTDISCONNECT Message.
		    		break;
		    	case "playwith" :
		    		break;
		    	case "end" :
		    		break;
		    	}
		    }
		}
	}