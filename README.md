Tom Goodman - txg523 - 1526322
==============================

(a) What you implemented.
-------------------------
I have implemented a basic, multi-threaded Client-Server architecture that allows multiple Clients to connect to the Server, and access basic functionality, such as listing the currently online Clients. I have also laid out a framework for implementing a "playwith" function. Data is transferred by means of an abstract data type that I have defined called a Message. Messages hold a Timestamp (of when they were sent), a sender, a receiver, a MessageType (which is an enum), and an Object of data. This means that there can be multiple different implementations of Message, which allows me to send multiple different kinds of serialisable data throughout my system.

>> What works well among the implemented things.
------------------------------------------------
>> >> : (1) Initial Handshake
-----
      >> The Server is started using 'java Server'.
      >> A Server is started on the port defined in the Constants.java file.
      >> The Server creates a new instance of the ConnectedClientData class.
      >> The Server attempts to open a ServerSocket.
      >> Once a ServerSocket has been opened, the Server will start waiting for new Clients.
      >> Clients are able to connect using 'java Client <nickname> <hostname> <port>
      >> The Client checks for correct usage of the args.
      >> It then attempts to open a socket to a Server at hostname:port.
      >> Once a client has connected, the Server will attempt to open an ObjectInputStream and and ObjectOutputStream to/from the Client.
      >> The Client also attempts to open an ObjectInputStream and an ObjectOutputStream to/from the Server.
      >> Now the Server waits for the Client to send it the requested nickname.
      >> The Client then sends the Server the requested nickname and then waits for a response from the Server.
      >> This is checked against the list of connected Clients by the Server, and if it is use, the Server will
         append a number, based on the usernames of the connected clients. (See (2) ).
      >> The Server then creates a record for this client in the ConnectedClientData.
      >> Next, the Server sends the Client it's final username.
      >> The Client now creates and starts an OutToServer Thread, which handles all user-input, and all output to the Server.
      >> The main Thread of the Client now begins to handle all input from the Server (Which will all be Messages from here on in).
      >> The Server now relays a PLAYERJOINED Message to all clients, except the Client that just connected. This prompts them to
         add the new Client to the list of connected clients stored in the ClientState.  
      >> The Server then relays the current list of connected players to the new Client in a CLIENTLIST Message. The Client then
         replaces it's current ClientList (in ClientState), which is currently empty, with this new list.
      >> Now the Server creates and starts an OutToClient Thread and an InToClient Thread. These are added to the instance of
         ConnectedClientData, to stop them from being disposed of when the Server starts waiting for another Client.

>> >> : (2) Unique Username Generation
-----
      >> When a Client connects with a username, checks are made in the Server to ensure that the username is not already in use.
      >> If the username is in use, the Server generates a unique username in the following way (Pseudocode) :
      >> (Defined as Constants.getUnusedUsername (which uses Constants.usernameInUse)

	  usernameInUse(nickname, connected_clients):
	      FOR EACH client in connected_clients
	          IF client = nickname THEN
		      RETURN true
	      return false

	  getUnusedUsername(requested_name, connected_clients)
	      n = 0
              new_name = requested_name
	      WHILE usernameInUse(new_name, connected_clients)
	          n++
		  new_name = requested_name + n
	      return new_name

      >> For example, if the connected clients were : [Tom, Mike, Bede, Tom1],
         and someone tried to request the username Tom,
	 they would be allocated the nickname "Tom2" by the server.

      >> This is a robust system for dealing with unique usernames, as it means that no matter what, the client is always assigned a
         unique username, and it doesn't require them to reconnect.

>> >> : (3) quit
-----
      >> The Client is able to enter the quit command, which allows them to close the Client without affecting the Server.
      >> When the Client types quit, a PLAYERDISCONNECT Message is sent to the Server, which removes the client from the
         ConnectedClientData, and distributes the Message to all other connected Clients.
      >> These clients then remove the disconnected Client's nickname from their ClientLists.

>> >> : (4) list
-----
      >> The Client is able to enter the list command, which will print a list of all currently connected Clients (in a pretty format)
         for the Client to see.
      >> As explained in other sections, the Client stores a ClientList in it's ClientState, which is updated whenever a CLIENTJOINED
         or CLIENTDISCONNECT Message is received.


>> What doesn't work so well.
-----------------------------

>> >> : playwith/end
-----
      >> I have only been able to implement a segment of the playwith/end functions, so they don't work particuarly well.
      >> The backbone for these functionalities is there, but it needs to be fully linked together before they will fully work.
      >> Had I had more time, I would have been able to implement the rest of this.

>> >> : Unexpected Disconnections
-----
      >> Although I was able to ascertain what goes wrong when a Client unexpectedly disconnects (eg. Ctrl + c), I ran out of time before
         being able to work out exactly what was happening to cause the server to crash in a very specific edge case :
	     1. A client connects to the server, and then unexpectedly disconnects.
	     2. Another client attempts to connect to the Server.
	     3. The Server finishes the handshake, but crashes due to a NullPointedExcepton which isn't handled correctly.
      >> Once again, had I had more time, I would have been able to implement a fix for this.

>> What doesn't work at all.
----------------------------

Everything that I have implemented works to some degree.



(b) What you didn't get to implement.
-------------------------------------
I didn't get to implement the following :
    >> TicTacToe Game Logic
    >> GUI
    >> Scoreboard



(c) Instructions to compile and run the files from the command line.
--------------------------------------------------------------------
1. javac *.java
2. java Server
3. java Client nickname hostname port



(d) Anything else that you consider to be relevant.
---------------------------------------------------
Although I was unable to implement the full system in the time scale, I think that, given a week or so more, I would have been able to get the whole system working, with the GUI and game logic. The big step is getting the networking working fine, and then a large proportion of the rest is just hooking existing code into the Client-System architecture.

I have included legacy versions of code in directories inside of the enclosed files. Although I haven't really implemented a large proportion of the assignment, I have still put a lot of effort in. I spent multiple hours planning out the architecture of the system, and ontop of this, I went through about 4 iterations of a Client-Server system before getting to this point.

Overall, I spent around 40 hours on this assignment, and have written around 6000 lines of code.

Despite not implementing a lot of the required features, I do feel that I have learned a lot by doing this exercise. I can now write a Client-Server system from scratch, without having to refer to previous code. I also have a heightened understanding of a multitude of things in Java now.

All of my work is also available at my git repository : https://github.com/TauOmicronMu/TicTacToe
