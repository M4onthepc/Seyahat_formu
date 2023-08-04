module com.example.mehmetakifersoy211130012 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.mehmetakifersoy211130012 to javafx.fxml;
    exports com.example.mehmetakifersoy211130012;
}