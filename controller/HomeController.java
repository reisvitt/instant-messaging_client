/* ***************************************************************
* Autor............: Vitor Reis
* Matricula........: 201710793
* Inicio...........: 10/08/2024
* Ultima alteracao.: 01/12/2024
* Nome.............: HomeController
* Funcao...........: Controla a tela Principal - HomeScreen
*************************************************************** */

package controller;

import java.net.URL;
import java.util.ResourceBundle;

import data.StageSingleton;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import model.Chat;
import model.Main;
import model.Message;

public class HomeController implements Initializable {
  StageSingleton stageSingleton;
  Main main;

  @FXML
  ListView<Chat> listView;

  /*
   * ***************************************************************
   * Metodo: handleButtonClick
   * Funcao: Nevega até a tela de Novo Grupo
   * Parametros: Event do botao
   * Retorno: void
   */
  @FXML
  private void handleButtonClick(ActionEvent event) {
    stageSingleton.goNewGroupScreen();
  }

  /*
   * ***************************************************************
   * Metodo: handleGoOutServer
   * Funcao: Fecha as conexões e retorna a tela de Configuração de Servidor
   * Parametros: Event do botao
   * Retorno: void
   */
  @FXML
  private void handleGoOutServer(ActionEvent event) {
    main.getTcpClient().close();
    main.getUdpClient().close();
    stageSingleton.goSelectionScreen();
  }

  /*
   * ***************************************************************
   * Metodo: renderGroups
   * Funcao: Renderiza em tela os grupos / Chats
   * Parametros: void
   * Retorno: void
   */
  public void renderGroups() {

    System.out.println("Chats: " + main.getChats());

    this.listView.setItems(main.getChats());

    // Cria uma ListView e vincula à ObservableList
    this.listView.setCellFactory(new Callback<ListView<Chat>, ListCell<Chat>>() {
      @Override
      public ListCell<Chat> call(ListView<Chat> listView) {
        return new ListCell<Chat>() {
          @Override
          protected void updateItem(Chat chat, boolean empty) {
            super.updateItem(chat, empty);
            if (empty || chat == null) {
              setText(null);
              setGraphic(null);
            } else {
              // Cria um HBox para conter o texto e o tempo
              HBox hbox = createChat(chat); // Espaço entre texto e tempo
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
   * Metodo: createChat
   * Funcao: Cria a renderização de um Chat
   * Parametros: Chat a ser renderizado
   * Retorno: void
   */
  private HBox createChat(Chat chat) {

    HBox hbox = new HBox();
    VBox vbox1 = new VBox();
    VBox vbox2 = new VBox();
    Label titleLabel = new Label(chat.getGroupName());
    Label subTitleLabel = new Label("");
    Label timeLabel = new Label("");

    if (chat.getLastMessage() != null) {
      subTitleLabel.setText(chat.getLastMessage().getMessage());
    }
    if (chat.getLastMessage() != null) {
      timeLabel.setText(chat.getLastMessage().getTime());
    }

    titleLabel.getStyleClass().add("title");
    subTitleLabel.getStyleClass().add("last-message-time");
    timeLabel.getStyleClass().add("last-message-time");
    timeLabel.getStyleClass().add("active");

    vbox2.getStyleClass().add("last-message-box");
    vbox1.getChildren().addAll(titleLabel, subTitleLabel);
    vbox2.getChildren().addAll(timeLabel);

    HBox.setHgrow(vbox1, Priority.ALWAYS);
    hbox.getChildren().addAll(vbox1, vbox2);
    hbox.setOnMouseClicked(this::openGroup);
    hbox.setId(chat.getGroupName());

    return hbox;
  }

  /*
   * ***************************************************************
   * Metodo: openGroup
   * Funcao: Abre um grupo
   * Parametros: evento do botao
   * Retorno: void
   */
  private void openGroup(MouseEvent event) {
    HBox chatBox = (HBox) event.getSource();

    Chat chat = main.getChat(chatBox.getId());

    if (chat == null) {
      System.out.println("Chat not found");
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Oops");
      alert.setHeaderText(null); // O cabeçalho pode ser nulo
      alert.setHeight(150);
      alert.setWidth(150);
      alert.setContentText("Erro ao abrir este grupo.");
      alert.showAndWait();
      return;
    }

    main.setSelectedChat(chat);
    stageSingleton.goChatScreen(chat.getGroupName());
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    stageSingleton = StageSingleton.getInstance();
    main = Main.getInstance();

    renderGroups();

    for (Chat chat : main.getChats()) {
      chat.getMessages().addListener((ListChangeListener<Message>) change -> {
        while (change.next()) {
          if (change.wasAdded() || change.wasRemoved()) {
            Platform.runLater(() -> {
              renderGroups();
            });
          }
        }
      });
    }
  }
}
