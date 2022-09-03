package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



public class bobkeyController implements Initializable{
	public ECDHController controllerb;
	Pane view ;
	@FXML
	Button generateb;
	@FXML
	Button submitb;
	@FXML
	TextField secretb;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		submitb.setVisible(false);
		// TODO Auto-generated method stub
	secretb.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				
				if (newValue.isEmpty()==false) {
					submitb.setVisible(true);
				}
				else {
					submitb.setVisible(false);
				}
			}

			
		});
		
	}
	@FXML
	private void generaterandomb(ActionEvent event) {
		long r=Math.round(Math.random()*20+1);
		secretb.setText(Long.toString(r));
	}
	@FXML
	private void submitkeyb (ActionEvent event) {
		
		try {
	   		URL fileurl = ECDHController.class.getResource("ECDH"+".fxml");
	    	 if (fileurl == null) {
	    	  throw new java.io.FileNotFoundException("Fxml file cannot be found");}
	    	  FXMLLoader loader=new FXMLLoader(fileurl);
	    	   view = loader.load();
	         
	          controllerb = loader.getController(); 
	          controllerb.sb=Long.parseLong(secretb.getText());
		      	System.out.println(controllerb.sb);

	          }
		
	          catch (Exception e)
	      	{
	      	System.out.println("No Page"+"Please check fxml loader");
	      	}
		
		
		
		
		  Stage stage =(Stage) submitb.getScene().getWindow();
		 
			 stage.close();
			  
		
	}
	

	
	

}