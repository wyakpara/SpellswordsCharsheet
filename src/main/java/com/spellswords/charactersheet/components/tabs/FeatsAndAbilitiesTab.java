package com.spellswords.charactersheet.components.tabs;

import com.spellswords.charactersheet.FxmlControl;
import com.spellswords.charactersheet.components.aggregate.Feat;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class FeatsAndAbilitiesTab implements Initializable {

    @FXML public HBox title;
    @FXML public Button addBtn;
    @FXML public GridPane featGrid;

    public ObservableList<Feat> feats = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title.prefWidthProperty().bind(featGrid.widthProperty());

        feats.addListener((ListChangeListener<? super Feat>) (obs) -> {
            while (obs.next()) {
                for (Feat feat : obs.getAddedSubList()){
                    /* get the row of the add Button atm */
                    int row = GridPane.getRowIndex(addBtn);
                    /* add the feat at that row */
                    featGrid.add(feat.cost, 0, row);
                    featGrid.add(feat.type, 1, row);
                    featGrid.add(feat.name, 2, row);
                    featGrid.add(feat.description, 3, row);
                    /* Move the button */
                    GridPane.setRowIndex(addBtn, row+1);
                }
            }
        });
    }

    public void addFeat(ActionEvent actionEvent) {
        feats.add(new Feat());
    }
}
