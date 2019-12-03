@XmlJavaTypeAdapters({
        @XmlJavaTypeAdapter(value=SimpleStringPropertyAdapter.class,type=SimpleStringProperty.class)
})

package com.spellswords.charactersheet.logic.aggregate;

import com.spellswords.charactersheet.utilities.SimpleStringPropertyAdapter;
import javafx.beans.property.SimpleStringProperty;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;

