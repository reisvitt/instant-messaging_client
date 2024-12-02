/* ***************************************************************
* Autor............: Vitor Reis
* Matricula........: 201710793
* Inicio...........: 10/08/2024
* Ultima alteracao.: 01/12/2024
* Nome.............: NewGroupController
* Funcao...........: Controla a tela de Novo Grupo
*************************************************************** */

package controller;

import java.net.URL;
import java.util.ResourceBundle;

import data.StageSingleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Chat;
import model.Main;
import javafx.scene.control.TextField;

public class NewGroupController implements Initializable {

  @FXML
  TextField inputGroupName;

  /*
   * ***************************************************************
   * Metodo: handleEnterGroup
   * Funcao: Entra em novo grupo
   * Parametros: Event do botao
   * Retorno: void
   */
  @FXML
  private void handleEnterGroup(ActionEvent event) {
    String groupName = inputGroupName.getText();
    if (groupName.isEmpty()) {
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("Oops");
      alert.setHeaderText(null); // O cabeçalho pode ser nulo, se desejado
      alert.setHeight(150);
      alert.setWidth(150);
      alert.setContentText("Digite o nome do grupo");
      alert.showAndWait();
      return;
    }

    Main main = Main.getInstance();
    if (!main.isClientsReady()) {
      main.createClients();
    }
    try {
      main.getTcpClient().send("JOIN " + groupName);

      Chat chat = main.getChat(groupName);

      if (chat == null) {
        chat = new Chat(groupName);
        main.addChat(chat);
      }

      main.setSelectedChat(chat);
      StageSingleton stageSingleton = StageSingleton.getInstance();
      stageSingleton.goChatScreen(groupName);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /*
   * ***************************************************************
   * Metodo: handleGoBack
   * Funcao: Retorna a tela Principal - HomeScreen
   * Parametros: Event do botao
   * Retorno: void
   */
  @FXML
  private void handleGoBack(ActionEvent event) {
    StageSingleton stageSingleton = StageSingleton.getInstance();
    stageSingleton.goHomeScreen();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }
}
