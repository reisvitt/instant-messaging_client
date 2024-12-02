/* ***************************************************************
* Autor............: Vitor Reis
* Matricula........: 201710793
* Inicio...........: 10/08/2024
* Ultima alteracao.: 01/12/2024
* Nome.............: Main
* Funcao...........: Armazena todos os chats e conexoes na aplicação
*************************************************************** */

package model;

import data.TCPReceive;
import data.UDPReceive;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import service.TCPClient;
import service.UDPClient;
import service.models.Client;

public class Main {
  public static Main instance;

  private ObservableList<Chat> chats;

  private Chat selectedChat;

  private Client tcpClient;
  private Client udpClient;

  private String serverIp;

  private Main() {
    this.chats = FXCollections.observableArrayList();
  }

  public static Main getInstance() {
    if (Main.instance == null) {
      Main.instance = new Main();
    }

    return Main.instance;
  }

  /*
   * ***************************************************************
   * Metodo: isClientsReady
   * Funcao: Verifica se os clients foram criados
   * Parametros: void
   * Retorno: Booleano
   */
  public Boolean isClientsReady() {
    return this.udpClient != null && this.tcpClient != null;
  }

  /*
   * ***************************************************************
   * Metodo: setIp
   * Funcao: Altera o IP do servidor
   * Parametros: String com o IP do servidor
   * Retorno: void
   */
  public void setIp(String serverIp) {
    this.serverIp = serverIp;
  }

  /*
   * ***************************************************************
   * Metodo: createClients
   * Funcao: Cria os clientes TCP e UDP
   * Parametros: void
   * Retorno: void
   */
  public void createClients() {
    if (this.serverIp == null) {
      throw new Error("Insira o IP do servidor.");
    }

    int tcpPort = 6789;
    int udpPort = 6790;

    this.tcpClient = new TCPClient(new TCPReceive(), this.serverIp, tcpPort);
    this.udpClient = new UDPClient(new UDPReceive(), this.serverIp, udpPort);
  }

  /*
   * ***************************************************************
   * Metodo: startClients
   * Funcao: Se conecta ao Servidor e escuta os retornos
   * Parametros: void
   * Retorno: void
   */
  public void startClients() {
    System.out.println("Connecting to server...");

    tcpClient.connect();
    udpClient.connect();
  }

  /*
   * ***************************************************************
   * Metodo: getServerIp
   * Funcao: Retorna o IP do servidor
   * Parametros: void
   * Retorno: String com o IP do servidor
   */
  public String getServerIp() {
    return serverIp;
  }

  /*
   * ***************************************************************
   * Metodo: getUdpClient
   * Funcao: Retorna o Client UDP
   * Parametros: void
   * Retorno: Um Client UDP
   */
  public Client getUdpClient() {
    return udpClient;
  }

  /*
   * ***************************************************************
   * Metodo: setUdpClient
   * Funcao: Altera o cliente UDP
   * Parametros: Cliente UDP
   * Retorno: void
   */
  public void setUdpClient(Client udpClient) {
    this.udpClient = udpClient;
  }

  /*
   * ***************************************************************
   * Metodo: getTcpClient
   * Funcao: Retorna o Client TCP
   * Parametros: void
   * Retorno: Um Client TCP
   */
  public Client getTcpClient() {
    return tcpClient;
  }

  /*
   * ***************************************************************
   * Metodo: setTcpClient
   * Funcao: Altera o cliente TCP
   * Parametros: Cliente TCP
   * Retorno: void
   */
  public void setTcpClient(Client tcpClient) {
    this.tcpClient = tcpClient;
  }

  /*
   * ***************************************************************
   * Metodo: addChat
   * Funcao: Adiciona um novo chat ao estado que controla os chats
   * Parametros: Chat a ser adicionado
   * Retorno: void
   */
  public void addChat(Chat chat) {
    if (!this.exist(chat)) {
      chats.add(0, chat);
    }
  }

  /*
   * ***************************************************************
   * Metodo: exist
   * Funcao: Verifica se determinado existe
   * Parametros: Chat a ser verificado
   * Retorno: Booleano com o resutlado
   */
  public Boolean exist(Chat chat) {
    return this.chats.stream().anyMatch(ch -> ch.getGroupName().equals(chat.getGroupName()));
  }

  /*
   * ***************************************************************
   * Metodo: removeChat
   * Funcao: Remove um chat do estado
   * Parametros: Chat a ser removido
   * Retorno: Booleano se foi removido com sucesso
   */
  public Boolean removeChat(Chat chat) {
    for (Chat ch : this.chats) {
      if (ch.getGroupName().equals(chat.getGroupName())) {
        this.chats.remove(ch);
        return true;
      }
    }
    return false;
  }

  /*
   * ***************************************************************
   * Metodo: getSelectedChat
   * Funcao: Retorna o Chat selecionado no momento
   * Parametros: void
   * Retorno: Chat selecionado
   */
  public Chat getSelectedChat() {
    return selectedChat;
  }

  /*
   * ***************************************************************
   * Metodo: setSelectedChat
   * Funcao: Altera o Chat selecionado
   * Parametros: Chat a ser selecionado
   * Retorno: void
   */
  public void setSelectedChat(Chat selectedChat) {
    this.selectedChat = selectedChat;
  }

  /*
   * ***************************************************************
   * Metodo: getChats
   * Funcao: Retorna todos os chats salvo no estado
   * Parametros: void
   * Retorno: Lista de Chats
   */
  public ObservableList<Chat> getChats() {
    return chats;
  }

  /*
   * ***************************************************************
   * Metodo: getChat
   * Funcao: Retorna Chat de acordo com o nome do Grupo
   * Parametros: String com o nome do Grupo
   * Retorno: Chat
   */
  public Chat getChat(String groupName) {
    return this.chats.stream().filter(chat -> chat.getGroupName().equals(groupName)).findAny().orElse(null);
  }

  /*
   * ***************************************************************
   * Metodo: setChats
   * Funcao: Altera a lista de Chats no estado
   * Parametros: Lista de Chats
   * Retorno: void
   */
  public void setChats(ObservableList<Chat> chats) {
    this.chats = chats;
  }
}
