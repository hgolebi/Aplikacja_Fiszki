module papfishes {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.base;
    requires javafx.media;
    requires javafx.fxml;
    requires java.sql;

    exports com.pap.fishes.papfishes to javafx.graphics, javafx.fxml;
    opens com.pap.fishes.papfishes to javafx.graphics, javafx.fxml;
}
