/* ***************************************************************
* Autor............: Vitor Reis
* Matricula........: 201710793
* Inicio...........: 10/08/2024
* Ultima alteracao.: 16/11/2024
* Nome.............: UDPReceive
* Funcao...........: Trata as mensagens UDP vindas do servidor
*************************************************************** */

package data;

import javafx.application.Platform;
import model.Chat;
import model.Main;
import model.Message;
import utils.GetData;

public class UDPReceive implements Receive {

  @Override
  public void receive(String data) {
    System.out.println("RECEIVE: " + data);

    if (Message.isValidMessageFromServer(data)) {
      this.receiveMessage(data);
    }
  }

  public void receiveMessage(String data) {
    Message message = Message.buildFromServer(data);
    String util = GetData.getUtil(data);
    String group = util.split(":")[0];

    Main main = Main.getInstance();

    Chat chat = main.getSelectedChat();

    if (chat == null) {
      chat = main.getChat(group);
    }

    if (chat == null) {
      System.out.println("Group doesnt exist.");
      return;
    }
    final Chat aux = chat;

    Platform.runLater(() -> {
      aux.addMessage(message);
    });
  }
}
