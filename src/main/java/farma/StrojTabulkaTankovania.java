package farma;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class StrojTabulkaTankovania {

    private final TankovanieDao tankovanieDao = DaoFactory.INSTANCE.getTankovanieDao();

    @FXML
    private TableView<Tankovanie> tankovaniaTableView;

    @FXML
    private TableColumn<Tankovanie, Integer> idTankovanieCol;

    @FXML
    private TableColumn<Tankovanie, Integer> idStrojaTankovanieCol;

    @FXML
    private TableColumn<Tankovanie, Object> datumTankovanieCol;

    @FXML
    private TableColumn<Tankovanie, Double> mnozstvoTankovanieCol;

    @FXML
    void initialize() {
        idTankovanieCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idStrojaTankovanieCol.setCellValueFactory(new PropertyValueFactory<>("strojId"));
        datumTankovanieCol.setCellValueFactory(new PropertyValueFactory<>("fDatum"));
        mnozstvoTankovanieCol.setCellValueFactory(new PropertyValueFactory<>("mnozstvo"));
        tankovaniaTableView.setItems(FXCollections.observableArrayList(tankovanieDao.getAll()));
    }
}
