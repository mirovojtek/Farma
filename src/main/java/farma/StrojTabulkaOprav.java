
package farma;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class StrojTabulkaOprav {
    
    private OpravaDao opravaDao = DaoFactory.INSTANCE.getOpravaDao();
    
     @FXML
    private TableView<Oprava> opravyTableView;

    @FXML
    void initialize() {
        
        TableColumn<Oprava, Integer> idStrojCol = new TableColumn<>("Id Stroja");
        idStrojCol.setCellValueFactory(new PropertyValueFactory<>("idStroj"));
        opravyTableView.getColumns().add(idStrojCol);
        
        TableColumn<Oprava, Object> datumCol = new TableColumn<>("Dátum");
        datumCol.setCellValueFactory(new PropertyValueFactory<>("datum"));
        opravyTableView.getColumns().add(datumCol);
        
        TableColumn<Oprava, Double> cenaCol = new TableColumn<>("Cena opravy");
        cenaCol.setCellValueFactory(new PropertyValueFactory<>("cena"));
        opravyTableView.getColumns().add(cenaCol);
        
        TableColumn<Oprava, String> poruchaCol = new TableColumn<>("Porucha");
        poruchaCol.setCellValueFactory(new PropertyValueFactory<>("porucha"));
        opravyTableView.getColumns().add(poruchaCol);
        
        TableColumn<Oprava, Integer> popisCol = new TableColumn<>("Popis");
        popisCol.setCellValueFactory(new PropertyValueFactory<>("popis"));
        opravyTableView.getColumns().add(popisCol);
        
        opravyTableView.setItems(FXCollections.observableArrayList(opravaDao.getAll()));

    }
    
}
