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

  public Boolean isClientsReady() {
    return this.udpClient != null && this.tcpClient != null;
  }

  public void createClients(String serverIp) {
    String server = "192.168.18.40";
    int tcpPort = 6789;
    int udpPort = 6790;

    if (serverIp != null) {
      server = serverIp;
    }

    System.out.println("Server Ip: " + server);

    this.serverIp = serverIp;

    this.tcpClient = new TCPClient(new TCPReceive(), server, tcpPort);
    this.udpClient = new UDPClient(new UDPReceive(), server, udpPort);
  }

  public void startClients() {
    System.out.println("Connecting to server...");

    tcpClient.connect();
    udpClient.connect();
  }

  public String getServerIp() {
    return serverIp;
  }

  public Client getUdpClient() {
    return udpClient;
  }

  public void setUdpClient(Client udpClient) {
    this.udpClient = udpClient;
  }

  public Client getTcpClient() {
    return tcpClient;
  }

  public void setTcpClient(Client tcpClient) {
    this.tcpClient = tcpClient;
  }

  public void addChat(Chat chat) {
    if (!this.exist(chat)) {
      chats.add(0, chat);
    }
  }

  public Boolean exist(Chat chat) {
    return this.chats.stream().anyMatch(ch -> ch.getGroupName().equals(chat.getGroupName()));
  }

  public Boolean removeChat(Chat chat) {
    for (Chat ch : this.chats) {
      if (ch.getGroupName().equals(chat.getGroupName())) {
        this.chats.remove(ch);
        return true;
      }
    }
    return false;
  }

  public Chat getSelectedChat() {
    return selectedChat;
  }

  public void setSelectedChat(Chat selectedChat) {
    this.selectedChat = selectedChat;
  }

  public ObservableList<Chat> getChats() {
    return chats;
  }

  public Chat getChat(String groupName) {
    return this.chats.stream().filter(chat -> chat.getGroupName().equals(groupName)).findAny().orElse(null);
  }

  public void setChats(ObservableList<Chat> chats) {
    this.chats = chats;
  }
}
