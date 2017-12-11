/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farma;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 *
 * @author Ondrej Spišák
 */
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
