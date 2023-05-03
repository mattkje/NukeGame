module com.example.playgroundfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.trgs to javafx.fxml;
    exports com.example.trgs;
}