package com.spellswords.charactersheet.components.aggregate;

import com.spellswords.charactersheet.components.base.UnderLabeledChoiceBox;
import com.spellswords.charactersheet.components.base.UnderLabeledText;
import javafx.beans.NamedArg;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import java.io.IOException;


public class ClassBox extends HBox {

    private final SimpleIntegerProperty classNum = new SimpleIntegerProperty();

    @FXML public UnderLabeledText classBox;
    @FXML public UnderLabeledChoiceBox die;
    @FXML public UnderLabeledText level;
    @FXML public UnderLabeledText skill;

    public ClassBox(@NamedArg(value = "classNum", defaultValue = "1") int classNum) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ClassBox.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.classNum.addListener(((observable, oldValue, newValue) -> {
            this.classBox.text.set(String.format("Class %s", this.classNum.get()));
        }));
        this.classNum.set(classNum);
    }

    @FXML
    public void initialize() {
    }
}
