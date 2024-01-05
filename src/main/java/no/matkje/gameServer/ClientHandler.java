package no.matkje.gameServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import no.matkje.command.Command;
import no.matkje.message.CurrentPromptMessage;
import no.matkje.message.Message;
import no.matkje.tools.Logger;
import no.matkje.message.MessageSerializer;

/**
 * This class represents a client handler.
 *
 * @author Matti Kjellstadli, Håkon Svensen Karlsen, Di Xie, Adrian Faustino Johansen
 * @version 09.11.2023
 */
public class ClientHandler extends Thread {
  private final Socket socket;
  private final GameServer server;

  private BufferedReader socketReader;
  private PrintWriter socketWriter;
  private boolean isConnected;

  /**
   * Creates an instance of ClientHandler.
   *
   * @param socket Socket associated with this client
   * @param server Reference to the main TCP server class
   * @throws IOException When something goes wrong with establishing the input or output streams
   */
  public ClientHandler(Socket socket, GameServer server) throws IOException {
    this.server = server;
    this.socket = socket;
    socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    socketWriter = new PrintWriter(socket.getOutputStream(), true);
  }

  /**
   * This method is responsible for handling client requests and executing commands.
   */
  @Override
  public void run() {
    Message response;
    while ((response = executeClientCommand()) != null) {
      if (isBroadcastMessage(response)) {
        server.broadcastMessageToAllClients(response);
      }
    }

    String clientAddress = socket.getRemoteSocketAddress().toString();
    System.out.println("Client at " + clientAddress + " has disconnected.");
    server.removeDisconnectedClient(this);
  }

  /**
   * Reads a client request and executes the corresponding command.
   *
   * @return The response message from the executed command or null if the command is null.
   */
  private Message executeClientCommand() {
    Command clientCommand = getClientCommand();
    if (clientCommand == null) {
      return null;
    }

    String commandName = clientCommand.getClass().getSimpleName();
    System.out.println("Received a " + commandName + " from the client.");
    return sendResponse(clientCommand.execute(server.getLogic()));
  }

  /**
   * Checks if the given message is a broadcast message.
   *
   * @param response The response to check.
   * @return True if the response is a broadcast message, false otherwise.
   */
  private boolean isBroadcastMessage(Message response) {
    return response instanceof CurrentPromptMessage;
  }

  /**
   * Read one message from the TCP socket - from the client.
   *
   * @return The received client message, or null on error
   */
  private Command getClientCommand() {
    Message clientCommand = null;
    try {
      String rawClientRequest = socketReader.readLine();
      clientCommand = MessageSerializer.fromString(rawClientRequest);
      if (!(clientCommand instanceof Command)) {
        if (clientCommand != null) {
          System.err.println("Received an unexpected message from the client: " + clientCommand);
        }
        clientCommand = null;
      }
    } catch (IOException e) {
      System.err.println("Failed to receive the client request: " + e.getMessage());
    } catch (NullPointerException e1) {
      System.out.println("The client has lost the connection");
    }

    assert clientCommand instanceof Command : "Expected a Command but received: " + clientCommand;
    return (Command) clientCommand;
  }

  /**
   * Send a response from the server to the client, over the TCP socket.
   *
   * @param message The message to send to the client
   */
  public Message sendResponse(Message message) {
    socketWriter.println(message);
    return message;
  }

}
