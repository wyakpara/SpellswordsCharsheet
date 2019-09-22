package com.spellswords.charactersheet.components.base;

import javafx.beans.NamedArg;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UnderLabeledChoiceBox extends UnderLabeledNode {

    @FXML public ChoiceBox<String> choiceBox;

    public ObservableList<String> choices = FXCollections.observableList(new ArrayList<>());

    public List<String> getChoices() {
        return choices;
    }


    public UnderLabeledChoiceBox(@NamedArg(value = "prefWidth", defaultValue = "-1") double prefWidth,
                                 @NamedArg(value = "text", defaultValue = "default") String text) {
        super(prefWidth, text);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UnderLabeledChoiceBox.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.choices.addListener((ListChangeListener<? super String>) (obs) -> {
            while (obs.next()) {
                this.choiceBox.getItems().removeAll(obs.getRemoved());
                this.choiceBox.getItems().addAll(obs.getAddedSubList());
            }
        });
    }

    @FXML
    public void initialize() {
        if (Objects.isNull(choiceBox)) {
            super.initialize();
            return;
        }
        choiceBox.minWidthProperty().bind(super.minWidthProperty());
        choiceBox.selectionModelProperty().addListener((obs, oldVal, newVal) -> {
            computedResize();
        });

        computedResize();
    }

    public void computedResize() {
        var choice = choiceBox.getSelectionModel().getSelectedItem();

        var tmp = new Text(choice + "XYZ");
        double proposedWidth = tmp.getLayoutBounds().getWidth();
        choiceBox.setPrefWidth(proposedWidth);
    }
}
