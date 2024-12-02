/* ***************************************************************
* Autor............: Vitor Reis
* Matricula........: 201710793
* Inicio...........: 10/08/2024
* Ultima alteracao.: 16/11/2024
* Nome.............: TCPReceive
* Funcao...........: Trata as mensagens TCP vindas do servidor
*************************************************************** */

package data;

public class TCPReceive implements Receive {

  @Override
  public void receive(String data) {
    System.out.println("RECEIVE (TCP): " + data);
  }

}
