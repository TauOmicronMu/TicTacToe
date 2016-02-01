import java.io.*;
import java.net.*;

public class Server {

	public static void main(String[] args) {
	    ServerSocket serverSocket = null;
	    
	    try {
	    	serverSocket = new ServerSocket(Constants.PORT);
	    }
	    catch (IOException e) {
	    	Constants.errorAndEnd("Couldn't connect to port : " + Constants.PORT, Constants.PORT_CONNECT_ERROR);
	    }
	    while(true) {
	    	try {
				Socket client = serverSocket.accept();
			} catch (IOException e) {
				Constants.errorAndEnd("Couldn't handle client.", Constants.CLIENT_HANDLE_ERROR);
			}
	    }
	}

}
