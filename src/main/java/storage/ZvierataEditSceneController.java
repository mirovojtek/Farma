package storage;

import farma.ZvieraDao;
import farma.Zviera;
import farma.DaoFactory;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ZvierataEditSceneController {

   private ZvieraDao zvieraDao = DaoFactory.INSTANCE.getZvieraDao();
   private ZvieraFxModel aktualneZviera =  new ZvieraFxModel();
   private ObservableList<Zviera> zvierataList= null;
   
   
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField registracneCisloTextField;

    @FXML
    private TextField druhTextField;

    @FXML
    private TextField datumNarodeniaTextField;

    @FXML
    private TextField datumNadobudnutiaTextField;

    @FXML
    private TextField plemenoTextField;
    
    @FXML
    private TextField kupnaCenaTextField;

    @FXML
    private TextField pohlavieTextField;

    @FXML
    private ListView<Zviera> zvierataListView;

    @FXML
    private Button vlozitButton;

    @FXML
    void initialize() {
        List<Zviera> zvierata = zvieraDao.getAll();
        zvierataList = FXCollections.observableArrayList(zvierata);
        zvierataListView.setItems(zvierataList);
        registracneCisloTextField.textProperty().bindBidirectional(
                aktualneZviera.registracneCisloProperty());
        druhTextField.textProperty().bindBidirectional(aktualneZviera.druhProperty());
        pohlavieTextField.textProperty().bindBidirectional(aktualneZviera.pohlavieProperty());
        vlozitButton.setOnAction(eh -> {
            zvieraDao.add(aktualneZviera.getZviera());
            zvierataList.setAll(zvieraDao.getAll());
        });
        
                }
}
