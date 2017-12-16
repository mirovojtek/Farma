
package farma;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class PoliaRozsireneVyhladavanieController {
    
    private PoleDao poleDao;
    
    private String typParcely = "%";
    private String typPozemku = "%";
    private String cisloParcely = "%";
    private String vlastnictvo = "%";
    private boolean akcia = false;
    
    public PoliaRozsireneVyhladavanieController(){
        poleDao = DaoFactory.INSTANCE.getPoleDao();
    }

    public String getTypParcely() {
        return typParcely;
    }

    public String getTypPozemku() {
        return typPozemku;
    }

    public String getCisloParcely() {
        return cisloParcely;
    }

    public String getVlastnictvo() {
        return vlastnictvo;
    }
    
    public boolean getAkcia(){
        return akcia;
    }
    
     @FXML
    private ComboBox<String> typParcelyComboBox;

    @FXML
    private ComboBox<String> typPozemkuComboBox;

    @FXML
    private ComboBox<String> cisloParcelyComboBox;

    @FXML
    private ComboBox<String> vlastnictvoComboBox;

    @FXML
    private Button vyhladatButton;

    @FXML
    void initialize() {
     
        typParcelyComboBox.setItems(FXCollections.observableArrayList(poleDao.getTypyParciel()));
        typParcelyComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String old, String newValue) {
                typParcely = newValue;
                cisloParcelyComboBox.setItems(FXCollections.observableArrayList(poleDao.getCislaParcielPodlaTypu(typParcely)));
            }
        });
        
        cisloParcelyComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String old, String newValue) {
                cisloParcely = newValue;
            }
        });
     
        typPozemkuComboBox.setItems(FXCollections.observableArrayList(poleDao.getTypyPozemkov()));
        typPozemkuComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String old, String newValue) {
                typPozemku = newValue;
            }
        });
        
       vlastnictvoComboBox.setItems(FXCollections.observableArrayList(poleDao.getVlastnictvo()));
       vlastnictvoComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String old, String newValue) {
                vlastnictvo = newValue;
            }
        });
       
       vyhladatButton.setOnAction(eh -> {
            akcia = true;
            vyhladatButton.getScene().getWindow().hide();
        });
    }
    
}
