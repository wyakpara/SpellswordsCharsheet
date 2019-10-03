package com.spellswords.charactersheet.logic.aggregate;

import com.spellswords.charactersheet.components.aggregate.ClassBox;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.Objects;

@XmlRootElement
@XmlType(propOrder = {"classBoxField", "die", "level", "skill", "bab"})
public class ClassBoxModel implements Serializable {

//    @XmlTransient
//    private final SimpleIntegerProperty classNum = new SimpleIntegerProperty();

    @XmlTransient
    @FXML public SimpleStringProperty classBoxField = new SimpleStringProperty();
    @XmlTransient
    @FXML public SimpleStringProperty die = new SimpleStringProperty();
    @XmlTransient
    @FXML public SimpleStringProperty level = new SimpleStringProperty();
    @XmlTransient
    @FXML public SimpleStringProperty skill = new SimpleStringProperty();
    @XmlTransient
    @FXML public SimpleStringProperty bab = new SimpleStringProperty();

    @XmlTransient
    private ClassBox classBox;

    public void setClassBox(ClassBox classBox) {
        this.classBox = classBox;
    }

    public String getClassBoxField() {
        return classBoxField.get();
    }

    public void setClassBoxField(String classBoxField) {
        this.classBoxField.set(classBoxField);
    }

    public String getDie() {
        return die.get();
    }

    public void setDie(String die) {
        this.die.set(die);
    }

    public String getLevel() {
        return level.get();
    }

    public void setLevel(String level) {
        this.level.set(level);
    }

    public String getSkill() {
        return skill.get();
    }

    public void setSkill(String skill) {
        this.skill.set(skill);
    }

    public String getBab() {
        return bab.get();
    }

    public void setBab(String bab) {
        this.bab.set(bab);
    }

    public void copy(ClassBoxModel model) {
        classBoxField.set(model.classBoxField.get());
        die.set(model.die.get());
        level.set(model.level.get());
        skill.set(model.skill.get());
        bab.set(model.bab.get());
    }

    public void bind(ClassBox clsBox) {
        classBoxField.bindBidirectional(clsBox.classBox.input.textProperty());
        die.bindBidirectional(clsBox.die.choiceBox.valueProperty());
        level.bindBidirectional(clsBox.level.input.textProperty());
        skill.bindBidirectional(clsBox.skill.input.textProperty());
        bab.bindBidirectional(clsBox.bab.choiceBox.valueProperty());
        this.classBox = clsBox;
    }

    public void clearBindings() {
        Objects.requireNonNull(classBox);
        classBoxField.bindBidirectional(classBox.classBox.input.textProperty());
        die.bindBidirectional(classBox.die.choiceBox.valueProperty());
        level.bindBidirectional(classBox.level.input.textProperty());
        skill.bindBidirectional(classBox.skill.input.textProperty());
        bab.bindBidirectional(classBox.bab.choiceBox.valueProperty());
        this.classBox = null;
    }
}
