package application;


import javafx.event.*;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class BasicOperationsController implements Initializable {
	 @FXML
	    private RadioButton small;

	    @FXML
	    private ToggleGroup curvesize;
	   
	
	    @FXML
	    private RadioButton  large;


	 
	 
	    @FXML
	 
	private BorderPane mainPane;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		// TODO Auto-generated method stub
		
	}
	


 
    @FXML
    void submitCurvesize(ActionEvent event) throws IOException {
    	
    	//Option 2: call "isSelected" on each of the radio buttons
    	if(small.isSelected()){
    		URL fileurl = BasicOperationsController.class.getResource("graph view"+".fxml");
    		System.out.print(fileurl);
    		JavaFxmlLoader2 object = new JavaFxmlLoader2();
    		Pane view = object.getpage("graph view");
    		
    		mainPane.setCenter(view);
    		
    		
    	} else if(large.isSelected()){
    		URL fileurl = BasicOperationsController.class.getResource("Eccoverfinitefield"+".fxml");
    		System.out.print(fileurl);
    		JavaFxmlLoader2 object = new JavaFxmlLoader2();
    		Pane view = object.getpage("Eccoverfinitefield");
    		mainPane.setCenter(view);
    		 EccoverfinitefieldController controller1 = new EccoverfinitefieldController();
      		 controller1.curve.setText("3HADOULLL3");
       		
    		
    	} }


	
}
