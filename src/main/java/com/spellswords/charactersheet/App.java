package com.spellswords.charactersheet;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

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
}


