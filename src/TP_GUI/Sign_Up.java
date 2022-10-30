package TP_GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import TP_KERNEL.Agence;
import TP_KERNEL.Personne;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Sign_Up {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button back;

    @FXML
    private Button signUp;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField pwd;

    @FXML
    private TextField mail;

    @FXML
    private TextField phone;

    @FXML
    private TextField adresse;

    @FXML
    void initialize() {

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Main.switchScene("Home Guest");
                } catch (SceneNotExistingException e) {
                    e.printStackTrace();
                }
            }
        });

        signUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                Agence.getListe_proprios()[Agence.getNb_proprios()] = new Personne(nom.getText(), prenom.getText(), adresse.getText(), phone.getText(), mail.getText(), pwd.getText());
                Main.connectedUser = Agence.getListe_proprios()[Agence.getNb_proprios()];

                Main.manager.removeScene("Signed In");

                try {
                    Main.manager.addScene("Signed In", FXMLLoader.load(getClass().getResource("Home_Member.fxml")));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    Main.manager.activateScene("Signed In");
                } catch (SceneNotExistingException e) {
                    e.printStackTrace();
                }

                try {
                    Main.switchScene("Signed In");
                } catch (SceneNotExistingException e) {
                    e.printStackTrace();
                }

            }
        });

    }
}
