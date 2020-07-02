//package com.spellswords.charactersheet.logic.aggregate;
//
//import com.spellswords.charactersheet.components.aggregate.ClassBox;
//import com.spellswords.charactersheet.logic.tabs.JavaFxSerializable;
//import javafx.beans.property.SimpleStringProperty;
//import javafx.fxml.FXML;
//
//import javax.xml.bind.annotation.XmlElement;
//import javax.xml.bind.annotation.XmlRootElement;
//import javax.xml.bind.annotation.XmlTransient;
//import javax.xml.bind.annotation.XmlType;
//import java.io.Serializable;
//import java.util.Objects;
//
//@XmlRootElement
//@XmlType(propOrder = {"classBoxField", "die", "level", "skill"})
//public class ClassBoxModel implements JavaFxSerializable<ClassBoxModel, ClassBox> {
//
//    @XmlElement(name = "class")
//    public SimpleStringProperty classBoxField = new SimpleStringProperty();
//    public SimpleStringProperty die = new SimpleStringProperty();
//    public SimpleStringProperty level = new SimpleStringProperty();
//    public SimpleStringProperty skill = new SimpleStringProperty();
//
//    private ClassBox classBox;
//
//    public void setClassBox(ClassBox classBox) {
//        this.classBox = classBox;
//    }
//
//    public void copy(ClassBoxModel model) {
//        classBoxField.set(model.classBoxField.get());
//        die.set(model.die.get());
//        level.set(model.level.get());
//        skill.set(model.skill.get());
//    }
//
//    public void bind(ClassBox clsBox) {
//        classBoxField.bindBidirectional(clsBox.classBox.input.textProperty());
//        die.bindBidirectional(clsBox.die.choiceBox.valueProperty());
//        level.bindBidirectional(clsBox.level.input.textProperty());
//        skill.bindBidirectional(clsBox.skill.input.textProperty());
//        this.classBox = clsBox;
//    }
//
//    public void clearBindings() {
//        if (Objects.nonNull(classBox)) {
//            classBoxField.unbindBidirectional(classBox.classBox.input.textProperty());
//            die.unbindBidirectional(classBox.die.choiceBox.valueProperty());
//            level.unbindBidirectional(classBox.level.input.textProperty());
//            skill.unbindBidirectional(classBox.skill.input.textProperty());
//            this.classBox = null;
//        }
//    }
//}
