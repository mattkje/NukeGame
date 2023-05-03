module com.example.playgroundfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.playgroundfx to javafx.fxml;
    exports com.example.playgroundfx;
}