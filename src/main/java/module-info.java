module GUI.Scene {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.rmi;
    requires java.logging;

    opens com.example.tictactoe to javafx.fxml;
    exports GUI;
    exports Logic;
    opens GUI to javafx.fxml;
}
