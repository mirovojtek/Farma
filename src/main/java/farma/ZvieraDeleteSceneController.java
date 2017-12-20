package farma;

import farma.DaoFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ZvieraDeleteSceneController {
    
    private String rc;
    ZvieraDao zvieraDao = DaoFactory.INSTANCE.getZvieraDao();
    ZvieraFxModel zvieraFxModel = new ZvieraFxModel();
    private boolean boloZmazaneZviera = false;
    
    public ZvieraDeleteSceneController(String rc) {
        this.rc = rc;
    }
    
    public boolean getBoloZmazaneZviera() {
        return boloZmazaneZviera;
    }
    
    public void setBoloZmazaneZviera(boolean boloZmazaneZviera) {
        this.boloZmazaneZviera = boloZmazaneZviera;
    }
    
    @FXML
    private Button anoButton;
    
    @FXML
    private Button nieButton;
    
    @FXML
    void initialize() {
        
        anoButton.setOnAction(eh -> {
            zvieraDao.deleteByRegistracneCislo(rc);
            setBoloZmazaneZviera(true);
            anoButton.getScene().getWindow().hide();
        });
        
        nieButton.setOnAction(eh -> {
            nieButton.getScene().getWindow().hide();
        });
    }
    
}
