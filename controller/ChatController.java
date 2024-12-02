/* ***************************************************************
* Autor............: Vitor Reis
* Matricula........: 201710793
* Inicio...........: 10/08/2024
* Ultima alteracao.: 01/12/2024
* Nome.............: ChatController
* Funcao...........: Controla a tela de Chat/Grupo
*************************************************************** */

package controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import data.StageSingleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import model.Chat;
import model.Main;
import model.Message;
import model.User;

public class ChatController implements Initializable {
  Main main;

  @FXML
  Label groupName;

  @FXML
  TextField inputText;

  @FXML
  ListView<Message> listMessages;

  private Chat chat;
  StageSingleton stageSingleton;

  /*
   * ***************************************************************
   * Metodo: handleGoBack
   * Funcao: Retorna a listagem de Chats / Grupos / Tela Principal
   * Parametros: evento do botao
   * Retorno: void
   */
  @FXML
  private void handleGoBack(ActionEvent event) {
    main.setSelectedChat(null);
    this.chat = null;

    stageSingleton.goHomeScreen();
  }

  /*
   * ***************************************************************
   * Metodo: handleLeaveGroup
   * Funcao: Sai do grupo e retorna a listagem de Chats / Grupos / Tela Principal
   * Parametros: evento do botao
   * Retorno: void
   */
  @FXML
  private void handleLeaveGroup(ActionEvent event) {
    try {
      main.getTcpClient().send("LEAVE " + this.chat.getGroupName());
      main.removeChat(chat);
      stageSingleton.goHomeScreen();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /*
   * ***************************************************************
   * Metodo: handleSend
   * Funcao: Envia mensagem neste grupo
   * Parametros: evento do botao
   * Retorno: void
   */
  @FXML
  private void handleSend(ActionEvent event) {
    String data = this.inputText.getText();
    if (data.isEmpty()) {
      return;
    }

    try {
      main.getUdpClient().send("SEND " + this.chat.getGroupName() + ":" + data);

      User user = new User("127.0.0.1");
      Message message = new Message(data, user, true, LocalDateTime.now());

      chat.addMessage(message);
      this.inputText.clear();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /*
   * ***************************************************************
   * Metodo: renderChat
   * Funcao: Renderiza as mensagens do Chat
   * Parametros: void
   * Retorno: void
   */
  public void renderChat() {

    this.listMessages.setItems(chat.getMessages());

    // Cria uma ListView e vincula à ObservableList
    this.listMessages.setCellFactory(new Callback<ListView<Message>, ListCell<Message>>() {
      @Override
      public ListCell<Message> call(ListView<Message> listView) {
        return new ListCell<Message>() {
          @Override
          protected void updateItem(Message message, boolean empty) {
            super.updateItem(message, empty);
            setMouseTransparent(true); // Prevent mouse interactions
            setDisable(true); //
            if (empty || message == null) {
              setText(null);
              setGraphic(null);
            } else {
              // Cria um HBox para conter o texto e o tempo
              HBox hbox = createMessage(message); // Espaço entre texto e tempo
              // Define o HBox como o gráfico da célula
              setGraphic(hbox);
            }
          }
        };
      }
    });
  }

  /*
   * ***************************************************************
   * Metodo: createMessage
   * Funcao: Cria a renderização de uma mensagem
   * Parametros: Mensagem a ser renderizada
   * Retorno: Hbox
   */
  private HBox createMessage(Message message) {
    HBox parent = new HBox();
    VBox vbox = new VBox();
    HBox hbox = new HBox();
    Label messageLabel = new Label(message.getMessage());
    Label messageTimeLabel = new Label(message.getTime());
    String userIp = message.getUser().getIp();

    messageTimeLabel.getStyleClass().add("message-time");
    hbox.getStyleClass().add("message-text-box");

    if (userIp.equals("127.0.0.1")) {
      parent.getStyleClass().add("my-message");
      hbox.getChildren().add(0, messageTimeLabel);
    } else {
      Label userNameLabel = new Label(message.getUser().getIp());

      parent.getStyleClass().add("message-box");
      userNameLabel.getStyleClass().add("message-user-name");

      vbox.getChildren().add(userNameLabel);
      hbox.getChildren().add(messageTimeLabel);
    }

    messageLabel.setWrapText(true);

    hbox.getChildren().add(0, messageLabel);
    vbox.getChildren().add(hbox);
    parent.getChildren().add(vbox);

    return parent;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    stageSingleton = StageSingleton.getInstance();
    main = Main.getInstance();
    this.chat = main.getSelectedChat();
    this.groupName.setText(this.chat.getGroupName());

    renderChat();
  }
}
