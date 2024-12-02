/* ***************************************************************
* Autor............: Vitor Reis
* Matricula........: 201710793
* Inicio...........: 10/08/2024
* Ultima alteracao.: 01/12/2024
* Nome.............: UDPClient
* Funcao...........: Cliente UDP - Faz toda a comunicação necessaria
*************************************************************** */

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
  private Boolean isConnected = false;

  public UDPClient(Receive receive, String host, int port) {
    super(host, port);
    this.receive = receive;
  }

  /*
   * ***************************************************************
   * Metodo: connect
   * Funcao: Conecta ao servidor
   * Parametros:
   * Retorno:
   */
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

  /*
   * ***************************************************************
   * Metodo: close
   * Funcao: Fecha a conexão com o servidor
   * Parametros:
   * Retorno:
   */
  @Override
  public void close() {
    System.out.println("Closing UDP socket...");
    this.socket.close();

    System.out.println("Socket (UDP) closed");
  }

  /*
   * ***************************************************************
   * Metodo: isConnected
   * Funcao: Verifica se há uma conexão ativa
   * Parametros:
   * Retorno: booleano se esta ou nao conectado
   */
  @Override
  public boolean isConnected() {
    return this.isConnected;
  }

  /*
   * ***************************************************************
   * Metodo: receive
   * Funcao: Espera novas mensagens
   * Parametros:
   * Retorno:
   */
  @Override
  public void receive() {
    new Thread(() -> {
      while (true && !this.socket.isClosed()) {
        byte[] receivedData = new byte[1024];
        DatagramPacket receivedDatagramPacket = new DatagramPacket(receivedData, receivedData.length);

        try {
          System.out.println("(UDP) Waiting for messages...");
          this.isConnected = true;
          socket.receive(receivedDatagramPacket);
          String data = new String(receivedDatagramPacket.getData());

          System.out.println("(UDP) Receive server data: " + data);
          this.receive.receive(data);
        } catch (IOException e) {
          this.isConnected = false;
          System.out.println("(UDP) Error receiving the message. " + e.getMessage());
        }
      }
    }).start();
  }

  /*
   * ***************************************************************
   * Metodo: send
   * Funcao: Envia mensagens
   * Parametros: message - Mensagem a ser enviada
   * Retorno:
   */
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
