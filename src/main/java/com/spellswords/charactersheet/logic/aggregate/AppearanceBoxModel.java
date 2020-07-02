//package com.spellswords.charactersheet.logic.aggregate;
//
//import com.spellswords.charactersheet.components.aggregate.AppearanceBox;
//import com.spellswords.charactersheet.components.aggregate.ClassBox;
//import com.spellswords.charactersheet.components.base.UnderLabeledText;
//import com.spellswords.charactersheet.logic.tabs.JavaFxSerializable;
//import com.spellswords.charactersheet.utilities.SimpleStringPropertyAdapter;
//import javafx.beans.property.SimpleStringProperty;
//import javafx.fxml.FXML;
//
//import javax.xml.bind.annotation.XmlRootElement;
//import javax.xml.bind.annotation.XmlTransient;
//import javax.xml.bind.annotation.XmlType;
//import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
//import java.io.Serializable;
//import java.util.Objects;
//
//@XmlRootElement
//@XmlType(propOrder = {"gender", "age", "height", "weight", "eyes", "hair", "skin", "demeanor"})
//public class AppearanceBoxModel implements JavaFxSerializable<AppearanceBoxModel, AppearanceBox> {
//
//    public SimpleStringProperty gender = new SimpleStringProperty();
//    public SimpleStringProperty age = new SimpleStringProperty();
//    public SimpleStringProperty height = new SimpleStringProperty();
//    public SimpleStringProperty weight = new SimpleStringProperty();
//    public SimpleStringProperty eyes = new SimpleStringProperty();
//    public SimpleStringProperty hair = new SimpleStringProperty();
//    public SimpleStringProperty skin = new SimpleStringProperty();
//    public SimpleStringProperty demeanor = new SimpleStringProperty();
//    private AppearanceBox appearanceBox;
//
//    public void copy(AppearanceBoxModel model) {
//        gender.set(model.gender.get());
//        age.set(model.age.get());
//        height.set(model.height.get());
//        weight.set(model.weight.get());
//        eyes.set(model.eyes.get());
//        hair.set(model.hair.get());
//        skin.set(model.skin.get());
//        demeanor.set(model.demeanor.get());
//    }
//
//    public void bind(AppearanceBox appearanceBox) {
//        gender.bindBidirectional(appearanceBox.gender.input.textProperty());
//        age.bindBidirectional(appearanceBox.age.input.textProperty());
//        height.bindBidirectional(appearanceBox.height.input.textProperty());
//        weight.bindBidirectional(appearanceBox.weight.input.textProperty());
//        eyes.bindBidirectional(appearanceBox.eyes.input.textProperty());
//        hair.bindBidirectional(appearanceBox.hair.input.textProperty());
//        skin.bindBidirectional(appearanceBox.skin.input.textProperty());
//        demeanor.bindBidirectional(appearanceBox.demeanor.input.textProperty());
//        this.appearanceBox = appearanceBox;
//    }
//
//    public void clearBindings() {
//        if (Objects.nonNull(appearanceBox)) {
//            gender.unbindBidirectional(appearanceBox.gender.input.textProperty());
//            age.unbindBidirectional(appearanceBox.age.input.textProperty());
//            height.unbindBidirectional(appearanceBox.height.input.textProperty());
//            weight.unbindBidirectional(appearanceBox.weight.input.textProperty());
//            eyes.unbindBidirectional(appearanceBox.eyes.input.textProperty());
//            hair.unbindBidirectional(appearanceBox.hair.input.textProperty());
//            skin.unbindBidirectional(appearanceBox.skin.input.textProperty());
//            demeanor.unbindBidirectional(appearanceBox.demeanor.input.textProperty());
//            this.appearanceBox = null;
//        }
//    }
//}
