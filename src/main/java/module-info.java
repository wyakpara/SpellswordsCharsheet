module com.spellswords.charactersheet {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.logging;
    requires org.controlsfx.controls;

    exports com.spellswords.charactersheet to
            javafx.graphics,
            javafx.fxml;

    exports com.spellswords.charactersheet.logic.tabs to
            javafx.graphics,
            javafx.fxml;

    exports com.spellswords.charactersheet.components.base to
            javafx.graphics,
            javafx.fxml;

    exports com.spellswords.charactersheet.components.aggregate to
            javafx.fxml;

    opens com.spellswords.charactersheet.logic.tabs to
            javafx.fxml;

    opens com.spellswords.charactersheet.components.base to
            javafx.graphics,
            javafx.fxml;

    opens com.spellswords.charactersheet.components.aggregate to
            javafx.graphics,
            javafx.fxml;

}