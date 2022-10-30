package TP_GUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.HashMap;

class SceneNotExistingException extends Exception {
    public SceneNotExistingException(String message){
        super(message);
    }
}

public class SceneController {

    HashMap<String, Parent> scenes = new HashMap<>();
    private Scene main = new Scene(new HBox(),600, 550);

    public SceneController() throws Exception {
    }

    public void addScene(String name, Parent scene){
        scenes.put(name, scene);
    }

    public void removeScene(String name){
        scenes.remove(name);
    }

    public void activateScene(String name) throws SceneNotExistingException{
            if( scenes.containsKey(name) ) {
                main.setRoot(scenes.get(name));
            }else{
                throw new SceneNotExistingException("You need to add the scene before Activating it");
            }
    }

    public Scene getMain() {
        return main;
    }
}
