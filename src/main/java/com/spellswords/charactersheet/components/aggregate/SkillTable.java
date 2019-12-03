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
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SkillTable extends VBox {

    @FXML private Label label;

    @FXML private VBox nest;
    public ObservableList<Skill> skills = FXCollections.observableList(new ArrayList<>());

    public String gettext() {
        return text.get();
    }

    public SimpleStringProperty textProperty() {
        return text;
    }

    public SimpleStringProperty text = new SimpleStringProperty();

    public SimpleDoubleProperty prefWidth = new SimpleDoubleProperty();

    public List<Skill> getSkills() {
        return skills;
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
    }

    public void settext(String txt) {
        text.set(txt);
    }

    @FXML
    public void initialize() {
        nest.getChildren().stream().filter(Skill.class::isInstance).forEachOrdered(x -> skills.add((Skill) x));
//        for (Node node : nest.getChildren()) {
//            if (node instanceof Skill) {
//                skills.add((Skill) node);
//            }
//        }
        nest.getChildren().addListener((ListChangeListener<? super Node>) observable -> {
            while (observable.next()) {
                observable.getAddedSubList().stream().filter(Skill.class::isInstance).forEachOrdered(x -> skills.add((Skill) x));
                observable.getRemoved().stream().filter(Skill.class::isInstance).forEachOrdered(skills::remove);
            }
        });
    }

    public void setMinComputedWidthByLabel() {
        var tmp = new Text(label.getText() + "X");
        this.setMinWidth(tmp.getLayoutBounds().getWidth());
    }
}
