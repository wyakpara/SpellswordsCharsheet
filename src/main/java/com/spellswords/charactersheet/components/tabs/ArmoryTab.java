package com.spellswords.charactersheet.components.tabs;

import com.spellswords.charactersheet.components.aggregate.ClassBox;
import com.spellswords.charactersheet.components.base.UnderLabeledChoiceBox;
import com.spellswords.charactersheet.components.base.UnderLabeledText;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ArmoryTab implements Initializable {

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
}
