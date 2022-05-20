module com.example.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.xml.bind;
    requires com.example.database;

    opens com.example.javafx to javafx.fxml;
    exports com.example.javafx;
}