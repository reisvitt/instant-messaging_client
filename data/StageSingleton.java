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

  public void goHomeScreen() {
    goScreen("../view/HomeScreen.fxml", "Grupos");
  }

  public void goChatScreen(String chatName) {
    goScreen("../view/ChatScreen.fxml", chatName);
  }

  public void goNewGroupScreen() {
    goScreen("../view/NewGroupScreen.fxml", "Entrar em novo grupo");
  }

  public void goChat(String groupName) {
    goScreen("../view/ChatScreen.fxml", groupName);
  }

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

  public Stage getStage() {
    return stage;
  }

  public void setStage(Stage stage) {
    this.stage = stage;
  }
}
