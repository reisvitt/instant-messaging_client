/* ***************************************************************
* Autor............: Vitor Reis
* Matricula........: 201710793
* Inicio...........: 10/08/2024
* Ultima alteracao.: 01/12/2024
* Nome.............: User
* Funcao...........: Representação do usuário da aplicação
*************************************************************** */

package model;

public class User {
  private String ip;
  private String name;

  public User(String ip) {
    this.ip = ip;
  }

  /*
   * ***************************************************************
   * Metodo: getIp
   * Funcao: Retorna o IP do usuario
   * Parametros:
   * Retorno: String com o IP
   */
  public String getIp() {
    return ip;
  }

  /*
   * ***************************************************************
   * Metodo: setIp
   * Funcao: Seta o IP do usuario
   * Parametros: String ip do usuario
   * Retorno: void
   */
  public void setIp(String ip) {
    this.ip = ip;
  }

  /*
   * ***************************************************************
   * Metodo: getName
   * Funcao: Retorna o nome do usuario
   * Parametros:
   * Retorno: String do nome
   */
  public String getName() {
    return name;
  }

  /*
   * ***************************************************************
   * Metodo: setName
   * Funcao: Altera o nome do usuario
   * Parametros: String do nome
   * Retorno: void
   */
  public void setName(String name) {
    this.name = name;
  }
}
