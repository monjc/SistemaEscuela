module com.example.proyectoprueba {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.proyectoprueba to javafx.fxml;
    exports com.example.proyectoprueba;
}