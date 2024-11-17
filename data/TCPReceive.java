package data;

public class TCPReceive implements Receive {

  @Override
  public void receive(String data) {
    System.out.println("RECEIVE (TCP): " + data);
  }

}
