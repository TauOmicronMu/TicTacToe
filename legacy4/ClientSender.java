
import java.io.*;

// Repeatedly reads recipient's nickname and text from the user in two
// separate lines, sending them to the server (read by ServerReceiver
// thread).

public class ClientSender extends Thread {

	private String nickname;
	private PrintStream server;

	ClientSender(String nickname, PrintStream server) {
		this.nickname = nickname;
		this.server = server;
	}

	public void run() {
		// So that we can use the method readLine:
		BufferedReader user = new BufferedReader(new InputStreamReader(System.in));

		try {
			// Tell the server what my nickname is:
			server.println(nickname);

			// Then loop forever sending messages to recipients via the server:
			while (true) {
				// Protocol
				String command1 = user.readLine().toLowerCase();
				switch (command1) {
				case "playwith":
					String command2 = user.readLine();
					server.println(command1);
					server.println(command2);
					break;
				case "quit":
					server.println(command1);
					server.println(nickname);
					System.exit(0);
					break;
				case "end":
					server.println(command1);
					break;
				case "list":
					
					break;
				default:
					String command3 = user.readLine();
					server.println(command1);
					server.println(command3);
				}
			}
		} catch (IOException e) {
			System.err.println("Communication broke in ClientSender" + e.getMessage());
			System.exit(1);
		}
	}
}
