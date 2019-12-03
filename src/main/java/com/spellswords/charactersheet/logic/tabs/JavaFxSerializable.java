package com.spellswords.charactersheet.logic.tabs;

import java.io.Serializable;

public interface JavaFxSerializable<T, V> extends Serializable {

    void copy(T toCopy);

    void bind(V toBind);

    void clearBindings();
}
