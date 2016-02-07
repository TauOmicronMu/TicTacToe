/**
 * Class holding the constants for the program.
 * @author TauOmicronMu
 *
 */
public class Constants {
	
    final static int PORT = 4444;


	/**
	 * Throws an error and exits the program.
	 * @param errorMessage The error message to display to the user.
	 * @param errorNumber The value to exit with, Eg. System.exit(errorNumber)
	 */
	public final static void errorAndEnd(String errorMessage, int errorNumber) {
		System.err.println(errorMessage);
		System.exit(errorNumber);
	}

	/**
	 * Throws an error and exits the program with exit code 1.
	 * @param errorMessage The error message to display to the user.
	 */
	public final static void errorAndEnd(String errorMessage){
		System.err.println(errorMessage);
		System.exit(1);
	}
}