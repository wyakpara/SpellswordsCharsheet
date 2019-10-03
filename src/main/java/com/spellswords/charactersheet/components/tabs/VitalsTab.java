package com.spellswords.charactersheet.components.tabs;

import com.spellswords.charactersheet.App;
import com.spellswords.charactersheet.components.aggregate.ClassBox;
import com.spellswords.charactersheet.components.base.UnderLabeledChoiceBox;
import com.spellswords.charactersheet.components.base.UnderLabeledText;
import com.spellswords.charactersheet.logic.tabs.VitalsModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.File;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class VitalsTab implements Initializable {

    @FXML public ClassBox classTwo;
    @FXML public ClassBox classOne;
    @FXML public ClassBox classThree;
    @FXML public ClassBox classFour;
    @FXML public UnderLabeledText charName;
    @FXML public UnderLabeledText playerName;
    @FXML public UnderLabeledText race;
    @FXML public UnderLabeledChoiceBox size;
    @FXML public UnderLabeledText alignment;
    @FXML public UnderLabeledText beliefs;
    public VitalsModel model;

//    public VitalsModel model = new VitalsModel(this);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initLabels();
        this.newModel();
//        CharacterSheet.REGISTRY.put(this.toString(), model);
    }

    private void initLabels() {
        charName.label.setText("Character Name");
        playerName.label.setText("Player Name");
        race.label.setText("Race");
        size.label.setText("Size");
        alignment.label.setText("Alignment");
        beliefs.label.setText("Diety/Beliefs");
    }

    public void newModel() {
        this.setModel(new VitalsModel(this));
    }

    public void setModel(VitalsModel vMod) {
        if (Objects.nonNull(model)) clearBindings();

        model = vMod;
        model.bind(this);
        App.app.models.put ("VitalsModel", model);
        App.app.save.setOnAction((event) -> {
            model.save(new File("test.xml"));
        });
        App.app.open.setOnAction((event) -> {
            model.load(new File("test.xml"));
        });
    }

    private void clearBindings() {
        charName.input.textProperty().unbindBidirectional(charName);
        playerName.input.textProperty().unbindBidirectional(playerName);
        race.input.textProperty().unbindBidirectional(race);
//        size.choiceBox.selectionModelProperty().unbindBidirectional(size);
        alignment.input.textProperty().unbindBidirectional(alignment);
        beliefs.input.textProperty().unbindBidirectional(beliefs);
    }
}
