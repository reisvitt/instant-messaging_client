/* ***************************************************************
* Autor............: Vitor Reis
* Matricula........: 201710793
* Inicio...........: 10/08/2024
* Ultima alteracao.: 16/11/2024
* Nome.............: StageSingleton
* Funcao...........: Controla o stage da aplicação
*************************************************************** */
package data;

import javafx.stage.Stage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class StageSingleton {
  private static StageSingleton instance;

  private Stage stage;

  private StageSingleton() {
  }

  public static StageSingleton getInstance() {
    if (StageSingleton.instance == null) {
      StageSingleton.instance = new StageSingleton();
    }
    return StageSingleton.instance;
  }

  /*
   * ***************************************************************
   * Metodo: goHomeScreen
   * Funcao: nevega até a tela de Home
   * Parametros: void
   * Retorno: void
   */
  public void goHomeScreen() {
    goScreen("../view/HomeScreen.fxml", "Grupos");
  }

  /*
   * ***************************************************************
   * Metodo: goChatScreen
   * Funcao: nevega até a tela de Chat
   * Parametros: String com o nome do Chat
   * Retorno: void
   */
  public void goChatScreen(String chatName) {
    goScreen("../view/ChatScreen.fxml", chatName);
  }

  /*
   * ***************************************************************
   * Metodo: goSelectionScreen
   * Funcao: nevega até a tela de Configuração do Servidor
   * Parametros: void
   * Retorno: void
   */
  public void goSelectionScreen() {
    goScreen("../view/SelectionScreen.fxml", "Configuração de servidor");
  }

  /*
   * ***************************************************************
   * Metodo: goNewGroupScreen
   * Funcao: nevega até a tela de Entrar em um novo Grupo
   * Parametros: void
   * Retorno: void
   */
  public void goNewGroupScreen() {
    goScreen("../view/NewGroupScreen.fxml", "Entrar em novo grupo");
  }

  /*
   * ***************************************************************
   * Metodo: goScreen
   * Funcao: nevega até uma tela
   * Parametros: Path do fxml e Titulo da nova tela
   * Retorno: void
   */
  public void goScreen(String path, String title) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));
      Parent homeScreen = fxmlLoader.load();
      this.stage.setScene(new Scene(homeScreen));
      this.stage.setTitle(title);
      this.stage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /*
   * ***************************************************************
   * Metodo: getStage
   * Funcao: Retorna o stage atual
   * Parametros: void
   * Retorno: Stage
   */
  public Stage getStage() {
    return stage;
  }

  /*
   * ***************************************************************
   * Metodo: setStage
   * Funcao: Altera o stage atual
   * Parametros: Stage
   * Retorno: void
   */
  public void setStage(Stage stage) {
    this.stage = stage;
  }
}
