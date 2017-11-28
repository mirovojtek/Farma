package farma;

import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ZvierataSceneController {

    private Zviera zviera;
    private ZvieraDao zvieraDao = DaoFactory.INSTANCE.getZvieraDao();
    private ZvieraFxModel aktualneZviera = new ZvieraFxModel();
    private ObservableList<Zviera> z;

    public ZvierataSceneController() {
        aktualneZviera = new ZvieraFxModel();
    }

    @FXML
    private Button pridatZvieraButton;

    @FXML
    private TableView<Zviera> zvierataTableView;

    @FXML

    void initialize() {
        List<Zviera> zvierata = zvieraDao.getAll();

        // registračné číslo
        TableColumn<Zviera, String> registracneCisloCol = new TableColumn<>("Registračné číslo");
        registracneCisloCol.setCellValueFactory(new PropertyValueFactory<>("registracneCislo"));
        zvierataTableView.getColumns().add(registracneCisloCol);

        // druh
        TableColumn<Zviera, String> druhCol = new TableColumn<>("Druh");
        druhCol.setCellValueFactory(new PropertyValueFactory<>("druh"));
        zvierataTableView.getColumns().add(druhCol);

        // plemeno
        TableColumn<Zviera, String> plemenoCol = new TableColumn<>("Plemeno");
        plemenoCol.setCellValueFactory(new PropertyValueFactory<>("plemeno"));
        zvierataTableView.getColumns().add(plemenoCol);
        
        // pohlavie
        TableColumn<Zviera, String> pohlavieCol = new TableColumn<>("Pohlavie");
        pohlavieCol.setCellValueFactory(new PropertyValueFactory<>("pohlavie"));
        zvierataTableView.getColumns().add(pohlavieCol);
        
        // dátum narodenia
        TableColumn<Zviera, Object> datumNarodeniaCol = new TableColumn<>("Dátum narodenia");
        datumNarodeniaCol.setCellValueFactory(new PropertyValueFactory<>("fDatumNarodenia"));
        zvierataTableView.getColumns().add(datumNarodeniaCol);
        
        // dátum nadobudnutia
        TableColumn<Zviera, Object> datumNadobudnutiaCol = new TableColumn<>("Dátum nadobudnutia");
        datumNadobudnutiaCol.setCellValueFactory(new PropertyValueFactory<>("fDatumNadobudnutia"));
        zvierataTableView.getColumns().add(datumNadobudnutiaCol);
       
        
        // kúpna cena
        TableColumn<Zviera, Double> kupnaCenaCol = new TableColumn<>("Kúpna cena");
        kupnaCenaCol.setCellValueFactory(new PropertyValueFactory<>("kupnaCena"));
        zvierataTableView.getColumns().add(kupnaCenaCol);
        
        
        zvierataTableView.setItems(FXCollections.observableArrayList(zvieraDao.getAll()));

        pridatZvieraButton.setOnAction(eh -> {
            ZvierataEditSceneController controller
                    = new ZvierataEditSceneController();
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("ZvierataEditScene.fxml"));
                loader.setController(controller);
                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Pridať zviera");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
                // toto sa vykona az po zatvoreni okna
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        });
    }
}
