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
	
	/**
	 * Auxiliary function for gerUnusedUsername.
	 * @param nickname The nickname requested by the client.
	 * @param connectedClients The currently connected clients.
	 * @return Whether or not the requested nickname is in use.
	 */
	public final static boolean usernameInUse(String nickname, ConnectedClientData connectedClients) {
		for(String name : connectedClients.getConnectedClients()) {
			if(name.equals(nickname)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns an unused username, based on the requestedName and the names already in use.
	 * @param requestedName The nickname requested by the client.
	 * @param connectedClients The currently connected clients.
	 * @return A username that is not in use, and as close to the requested name as possible. 
	 */
	public final static String getUnusedUsername(String requestedName, ConnectedClientData connectedClients) {
		int n = 0;
		String newName = requestedName;
		while(usernameInUse(newName, connectedClients)) {
			n++;
			newName = requestedName + n;
		}
		return newName;
	}
}