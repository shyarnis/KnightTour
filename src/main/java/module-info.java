module com.example.knighttour {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens com.example.knighttour to javafx.fxml;
    exports com.example.knighttour;
}