module com.example.game2048 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.game2048 to javafx.fxml;
    opens com.example.game2048.Pages.Game to javafx.fxml;
    exports com.example.game2048;
}