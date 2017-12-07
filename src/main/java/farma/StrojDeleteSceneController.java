
package farma;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class StrojDeleteSceneController {
    private int id;
    StrojDao strojDao = DaoFactory.INSTANCE.getStrojDao();
    StrojFxModel strojFxModel = new StrojFxModel();
    
    public StrojDeleteSceneController(int id) {
        this.id = id;
    }

    @FXML
    private Button anoButton;

    @FXML
    private Button nieButton;

    @FXML
    void initialize() {

        anoButton.setOnAction(eh -> {
            strojDao.deleteById(id);
            anoButton.getScene().getWindow().hide();
        });

        nieButton.setOnAction(eh -> {
            nieButton.getScene().getWindow().hide();
        });
    }

}
