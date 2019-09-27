package com.spellswords.charactersheet;

import com.spellswords.charactersheet.logic.tabs.VitalsTab;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class App implements Initializable {
    @FXML
    public TabPane tabs;
    public SimpleListProperty<FxmlControl<CharacterTab, Tab>> tabControllers = new SimpleListProperty<>(FXCollections.observableList(new ArrayList<>()));

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabControllers.addListener((ListChangeListener<? super FxmlControl<CharacterTab, Tab>>) c -> {
            while (c.next()) {
                List<Tab> tabsToRemove = c.getRemoved().stream().map(x -> x.root).collect(Collectors.toList());
                tabs.getTabs().removeAll(tabsToRemove);

                List<Tab> tabsToAdd = c.getAddedSubList().stream().map(x -> x.root).collect(Collectors.toList());
                tabs.getTabs().addAll(tabsToAdd);
            }
        });
    }

    public void addTab(FxmlControl<CharacterTab, Tab> tab) {
        tabControllers.getValue().add(tab);
    }

    @FXML
    public void open(ActionEvent actionEvent) {
        TabInfo.tabs.stream().filter(tab -> tab.name.equals("Vitals")).findFirst().ifPresent(tabInfo -> {
            VitalsTab vt = ((VitalsTab) tabInfo.control.controller).load(new File("test.xml"));
            tabInfo.control.controller = vt;
        });
    }

    @FXML
    public void save(ActionEvent actionEvent) {
        TabInfo.tabs.stream().filter(tab -> tab.name.equals("Vitals")).findFirst().ifPresent(tabInfo -> {
            ((VitalsTab) tabInfo.control.controller).save(new File("test.xml"));
        });
    }
}


