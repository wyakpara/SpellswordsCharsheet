package com.spellswords.charactersheet.logic.tabs;

import com.spellswords.charactersheet.components.aggregate.ClassBox;
import com.spellswords.charactersheet.components.tabs.VitalsTab;
import com.spellswords.charactersheet.logic.aggregate.ClassBoxModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SingleSelectionModel;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.*;

@XmlRootElement(name = "vitals")
@XmlType(propOrder={"charName", "playerName", "race", "alignment", "beliefs", "size", "classOne", "classTwo", "classThree", "classFour"})
public class VitalsModel implements Serializable {


    @XmlElement()
    public String getCharName() {
        return charName.get();
    }
    public void setCharName(String charName) {
        this.charName.set(charName);
    }

    @XmlElement()
    public String getPlayerName() {
        return playerName.get();
    }
    public void setPlayerName(String playerName) {
        this.playerName.set(playerName);
    }

    @XmlElement()
    public String getRace() {
        return race.get();
    }
    public void setRace(String race) {
        this.race.set(race);
    }

    @XmlElement()
    public String getAlignment() {
        return alignment.get();
    }
    public void setAlignment(String alignment) {
        this.alignment.set(alignment);
    }

    @XmlElement()
    public String getBeliefs() {
        return beliefs.get();
    }
    public void setBeliefs(String beliefs) {
        this.beliefs.set(beliefs);
    }

    @XmlElement()
    public String getSize() {
        return size.get();
    }
    public void setSize(String size) {
        this.size.set(size);
    }

    @XmlElement()
    public ClassBoxModel classOne = new ClassBoxModel();
    @XmlElement()
    public ClassBoxModel classTwo = new ClassBoxModel();
    @XmlElement()
    public ClassBoxModel classThree = new ClassBoxModel();
    @XmlElement()
    public ClassBoxModel classFour = new ClassBoxModel();
    @XmlTransient
    public SimpleStringProperty charName = new SimpleStringProperty();
    @XmlTransient
    public SimpleStringProperty playerName = new SimpleStringProperty();
    @XmlTransient
    public SimpleStringProperty race = new SimpleStringProperty();
    @XmlTransient
    public SimpleStringProperty size = new SimpleStringProperty();
    @XmlTransient
    public SimpleStringProperty alignment = new SimpleStringProperty();
    @XmlTransient
    public SimpleStringProperty beliefs = new SimpleStringProperty();

    private VitalsTab tab;


    public VitalsModel() {

    }

    public VitalsModel(VitalsTab vitalsTab) {
        bind(vitalsTab);
    }

    public void bind(VitalsTab vitalsTab) {
        classOne.bind(vitalsTab.classOne);
        classTwo.bind(vitalsTab.classTwo);
        classThree.bind(vitalsTab.classThree);
        classFour.bind(vitalsTab.classFour);
        charName.bindBidirectional(vitalsTab.charName.input.textProperty());
        playerName.bindBidirectional(vitalsTab.playerName.input.textProperty());
        race.bindBidirectional(vitalsTab.race.input.textProperty());
        alignment.bindBidirectional(vitalsTab.alignment.input.textProperty());
        beliefs.bindBidirectional(vitalsTab.beliefs.input.textProperty());

        size.bindBidirectional(vitalsTab.size.choiceBox.valueProperty());

        tab = vitalsTab;
    }

    public void save(File file) {
        try {
            JAXBContext ctx;
            ctx = JAXBContext.newInstance(VitalsModel.class);
            Marshaller marshal = ctx.createMarshaller();
            marshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshal.marshal(this, file);
        } catch (JAXBException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Could not save data");
            try {
                alert.setContentText("Error saving data to file:\n" + file.getCanonicalPath());
            } catch (IOException ex) {
                alert.setContentText("Error saving data to file:\n" + file.getPath());
            }
            e.printStackTrace();
        }
    }

    public VitalsModel load(File file) {
        VitalsModel model = null;
        try {
            JAXBContext ctx;
            ctx = JAXBContext.newInstance(VitalsModel.class);
            Unmarshaller marshal = ctx.createUnmarshaller();
            model = (VitalsModel) marshal.unmarshal(file);
            this.copy(model);
        } catch (JAXBException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Could not load data");
            try {
                alert.setContentText("Error loading data from file:\n" + file.getCanonicalPath());
            } catch (IOException ex) {
                alert.setContentText("Error loading data from file:\n" + file.getPath());
            }
            e.printStackTrace();
        }
        return model;
    }

    private void copy(VitalsModel model) {
        charName.set(model.charName.get());
        playerName.set(model.playerName.get());
        race.set(model.race.get());
        size.set(model.size.get());
        alignment.set(model.alignment.get());
        beliefs.set(model.beliefs.get());
        classOne.copy(model.classOne);
        classTwo.copy(model.classTwo);
        classThree.copy(model.classThree);
        classFour.copy(model.classFour);
    }

    public void clearBindings() {
        charName.unbindBidirectional(tab.charName.input.textProperty());
        playerName.unbindBidirectional(tab.playerName.input.textProperty());
        race.unbindBidirectional(tab.race.input.textProperty());
        size.unbindBidirectional(tab.size.choiceBox.valueProperty());
        alignment.unbindBidirectional(tab.alignment.input.textProperty());
        beliefs.unbindBidirectional(tab.beliefs.input.textProperty());
        this.classOne.clearBindings();
        this.classTwo.clearBindings();
        this.classThree.clearBindings();
        this.classFour.clearBindings();
    }
}
