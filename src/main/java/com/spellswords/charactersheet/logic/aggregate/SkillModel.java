//package com.spellswords.charactersheet.logic.aggregate;
//
//import com.spellswords.charactersheet.logic.tabs.JavaFxSerializable;
//import javafx.beans.property.SimpleStringProperty;
//
//import javax.xml.bind.annotation.XmlAttribute;
//import javax.xml.bind.annotation.XmlRootElement;
//import javax.xml.bind.annotation.XmlTransient;
//import javax.xml.bind.annotation.XmlType;
//import com.spellswords.charactersheet.components.aggregate.Skill;
//
//import java.util.Objects;
//
//@XmlType(propOrder = {"score", "mod", "base", "bonus", "temp", "other"})
//public class SkillModel implements JavaFxSerializable<SkillModel, Skill> {
//
//    @XmlAttribute(name="name")
//    public SimpleStringProperty skillName = new SimpleStringProperty();
//
//    public SimpleStringProperty score = new SimpleStringProperty();
//    public SimpleStringProperty mod = new SimpleStringProperty();
//    public SimpleStringProperty base = new SimpleStringProperty();
//    public SimpleStringProperty bonus = new SimpleStringProperty();
//    public SimpleStringProperty temp = new SimpleStringProperty();
//    public SimpleStringProperty other = new SimpleStringProperty();
//
//    @XmlTransient
//    public Skill skill;
//
//    public SkillModel() {}
//
//    public SkillModel(Skill skill) {
//        this.bind(skill);
//    }
//
//
//    @Override
//    public void copy(SkillModel toCopy) {
//        skillName.set(toCopy.skillName.get());
//        score.set(toCopy.score.get());
//        mod.set(toCopy.mod.get());
//        base.set(toCopy.base.get());
//        bonus.set(toCopy.bonus.get());
//        temp.set(toCopy.temp.get());
//        other.set(toCopy.other.get());
//
//    }
//
//    @Override
//    public void bind(Skill toBind) {
//        skillName.bindBidirectional(toBind.skillProperty);
//
//        score.bindBidirectional(toBind.score.textProperty());
//        mod.bindBidirectional(toBind.mod.textProperty());
//        base.bindBidirectional(toBind.base.textProperty());
//        bonus.bindBidirectional(toBind.bonus.textProperty());
//        temp.bindBidirectional(toBind.temp.textProperty());
//        other.bindBidirectional(toBind.other.textProperty());
//
//        /* just ensure we set them if they are copied BEFORE binding*/
//        if (Objects.nonNull(score.get()) && !(score.get().equals(toBind.score.getText()))) score.set(score.get());
//        if (Objects.nonNull(mod.get()) && !(mod.get().equals(toBind.mod.getText()))) mod.set(mod.get());
//        if (Objects.nonNull(base.get()) && !(base.get().equals(toBind.base.getText()))) base.set(base.get());
//        if (Objects.nonNull(bonus.get()) && !(bonus.get().equals(toBind.bonus.getText()))) bonus.set(bonus.get());
//        if (Objects.nonNull(temp.get()) && !(temp.get().equals(toBind.temp.getText()))) temp.set(temp.get());
//        if (Objects.nonNull(other.get()) && !(other.get().equals(toBind.other.getText()))) other.set(other.get());
//
//        this.skill = toBind;
//
//    }
//
//    @Override
//    public void clearBindings() {
//        skillName.unbindBidirectional(skill.skillProperty);
//
//        score.unbindBidirectional(skill.score.textProperty());
//        mod.unbindBidirectional(skill.mod.textProperty());
//        base.unbindBidirectional(skill.base.textProperty());
//        bonus.unbindBidirectional(skill.bonus.textProperty());
//        temp.unbindBidirectional(skill.temp.textProperty());
//        other.unbindBidirectional(skill.other.textProperty());
//
//        this.skill = null;
//
//    }
//}
