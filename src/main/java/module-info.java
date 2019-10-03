module com.spellswords.charactersheet {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.logging;
    requires org.controlsfx.controls;
    requires java.xml.bind;
    requires com.sun.xml.bind;

    exports com.spellswords.charactersheet to
            javafx.graphics,
            javafx.fxml;

    exports com.spellswords.charactersheet.logic.tabs to
            javafx.graphics,
            javafx.fxml;

    exports com.spellswords.charactersheet.logic.aggregate to
            com.sun.xml.bind;

    exports com.spellswords.charactersheet.components.base to
            javafx.graphics,
            javafx.fxml;

    exports com.spellswords.charactersheet.components.tabs to
            javafx.graphics,
            javafx.fxml;

    exports com.spellswords.charactersheet.components.aggregate to
            javafx.fxml;

    opens com.spellswords.charactersheet to
            javafx.graphics,
            javafx.fxml;

    opens com.spellswords.charactersheet.logic.tabs to
            java.xml.bind,
            javafx.fxml;

    opens com.spellswords.charactersheet.components.base to
            javafx.graphics,
            javafx.fxml;

    opens com.spellswords.charactersheet.logic.aggregate to
            com.sun.xml.bind;

    opens com.spellswords.charactersheet.components.aggregate to
            javafx.graphics,
            javafx.fxml,
            com.sun.xml.bind;

    opens com.spellswords.charactersheet.components.tabs to
            javafx.graphics,
            javafx.fxml,
            java.xml.bind;


}