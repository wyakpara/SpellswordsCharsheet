package com.spellswords.charactersheet.components.base;

import javafx.beans.NamedArg;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class UnderLabeledNode extends VBox {

    @FXML public Label label;
    @FXML public HBox nest;

    public ObservableList<Node> over = FXCollections.observableList(new ArrayList<>());

    public ObservableList<Node> getOver() {
        return over;
    }

    public SimpleStringProperty textProperty() {
        return text;
    }

    public SimpleStringProperty text = new SimpleStringProperty();

    public SimpleDoubleProperty prefWidth = new SimpleDoubleProperty();

    public UnderLabeledNode(@NamedArg(value = "prefWidth", defaultValue = "-1") double prefWidth,
                            @NamedArg(value = "text", defaultValue = "default") String text) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UnderLabeledNode.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }



        this.over.addListener((ListChangeListener<? super Node>) (obs) -> {
            while (obs.next()) {
                this.nest.getChildren().removeAll(obs.getRemoved());
                this.nest.getChildren().addAll(obs.getAddedSubList());
            }
        });

        this.label.textProperty().bindBidirectional(this.text);
        this.text.set(text);

        if (prefWidth == -1) {
            this.setMinComputedWidthByLabel();
        } else {
            this.prefWidth.set(prefWidth);
            this.setMinWidth(prefWidth);

        }


    }

    @FXML
    public void initialize() {
        if (this.getMinWidth() == -1) this.setMinComputedWidthByLabel();
    }

    public void setMinComputedWidthByLabel() {
        var tmp = new Text(label.getText() + "X");
        this.setMinWidth(tmp.getLayoutBounds().getWidth());
    }
}
