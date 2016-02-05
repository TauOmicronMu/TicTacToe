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
		try {
			fromServer = new ObjectInputStream(socket.getInputStream());
			toServer = new ObjectOutputStream(socket.getOutputStream());
			toServer.flush();
		}
		catch (Exception e){
		    Constants.errorAndEnd("Something went wrong opening and flushing the I/O Stream.");
		} 
		
		//Attempt to send our nickname to the server. The server will respond to us, telling us what our nickname is.
		try {
			toServer.writeObject(args[1]);
		} 
		catch (IOException e) {
			Constants.errorAndEnd("Something went wrong sending the nickname to the server.");
		}
	}
}
