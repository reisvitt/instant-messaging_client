
import controller.ChatController;
import controller.HomeController;
import controller.NewGroupController;
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