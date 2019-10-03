package com.spellswords.charactersheet.logic.aggregate;

import com.spellswords.charactersheet.components.aggregate.ClassBox;
import com.spellswords.charactersheet.components.base.UnderLabeledChoiceBox;
import com.spellswords.charactersheet.components.base.UnderLabeledText;
import com.spellswords.charactersheet.logic.tabs.VitalsModel;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

@XmlRootElement
@XmlType(propOrder = {"classBox", "die", "level", "skill", "bab"})
public class ClassBoxModel implements Serializable {

//    @XmlTransient
//    private final SimpleIntegerProperty classNum = new SimpleIntegerProperty();

    @XmlTransient
    @FXML public SimpleStringProperty classBox = new SimpleStringProperty();
    @XmlTransient
    @FXML public SimpleStringProperty die = new SimpleStringProperty();
    @XmlTransient
    @FXML public SimpleStringProperty level = new SimpleStringProperty();
    @XmlTransient
    @FXML public SimpleStringProperty skill = new SimpleStringProperty();
    @XmlTransient
    @FXML public SimpleStringProperty bab = new SimpleStringProperty();

    public String getClassBox() {
        return classBox.get();
    }

    public void setClassBox(String classBox) {
        this.classBox.set(classBox);
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
        classBox.set(model.classBox.get());
        die.set(model.die.get());
        level.set(model.level.get());
        skill.set(model.skill.get());
        bab.set(model.bab.get());
    }

    public void bind(ClassBox classOne) {
        classBox.bindBidirectional(classOne.classBox.input.textProperty());
        die.bindBidirectional(classOne.die.choiceBox.valueProperty());
        level.bindBidirectional(classOne.level.input.textProperty());
        skill.bindBidirectional(classOne.skill.input.textProperty());
        bab.bindBidirectional(classOne.bab.choiceBox.valueProperty());
    }
}
