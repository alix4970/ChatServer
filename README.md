# ChatServer
Mandatory assignment

Overview
You should code a Chat system (client and server), with a chat client that can connect to a
chat server.
You must use threads in client and/or in server. The client should at the start ask the user
his/her chat-name and then send a join message to the server.
The server should accept clients to join the chat system, using a protocol specified below.
When a client joins, the server should maintain and update a list of all active clients. The
server will need to save for each client the user name, IP address and Port number.
If a new user tries to join with the same name as an already active user, then an error
message should be sent back to client. Client can try again with a new name.
An active client can send user text message to the server that will just send a copy to all
active clients in the client list.
The Client must send a “heartbeat alive” message once every minute to the Server. The
server should (maybe with a specialized thread) check the active list, and delete clients that
stop sending heartbeat messages. Maybe the active list should include last heartbeat time.
The Client must send a Quit message when it is closing.
Use a thread pool and limit the number of clients to e.g. 5.
Enable the system to log transaktions <<timestamp>> + <<request>> and <<timestamp>> +
<<response>>
Requests not following the protocol should give an error response back to the client and of
course log the event.
