package com.spellswords.charactersheet.components.aggregate;

import com.spellswords.charactersheet.utilities.StringConverters;
import javafx.beans.NamedArg;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class Feat extends HBox {

    @FXML public TextField cost;
    @FXML public ChoiceBox type;
    @FXML public TextField name;
    @FXML public TextField description;

    public Feat() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Feat.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        type.getItems().addAll("P", "M", "S", "R", "U");
    }

    @FXML
    public void initialize() {
    }
}
