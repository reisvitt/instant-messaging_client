/* ***************************************************************
* Autor............: Vitor Reis
* Matricula........: 201710793
* Inicio...........: 10/08/2024
* Ultima alteracao.: 01/12/2024
* Nome.............: Message
* Funcao...........: Representação da Mensagem da aplicação
*************************************************************** */

package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import utils.GetData;

public class Message {
  private String message;
  private User user;
  private Boolean read;
  private LocalDateTime dateTime;

  public Message(String message, User user, Boolean read, LocalDateTime dateTime) {
    this.message = message;
    this.user = user;
    this.read = false;
    this.dateTime = dateTime;
  }

  /*
   * ***************************************************************
   * Metodo: getMessage
   * Funcao: Retorna a string da mensagem
   * Parametros:
   * Retorno: String com a mensagem
   */
  public String getMessage() {
    return message;
  }

  /*
   * ***************************************************************
   * Metodo: setMessage
   * Funcao: Altera a mensagem
   * Parametros: String com a mensagem
   * Retorno: void
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /*
   * ***************************************************************
   * Metodo: getUser
   * Funcao: Retorna o usuario da mensagem
   * Parametros:
   * Retorno: User com o usuario
   */
  public User getUser() {
    return this.user;
  }

  /*
   * ***************************************************************
   * Metodo: setUser
   * Funcao: Altera o usuario
   * Parametros: User da mensagem
   * Retorno: void
   */
  public void setUser(User user) {
    this.user = user;
  }

  /*
   * ***************************************************************
   * Metodo: isRead
   * Funcao: Retorna se esta mensagem já foi lida
   * Parametros:
   * Retorno: Booleano se ja foi lida
   */
  public Boolean isRead() {
    return read;
  }

  /*
   * ***************************************************************
   * Metodo: setRead
   * Funcao: Altera se já foi lida
   * Parametros: Read - Booleano se já foi lida
   * Retorno: void
   */
  public void setRead(Boolean read) {
    this.read = read;
  }

  /*
   * ***************************************************************
   * Metodo: getDateTime
   * Funcao: Retorna a data e horário da mensagem
   * Parametros: void
   * Retorno: LocalDateTime com a data e hora
   */
  public LocalDateTime getDateTime() {
    return dateTime;
  }

  /*
   * ***************************************************************
   * Metodo: setDateTime
   * Funcao: Altera a data e hora da mensagem
   * Parametros: LocalDateTime nova data e hora
   * Retorno: void
   */
  public void setDateTime(LocalDateTime dateTime) {
    this.dateTime = dateTime;
  }

  /*
   * ***************************************************************
   * Metodo: getTime
   * Funcao: Retorna hora da mensagem
   * Parametros: void
   * Retorno: String com a hora
   */
  public String getTime() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    String formattedTime = dateTime.format(formatter);
    return formattedTime;
  }

  /*
   * ***************************************************************
   * Metodo: isValidMessageFromServer
   * Funcao: Verifica se a mensagem do servidor é válida
   * Parametros: String com a mensagem
   * Retorno: boolean com a verificação
   */
  public static Boolean isValidMessageFromServer(String data) {

    String method = data.split(" ")[0];

    if (!method.equalsIgnoreCase("SEND")) {
      return false;
    }

    String util = GetData.getUtil(data);

    if (util.split(":").length != 3) {
      return false;
    }

    return true;
  }

  /*
   * ***************************************************************
   * Metodo: buildFromServer
   * Funcao: Construir a mensagem de acordo com a string recebida do servidor
   * Parametros: String com a mensagem completa
   * Retorno: Message como uma instancia de Message
   */
  public static Message buildFromServer(String data) {
    String util = GetData.getUtil(data);

    String[] parts = util.split(":");

    User user = new User(parts[1]);
    Message message = new Message(parts[2], user, false, LocalDateTime.now());

    return message;
  }
}
