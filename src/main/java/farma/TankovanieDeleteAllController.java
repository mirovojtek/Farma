
package farma;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class TankovanieDeleteAllController {
   private TankovanieDao tankovanieDao = DaoFactory.INSTANCE.getTankovanieDao();
   private Stroj stroj; 
   
   public TankovanieDeleteAllController(Stroj stroj){
       this.stroj= stroj;
   }
   
     @FXML
    private Button anoButton;

    @FXML
    private Button nieButton;

    @FXML
    void initialize() {
       
        
        anoButton.setOnAction(eh -> {
            tankovanieDao.deleteAllPodlaIdStroja(stroj.getId());
            anoButton.getScene().getWindow().hide();
        });

        nieButton.setOnAction(eh -> {
            nieButton.getScene().getWindow().hide();
        });
    }
        
}
   
