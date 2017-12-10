
package farma;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class OpravaDeleteSceneController {
   
    private Oprava oprava;
    private OpravaDao opravaDao = DaoFactory.INSTANCE.getOpravaDao();
    
    public OpravaDeleteSceneController(Oprava oprava){
        this.oprava= oprava;
    }
    
    @FXML
    private Button anoButton;

    @FXML
    private Button nieButton;

    @FXML
    void initialize() {

        anoButton.setOnAction(eh -> {
            opravaDao.deletePodlaId(oprava.getId());
            anoButton.getScene().getWindow().hide();
        });

        nieButton.setOnAction(eh -> {
            nieButton.getScene().getWindow().hide();
        });
    }
}
