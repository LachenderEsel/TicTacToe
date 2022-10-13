module com.example.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.rmi;


    opens com.example.tictactoe to javafx.fxml;
    exports com.example.tictactoe;
}