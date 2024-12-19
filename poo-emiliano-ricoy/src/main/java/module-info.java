module edu.udelp.poo.emiliano.ricoy {
    requires javafx.controls;
    requires javafx.fxml;
	requires lombok;
	requires com.google.gson;
    opens edu.udelp.poo.emiliano.ricoy to javafx.fxml;
    exports edu.udelp.poo.emiliano.ricoy;
    opens edu.udelp.poo.emiliano.ricoy.model to com.google.gson;
    exports edu.udelp.poo.emiliano.ricoy.model;
}
