package application;

import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;


public class sharedkeyController implements Initializable{
	public ECDHController controller2 ;
	Pane view;
	@FXML
	BorderPane borderpane;
	
	@FXML 
	Button next3;
	@FXML 
	Button calculatealice;
	@FXML 
	Button calculatebob;
	@FXML 
    TextField aliceshared;
	@FXML 
    TextField bobshared;
	
	@FXML 
    Text curvestring;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		// TODO Auto-generated method stub
		
	}
	public void alicesharedkey(ActionEvent event) throws IOException {
		
	   		URL fileurl = ECDHController.class.getResource("ECDH"+".fxml");
	    	 
	    	  
	    	  FXMLLoader loader=new FXMLLoader(fileurl);
	    	   view = loader.load();
	    	   
	    	   controller2 = loader.getController(); 
	    	   
	    	   System.out.print(ECDHController.index );
	    	   
	    	   
	         EllipticCurve e=ECDHController.curves.get(ECDHController.index);
	         curvestring.setText("y^2 mod "+e.getP()+"= x^3 + "+e.getA()+"x^2 + " +e.getB()+" mod "+e.getP());
	         ECPoint key= new ECPoint();
	         key=e.multiply(e.getBasePoint(), ECDHController.sa);
	         
	         System.out.print(key.toString(16));
	         aliceshared.setText(key.toString(16));
	          
	 		 

	
		
	}
	public void bobsharedkey(ActionEvent event) throws IOException {
		
   		URL fileurl = ECDHController.class.getResource("ECDH"+".fxml");
    	 
    	  
    	  FXMLLoader loader=new FXMLLoader(fileurl);
    	   view = loader.load();
    	   
    	   controller2 = loader.getController(); 
    	   
    	   System.out.print(ECDHController.index );
    	   
         EllipticCurve e=ECDHController.curves.get(ECDHController.index);
         ECPoint key= new ECPoint();
         key=e.multiply(e.getBasePoint(), ECDHController.sb);
         
         System.out.print(key.toString(16));
         bobshared.setText(key.toString(16));
          
 		 


	
}
	  public void nextbutton(ActionEvent event) throws IOException {
		   javaFxmlLoader object = new javaFxmlLoader();
			Pane view = object.getpage("generatecommonkey");
		
			borderpane.setCenter(view);
			
		
	}
	

}
