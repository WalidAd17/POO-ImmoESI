package TP_GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import TP_KERNEL.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import javax.swing.*;

public class add {

    ObservableList<Tdb> tdb_cb = FXCollections.observableArrayList();
    ObservableList<Transaction> transaction_cb= FXCollections.observableArrayList();
    ObservableList<Wilaya> wil= FXCollections.observableArrayList();
    ObservableList<Type> sidu= FXCollections.observableArrayList();

    @FXML
    private Button addBien;

    @FXML
    private ChoiceBox<Tdb> tdb;

    @FXML
    private ChoiceBox<Wilaya> wilaya;

    @FXML
    private ChoiceBox<Transaction> transaction;

    @FXML
    private TextField address;

    @FXML
    private TextArea Description;

    @FXML
    private TextField nbPiec;

    @FXML
    private TextField etage;

    @FXML
    private CheckBox neg;

    @FXML
    private CheckBox meu;

    @FXML
    private TextField pi;

    @FXML
    private ChoiceBox<Type> sdu;

    @FXML
    private TextField sup;

    @FXML
    private TextField stat;

    @FXML
    private CheckBox jardin;

    @FXML
    private CheckBox piscine;

    @FXML
    private TextField nbg;

    @FXML
    private TextField nbf;

    @FXML
    void initialize() throws SceneNotExistingException {
        if(Main.connectedUser == null){
            Main.switchScene("Home Guest");
        }

        setChoicers();

        addBien.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Date date = new Date();
                int inter = Agence.add_good(tdb.getSelectionModel().getSelectedIndex(), address.getText(), Description.getText(), Integer.parseInt(nbPiec.getText()), Integer.parseInt(etage.getText()), wilaya.getSelectionModel().getSelectedItem(), transaction.getSelectionModel().getSelectedItem(), new java.util.Date().toString(), Boolean.parseBoolean(String.valueOf(meu.selectedProperty())), new Prix(Integer.parseInt(pi.getText()), Boolean.parseBoolean(String.valueOf(neg.selectedProperty()))), sdu.getSelectionModel().getSelectedItem(), Integer.parseInt(sup.getText()), stat.getText(), Integer.parseInt(nbf.getText()), Integer.parseInt(nbg.getText()), Boolean.parseBoolean(jardin.selectedProperty().toString()), Boolean.parseBoolean(piscine.selectedProperty().toString()));
                Agence.getListe_biens()[inter].calc_prix();
                try {
                    Main.manager.removeScene("Signed In");
                    Main.manager.addScene("Signed In", FXMLLoader.load(getClass().getResource("Home_Member.fxml")));
                    Main.switchScene("Signed In");
                } catch (SceneNotExistingException | IOException e) {
                    e.printStackTrace();
                }
            }
        });



    }

    void setChoicers(){

        tdb_cb.addAll(Tdb.values());
        transaction_cb.addAll(Transaction.values());
        wil.addAll(Wilaya.values());
        sidu.addAll(Type.values());

        tdb.setItems(tdb_cb);
        tdb.getSelectionModel().selectFirst();

        transaction.setItems(transaction_cb);
        transaction.getSelectionModel().selectFirst();

        wilaya.setItems(wil);
        wilaya.getSelectionModel().selectFirst();

        sdu.setItems(sidu);
        sdu.getSelectionModel().selectFirst();
    }
}
