package com.spellswords.charactersheet.components.aggregate;

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
import java.util.ArrayList;
import java.util.List;

public class SkillTable extends VBox {

    @FXML private Label label;

    @FXML private HBox nest;
    private ObservableList<Node> row = FXCollections.observableList(new ArrayList<>());

    public String gettext() {
        return text.get();
    }

    public SimpleStringProperty textProperty() {
        return text;
    }

    public SimpleStringProperty text = new SimpleStringProperty();

    public SimpleDoubleProperty prefWidth = new SimpleDoubleProperty();

    public List<Node> getRow() {
        return row;
    }




    public SkillTable(@NamedArg(value = "prefWidth", defaultValue = "-1") double prefWidth,
                      @NamedArg(value = "text", defaultValue = "default") String text) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SkillTable.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
//
//        this.label.textProperty().bindBidirectional(this.text);
//        this.text.set(text);
//
//        if (prefWidth == -1) {
//            this.label.textProperty().addListener(((observable, oldValue, newValue) -> {
//
//                this.setMinComputedWidthByLabel();
//            }));
//        }
//
//        this.row.addListener((ListChangeListener<? super Node>) (obs) -> {
//            while(obs.next()) {
//                this.nest.getChildren().removeAll(obs.getRemoved());
//                this.nest.getChildren().addAll(obs.getAddedSubList());
//            }
//        });
//
//        this.prefWidth.set(prefWidth);
//        this.setMinWidth(prefWidth);
//        System.err.println("after?");

    }

    public void settext(String txt) {
        text.set(txt);
    }

    @FXML
    public void initialize() {
//        if (this.getMinWidth() == -1) this.setMinComputedWidthByLabel();
    }

    public void setMinComputedWidthByLabel() {
        var tmp = new Text(label.getText() + "X");
        this.setMinWidth(tmp.getLayoutBounds().getWidth());
    }
}
