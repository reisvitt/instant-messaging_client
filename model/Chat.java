/* ***************************************************************
* Autor............: Vitor Reis
* Matricula........: 201710793
* Inicio...........: 10/08/2024
* Ultima alteracao.: 01/12/2024
* Nome.............: Chat
* Funcao...........: Controla o Chat da aplicação
*************************************************************** */

package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Chat {
  private ObservableList<Message> messages;
  private String groupName;

  public Chat(String groupName) {
    this.groupName = groupName;
    this.messages = FXCollections.observableArrayList();
  }

  /*
   * ***************************************************************
   * Metodo: getMessages
   * Funcao: Retorna todas as mensagens
   * Parametros: void
   * Retorno: Lista de mensagens
   */
  public ObservableList<Message> getMessages() {
    return messages;
  }

  /*
   * ***************************************************************
   * Metodo: setMessages
   * Funcao: Altera todas as mensagens
   * Parametros: Lista de mensagens
   * Retorno: void
   */
  public void setMessages(ObservableList<Message> messages) {
    this.messages = messages;
  }

  /*
   * ***************************************************************
   * Metodo: getGroupName
   * Funcao: Retorna o nome do Grupo das mensagens
   * Parametros: void
   * Retorno: String com o nome do Grupo
   */
  public String getGroupName() {
    return groupName;
  }

  /*
   * ***************************************************************
   * Metodo: setGroupName
   * Funcao: Altera o nome do Grupo das mensagens
   * Parametros: String com o nome do Grupo
   * Retorno: void
   */
  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  /*
   * ***************************************************************
   * Metodo: getLastMessage
   * Funcao: Retorna a ultima mensagem do chat
   * Parametros: void
   * Retorno: Message com a ultima mensagem do grupo
   */
  public Message getLastMessage() {
    if (messages.size() == 0) {
      return null;
    }

    Message message = messages.get(messages.size() - 1);

    return message;
  }

  /*
   * ***************************************************************
   * Metodo: addMessage
   * Funcao: Insere nova mensagem na lista
   * Parametros: Message a ser inserida
   * Retorno: void
   */
  public void addMessage(Message message) {
    messages.add(message);
  }
}