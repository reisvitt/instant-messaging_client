/* ***************************************************************
* Autor............: Vitor Reis
* Matricula........: 201710793
* Inicio...........: 10/08/2024
* Ultima alteracao.: 01/12/2024
* Nome.............: SelectionController
* Funcao...........: Controla a tela de Configuração do Servidor
*************************************************************** */

package controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import data.StageSingleton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.Main;

public class SelectionController implements Initializable {

  @FXML
  TextField ipField;

  @FXML
  Button submitButton;

  /*
   * ***************************************************************
   * Metodo: onSubmit
   * Funcao: Seta o IP do servidor e inicia a conexão
   * Parametros: Event do botao
   * Retorno: void
   */
  @FXML
  private void onSubmit(ActionEvent event) {
    // connect to the server
    Main main = Main.getInstance();
    main.setIp(ipField.getText().trim());
    main.createClients();

    submitButton.setDisable(true);
    // connecting to the server

    new Thread(() -> {
      main.startClients();

      int tryCount = 0;
      Boolean tcpConnected = main.getTcpClient().isConnected();
      Boolean udpConnected = main.getUdpClient().isConnected();

      while (tryCount < 3) {
        try {
          TimeUnit.SECONDS.sleep(2);

        } catch (InterruptedException e) {
          e.printStackTrace();
        }

        tcpConnected = main.getTcpClient().isConnected();
        udpConnected = main.getUdpClient().isConnected();

        if (tcpConnected && udpConnected) {
          System.out.println("Connected");
          Platform.runLater(() -> {
            StageSingleton stageSingleton = StageSingleton.getInstance();
            Platform.runLater(() -> stageSingleton.goHomeScreen());
          });
          return;
        }
        tryCount++;
      }

      if (!tcpConnected || !udpConnected) {
        Platform.runLater(() -> {
          Alert alert = new Alert(AlertType.ERROR);
          alert.setTitle("Oops");
          alert.setContentText("Falha ao conectar. Tente novamente.");
          alert.showAndWait();
          submitButton.setDisable(false);
        });
        System.out.println("Falha ao conectar");
      }
    }).start();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    //
  }
}
