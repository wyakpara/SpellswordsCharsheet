//package com.spellswords.charactersheet.logic.tabs;
//
//import com.spellswords.charactersheet.components.aggregate.Category;
//import com.spellswords.charactersheet.components.aggregate.Health;
//import com.spellswords.charactersheet.components.base.UnderLabeledText;
//import javafx.beans.property.SimpleStringProperty;
//import javafx.print.JobSettings;
//import javafx.scene.Node;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//
//import javax.xml.bind.annotation.XmlRootElement;
//import javax.xml.bind.annotation.XmlType;
//
//@XmlRootElement(name = "Health")
//@XmlType(propOrder = {"current", "level", "con", "bonus", "lost", "sub", "temp", "thresh", "hpPer"})
//public class HealthModel implements JavaFxSerializable<HealthModel, Health> {
//
//    public SimpleStringProperty current = new SimpleStringProperty();
//    public SimpleStringProperty level = new SimpleStringProperty();
//    public SimpleStringProperty con = new SimpleStringProperty();
//    public SimpleStringProperty bonus = new SimpleStringProperty();
//    public SimpleStringProperty lost = new SimpleStringProperty();
//    public SimpleStringProperty sub = new SimpleStringProperty();
//    public SimpleStringProperty temp = new SimpleStringProperty();
//    public SimpleStringProperty thresh = new SimpleStringProperty();
//    public SimpleStringProperty hpPer = new SimpleStringProperty();
//
//    private Health health;
//
//
//    @Override
//    public void copy(HealthModel toCopy) {
//        current.set(toCopy.current.get());
//        level.set(toCopy.level.get());
//        con.set(toCopy.con.get());
//        bonus.set(toCopy.bonus.get());
//        lost.set(toCopy.lost.get());
//        sub.set(toCopy.sub.get());
//        temp.set(toCopy.temp.get());
//        thresh.set(toCopy.thresh.get());
//        hpPer.set(toCopy.hpPer.get());
//
//    }
//
//    @Override
//    public void bind(Health toBind) {
//        current.bindBidirectional(toBind.current.input.textProperty());
//        level.bindBidirectional(toBind.level.input.textProperty());
//        con.bindBidirectional(toBind.con.input.textProperty());
//        bonus.bindBidirectional(toBind.bonus.input.textProperty());
//        lost.bindBidirectional(toBind.lost.input.textProperty());
//        sub.bindBidirectional(toBind.sub.input.textProperty());
//        temp.bindBidirectional(toBind.temp.input.textProperty());
//        thresh.bindBidirectional(toBind.thresh.input.textProperty());
//        hpPer.bindBidirectional(toBind.hpPer.input.textProperty());
//        this.health = toBind;
//
//    }
//
//    @Override
//    public void clearBindings() {
//        current.unbindBidirectional(health.current.input.textProperty());
//        level.unbindBidirectional(health.level.input.textProperty());
//        con.unbindBidirectional(health.con.input.textProperty());
//        bonus.unbindBidirectional(health.bonus.input.textProperty());
//        lost.unbindBidirectional(health.lost.input.textProperty());
//        sub.unbindBidirectional(health.sub.input.textProperty());
//        temp.unbindBidirectional(health.temp.input.textProperty());
//        thresh.unbindBidirectional(health.thresh.input.textProperty());
//        hpPer.unbindBidirectional(health.hpPer.input.textProperty());
//    }
//}
