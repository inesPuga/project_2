module com.example.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.xml.bind;
    requires com.example.database;
    requires java.persistence;

    opens com.example.javafx to javafx.fxml;
    exports com.example.javafx;
    exports com.example.javafx.tables;
    opens com.example.javafx.tables to javafx.fxml;
}