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
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

import javax.xml.transform.Result;

public class ControllerHomeGuest {

    @FXML
    private ListView biens;

    @FXML
    private Button LogIn, SignUp;

    ObservableList<HBox> items = FXCollections.observableArrayList();
    ObservableList<HBox> resultList = FXCollections.observableArrayList();
    ObservableList<Transaction> trns = FXCollections.observableArrayList();
    ObservableList<Wilaya> wil = FXCollections.observableArrayList();
    ObservableList<Tdb> tdb_CB = FXCollections.observableArrayList();

    int i = 0;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<Wilaya> wilaya;

    @FXML
    private ChoiceBox<Tdb> tdb;

    @FXML
    private ChoiceBox<Transaction> transaction;

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
    void UpdateBien(){
        biens.setItems(items);
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
        wil.addAll(Wilaya.values());
        transaction.setItems(trns);
        tdb.setItems(tdb_CB);
        wilaya.setItems(wil);
        transaction.getSelectionModel().selectLast();
        tdb.getSelectionModel().selectLast();
        wilaya.getSelectionModel().selectLast();

    }

    @FXML
    void initialize() {

        LogIn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Main.switchScene("Sign In");
                } catch (SceneNotExistingException e) {
                    e.printStackTrace();
                }
            }
        }

        );

        SignUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Main.switchScene("Sign Up");
                } catch (SceneNotExistingException e) {
                    e.printStackTrace();
                }
            }
        });

        for ( i = 0; i < Agence.getNb_biens(); i++){

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
}
