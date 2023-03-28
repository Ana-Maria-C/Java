module com.example.compulsoryclases {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.compulsoryclases to javafx.fxml;
    exports com.example.compulsoryclases;
}