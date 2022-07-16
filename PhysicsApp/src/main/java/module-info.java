module com.example.physicsapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.physicsapp to javafx.fxml;
    exports com.example.physicsapp;
}