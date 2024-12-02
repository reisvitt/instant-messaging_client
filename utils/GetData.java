/* ***************************************************************
* Autor............: Vitor Reis
* Matricula........: 201710793
* Inicio...........: 10/08/2024
* Ultima alteracao.: 01/11/2024
* Nome.............: GetData
* Funcao...........: Recuperar a parte util da mensagem do servidor
*************************************************************** */

package utils;

public class GetData {

  public static String getUtil(String data) {
    String remove = data.split(" ")[0];
    return data.replaceAll(remove, "").trim();
  }

}
