package com.spellswords.charactersheet.components.base;

import javafx.beans.NamedArg;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Objects;

public class UnderLabeledText extends UnderLabeledNode implements Serializable {

    @FXML public AutosizeTextField input;

    public UnderLabeledText(@NamedArg(value = "prefWidth", defaultValue = "-1") double prefWidth,
                            @NamedArg(value = "text", defaultValue = "default") String text) {
        super(prefWidth, text);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UnderLabeledText.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        if (Objects.isNull(input)) {
            super.initialize();
            return;
        }
        input.minWidthProperty().bind(super.minWidthProperty());
        input.computedResize();


        this.getStyleClass().addListener((ListChangeListener<? super String>) (obs) -> {
            while (obs.next()) {
                String unmod = "unmodifiable";
                if (this.getStyleClass().contains(unmod)){
                    this.getStyleClass().remove(unmod);
                    if (!this.input.getStyleClass().contains(unmod)) {
                        this.input.getStyleClass().add(unmod);
                    }
                }
          }
        });
    }

//    public void writeObject(ObjectOutputStream out) throws IOException {
////        super.writeObject(out);
//        out.defaultWriteObject();
//        out.writeDouble(this.prefWidth.getValue());
//    }

//    public void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
////        super.readObject(in);
//        in.defaultReadObject();
//        this.prefWidth.setValue(in.readDouble());
//    }
}
