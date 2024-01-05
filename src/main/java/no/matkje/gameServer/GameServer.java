package no.matkje.gameServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import no.matkje.message.Message;
import no.matkje.tools.Logger;

public class GameServer {
  public static final int PORT_NUMBER = 1238;
  private final List<ClientHandler> connectedClients = new ArrayList<>();
  private ServerSocket serverSocket;

  private GameLogic logic;

  public GameServer(GameLogic logic) {
    this.logic = logic;
  }

  /**
   * Initialize the game but don't start the simulation just yet.
   */
  public void initialize() {
    Scanner scanner = new Scanner(System.in);

    Logger.info("Game initialized");
  }

  /**
   * Start a simulation of a greenhouse - all the sensor and actuator nodes inside it.
   */
  public void start() {
    startServer();
  }

  /**
   * Starts the server.
   */
  private void startServer() {
    Thread serverThread = new Thread(this::initiateCommunication);
    serverThread.start();
  }


  /**
   * Sets up the TCP communication.
   */
  private void initiateCommunication() {
    try {
      serverSocket = new ServerSocket(PORT_NUMBER);
      Logger.info("Server is now listening on port " + PORT_NUMBER);
    } catch (IOException e) {
      Logger.error("Could not set up TCP connection: " + e.getMessage());
      return;
    }
    while (!serverSocket.isClosed()) {
      acceptNextClientConnection();
    }
  }

  /**
   * Accepts a new client connection.
   */
  private void acceptNextClientConnection() {
    try {
      Socket clientSocket = serverSocket.accept();
      Logger.info("New client connected from " + clientSocket.getRemoteSocketAddress());
      ClientHandler clientHandler = new ClientHandler(clientSocket, this);

      connectedClients.add(clientHandler);
      clientHandler.start();
    } catch (IOException e) {
      Logger.error("Could not accept client connection: " + e.getMessage());
    }
  }

  /**
   * Stop the simulation of the greenhouse - all the nodes in it.
   */
  public void stop() {
    stopCommunication();
  }

  /**
   * This method should stop the TCP communication.
   */
  private void stopCommunication() {
    try {
      serverSocket.close();
      Logger.info("TCP connection successfully closed");
    } catch (IOException e) {
      Logger.error("An error occurred while stopping communication");
    }
  }


  /**
   * This method should remove any disconnected clients.
   *
   * @param clientHandler The current client handler.
   */
  public void removeDisconnectedClient(ClientHandler clientHandler) {
    connectedClients.remove(clientHandler);
  }

  /**
   * Send a message to all currently connected clients.
   *
   * @param message The message to send
   */
  public void broadcastMessageToAllClients(Message message) {
    connectedClients.forEach(clientHandler -> clientHandler.sendResponse(message));
  }

  public GameLogic getLogic() {
    return logic;
  }
}
