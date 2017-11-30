
package farma;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class ZvieraPopisController {
     private ZvieraFxModel popisZviera;
     private Zviera kliknuteZviera;
     private ZvieraDao zvieraDao = DaoFactory.INSTANCE.getZvieraDao();
     private ObservableList<Zviera> zvierataList = null;
     
    public ZvieraPopisController(Zviera zviera){
        kliknuteZviera = zviera;
        popisZviera = new ZvieraFxModel();
    }
    
    @FXML
    private TextArea popisTextArea;

    @FXML
    private Button ulozitPopisButton;

    @FXML
    private TextFlow popisTextFlow;

    @FXML
    private ListView<Zviera> vybraneZvieraListView;

    @FXML
    void initialize() {
        Zviera zviera = zvieraDao.findByRegistracneCislo(kliknuteZviera.getRegistracneCislo());
        List<Zviera> vybraneZviera = new ArrayList<>();
        vybraneZviera.add(zviera);
        zvierataList = FXCollections.observableArrayList(vybraneZviera);
        vybraneZvieraListView.setItems(zvierataList);
        popisZviera.setZviera(zviera);
        Text t1 = new Text(popisZviera.getPopis());
        popisTextFlow.getChildren().add(t1);
        popisTextArea.textProperty().bindBidirectional(popisZviera.popisProperty());
        
        ulozitPopisButton.setOnAction(eh -> {
            zvieraDao.pridajPopis(popisZviera.getZviera());
             Text t2 = new Text(popisZviera.getPopis());
             popisTextFlow.getChildren().clear();
            popisTextFlow.getChildren().add(t2);
        });
    }
}
