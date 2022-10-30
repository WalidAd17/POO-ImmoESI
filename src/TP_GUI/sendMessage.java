package TP_GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import TP_KERNEL.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class sendMessage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea message;

    @FXML
    private Button sendMessage;

    @FXML
    void initialize() {
        assert message != null : "fx:id=\"message\" was not injected: check your FXML file 'send_Message.fxml'.";
        assert sendMessage != null : "fx:id=\"sendMessage\" was not injected: check your FXML file 'send_Message.fxml'.";
    }

    @FXML
    public void message(ActionEvent actionEvent) {
        Main.bienAffiché.AddComment(new Message(message.getText(), Main.bienAffiché ,Main.connectedUser));
        try {
            Main.manager.removeScene("Show");
            Main.manager.addScene("Show", FXMLLoader.load(Main.class.getResource("Show_Bien.fxml")));
            Main.switchScene("Show");
        } catch (IOException | SceneNotExistingException e) {
            e.printStackTrace();
        }
    }
}
