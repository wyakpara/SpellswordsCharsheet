package com.spellswords.charactersheet;

import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class FxmlControl<T, V> {

    FXMLLoader loader;
    V root;
    T controller;


    FxmlControl(String resource) throws IOException {
        URL appFxml = FxmlControl.class.getResource(resource);
        loader = new FXMLLoader(appFxml);
        root = loader.load();
        controller = loader.getController();
        Objects.requireNonNull(root);
        Objects.requireNonNull(controller);

    }

    public V getRoot() {
        return root;
    }




}
