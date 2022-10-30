package TP_GUI;

import TP_KERNEL.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Sign_In {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button back;

    @FXML
    private Button signIn;

    @FXML
    private TextField mail;

    @FXML
    private TextField pwd;

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

        signIn.setOnAction(new EventHandler<ActionEvent>() {


            @Override
            public void handle(ActionEvent actionEvent) {
                Reason reason = new Reason();
                if(Agence.connect(mail.getText(), pwd.getText(), reason)){
                    try {
                        Main.manager.removeScene("Signed In");
                        Main.manager.addScene("Signed In", FXMLLoader.load(getClass().getResource("Home_Member.fxml")));
                        Main.switchScene("Signed In");
                    } catch (SceneNotExistingException | IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    System.out.print(reason);
                    if( reason.getReason() == 1 ){
                        mail.setStyle("-fx-background-color: #FF0000; -fx-text-fill: #FFF");
                    }else if ( reason.getReason() == 3 ){
                        mail.setStyle("-fx-background-color: #FF0000; -fx-text-fill: #FFF");
                        pwd.setStyle("-fx-background-color: #FF0000; -fx-text-fill: #FFF");

                    }else if ( reason.getReason() == 2 ){
                        pwd.setStyle("-fx-background-color: #FF0000; -fx-text-fill: #FFF");
                    }
                }
            }
        });
    }
}