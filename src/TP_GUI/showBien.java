package TP_GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import TP_KERNEL.Agence;
import TP_KERNEL.Bien;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class showBien {

    public Bien referenced = Main.bienAffiché;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label onBoarding;

    @FXML
    private Label trns;

    @FXML
    private Label address;

    @FXML
    private Label props;

    @FXML
    private Label stat;

    @FXML
    private Label wil;

    @FXML
    private Label sup;

    @FXML
    private Label date;

    @FXML
    private Button back;

    @FXML
    private Button contact;

    @FXML
    private Label description;

    @FXML
    private ImageView image;

    @FXML
    private Button archive;

    @FXML
    void initialize() {
        assert onBoarding != null : "fx:id=\"onBoarding\" was not injected: check your FXML file 'Show_Bien.fxml'.";
        assert trns != null : "fx:id=\"trns\" was not injected: check your FXML file 'Show_Bien.fxml'.";
        assert address != null : "fx:id=\"address\" was not injected: check your FXML file 'Show_Bien.fxml'.";
        assert props != null : "fx:id=\"props\" was not injected: check your FXML file 'Show_Bien.fxml'.";
        assert stat != null : "fx:id=\"stat\" was not injected: check your FXML file 'Show_Bien.fxml'.";
        assert wil != null : "fx:id=\"wil\" was not injected: check your FXML file 'Show_Bien.fxml'.";
        assert sup != null : "fx:id=\"sup\" was not injected: check your FXML file 'Show_Bien.fxml'.";
        assert date != null : "fx:id=\"date\" was not injected: check your FXML file 'Show_Bien.fxml'.";
        assert back != null : "fx:id=\"back\" was not injected: check your FXML file 'Show_Bien.fxml'.";
        assert contact != null : "fx:id=\"contact\" was not injected: check your FXML file 'Show_Bien.fxml'.";
        assert description != null : "fx:id=\"description\" was not injected: check your FXML file 'Show_Bien.fxml'.";

        trns.setText(Main.bienAffiché.getTransaction().toString());
        address.setText(referenced.getAdresse().toString());
        props.setText(referenced.getPropriétaire().getNom() + " " + referenced.getPropriétaire().getPrenom());
        wil.setText(referenced.getWilaya().toString());
        sup.setText((Float.toString(referenced.getSuperficie())));
        date.setText(referenced.getDate());
        description.setText(referenced.getDescription());

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (Main.connectedUser == null){
                    try {
                        Main.switchScene("Home Guest");
                    } catch (SceneNotExistingException e) {
                        e.printStackTrace();
                    }
                }else{
                    try {
                        Main.manager.removeScene("Signed In");
                        Main.manager.addScene("Signed In", FXMLLoader.load(getClass().getResource("Home_Member.fxml")));
                        Main.switchScene("Signed In");
                    } catch (SceneNotExistingException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        if(Main.connectedUser != null) {
            if (Main.connectedUser.getNom() == "admin") {
                archive.setDisable(false);
            }
        }else{
            contact.setDisable(true);
        }

        contact.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Main.manager.addScene("Message", FXMLLoader.load(getClass().getResource("send_Message.fxml")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    Main.switchScene("Message");
                } catch (SceneNotExistingException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    @FXML
    void archive() throws SceneNotExistingException, IOException {
        if ( !referenced.isArchived() ){
            Agence.archiver(referenced);
            archive.setText("Un-archive");
        }else{
            referenced.setArchived(false);
            archive.setText("Archive");
        }
        Main.manager.removeScene("Signed In");
        Main.manager.addScene("Signed In", FXMLLoader.load(getClass().getResource("Home_Member.fxml")));
        Main.switchScene("Signed In");

        }
}