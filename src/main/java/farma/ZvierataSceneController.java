package farma;

import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ZvierataSceneController {

    private Zviera zviera;
    private ZvieraDao zvieraDao = DaoFactory.INSTANCE.getZvieraDao();
    private ZvieraFxModel aktualneZviera = new ZvieraFxModel();
    private ObservableList<Zviera> z;

    public ZvierataSceneController() {
        aktualneZviera = new ZvieraFxModel();
    }

    @FXML
    private ListView<Zviera> zvierataListView;

    @FXML
    private Button pridatZvieraButton;

    @FXML

    void initialize() {
        List<Zviera> zvierata = zvieraDao.getAll();
        z = FXCollections.observableArrayList(zvierata);
        zvierataListView.setItems(z);

        pridatZvieraButton.setOnAction(eh -> {
            ZvierataEditSceneController controller
                    = new ZvierataEditSceneController();
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("ZvierataEditScene.fxml"));
                loader.setController(controller);
                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Zvierata");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
                // toto sa vykona az po zatvoreni okna
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        });
    }
}
