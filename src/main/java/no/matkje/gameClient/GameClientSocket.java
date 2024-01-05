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
   * Attempts to establish a connection to the remote server. If successful,
   * initializes the necessary communication streams and sets up the connection.
   *
   * @return True if the connection to the server was successfully established; false otherwise.
   */
  public boolean start() {
    boolean connected = false;
    try {
      socket = new Socket(SERVER_HOST, PORT_NUMBER);
      socketWriter = new PrintWriter(socket.getOutputStream(), true);
      socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      connected = true;
    } catch (IOException e) {
      System.err.println("Could not connect to the server: " + e.getMessage());
    }
    return connected;
  }

  /**
   * Closes the socket and nullifies associated resources,
   * including the socket itself, socketReader, and socketWriter.
   */
  public void stop() {
    if (socket != null) {
      try {
        socket.close();
      } catch (IOException e) {
        System.err.println("Error while closing the socket: " + e.getMessage());
      } finally {
        socket = null;
        socketReader = null;
        socketWriter = null;
      }
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

  /**
   * This method should return the current server host.
   *
   * @return the current server host.
   */
  public String getServerHost() {
    return SERVER_HOST + ":" + PORT_NUMBER;
  }
}
