module com.pepe.j {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;

    opens com.pepe.j to javafx.fxml;
    exports com.pepe.j;
}