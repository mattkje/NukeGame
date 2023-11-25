package no.matkje.gameServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import no.matkje.tools.Logger;

/**
 * This class represents a client handler.
 *
 * @author Matti Kjellstadli, Håkon Svensen Karlsen, Di Xie, Adrian Faustino Johansen
 * @version 09.11.2023
 */
public class ClientHandler extends Thread {
  private final Socket socket;
  private final GameLogic logic;
  private BufferedReader socketReader;
  private PrintWriter socketWriter;
  private boolean isConnected;

  /**
   * Creates an instance of ClientHandler.
   *
   * @param socket    Socket associated with this client
   * @param logic Reference to the main TCP server class
   * @throws IOException When something goes wrong with establishing the input or output streams
   */
  public ClientHandler(Socket socket, GameLogic logic)
      throws IOException {
    this.logic = logic;
    this.socket = socket;
    starter();
  }

  /**
   * This method starts the connection without encryption.
   *
   * @throws IOException Exception thrown if connection fails.
   */
  private void starter() throws IOException {
    socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    socketWriter = new PrintWriter(socket.getOutputStream(), true);
    isConnected = true;
  }

  /**
   * This method is responsible for handling client requests and executing commands.
   */
  @Override
  public void run() {
    String rawCommand;
    try {
      while (isConnected && (rawCommand = socketReader.readLine()) != null) {
        processCommand(rawCommand);
      }
    } catch (IOException e) {
      Logger.error("An error occurred while reading from the socket: " + e.getMessage());
    }

    String clientAddress = socket.getRemoteSocketAddress().toString();
    Logger.info("Client at " + clientAddress + " has disconnected.");
    logic.removeDisconnectedClient(this);
  }

  /**
   * Processes a raw command received from the client, providing feedback accordingly.
   *
   * @param rawCommand The command as a string
   */
  private void processCommand(String rawCommand) {

    if (rawCommand != null && !rawCommand.isEmpty()) {
      executeCommand(rawCommand);
      Logger.info("Received a command from the client: " + rawCommand);
    }
  }

  /**
   * Process and execute actions based on the content of the provided command.
   *
   * @param processedCommand The command as a string
   */
  private void executeCommand(String processedCommand) {
    char firstChar = processedCommand.charAt(0);
    switch (firstChar) {
      case 'c':
        handleConnect();
        break;
      case 'd':
        handleDenied();
        break;
      case '0':
        handleDisconnect();
        break;
      default:
        break;
    }
  }

  private void handleConnect() {
    sendResponse("ok");
  }

  /**
   * This method should handle a denial message.
   */
  private void handleDenied() {
    socketWriter.println("d");
  }


  /**
   * Handles disconnecting.
   */
  private void handleDisconnect() {
    isConnected = false;
  }


  /**
   * This method should send responses back to the client.
   *
   * @param response Command
   */
  public void sendResponse(String response) {
    socketWriter.println(response);
    socketWriter.flush();
  }

}
