package no.matkje.run;

import no.matkje.gameClient.GameClientLogic;
import no.matkje.gameClient.GameClientSocket;
import no.matkje.gui.GameClientApplication;
import no.matkje.tools.Logger;

public class GameClientStarter {

  public static String SERVER_HOST = "localhost";
  private GameClientSocket gameClientSocket;
  public static void main(String[] args) {
    GameClientStarter starter = new GameClientStarter();
    starter.start();
  }

  private void start() {
    GameClientLogic logic = new GameClientLogic();

    GameClientSocket socket = initiateSocketCommunication(logic);
    GameClientApplication.startApp(logic, socket);
    Logger.info("Exiting the control panel application");
    stopCommunication();
  }

  private GameClientSocket initiateSocketCommunication(GameClientLogic logic) {
    gameClientSocket = new GameClientSocket(logic);
    return gameClientSocket;
  }

  private void stopCommunication() {
    gameClientSocket.close();
  }

}
