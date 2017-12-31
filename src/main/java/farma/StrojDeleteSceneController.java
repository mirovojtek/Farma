package farma;

import farma.DaoFactory;
import farma.StrojFxModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StrojDeleteSceneController {
    
    private int id;
    StrojDao strojDao = DaoFactory.INSTANCE.getStrojDao();
    StrojFxModel strojFxModel = new StrojFxModel();
    private boolean bolZmazanyStroj = false;
    
    public StrojDeleteSceneController(int id) {
        this.id = id;
    }
    
    public boolean getBolZmazanyStroj() {
        return bolZmazanyStroj;
    }
    
    public void setBolZmazanyStroj(boolean bolZmazanyStroj) {
        this.bolZmazanyStroj = bolZmazanyStroj;
    }
    
    @FXML
    private Button anoButton;
    
    @FXML
    private Button nieButton;
    
    @FXML
    void initialize() {
        
        anoButton.setOnAction(eh -> {
            strojDao.deleteById(id);
            setBolZmazanyStroj(true);
            anoButton.getScene().getWindow().hide();
        });
        
        nieButton.setOnAction(eh -> {
            nieButton.getScene().getWindow().hide();
        });
    }
    
}
