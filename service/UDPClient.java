package service;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import data.Receive;
import service.models.Client;

public class UDPClient extends Client {
  private Receive receive;
  private DatagramSocket socket;

  public UDPClient(Receive receive, String host, int port) {
    super(host, port);
    this.receive = receive;
  }

  @Override
  public void connect() {
    System.out.println("Creating UDP socket...");
    try {
      this.socket = new DatagramSocket(getPort());
      this.receive();
    } catch (SocketException e) {
      System.out.println("(UDP) Error creating socket: " + e.getMessage());
      e.printStackTrace();
    }
  }

  @Override
  public void close() {
    System.out.println("Closing UDP socket...");
    this.socket.close();

    System.out.println("Socket (UDP) closed");
  }

  @Override
  public boolean isConnected() {
    // UDP has no connection

    return false;
  }

  @Override
  public void receive() {
    new Thread(() -> {
      while (true) {
        byte[] receivedData = new byte[1024];
        DatagramPacket receivedDatagramPacket = new DatagramPacket(receivedData, receivedData.length);

        try {
          System.out.println("(UDP) Waiting for messages...");
          socket.receive(receivedDatagramPacket);
          String data = new String(receivedDatagramPacket.getData());

          System.out.println("(UDP) Receive server data: " + data);
          this.receive.receive(data);
        } catch (IOException e) {
          System.out.println("(UDP) Error receiving the message.");
          e.printStackTrace();
        }
      }
    }).start();
  }

  @Override
  public void send(String message) {
    System.out.println("(UDP) Sending: " + message);

    byte[] byteData = message.getBytes();

    InetAddress serverAddress = null;

    try {
      serverAddress = InetAddress.getByName(getHost());
    } catch (UnknownHostException e) {
      System.out.println("(UDP) Error getting server address: " + e.getMessage());
      e.printStackTrace();
    }

    DatagramPacket packet = new DatagramPacket(byteData, byteData.length, serverAddress, getPort());
    try {
      this.socket.send(packet);
      System.out.println("(UDP) Message Sended");
    } catch (IOException e) {
      System.out.println("(UDP) Error sending packet: " + e.getMessage());
      e.printStackTrace();
    }
  }
}
