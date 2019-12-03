package com.spellswords.charactersheet.components.aggregate;

import com.spellswords.charactersheet.utilities.StringConverters;
import javafx.beans.NamedArg;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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

    public SimpleStringProperty skillProperty = new SimpleStringProperty();

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


        modProperty.addListener(this::updateScore);
        scoreProperty.addListener(this::updateMod);;
    }

    private void updateScore(ObservableValue<? extends Number> observableValue, Number oldVal, Number newVal) {
        if (oldVal != newVal) {
            scoreProperty.set(baseProperty.get() + bonusProperty.get() + tempProperty.get() + otherProperty.get());
        }
    }

    private void updateMod(ObservableValue<? extends Number> observableValue, Number oldVal, Number newVal) {
        if (oldVal != newVal) {
            modProperty.set((int) Math.floor(scoreProperty.get() - 10 /2));
        }
    }

    @FXML
    public void initialize() {
        score.setText("0");
        mod.setText("0");
        base.setText("0");
        bonus.setText("0");
        temp.setText("0");

    }
}
