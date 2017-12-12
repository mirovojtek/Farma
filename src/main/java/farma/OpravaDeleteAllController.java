package farma;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class OpravaDeleteAllController {
   private OpravaDao opravaDao = DaoFactory.INSTANCE.getOpravaDao();
   private Stroj stroj; 
   
   public OpravaDeleteAllController(Stroj stroj){
       this.stroj= stroj;
   }
   
     @FXML
    private Button anoButton;

    @FXML
    private Button nieButton;

    @FXML
    void initialize() {
       
        
        anoButton.setOnAction(eh -> {
            opravaDao.deleteAllPodlaIdStroja(stroj.getId());
            anoButton.getScene().getWindow().hide();
        });

        nieButton.setOnAction(eh -> {
            nieButton.getScene().getWindow().hide();
        });
    }
         
}
