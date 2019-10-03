package com.spellswords.charactersheet.components.base;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

public class AutosizeTextField extends TextField {

    public AutosizeTextField() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AutosizeTextField.fxml"));
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
        this.textProperty().addListener((obs, oldVal, newVal) -> {
            computedResize();
        });
        computedResize();

        this.getStyleClass().addListener((ListChangeListener<? super String>) observable -> {
            while (observable.next()) {
                String unmod = "unmodifiable";
                if (observable.getAddedSubList().contains(unmod)) {
                    this.setEditable(false);
                } else if (observable.getRemoved().contains(unmod)) {
                    this.setEditable(true);
                }
            }
        });
    }

    public void computedResize() {
        var tmp = new Text(this.getText() + "XYZ");
        double proposedWidth = tmp.getLayoutBounds().getWidth();
        this.setPrefWidth(proposedWidth);
    }
}
