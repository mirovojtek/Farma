package farma;

import farma.DaoFactory;
import farma.Oprava;
import farma.OpravaDao;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class StrojTabulkaOprav {

    private final OpravaDao opravaDao = DaoFactory.INSTANCE.getOpravaDao();

    @FXML
    private TableView<Oprava> opravyTableView;

    @FXML
    private TableColumn<Oprava, Integer> idOpravaCol;

    @FXML
    private TableColumn<Oprava, Integer> idStrojaOpravaCol;

    @FXML
    private TableColumn<Oprava, Object> datumOpravaCol;

    @FXML
    private TableColumn<Oprava, Double> cenaOpravaCol;

    @FXML
    private TableColumn<Oprava, String> poruchaOpravaCol;

    @FXML
    void initialize() {
        idOpravaCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idStrojaOpravaCol.setCellValueFactory(new PropertyValueFactory<>("idStroj"));
        datumOpravaCol.setCellValueFactory(new PropertyValueFactory<>("fDatum"));
        cenaOpravaCol.setCellValueFactory(new PropertyValueFactory<>("cena"));
        poruchaOpravaCol.setCellValueFactory(new PropertyValueFactory<>("porucha"));
        opravyTableView.setItems(FXCollections.observableArrayList(opravaDao.getAll()));
    }
}
