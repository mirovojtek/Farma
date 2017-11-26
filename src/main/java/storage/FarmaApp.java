package storage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import storage.MainSceneController;

public class FarmaApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        MainSceneController controller = new MainSceneController();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("MainScene.fxml"));
        loader.setController(controller);
        Parent parentPane = loader.load();
        Scene scene = new Scene(parentPane);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Farma");
    }

    public static void main(String[] args) {
        launch(args);
    }

}
