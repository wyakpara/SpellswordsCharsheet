package com.spellswords.charactersheet.components.aggregate;

import com.spellswords.charactersheet.components.base.UnderLabeledText;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class AppearanceBox extends VBox {

    @FXML private UnderLabeledText gender;
    @FXML private UnderLabeledText age;
    @FXML private UnderLabeledText height;
    @FXML private UnderLabeledText weight;
    @FXML private UnderLabeledText eyes;
    @FXML private UnderLabeledText hair;
    @FXML private UnderLabeledText skin;
    @FXML private UnderLabeledText demeanor;


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
