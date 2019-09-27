/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spellswords.charactersheet;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Didge
 */
public class CharacterSheet extends Application {

    private FxmlControl<App, VBox> app;
    private static String TAB = "Tab.fxml";
    private static String APP = "App.fxml";

    @Override
    public void start(Stage primaryStage) throws IOException {

        app = new FxmlControl<App, VBox>(APP);
        App contr = app.controller;
        for (TabInfo<?, ? extends Node> tabInfo : TabInfo.tabs) {
            var tab = new FxmlControl<CharacterTab, Tab>(TAB);
            tab.root.setText(tabInfo.name);

            contr.addTab(tab);

            tab.root.setContent(tabInfo.control.root);
        }

        VBox root = app.getRoot();
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
