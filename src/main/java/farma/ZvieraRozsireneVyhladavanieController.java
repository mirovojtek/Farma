package farma;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class ZvieraRozsireneVyhladavanieController {

    private ZvieraDao zvieraDao;
    private String druh = "%";
    private String pohlavie = "%";
    private String rokNarodenia = "%";
    private String rokNadobudnutia = "%";
    private String plemeno = "%";
    private boolean akcia = false;

    public ZvieraRozsireneVyhladavanieController() {
        zvieraDao = DaoFactory.INSTANCE.getZvieraDao();
    }

    ;
    
    public boolean getAkcia() {
        return akcia;
    }

    public String getDruh() {
        return druh;
    }

    public String getPohlavie() {
        return pohlavie;
    }

    public String getRokNarodenia() {
        return rokNarodenia;
    }

    public String getRokNadobudnutia() {
        return rokNadobudnutia;
    }

    public String getPlemeno() {
        return plemeno;
    }

    @FXML
    private ComboBox<String> druhComboBox;

    @FXML
    private ComboBox<String> pohlavieComboBox;

    @FXML
    private ComboBox<String> rokNarodeniaComboBox;

    @FXML
    private ComboBox<String> rokNadobudnutiaComboBox;

    @FXML
    private ComboBox<String> plemenoComboBox;

    @FXML
    private Button vyhladatButton;

    @FXML
    void initialize() {
        druhComboBox.setItems(FXCollections.observableArrayList(zvieraDao.getDruhy()));
        druhComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String old, String newValue) {
                druh = newValue;
                //
                plemenoComboBox.setItems(FXCollections.observableArrayList(zvieraDao.getPlemenaPodlaDruhu(druh)));
                //
            }

        });
        pohlavieComboBox.setItems(FXCollections.observableArrayList(zvieraDao.getPohlavia()));
        pohlavieComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String old, String newValue) {
                pohlavie = newValue;
            }

        });
        plemenoComboBox.setItems(FXCollections.observableArrayList(zvieraDao.getPlemena()));
        plemenoComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String old, String newValue) {
                plemeno = newValue;
            }

        });
        rokNarodeniaComboBox.setItems(FXCollections.observableArrayList(zvieraDao.getRokyNarodenia()));
        rokNarodeniaComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String old, String newValue) {
                rokNarodenia = newValue;
            }

        });
        
        rokNadobudnutiaComboBox.setItems(FXCollections.observableArrayList(zvieraDao.getRokyNadobudnutia()));
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
