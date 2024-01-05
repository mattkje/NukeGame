package no.matkje.gameClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import no.matkje.command.Command;
import no.matkje.message.MessageSerializer;
import no.matkje.tools.Logger;

public class GameClientSocket {

  private static final String SERVER_HOST = "localhost";
  private static final int PORT_NUMBER = 1238;
  private GameClientLogic logic;
  private boolean isConnected;
  private Socket socket;
  private PrintWriter socketWriter;
  private BufferedReader socketReader;

  /**
   * Creates an instance of GameClientSocket
   *
   * @param logic The application logic class.
   */
  public GameClientSocket(GameClientLogic logic) {
    this.logic = logic;
  }

  /**
   * Opens a TCP socket connection to the specified server.
   *
   * @return true if the socket connection was successfully established, false otherwise.
   */
  public boolean open() {
    try {
      socket = new Socket(SERVER_HOST, PORT_NUMBER);
      Logger.info("Successfully connected to: " + SERVER_HOST + ":" + PORT_NUMBER);

      Logger.request("Testing connection");
      socketWriter = new PrintWriter(socket.getOutputStream(), true);
      socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

      Logger.acceptMessage("[SUCCESS]");


      isConnected = true;
      return true;
    } catch (IOException e) {
      Logger.error("Could not connect to server: " + e.getMessage());
      return false;
    }
  }

  /**
   * This method should close the connection to the server.
   */
  public void close() {
    try {
      if (isConnected) {
        socket.close();
        socketWriter.close();
        socketReader.close();
        Logger.info(
            "Connection with server: " + SERVER_HOST + ":" + PORT_NUMBER + " has been closed");
        System.exit(0);
      }
    } catch (IOException e) {
      Logger.error("Could not close connection: " + e.getMessage());
    }
  }

  /**
   * This method should process commands, send the processed commands to
   * the server and return the server response.
   *
   * @param command Current sent command.
   * @return Server response.
   */
  public void sendCommand(Command command) {
    if (socketWriter != null && socketReader != null) {
      try {
        socketWriter.println(MessageSerializer.toString(command));
        System.out.println("Sending command: " + MessageSerializer.toString(command));
        String serverResponse;
        try {
          serverResponse = socketReader.readLine();
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        System.out.println("Server response: " + serverResponse);
      } catch (Exception e) {
        System.err.println("Could not send a command: " + e.getMessage());
      }
    }
  }

}
