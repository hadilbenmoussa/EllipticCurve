module first {
	requires javafx.controls;requires javafx.fxml;
	requires java.desktop;
	requires javafx.graphics;
	requires java.logging;
	requires javafx.base;
	requires javafx.swing;
	
	opens application to javafx.graphics, javafx.fxml;
}
