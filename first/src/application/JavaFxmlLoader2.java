package application;

import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class JavaFxmlLoader2 {
	private Pane view;
	


public Pane getpage(String filename) {
	try {
		URL fileurl = BasicOperationsController.class.getResource(filename+".fxml");
		if (fileurl == null) {
			throw new java.io.FileNotFoundException("Fxml file cannot be found");
			
		}
		new FXMLLoader();
		view = FXMLLoader.load(fileurl);
		
		
		
	}
	catch (Exception e)
	{
		System.out.println("No Page"+ filename+"Please check fxml loader");
	}
	
	return view;
}}


