package com.spellswords.charactersheet.components.aggregate;

import com.spellswords.charactersheet.utilities.StringConverters;
import javafx.beans.NamedArg;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import org.controlsfx.tools.Borders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Skill extends HBox {

    @FXML public Label label;
    @FXML public TextField score;
    private SimpleIntegerProperty scoreProperty = new SimpleIntegerProperty();
    @FXML public TextField mod;
    private SimpleIntegerProperty modProperty = new SimpleIntegerProperty();
    @FXML public TextField base;
    private SimpleIntegerProperty baseProperty = new SimpleIntegerProperty();
    @FXML public TextField bonus;
    private SimpleIntegerProperty bonusProperty = new SimpleIntegerProperty();
    @FXML public TextField temp;
    private SimpleIntegerProperty tempProperty = new SimpleIntegerProperty();
    @FXML public TextField other;
    private SimpleIntegerProperty otherProperty = new SimpleIntegerProperty();

    private SimpleStringProperty skillProperty = new SimpleStringProperty();

    public Skill(@NamedArg(value = "skill", defaultValue = "N/A") String skill) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Skill.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        skillProperty.bindBidirectional(label.textProperty());
        skillProperty.set(skill);

        score.textProperty().bindBidirectional(scoreProperty, StringConverters.StringIntConverter);
        mod.textProperty().bindBidirectional(modProperty, StringConverters.StringIntConverter);
        base.textProperty().bindBidirectional(baseProperty, StringConverters.StringIntConverter);
        bonus.textProperty().bindBidirectional(bonusProperty, StringConverters.StringIntConverter);
        temp.textProperty().bindBidirectional(tempProperty, StringConverters.StringIntConverter);
        other.textProperty().bindBidirectional(otherProperty, StringConverters.StringIntConverter);

        scoreProperty.addListener(((observable, oldValue, newValue) -> {
            modProperty.setValue(newValue.intValue() + 1);
        }));
        baseProperty.addListener(((observable, oldValue, newValue) -> {
            int modValue = (newValue.intValue() - 10) / 2;
            modProperty.setValue(modValue);
        }));
        bonusProperty.addListener(((observable, oldValue, newValue) -> {
            tempProperty.setValue(newValue.intValue() + 1);
        }));
        tempProperty.addListener(((observable, oldValue, newValue) -> {
            otherProperty.setValue(newValue.intValue() + 1);
        }));

    }

    @FXML
    public void initialize() {
        score.setText("0");
        mod.setText("0");
        base.setText("0");
        bonus.setText("0");
        temp.setText("0");
        other.setText("0");

    }
}
