package controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import data.StageSingleton;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import model.Main;

public class SplashController implements Initializable {
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    scheduler.schedule(() -> {
      // connect to the server
      Main main = Main.getInstance();
      main.createClients(null);
      main.startClients();

      StageSingleton stageSingleton = StageSingleton.getInstance();
      Platform.runLater(() -> stageSingleton.goHomeScreen());
    }, 1, TimeUnit.SECONDS);
    scheduler.shutdown();
  }
}
