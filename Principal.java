
/* ***************************************************************
* Autor............: Vitor Reis
* Matricula........: 201710793
* Inicio...........: 10/08/2024
* Ultima alteracao.: 01/12/2024
* Nome.............: MSN
* Funcao...........: Aplicação de chat de mensagens utilizando os protocolos UDP e TCP
*************************************************************** */
import controller.ChatController;
import controller.HomeController;
import controller.NewGroupController;
import controller.SelectionController;
import controller.SplashController;
import data.StageSingleton;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Principal extends Application {
  ChatController chatController = new ChatController();
  HomeController homeController = new HomeController();
  NewGroupController newGroupController = new NewGroupController();
  SplashController splashController = new SplashController();
  SelectionController selectionController = new SelectionController();

  @Override
  public void start(Stage stage) throws Exception {
    StageSingleton stageSingleton = StageSingleton.getInstance();
    stageSingleton.setStage(stage);

    Parent root = FXMLLoader.load(getClass().getResource("view/SplashScreen.fxml"));
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.setTitle("MSN");
    stage.setOnCloseRequest(t -> {
      Platform.exit();
      System.exit(0);
    });
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}