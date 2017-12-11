
package farma;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class OpravyKonkretnehoStroja {
    
    private Stroj kliknutyStroj;
    private Oprava kliknutaOprava;
    private StrojDao strojDao = DaoFactory.INSTANCE.getStrojDao();
    private OpravaDao opravaDao = DaoFactory.INSTANCE.getOpravaDao();
    private ObservableList<Stroj> strojeList = null;
    
    public OpravyKonkretnehoStroja(Stroj stroj){
        kliknutyStroj = stroj;
    }
    
    @FXML
    private TableView<Stroj> konkretnyStrojTableView;

    @FXML
    private TableView<Oprava> opravyTableView;

    @FXML
    private Button pridatButton;

    @FXML
    private Button zmazatButton;
    
    @FXML
    private Button zmazatVsetkoButton;

    @FXML
    void initialize() {
        Stroj stroj = strojDao.findById(kliknutyStroj.getId());
        List<Stroj> vybranyStroj = new ArrayList<>();
        vybranyStroj.add(stroj);
        strojeList = FXCollections.observableArrayList(vybranyStroj);
        
        TableColumn<Stroj, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        konkretnyStrojTableView.getColumns().add(idCol);

        TableColumn<Stroj, String> vyrobcaCol = new TableColumn<>("Výrobca");
        vyrobcaCol.setCellValueFactory(new PropertyValueFactory<>("vyrobca"));
       konkretnyStrojTableView.getColumns().add(vyrobcaCol);

        TableColumn<Stroj, String> typCol = new TableColumn<>("Typ");
        typCol.setCellValueFactory(new PropertyValueFactory<>("typ"));
       konkretnyStrojTableView.getColumns().add(typCol);

        TableColumn<Stroj, String> kategoriaCol = new TableColumn<>("Kategória");
        kategoriaCol.setCellValueFactory(new PropertyValueFactory<>("kategoria"));
         konkretnyStrojTableView.getColumns().add(kategoriaCol);

        TableColumn<Stroj, Object> datumCol = new TableColumn<>("Dátum");
        datumCol.setCellValueFactory(new PropertyValueFactory<>("datum"));
         konkretnyStrojTableView.getColumns().add(datumCol);

        TableColumn<Stroj, Double> cenaCol = new TableColumn<>("Cena");
        cenaCol.setCellValueFactory(new PropertyValueFactory<>("cena"));
         konkretnyStrojTableView.getColumns().add(cenaCol);
        
         konkretnyStrojTableView.setItems(strojeList);
         
         TableColumn<Oprava, Integer> idStrojCol = new TableColumn<>("Id Stroja");
        idStrojCol.setCellValueFactory(new PropertyValueFactory<>("idStroj"));
        opravyTableView.getColumns().add(idStrojCol);
        
        TableColumn<Oprava, Object> datumOpravaCol = new TableColumn<>("Dátum");
        datumOpravaCol.setCellValueFactory(new PropertyValueFactory<>("datum"));
        opravyTableView.getColumns().add(datumOpravaCol);
        
        TableColumn<Oprava, Double> cenaOpravaCol = new TableColumn<>("Cena opravy");
        cenaOpravaCol.setCellValueFactory(new PropertyValueFactory<>("cena"));
        opravyTableView.getColumns().add(cenaOpravaCol);
        
        TableColumn<Oprava, String> poruchaCol = new TableColumn<>("Porucha");
        poruchaCol.setCellValueFactory(new PropertyValueFactory<>("porucha"));
        opravyTableView.getColumns().add(poruchaCol);
        
        TableColumn<Oprava, Integer> popisCol = new TableColumn<>("Popis");
        popisCol.setCellValueFactory(new PropertyValueFactory<>("popis"));
        opravyTableView.getColumns().add(popisCol);
        
        opravyTableView.setItems(FXCollections.observableArrayList(opravaDao.getAllPodlaIdStroja(kliknutyStroj.getId())));
        
        pridatButton.setOnAction(eh ->{
           OpravaEditSceneController controller = new OpravaEditSceneController(kliknutyStroj);
                    try {
                        FXMLLoader loader = new FXMLLoader(
                                getClass().getResource("PridatOpravu.fxml"));
                        loader.setController(controller);
                        Parent parentPane = loader.load();
                        Scene scene = new Scene(parentPane);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.setTitle("Pridať opravu");
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.showAndWait();
                        // toto sa vykona az po zatvoreni okna
                    } catch (IOException iOException) {
                        iOException.printStackTrace();
                    }
                    opravyTableView.setItems(FXCollections.observableArrayList(opravaDao.getAllPodlaIdStroja(kliknutyStroj.getId())));
        });
        
        TableView<Oprava> table = opravyTableView;
        table.setRowFactory(tv -> {
            TableRow<Oprava> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 1) {

                    kliknutaOprava = row.getItem();
                }
                     });
            return row;
        });
        
        zmazatButton.setOnAction(eh ->{
            if (kliknutaOprava == null) {
                PrazdneMazanieSceneController controller = new PrazdneMazanieSceneController();
                try {
                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("PrazdneMazanie.fxml"));
                    loader.setController(controller);
                    Parent parentPane = loader.load();
                    Scene scene = new Scene(parentPane);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Zmazať opravu");
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.showAndWait();
                    // toto sa vykona az po zatvoreni okna
                } catch (IOException iOException) {
                    iOException.printStackTrace();
                }

                return;
            }
            OpravaDeleteSceneController controller
                    = new OpravaDeleteSceneController(kliknutaOprava);
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("PotvrdenieZmazania.fxml"));
                loader.setController(controller);
                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Zmazať opravu");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
                // toto sa vykona az po zatvoreni okna
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
            opravyTableView.setItems(FXCollections.observableArrayList(opravaDao.getAllPodlaIdStroja(kliknutyStroj.getId())));
            kliknutaOprava = null;
        });
        
        zmazatVsetkoButton.setOnAction(eh ->{
            OpravaDeleteAllController controller
                    = new OpravaDeleteAllController(kliknutyStroj);
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("PotvrdenieZmazaniaVsetkeho.fxml"));
                loader.setController(controller);
                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Vymaž všetko");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
                // toto sa vykona az po zatvoreni okna
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
            opravyTableView.setItems(FXCollections.observableArrayList(opravaDao.getAllPodlaIdStroja(kliknutyStroj.getId())));
        });
    }
}
