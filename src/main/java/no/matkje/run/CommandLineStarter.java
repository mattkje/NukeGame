package no.matkje.run;

import no.matkje.gameServer.GameLogic;
import no.matkje.tools.Logger;

public class CommandLineStarter {
  /**
   * Application entrypoint for the command-line version of the simulator.
   *
   * @param args Command line arguments, only the first one of them used: when it is "fake",
   *             emulate fake events, when it is either something else or not present,
   *             use real socket communication.
   */
  public static void main(String[] args) {
    Logger.info("Running greenhouse simulator in command line (without GUI)...");
    GameLogic logic = new GameLogic();
    logic.initialize();
    logic.start();
  }
}
