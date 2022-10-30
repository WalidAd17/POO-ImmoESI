package TP_GUI;

import TP_KERNEL.Agence;
import TP_KERNEL.Bien;
import TP_KERNEL.Personne;
import TP_KERNEL.Transaction;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class Main extends Application {
    static Personne connectedUser;
    static SceneController manager;
    static Bien bienAffiché;

    static {
        try {
            manager = new SceneController();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Main() throws Exception {
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        manager.addScene("Home Guest", FXMLLoader.load(getClass().getResource("Home_Guest.fxml")));
        manager.activateScene("Home Guest");
        manager.addScene("Sign In", FXMLLoader.load(getClass().getResource("Sign_IN.fxml")));
        manager.addScene("Sign Up", FXMLLoader.load(getClass().getResource("Sign_UP.fxml")));
        manager.addScene("Add", FXMLLoader.load(getClass().getResource("add.fxml")));
        primaryStage.setTitle("ImmoESI");
        primaryStage.setScene(manager.getMain());
        primaryStage.show();

    }

    public static ObservableList<Transaction> trns;

    public static void main(String[] args) {
        Agence.main(args);
        bienAffiché = Agence.getListe_biens()[0];
        launch(args);
    }

    public static void switchScene(String name) throws SceneNotExistingException {
            manager.activateScene(name);
    }

    public Personne getConnectedUser() {
        return connectedUser;
    }

    public static void setConnectedUser(Personne User) {
        connectedUser = User;
    }

    public static void showBien(ActionEvent actionEvent) throws IOException, SceneNotExistingException {
        Button Instigator = (Button)actionEvent.getSource();
        System.out.print(Integer.parseInt(Instigator.getId()));
        bienAffiché = Agence.getListe_biens()[Integer.parseInt(Instigator.getId())];
        Main.manager.removeScene("Show");
        Main.manager.addScene("Show", FXMLLoader.load(Main.class.getResource("Show_Bien.fxml")));
        switchScene("Show");
    }

    public static void deleteBien(ActionEvent actionEvent) throws SceneNotExistingException, IOException {
        Button Instigator = (Button)actionEvent.getSource();
        Agence.getListe_biens()[Integer.parseInt(Instigator.getId())].setArchived(true);
        Main.manager.removeScene("Signed In");
        Main.manager.addScene("Signed In", FXMLLoader.load(Main.class.getResource("Home_Member.fxml")));
        switchScene("Signed In");
    }
}