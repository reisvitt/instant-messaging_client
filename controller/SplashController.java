/* ***************************************************************
* Autor............: Vitor Reis
* Matricula........: 201710793
* Inicio...........: 10/08/2024
* Ultima alteracao.: 01/12/2024
* Nome.............: SplashController
* Funcao...........: Controla a Splash Screen
*************************************************************** */

package controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import data.StageSingleton;
import javafx.application.Platform;
import javafx.fxml.Initializable;

public class SplashController implements Initializable {
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    scheduler.schedule(() -> {
      StageSingleton stageSingleton = StageSingleton.getInstance();
      Platform.runLater(() -> stageSingleton.goSelectionScreen());
    }, 1, TimeUnit.SECONDS);
    scheduler.shutdown();
  }
}
