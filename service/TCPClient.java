package service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import data.Receive;
import service.models.Client;

public class TCPClient extends Client {
  private Socket socket;
  private Receive receive;

  public TCPClient(Receive receive, String host, int port) {
    super(host, port);
    this.receive = receive;
  }

  @Override
  public void connect() {
    System.out.println("(TCP) connecting to " + getHost() + ":" + getPort() + "...");
    try {
      this.socket = new Socket(this.getHost(), this.getPort());
      String myIp = socket.getLocalAddress().getHostAddress();
      System.out.println("(TCP) Server connected - My Ip: " + myIp);
      this.receive();
    } catch (IOException e) {
      System.out.println("(TCP) Error connecting to " + getHost() + ":" + getPort() + ". " + e.getMessage());
      e.printStackTrace();
    }
  }

  @Override
  public void close() {
    System.out.println("(TCP) Closing client...");
    try {
      this.socket.close();
      System.out.println("(TCP) Client closed");
    } catch (IOException e) {
      System.out.println("(TCP) Erro closing connection: " + e.getMessage());
      e.printStackTrace();
    }
  }

  @Override
  public boolean isConnected() {
    return this.socket.isConnected();
  }

  @Override
  public void receive() {
    new Thread(() -> {
      do {
        try {
          System.out.println("(TCP) Waiting for messages...");
          ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
          String data = ois.readUTF();
          System.out.println("(TCP) Receive server data: " + data);
          this.receive.receive(data);
        } catch (IOException e) {
          System.out.println("(TCP) Error receiving the message.");
          e.printStackTrace();
        }
      } while (this.socket.isConnected() && !this.socket.isClosed());
    }).start();
  }

  @Override
  public void send(String message) {
    System.out.println("(TCP) Sending: " + message);
    try {
      ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
      output.writeUTF(message);
      output.flush();
      System.out.println("(TCP) Message Sended");
    } catch (IOException e) {
      System.out.println("(TCP) Erro sending the message: " + e.getMessage());
      e.printStackTrace();
    }
  }
}
