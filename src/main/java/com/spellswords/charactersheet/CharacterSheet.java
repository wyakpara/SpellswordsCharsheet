/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spellswords.charactersheet;

import com.spellswords.charactersheet.logic.tabs.VitalsTab;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Didge
 */
public class CharacterSheet extends Application {

    private FxmlControl app;
    private static String TAB = "Tab.fxml";
    private static String APP = "App.fxml";
    private String[] tabNames = new String[]{"Vitals", "Feats & Abilities", "Armory",
            "Inventory", "Storage", "Power List", "Powers"};

    @Override
    public void start(Stage primaryStage) throws IOException {

        var app = new FxmlControl<App, TabPane>(APP);
        App contr = app.controller;
        for (String name : tabNames) {
            var tab = new FxmlControl<CharacterTab, Tab>(TAB);
            tab.root.setText(name);
            contr.addTab(tab);

            if (name.equals("Vitals")) {
                var vitals = new FxmlControl<VitalsTab, GridPane>("tabs/Vitals.fxml");
                tab.root.setContent(vitals.root);
            }

        }
        TabPane root = app.getRoot();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("App.css").toExternalForm());

        primaryStage.setTitle("Versebender!");
        primaryStage.setHeight(760);
        primaryStage.setWidth(1080);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }


}