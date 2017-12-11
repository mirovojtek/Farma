
package farma;

import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class TankovanieDeleteSceneController {
    
    private Tankovanie tankovanie;
    private TankovanieDao tankovanieDao = DaoFactory.INSTANCE.getTankovanieDao();
    
    public TankovanieDeleteSceneController(Tankovanie tankovanie){
        this.tankovanie= tankovanie;
    }
    
    @FXML
    private Button anoButton;

    @FXML
    private Button nieButton;

    @FXML
    void initialize() {

        anoButton.setOnAction(eh -> {
            tankovanieDao.deletePodlaId(tankovanie.getId());
            anoButton.getScene().getWindow().hide();
        });

        nieButton.setOnAction(eh -> {
            nieButton.getScene().getWindow().hide();
        });
    }
}
