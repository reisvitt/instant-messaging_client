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
      main.createClients(null);
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

  @FXML
  private void handleGoBack(ActionEvent event) {
    StageSingleton stageSingleton = StageSingleton.getInstance();
    stageSingleton.goHomeScreen();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }
}
