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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

import javax.swing.*;

public class ControllerHomeMember {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    ObservableList<HBox> items = FXCollections.observableArrayList();
    ObservableList<HBox> resultList = FXCollections.observableArrayList();
    ObservableList<Transaction> trns = FXCollections.observableArrayList();
    ObservableList<Wilaya> wil = FXCollections.observableArrayList();
    ObservableList<Tdb> tdb_CB = FXCollections.observableArrayList();
    ObservableList<HBox> propers = FXCollections.observableArrayList();
    ObservableList<HBox> mesBiens = FXCollections.observableArrayList();
    ObservableList<HBox> archiveLister = FXCollections.observableArrayList();
    ObservableList<HBox> messages = FXCollections.observableArrayList();

    @FXML
    private Label onBoarding;

    @FXML
    private Button logOut;

    @FXML
    private ListView<HBox> biens;

    @FXML
    private Button add;

    @FXML
    private ChoiceBox<Transaction> transaction;

    @FXML
    private ChoiceBox<Tdb> tdb;

    @FXML
    private ChoiceBox<Wilaya> wilaya;

    @FXML
    private TextField maxPrice;

    @FXML
    private TextField minPrice;

    @FXML
    private TextField supMin;

    @FXML
    private TextField nbPicMin;

    @FXML
    private Button search;

    @FXML
    private ListView<HBox> searchResult;

    @FXML
    private Tab props;

    @FXML
    private Tab archive;

    @FXML
    private ListView<HBox> proprio;

    @FXML
    private ListView<HBox> mesBiensListe;

    @FXML
    private ListView<HBox> archiveList;

    @FXML
    private Tab message;

    @FXML
    private ListView<HBox> messagesList;

    int i = 0;

    @FXML
    void updateMessages(){
        messages.clear();
        for ( int l = 0; l < Agence.getNb_biens(); l++)
        {
            for ( i = 0; i < Agence.getListe_biens()[l].Comments.size(); i++){
            if ( Agence.getListe_biens()[i].isArchived() ){
                continue;
            }

            Pane spacer = new Pane();
            HBox.setHgrow(spacer, Priority.ALWAYS);
            spacer.setMinSize(10, 1);

            Message msg = Agence.getListe_biens()[i].Comments.get(l);
            HBox item = new HBox(8);
            item.getChildren().add(new Label("Concerned :" + msg.getGood().getPropriétaire().getNom()));
            item.getChildren().add(new Label("Message :" + msg.getMsg()));
            item.getChildren().add(new Label("Sender : " + msg.getProp().getNom()));
            item.getChildren().add(spacer);
            messages.add(item);

        }}

        messagesList.setItems(messages);
    }

    @FXML
    void updateArchive(){
        archiveLister.clear();
        for ( i = 0; i < Agence.getNb_archives(); i++){
            if ( !Agence.getListe_archives()[i].isArchived() ){
                continue;
            }

            Pane spacer = new Pane();
            HBox.setHgrow(spacer, Priority.ALWAYS);
            spacer.setMinSize(10, 1);

            Bien bien = Agence.getListe_biens()[i];
            HBox item = new HBox(8);
            item.getChildren().add(new Label(bien.getPropriétaire().getNom() + " " + bien.getPropriétaire().getPrenom()));
            item.getChildren().add(new Label(bien.getWilaya().toString()));
            item.getChildren().add(new Label(Price(i)));
            item.getChildren().add(spacer);
            archiveLister.add(item);

        }

        archiveList.setItems(archiveLister);
    }

    @FXML
    void UpdateSearch(){

        resultList.clear();
        minPrice.setText("0");
        maxPrice.setText("0");
        nbPicMin.setText("0");
        supMin.setText("0");

        IntContainer nb = new IntContainer(0);
        critere Searching = new critere(transaction.getSelectionModel().getSelectedItem(), wilaya.getSelectionModel().getSelectedItem(), Integer.parseInt(maxPrice.getText()), Integer.parseInt(minPrice.getText()), Integer.parseInt(supMin.getText()), Integer.parseInt(nbPicMin.getText()), tdb.getSelectionModel().getSelectedItem());
        Searching.setNb_piece_min(-1);
        Searching.setPrix_max(-1);
        Searching.setPrix_min(-1);
        Searching.setSup_min(-1);
        Bien[] result = Agence.recherche(Searching, nb);
        for (i = 0; i < nb.getI(); i++){
            if (result[i].isArchived()) {
                continue;
            }
            Pane spacerr = new Pane();
            HBox.setHgrow(spacerr, Priority.ALWAYS);
            spacerr.setMinSize(10, 1);

            Button ShowButton = new Button("Show Bien");
            ShowButton.setId(Integer.toString(i));
            ShowButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {

                }
            });

            ShowButton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        Main.showBien(actionEvent);
                    } catch (IOException | SceneNotExistingException e) {
                        e.printStackTrace();
                    }
                }

            });

            Bien bien = result[i];
            HBox searchItem = new HBox(8);
            searchItem.getChildren().add(new Label(bien.getPropriétaire().getNom() + " " + bien.getPropriétaire().getPrenom()));
            searchItem.getChildren().add(new Label(bien.getWilaya().toString()));
            searchItem.getChildren().add(new Label(Price(i)));
            searchItem.getChildren().add(spacerr);
            searchItem.getChildren().add(ShowButton);
            resultList.add(searchItem);

        }

        searchResult.getItems().removeAll();

        searchResult.setItems(resultList);

        minPrice.setText("");
        maxPrice.setText("");
        nbPicMin.setText("");
        supMin.setText("");

    }

    @FXML
    void SwitchScreen(String name) throws SceneNotExistingException {
        Main.switchScene(name);
    }

    @FXML
    void critUpdate(){

        trns.addAll(Transaction.values());
        tdb_CB.addAll(Tdb.values());
        tdb_CB.addAll(Tdb.values());
        wil.addAll(Wilaya.values());
        transaction.setItems(trns);
        tdb.setItems(tdb_CB);
        wilaya.setItems(wil);
        transaction.getSelectionModel().selectLast();
        tdb.getSelectionModel().selectLast();
        wilaya.getSelectionModel().selectLast();

    }

    @FXML
    public void UpdateBien(){
        biens.setItems(items);
    }

    @FXML
    void initialize() throws SceneNotExistingException {
        updateMyBiens();
        UpdateBien();
        if(Main.connectedUser == null){
            Main.switchScene("Home Guest");
        }

        if ( Main.connectedUser.getNom() == "admin" ){
            archive.setDisable(false);
            props.setDisable(false);
            message.setDisable(false);
        }

        onBoarding.setText("Welcome " + Main.connectedUser.getNom() + " " + Main.connectedUser.getPrenom());

        logOut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Main.connectedUser = null;
                try {
                    Main.switchScene("Home Guest");
                } catch (SceneNotExistingException e) {
                    e.printStackTrace();
                }
            }
        });

        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Main.manager.removeScene("Add");
                    Main.manager.addScene("Add", FXMLLoader.load(getClass().getResource("add.fxml")));
                    Main.switchScene("Add");
                } catch (SceneNotExistingException | IOException e) {
                    e.printStackTrace();
                }
            }
        });
        for ( i = 0; i < Agence.getNb_biens(); i++){
            if ( Agence.getListe_biens()[i].isArchived() ){
                continue;
            }

            Pane spacer = new Pane();
            HBox.setHgrow(spacer, Priority.ALWAYS);
            spacer.setMinSize(10, 1);

            Button ShowButton = new Button("Show Bien");
            ShowButton.setId(Integer.toString(i));
            ShowButton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        Main.showBien(actionEvent);
                    } catch (IOException | SceneNotExistingException e) {
                        e.printStackTrace();
                    }
                }

            });

            Bien bien = Agence.getListe_biens()[i];
            HBox item = new HBox(8);
            item.getChildren().add(new Label(bien.getPropriétaire().getNom() + " " + bien.getPropriétaire().getPrenom()));
            item.getChildren().add(new Label(bien.getWilaya().toString()));
            item.getChildren().add(new Label(Price(i)));
            item.getChildren().add(spacer);
            item.getChildren().add(ShowButton);
            items.add(item);

        }

        UpdateBien();
    }

    String Price (int k){

        Double inter;
        inter = Agence.getListe_biens()[k].getPrix().getPf();
        return String.format("%.1f", inter);

    }

    @FXML
    void updateProps (){
        propers.clear();
        for (int j = 0; j< Agence.getNb_proprios(); j++) {
            HBox hbox = new HBox(10);
            hbox.getChildren().addAll(  new Label(Agence.getListe_proprios()[j].getNom() + " " + Agence.getListe_proprios()[j].getPrenom()));
            propers.add(hbox);
        }

        proprio.setItems(propers);
    }

    public void updateMyBiens(){
        mesBiens.clear();
        for ( i = 0; i < Main.connectedUser.getNb_bien(); i++){
                if(Main.connectedUser.getList_bien()[i].isArchived()) {
                    continue;
                }
                System.out.print("Hada maRah Archivé " + i);
                Pane spacer = new Pane();
                HBox.setHgrow(spacer, Priority.ALWAYS);
                spacer.setMinSize(10, 1);

                Button deleteButton = new Button("Delete");
                deleteButton.setId(Integer.toString(i));

                deleteButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        try {
                            Main.deleteBien(actionEvent);
                        } catch (SceneNotExistingException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

                Button ShowButton = new Button("Show Bien");
                ShowButton.setId(Integer.toString(i));
                ShowButton.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent actionEvent) {
                        try {
                            Main.showBien(actionEvent);
                        } catch (IOException | SceneNotExistingException e) {
                            e.printStackTrace();
                        }
                    }

                });

                Bien bien = Main.connectedUser.getList_bien()[i];
                HBox item = new HBox(8);
                item.getChildren().add(new Label(bien.getPropriétaire().getNom() + " " + bien.getPropriétaire().getPrenom()));
                item.getChildren().add(new Label(bien.getWilaya().toString()));
                item.getChildren().add(new Label(Price(i)));
                item.getChildren().add(spacer);
                item.getChildren().add(ShowButton);
                item.getChildren().add(deleteButton);
                mesBiens.add(item);
        }
        mesBiensListe.setItems(mesBiens);
        mesBiensListe.refresh();
    }
}
