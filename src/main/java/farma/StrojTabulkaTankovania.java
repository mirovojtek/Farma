
package farma;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class StrojTabulkaTankovania {
    
    private TankovanieDao tankovanieDao = DaoFactory.INSTANCE.getTankovanieDao();
    
    @FXML
    private TableView<Tankovanie> tankovanieTableView;

    @FXML
    void initialize() {
        
        TableColumn<Tankovanie, Integer> idStrojCol = new TableColumn<>("Id Stroja");
        idStrojCol.setCellValueFactory(new PropertyValueFactory<>("strojId"));
        tankovanieTableView.getColumns().add(idStrojCol);
        
        TableColumn<Tankovanie, Double> mnozstvoCol = new TableColumn<>("Množstvo paliva");
        mnozstvoCol.setCellValueFactory(new PropertyValueFactory<>("mnozstvo"));
        tankovanieTableView.getColumns().add(mnozstvoCol);
        
        TableColumn<Tankovanie, Object> datumCol = new TableColumn<>("Dátum");
        datumCol.setCellValueFactory(new PropertyValueFactory<>("fDatum"));
        tankovanieTableView.getColumns().add(datumCol);
        
       tankovanieTableView.setItems(FXCollections.observableArrayList(tankovanieDao.getAll()));

    }
    
    
}
