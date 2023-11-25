package no.matkje.tools;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class CircularPane extends Pane {
  private long degreese = 0;
  private long increment;
  @Override
  protected void layoutChildren() {
    final int radius = 150;
    final double increment = 360 / getChildren().size();
    double degreese = 0;
    for (Node node : getChildren()) {
      double x = radius * Math.cos(Math.toRadians(degreese)) + getWidth() / 2;
      double y = radius * Math.sin(Math.toRadians(degreese)) + getHeight() / 2;
      layoutInArea(node, x - node.getBoundsInLocal().getWidth() / 2, y - node.getBoundsInLocal().getHeight() / 2, getWidth(), getHeight(), 0.0, HPos.LEFT, VPos.TOP);
      degreese += increment;
    }
  }
}