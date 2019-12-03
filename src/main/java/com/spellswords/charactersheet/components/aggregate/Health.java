package com.spellswords.charactersheet.components.aggregate;

import com.spellswords.charactersheet.components.base.UnderLabeledText;
import com.spellswords.charactersheet.utilities.StringConverters;
import javafx.beans.NamedArg;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.Objects;

public class Health extends Category {

    @FXML public UnderLabeledText current;
    public SimpleIntegerProperty currentInt = new SimpleIntegerProperty();
    @FXML public UnderLabeledText total;
    public SimpleIntegerProperty totalInt = new SimpleIntegerProperty();
    @FXML public UnderLabeledText level;
    public SimpleIntegerProperty levelInt = new SimpleIntegerProperty();
    @FXML public UnderLabeledText con;
    public SimpleIntegerProperty conInt = new SimpleIntegerProperty();
    @FXML public UnderLabeledText bonus;
    public SimpleIntegerProperty bonusInt = new SimpleIntegerProperty();
    @FXML public UnderLabeledText lost;
    public SimpleIntegerProperty lostInt = new SimpleIntegerProperty();
    @FXML public UnderLabeledText sub;
    public SimpleIntegerProperty subInt = new SimpleIntegerProperty();
    @FXML public UnderLabeledText temp;
    public SimpleIntegerProperty tempInt = new SimpleIntegerProperty();
    @FXML public UnderLabeledText thresh;
    public SimpleIntegerProperty threshInt = new SimpleIntegerProperty();
    @FXML public UnderLabeledText hpPer;
    public SimpleIntegerProperty hpPerInt = new SimpleIntegerProperty();


    public Health(@NamedArg(value = "prefWidth", defaultValue = "-1") double prefWidth,
                  @NamedArg(value = "text", defaultValue = "Hit Points") String text) {
        super(prefWidth, text);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Health.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    @FXML
    public void initialize() {
        super.initialize();
        current.input.textProperty().bindBidirectional(currentInt, StringConverters.StringIntConverter);
        total.input.textProperty().bindBidirectional(totalInt, StringConverters.StringIntConverter);
        level.input.textProperty().bindBidirectional(levelInt, StringConverters.StringIntConverter);
        con.input.textProperty().bindBidirectional(conInt, StringConverters.StringIntConverter);
        bonus.input.textProperty().bindBidirectional(bonusInt, StringConverters.StringIntConverter);
        lost.input.textProperty().bindBidirectional(lostInt, StringConverters.StringIntConverter);
        sub.input.textProperty().bindBidirectional(subInt, StringConverters.StringIntConverter);
        temp.input.textProperty().bindBidirectional(tempInt, StringConverters.StringIntConverter);
        thresh.input.textProperty().bindBidirectional(threshInt, StringConverters.StringIntConverter);
        hpPer.input.textProperty().bindBidirectional(hpPerInt, StringConverters.StringIntConverter);

        lostInt.addListener(this::updateCurrent);
        totalInt.addListener(this::updateCurrent);

        totalInt.addListener(this::updateTotal);
    }

    private void updateCurrent(ObservableValue<? extends Number> observableValue, Number oldVal, Number newVal) {
        if (Objects.equals(oldVal, newVal)) return;
        this.currentInt.set(totalInt.get() - lostInt.get());
    }

    private void updateTotal(ObservableValue<? extends Number> observableValue, Number oldVal, Number newVal) {
        if (Objects.equals(oldVal, newVal)) return;
        this.totalInt.set(levelInt.get() + conInt.get() + bonusInt.get());
    }
}
