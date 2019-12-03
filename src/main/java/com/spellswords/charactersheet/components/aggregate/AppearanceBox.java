package com.spellswords.charactersheet.components.aggregate;

import com.spellswords.charactersheet.components.base.UnderLabeledText;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class AppearanceBox extends VBox {

    @FXML public UnderLabeledText gender;
    @FXML public UnderLabeledText age;
    @FXML public UnderLabeledText height;
    @FXML public UnderLabeledText weight;
    @FXML public UnderLabeledText eyes;
    @FXML public UnderLabeledText hair;
    @FXML public UnderLabeledText skin;
    @FXML public UnderLabeledText demeanor;


    public AppearanceBox() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AppearanceBox.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
    }
}
