package farma;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class StrojRozsireneVyhladavanieController {

    private StrojDao strojDao;

    private String vyrobca = "%";
    private String typ = "%";
    private String kategoria = "%";
    private String rokNadobudnutia = "%";
    private boolean akcia = false;

    public StrojRozsireneVyhladavanieController() {
        strojDao = DaoFactory.INSTANCE.getStrojDao();
    }

    ;
    
    public boolean getAkcia() {
        return akcia;
    }

    public String getVyrobca() {
        return vyrobca;
    }

    public String getTyp() {
        return typ;
    }

    public String getKategoria() {
        return kategoria;
    }

    public String getRokNadobudnutia() {
        return rokNadobudnutia;
    }

    @FXML
    private ComboBox<String> vyrobcaComboBox;

    @FXML
    private ComboBox<String> typComboBox;

    @FXML
    private ComboBox<String> kategoriaComboBox;

    @FXML
    private ComboBox<String> rokNadobudnutiaComboBox;

    @FXML
    private Button vyhladatButton;

    @FXML
    void initialize() {

        vyrobcaComboBox.setItems(FXCollections.observableArrayList(strojDao.getVyrobcovia()));
        vyrobcaComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String old, String newValue) {
                vyrobca = newValue;
                typComboBox.setItems(FXCollections.observableArrayList(strojDao.getTypyPodlaVyrobcu(vyrobca)));
            }
        });

        typComboBox.setItems(FXCollections.observableArrayList(strojDao.getTypy()));
        typComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String old, String newValue) {
                typ = newValue;
            }
        });

        kategoriaComboBox.setItems(FXCollections.observableArrayList(strojDao.getKategorie()));
        kategoriaComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String old, String newValue) {
                kategoria = newValue;
            }
        });

        rokNadobudnutiaComboBox.setItems(FXCollections.observableArrayList(strojDao.getRokyNadobudnutia()));
        rokNadobudnutiaComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String old, String newValue) {
                rokNadobudnutia = newValue;
            }
        });

        vyhladatButton.setOnAction(eh -> {
            akcia = true;
            vyhladatButton.getScene().getWindow().hide();
        });
    }


}
