package farma;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ZvieraPodlaRegistracnehoCislaController {

    private String rc;
    private boolean akcia;

    public String getRC() {
        return rc;
    }

    public boolean getAkcia() {
        return akcia;
    }
    
    @FXML
    private Button okButton;

    @FXML
    private TextField regCisloTextField;

    @FXML
    void initialize() {

        okButton.setOnAction(eh -> {

            if (regCisloTextField.getText().equals("")) {
                // vyskoči okno, že registračné číslo musi byť zadene

                ZvieraNevyplneneRegistracneCisloController controller
                        = new ZvieraNevyplneneRegistracneCisloController();
                try {
                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("NevyplneneRegCislo.fxml"));
                    loader.setController(controller);
                    Parent parentPane = loader.load();
                    Scene scene = new Scene(parentPane);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Registračné číslo");
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.showAndWait();
                    // toto sa vykona az po zatvoreni okna
                } catch (IOException iOException) {
                    iOException.printStackTrace();
                }

            } else {
                rc = regCisloTextField.getText();
                akcia = true;
                okButton.getScene().getWindow().hide();
            }
        });

    }

}
