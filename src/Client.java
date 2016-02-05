import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {
	    //Check correct usage.
		if(args.length != 3) {
	    	Constants.errorAndEnd("Invalid usage of args, Usage : java Client nickname hostname port");
	    }	    
		
		Socket socket = null;
		//Attempt to open a socket to the server at hostname:port
		try {
		    socket = new Socket(args[1], Integer.parseInt(args[2]));
		}
		catch (Exception e) {
			Constants.errorAndEnd("Something went wrong opening a socket. Was your port an integer?");
		}
		
		ObjectInputStream fromServer = null;
		ObjectOutputStream toServer = null;
		//Attempt to open an input and output stream to/from the server.
		/*
		 * ALWAYS OPEN OUTPUT BEFORE INPUT BECAUSE IT WON'T WORK OTHERWISE,
		 * BECAUSE JAVA HAS REALLY WEIRD QUIRKS.
		 */
		try {
			System.out.println("Started opening I/O Streams.");
		
			toServer = new ObjectOutputStream(socket.getOutputStream());
			System.out.println("Opened ObjectOutputStream");
			
			fromServer = new ObjectInputStream(socket.getInputStream());
			System.out.println("Opened ObjectInputStream");
			
			toServer.flush();
		}
		catch (Exception e){
		    Constants.errorAndEnd("Something went wrong opening and flushing the I/O Stream.");
		}
		
		//Debugging
		/*
		try {
			byte[] hello = new byte[5];
			fromServer.read(hello);
			System.out.println(new String(hello));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		*/
		
		//Attempt to send our nickname to the server. The server will respond to us, telling us what our nickname is.
		try {
			toServer.writeObject(args[0]);
			System.out.println("Wrote nickname to Output Stream.");
		} 
		catch (IOException e) {
			Constants.errorAndEnd("Something went wrong sending the nickname to the server.");
		}
		
		//TODO : create a thread to constantly get and interpret input from the server - do everything else directly in here,
		//       there is no need for 2 threads.
        
		//TODO : Display a JList that holds all client names with a busy state of false.
		//TODO : Make the JList elements selectable.
	}
}
