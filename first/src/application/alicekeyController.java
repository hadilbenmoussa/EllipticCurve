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



public class alicekeyController implements Initializable{
	public ECDHController controller;
	Pane view ;
	@FXML
	Button generate;
	@FXML
	Button submita;
	@FXML
	TextField secreta;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		submita.setVisible(false);
		// TODO Auto-generated method stub
	secreta.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				
				if (newValue.isEmpty()==false){
					submita.setVisible(true);
				}
				else {submita.setVisible(false);
					
				}
				
			}

			
		});
		
	}
	@FXML
	private void generaterandom(ActionEvent event) {
		long r=Math.round(Math.random()*20+1);
		secreta.setText(Long.toString(r));
	}
	@FXML
	private void submitkey (ActionEvent event) {
		
		try {
	   		URL fileurl = ECDHController.class.getResource("ECDH"+".fxml");
	    	 if (fileurl == null) {
	    	  throw new java.io.FileNotFoundException("Fxml file cannot be found");}
	    	  FXMLLoader loader=new FXMLLoader(fileurl);
	    	   view = loader.load();
	          
	          controller = loader.getController(); 
	          ECDHController.sa=Long.parseLong(secreta.getText());
		      	System.out.println(ECDHController.sa);

	          }
		
	          catch (Exception e)
	      	{
	      	System.out.println("No Page"+"Please check fxml loader");
	      	}
		
		
		
		
		  Stage stage =(Stage) submita.getScene().getWindow();
		 
			 stage.close();
			  
		
	}
	

	
	

}
