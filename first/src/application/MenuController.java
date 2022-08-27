package application;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class MenuController implements Initializable{
	@FXML
	private BorderPane borderpane;
	   @FXML
	private Button NewButton;
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
		
	}
	@FXML
	private void handleButton1Action(ActionEvent event) {
		System.out.print("youclicked me");
	javaFxmlLoader object = new javaFxmlLoader();
	Pane view = object.getpage("menuoperations");
	borderpane.setCenter(view);
	}
@FXML
private void handleButton2Action(ActionEvent event) {
	System.out.print("youclicked me");
javaFxmlLoader object = new javaFxmlLoader();
Pane view = object.getpage("menuecdh");
borderpane.setCenter(view);
}
@FXML
private void handleButton3Action(ActionEvent event) {
	System.out.print("youclicked me");
javaFxmlLoader object = new javaFxmlLoader();
Pane view = object.getpage("about");
borderpane.setCenter(view);
}
@FXML
private void handlenewfileAction(ActionEvent event) {
	System.out.print("youclicked me");
javaFxmlLoader object = new javaFxmlLoader();
Pane view = object.getpage("basicoperations");
borderpane.setCenter(view);
}

}
	
	


