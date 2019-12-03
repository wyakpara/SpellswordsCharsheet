package com.spellswords.charactersheet.logic.aggregate;

import com.spellswords.charactersheet.components.aggregate.Category;
import com.spellswords.charactersheet.components.aggregate.Fatigue;
import com.spellswords.charactersheet.components.base.UnderLabeledText;
import com.spellswords.charactersheet.logic.tabs.JavaFxSerializable;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Fatigue")
@XmlType(propOrder = {"current", "level", "con", "bonus", "lost", "sub", "temp"})
public class FatigueModel implements JavaFxSerializable<FatigueModel, Fatigue> {

    public SimpleStringProperty current = new SimpleStringProperty();
    public SimpleStringProperty level = new SimpleStringProperty();
    public SimpleStringProperty con = new SimpleStringProperty();
    public SimpleStringProperty bonus = new SimpleStringProperty();
    public SimpleStringProperty lost = new SimpleStringProperty();
    public SimpleStringProperty sub = new SimpleStringProperty();
    public SimpleStringProperty temp = new SimpleStringProperty();

    private Fatigue fatigue;


    @Override
    public void copy(FatigueModel toCopy) {
        current.set(toCopy.current.get());
        level.set(toCopy.level.get());
        con.set(toCopy.con.get());
        bonus.set(toCopy.bonus.get());
        lost.set(toCopy.lost.get());
        sub.set(toCopy.sub.get());
        temp.set(toCopy.temp.get());

    }

    @Override
    public void bind(Fatigue toBind) {
        current.bindBidirectional(toBind.current.input.textProperty());
        level.bindBidirectional(toBind.level.input.textProperty());
        con.bindBidirectional(toBind.con.input.textProperty());
        bonus.bindBidirectional(toBind.bonus.input.textProperty());
        lost.bindBidirectional(toBind.lost.input.textProperty());
        sub.bindBidirectional(toBind.sub.input.textProperty());
        temp.bindBidirectional(toBind.temp.input.textProperty());
        this.fatigue = toBind;

    }

    @Override
    public void clearBindings() {
        current.unbindBidirectional(fatigue.current.input.textProperty());
        level.unbindBidirectional(fatigue.level.input.textProperty());
        con.unbindBidirectional(fatigue.con.input.textProperty());
        bonus.unbindBidirectional(fatigue.bonus.input.textProperty());
        lost.unbindBidirectional(fatigue.lost.input.textProperty());
        sub.unbindBidirectional(fatigue.sub.input.textProperty());
        temp.unbindBidirectional(fatigue.temp.input.textProperty());
    }
}
