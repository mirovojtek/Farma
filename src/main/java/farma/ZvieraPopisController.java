
package farma;

import farma.DaoFactory;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableView<Zviera> vybraneZvieraTableView;

    @FXML
    void initialize() {
        Zviera zviera = zvieraDao.findByRegistracneCislo(kliknuteZviera.getRegistracneCislo());
        List<Zviera> vybraneZviera = new ArrayList<>();
        vybraneZviera.add(kliknuteZviera);
        zvierataList = FXCollections.observableArrayList(vybraneZviera);
        
        // registračné číslo
        TableColumn<Zviera, String> registracneCisloCol = new TableColumn<>("Registračné číslo");
        registracneCisloCol.setCellValueFactory(new PropertyValueFactory<>("registracneCislo"));
        vybraneZvieraTableView.getColumns().add(registracneCisloCol);

        // druh
        TableColumn<Zviera, String> druhCol = new TableColumn<>("Druh");
        druhCol.setCellValueFactory(new PropertyValueFactory<>("druh"));
        vybraneZvieraTableView.getColumns().add(druhCol);

        // plemeno
        TableColumn<Zviera, String> plemenoCol = new TableColumn<>("Plemeno");
        plemenoCol.setCellValueFactory(new PropertyValueFactory<>("plemeno"));
        vybraneZvieraTableView.getColumns().add(plemenoCol);

        // pohlavie
        TableColumn<Zviera, String> pohlavieCol = new TableColumn<>("Pohlavie");
        pohlavieCol.setCellValueFactory(new PropertyValueFactory<>("pohlavie"));
        vybraneZvieraTableView.getColumns().add(pohlavieCol);

        // dátum narodenia
        TableColumn<Zviera, Object> datumNarodeniaCol = new TableColumn<>("Dátum narodenia");
        datumNarodeniaCol.setCellValueFactory(new PropertyValueFactory<>("fDatumNarodenia"));
        vybraneZvieraTableView.getColumns().add(datumNarodeniaCol);

        // dátum nadobudnutia
        TableColumn<Zviera, Object> datumNadobudnutiaCol = new TableColumn<>("Dátum nadobudnutia");
        datumNadobudnutiaCol.setCellValueFactory(new PropertyValueFactory<>("fDatumNadobudnutia"));
        vybraneZvieraTableView.getColumns().add(datumNadobudnutiaCol);

        // kúpna cena
        TableColumn<Zviera, Double> kupnaCenaCol = new TableColumn<>("Kúpna cena");
        kupnaCenaCol.setCellValueFactory(new PropertyValueFactory<>("kupnaCena"));
        vybraneZvieraTableView.getColumns().add(kupnaCenaCol);

        vybraneZvieraTableView.setItems(zvierataList);
          
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
