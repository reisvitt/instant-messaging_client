/* ***************************************************************
* Autor............: Vitor Reis
* Matricula........: 201710793
* Inicio...........: 10/08/2024
* Ultima alteracao.: 01/12/2024
* Nome.............: Client
* Funcao...........: Métodos e atributos comuns aos clientes de UDP e TCP
*************************************************************** */

package service.models;

public abstract class Client {
  private int port;
  private String host;

  public Client(String host, int port) {
    this.port = port;
    this.host = host;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public abstract void send(String data);

  public abstract void receive();

  public abstract void connect();

  public abstract void close();

  public abstract boolean isConnected();

}
