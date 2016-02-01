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
}
