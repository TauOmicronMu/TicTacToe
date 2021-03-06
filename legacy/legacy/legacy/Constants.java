package legacy;
import java.util.HashMap;

/**
 * Class to hold all of the shared values and methods for the program. 
 * @author TauOmicronMu
 */
public class Constants {
	/**
	 * The port to use when connecting to the server.
	 */
    public static final int PORT = 4444;

    /**
     * Throws an error and exits the program.
     * @param errorMessage The error message to display to the user.
     * @param errorNumber The value to exit with, Eg. System.exit(errorNumber)
     */
    public final static void errorAndEnd(String errorMessage, int errorNumber) {
		System.err.println(errorMessage);
		System.exit(errorNumber);
    }
    
    public final static int CLIENT_ARGS_ERROR = 1;
    public final static int UNKNOWN_HOST_ERROR = 2;
    public final static int SERVER_NOT_RUNNING_ERROR = 3;
    public final static int PORT_CONNECT_ERROR = 4;
    public final static int CLIENT_HANDLE_ERROR = 5;
}
