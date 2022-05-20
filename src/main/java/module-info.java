module com.example.projetaslaks {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.projetaslaks to javafx.fxml;
    exports com.example.projetaslaks;
}