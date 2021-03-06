import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.sun.corba.se.impl.orbutil.closure.Constant;

import example.ClientReceiver;

/**
 * Class holding all of the logic for the client.
 * 
 * USAGE : java Client nickname hostname port
 * 
 * >> Check if args is the correct length,
 * >> Attempt to open a socket to hostname:port,
 * >> If the socket is successfully opened, create 
 *    input and output streams to/from the server.
 * >> Spin up two threads (ClientSender & ClientReceiver).
 * 
 * @author TauOmicronMu
 *
 */
public class Client {
 
	public static void main(String[] args) {
		
		/*
		 * Check that args is the right length.
		 * If it isn't, tell the user and exit the program.
		 */
		if(args.length != 3){
		    Constants.errorAndEnd("Invalid usage of Client. Correct Usage: java Client nickname hostname port");
		}
		
		String nickname = args[0];
		String hostname = args[1];
		
		/*
		 * Initialises port and assigns a default value to the port to stop Java from crying.
		 */
		int port = 4444;
		
		try {
		    port = Integer.parseInt(args[2]);   	
		}
		catch(Exception e) {
			Constants.errorAndEnd("Invalid port - Ports must be integers.");
		}
		
		Socket socket = null;
		
		try {
			socket = new Socket(hostname, port);
		}
		catch(IOException e) {
			Constants.errorAndEnd("Something went wrong opening the socket to the server (IO Exception). Please try again.");
		}
		
		System.out.println("Connected to server at " + hostname + ":" + args[2]);
		
		ObjectOutputStream toServer = null;
		ObjectInputStream fromServer = null;
		
		try {
			toServer = new ObjectOutputStream(socket.getOutputStream());
			fromServer = new ObjectInputStream(socket.getInputStream());
		}
		catch(IOException e){
			Constants.errorAndEnd("Something went wrong opening the I/O Streams (I/O Exception). Please try again.");
		}
		System.out.println("Opened Object IO Streams toServer : " + toServer + " and fromServer : " + fromServer);
		
		ClientSender sender = new ClientSender(nickname, toServer, fromServer);
		ClientReceiver receiver = new ClientReceiver(fromServer);
		
		System.out.println("Created ClientSender : " + sender + " and ClientReceiver : " + receiver + " Threads.");
		
		sender.start();
		receiver.start();
		
		System.out.println("Started both Threads.");
	}
    
	/**
	 * Handles sending Objects to the server.
	 * @author TauOmicronMu
	 *
	 */
	public static class ClientSender extends Thread {
		
		private String nickname;
		private ObjectOutputStream server;
		private ObjectInputStream server2;
		
		public ClientSender(String nickname, ObjectOutputStream server, ObjectInputStream server2){
		    this.nickname = nickname;
		    this.server = server;
		    this.server2 = server2;
		}
		
		public void run() {
			try {
			    server.flush();
			}
			catch(IOException e) {
				Constants.errorAndEnd("Error flushing I/O Stream in ClientSender (I/O Exception).");
			}
			/*
			 * Attempt to tell the server the nickname. If the nickname is
			 * free it will be assigned to the client, otherwise a number 
			 * will be generated recursively and appended to the nickname.
			 */
			try {
				System.out.println("PRECUNT");
				server.writeUTF(nickname); //THE SOURCE OF ALL EVIL (THE SOURCE OF THE PROBLEM!) MMM SAUCE... FUCK I'M HUNGRY.
				System.out.println(server2.readUTF());
				System.out.println("CUNT");
		            
				//TODO : Add the protocol here.  
			}	
			catch(IOException e) {
				Constants.errorAndEnd("Error with communication in ClientSender (I/O Exception).");
			}
		}	
	}
	
	/**
	 * Handles receiving Objects from the server.
	 * @author TauOmicronMu
	 *
	 */
	public static class ClientReceiver extends Thread {
		
		private ObjectInputStream server;
		
		public ClientReceiver(ObjectInputStream server) {
			this.server = server;
		}
		
		public void run() {
			
			try {
				//Tell the user what their username is.
				System.out.println(this.server.readObject());
				//TODO : Define protocol for the client given Messages from the server.
			}
			catch (IOException e) {
				Constants.errorAndEnd("Error with communication in ClientReceiver (I/O Exception).");
			} catch (ClassNotFoundException e) {
				Constants.errorAndEnd("Error with communication in ClientReceiver (ClassNotFound Exception).");
			}
		}
	}
}

	


