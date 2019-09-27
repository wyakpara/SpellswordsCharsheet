package com.spellswords.charactersheet.logic.tabs;

import com.spellswords.charactersheet.components.aggregate.ClassBox;
import com.spellswords.charactersheet.components.base.UnderLabeledChoiceBox;
import com.spellswords.charactersheet.components.base.UnderLabeledText;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement(name = "vitals")

@XmlType(propOrder = {"classTwo", "classOne", "classThree", "classFour", "charName", "playerName", "race", "size", "alignment", "beliefs"})
public class VitalsTab implements Initializable {

    @FXML ClassBox classTwo;
    @FXML ClassBox classOne;
    @FXML ClassBox classThree;
    @FXML ClassBox classFour;
    @FXML UnderLabeledText charName;
    @FXML UnderLabeledText playerName;
    @FXML UnderLabeledText race;
    @FXML UnderLabeledChoiceBox size;
    @FXML UnderLabeledText alignment;
    @FXML UnderLabeledText beliefs;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initLabels();

    }

    private void initLabels() {
        charName.label.setText("Character Name");
        playerName.label.setText("Player Name");
        race.label.setText("Race");
        size.label.setText("Size");
        alignment.label.setText("Alignment");
        beliefs.label.setText("Diety/Beliefs");
    }

    public void setClassOne(String classOne) {
//        this.classOne = classOne;
    }

    public void setClassTwo(String classTwo) {
//        this.classTwo = classTwo;
    }

    public void setClassThree(String classThree) {
//        this.classThree = classThree;
    }

    public void setClassFour(String classFour) {
//        this.classFour = classFour;
    }

    public void setCharName(String charName) {
        this.charName.input.setText(charName);
    }

    public void setPlayerName(String playerName) {
        this.playerName.input.setText(playerName);
    }

    public void setRace(String race) {
        this.race.input.setText(race);
    }

    public void setSize(String size) {
        this.size.choiceBox.getSelectionModel().select(size);
    }

    public void setAlignment(String alignment) {
        this.alignment.input.setText(alignment);
    }

    public void setBeliefs(String beliefs) {
        this.beliefs.input.setText(beliefs);
    }

    public String getClassOne() {
        return "classOne";
    }

    public String getClassTwo() {
        return "classTwo";
    }

    public String getClassThree() {
        return "classThree";
    }

    public String getClassFour() {
        return "classFour";
    }

    public String getCharName() {
        return charName.input.getText();
    }

    public String getPlayerName() {
        return playerName.input.getText();
    }

    public String getRace() {
        return race.input.getText();
    }

    public String getSize() {
        return size.choiceBox.getSelectionModel().getSelectedItem();
    }

    public String getAlignment() {
        return alignment.input.getText();
    }

    public String getBeliefs() {
        return beliefs.input.getText();
    }

    public void save(File file) {
        try {
            JAXBContext ctx;
            ctx = JAXBContext.newInstance(VitalsTab.class);
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

    public VitalsTab load(File file) {
        VitalsTab tab = null;
        try {
            JAXBContext ctx;
            ctx = JAXBContext.newInstance(VitalsTab.class);
            Unmarshaller marshal = ctx.createUnmarshaller();
            tab = (VitalsTab) marshal.unmarshal(file);
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
        return tab;
    }
}
