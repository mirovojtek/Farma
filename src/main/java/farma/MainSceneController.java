package farma;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainSceneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button strojeButton;

    @FXML
    private Button zvierataButton;

    @FXML
    private Button poliaButton;

    @FXML
    private Button financieButton;

    @FXML
    void initialize() {
        zvierataButton.setOnAction(eh -> {

            ZvieraSceneController controller
                    = new ZvieraSceneController();
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("ZvierataScene.fxml"));
                loader.setController(controller);

                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);

                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Zvierata");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();

                // toto sa vykona az po zatvoreni okna
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }

        });

        strojeButton.setOnAction(eh -> {
            StrojSceneController controller = new StrojSceneController();
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("StrojeScene.fxml"));
                loader.setController(controller);

                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);

                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Stroje");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();

                // toto sa vykona az po zatvoreni okna
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        });

        poliaButton.setOnAction(eh -> {
            PoliaSceneController controller = new PoliaSceneController();
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("PoliaScene.fxml"));
                loader.setController(controller);
                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Polia");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();

                // toto sa vykona az po zatvoreni okna
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        });
    }
}
