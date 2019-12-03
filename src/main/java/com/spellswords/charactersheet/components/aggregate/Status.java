package com.spellswords.charactersheet.components.aggregate;

import javafx.beans.NamedArg;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class Status extends HBox {

    @FXML public Label effect;
    @FXML public TextField severity;
    @FXML public TextField consequence;
    @FXML public TextField ffQuestion;
    @FXML public TextField durationOne;
    @FXML public TextField durationTwo;

    private SimpleStringProperty effectProperty = new SimpleStringProperty();

    public Status(@NamedArg(value = "effect", defaultValue = "N/A") String effect) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Status.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.effect.setText(effect);
    }

    @FXML
    public void initialize() {
    }
}
