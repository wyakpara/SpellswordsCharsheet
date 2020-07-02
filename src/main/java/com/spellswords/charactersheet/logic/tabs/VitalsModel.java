//package com.spellswords.charactersheet.logic.tabs;
//
//import com.spellswords.charactersheet.components.tabs.VitalsTab;
//import com.spellswords.charactersheet.logic.aggregate.*;
//import javafx.beans.property.SimpleStringProperty;
//import javafx.scene.control.Alert;
//
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBException;
//import javax.xml.bind.Marshaller;
//import javax.xml.bind.Unmarshaller;
//import javax.xml.bind.annotation.*;
//import java.io.*;
//
//@XmlRootElement(name = "vitals")
//@XmlType(propOrder = {"charName", "playerName", "race", "alignment", "beliefs",
//        "size", "classOne", "classTwo", "classThree", "classFour", "appearance",
//        "fatigue", "health", "skills"})
//public class VitalsModel implements JavaFxSerializable<VitalsModel, VitalsTab> {
//
//    public ClassBoxModel classOne = new ClassBoxModel();
//    public ClassBoxModel classTwo = new ClassBoxModel();
//    public ClassBoxModel classThree = new ClassBoxModel();
//    public ClassBoxModel classFour = new ClassBoxModel();
//    public SimpleStringProperty charName = new SimpleStringProperty();
//    public SimpleStringProperty playerName = new SimpleStringProperty();
//    public SimpleStringProperty race = new SimpleStringProperty();
//    public SimpleStringProperty size = new SimpleStringProperty();
//    public SimpleStringProperty alignment = new SimpleStringProperty();
//    public SimpleStringProperty beliefs = new SimpleStringProperty();
//
//    public AppearanceBoxModel appearance = new AppearanceBoxModel();
//
//    public SkillTableModel skills = new SkillTableModel();
//    public FatigueModel fatigue = new FatigueModel();
//    public HealthModel health = new HealthModel();
//    @XmlTransient public StatusEffectTableModel status = new StatusEffectTableModel();
//
//    private VitalsTab tab;
//
//
//    public VitalsModel() {
//
//    }
//
//    public VitalsModel(VitalsTab vitalsTab) {
//        bind(vitalsTab);
//    }
//
//    public void bind(VitalsTab vitalsTab) {
//        classOne.bind(vitalsTab.classOne);
//        classTwo.bind(vitalsTab.classTwo);
//        classThree.bind(vitalsTab.classThree);
//        classFour.bind(vitalsTab.classFour);
//        charName.bindBidirectional(vitalsTab.charName.input.textProperty());
//        playerName.bindBidirectional(vitalsTab.playerName.input.textProperty());
//        race.bindBidirectional(vitalsTab.race.input.textProperty());
//        alignment.bindBidirectional(vitalsTab.alignment.input.textProperty());
//        beliefs.bindBidirectional(vitalsTab.beliefs.input.textProperty());
//
//        size.bindBidirectional(vitalsTab.size.choiceBox.valueProperty());
//
//        appearance.bind(vitalsTab.appearance);
//        fatigue.bind(vitalsTab.fatigue);
//        health.bind(vitalsTab.health);
//        skills.bind(vitalsTab.skills);
//
//        tab = vitalsTab;
//    }
//
//    public void save(File file) {
//        try {
//            JAXBContext ctx;
//            ctx = JAXBContext.newInstance(VitalsModel.class);
//            Marshaller marshal = ctx.createMarshaller();
//            marshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//            marshal.marshal(this, file);
//        } catch (JAXBException e) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error!");
//            alert.setHeaderText("Could not save data");
//            try {
//                alert.setContentText("Error saving data to file:\n" + file.getCanonicalPath());
//            } catch (IOException ex) {
//                alert.setContentText("Error saving data to file:\n" + file.getPath());
//            }
//            e.printStackTrace();
//        }
//    }
//
//    public VitalsModel load(File file) {
//        VitalsModel model = null;
//        try {
//            JAXBContext ctx;
//            ctx = JAXBContext.newInstance(VitalsModel.class);
//            Unmarshaller marshal = ctx.createUnmarshaller();
//            model = (VitalsModel) marshal.unmarshal(file);
//            this.copy(model);
//        } catch (JAXBException e) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error!");
//            alert.setHeaderText("Could not load data");
//            try {
//                alert.setContentText("Error loading data from file:\n" + file.getCanonicalPath());
//            } catch (IOException ex) {
//                alert.setContentText("Error loading data from file:\n" + file.getPath());
//            }
//            e.printStackTrace();
//        }
//        return model;
//    }
//
//    public void copy(VitalsModel model) {
//        charName.set(model.charName.get());
//        playerName.set(model.playerName.get());
//        race.set(model.race.get());
//        size.set(model.size.get());
//        alignment.set(model.alignment.get());
//        beliefs.set(model.beliefs.get());
//        classOne.copy(model.classOne);
//        classTwo.copy(model.classTwo);
//        classThree.copy(model.classThree);
//        classFour.copy(model.classFour);
//        appearance.copy(model.appearance);
//        fatigue.copy(model.fatigue);
//        health.copy(model.health);
//        skills.copy(model.skills);
//    }
//
//    public void clearBindings() {
//        charName.unbindBidirectional(tab.charName.input.textProperty());
//        playerName.unbindBidirectional(tab.playerName.input.textProperty());
//        race.unbindBidirectional(tab.race.input.textProperty());
//        size.unbindBidirectional(tab.size.choiceBox.valueProperty());
//        alignment.unbindBidirectional(tab.alignment.input.textProperty());
//        beliefs.unbindBidirectional(tab.beliefs.input.textProperty());
//        classOne.clearBindings();
//        classTwo.clearBindings();
//        classThree.clearBindings();
//        classFour.clearBindings();
//        appearance.clearBindings();
//        fatigue.clearBindings();
//        health.clearBindings();
//        skills.clearBindings();
//    }
//}
