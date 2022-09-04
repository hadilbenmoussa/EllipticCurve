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

public class generatecommonkeyController implements Initializable{
	@FXML 
    Text textdes;
	@FXML 
	Button sessionkey;
	@FXML
TextField skey;
	public ECDHController controller3 ;
	Pane view;
@FXML
BorderPane borderpane;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	public void sessionkeycalculate(ActionEvent event) throws IOException {
		
   		URL fileurl = ECDHController.class.getResource("ECDH"+".fxml");
    	 
    	  
    	  FXMLLoader loader=new FXMLLoader(fileurl);
    	   view = loader.load();
    	   
    	   controller3 = loader.getController(); 
    	   
    	   System.out.print(ECDHController.index );
    	   
         EllipticCurve e=ECDHController.curves.get(ECDHController.index);
         ECPoint key= new ECPoint();
         ECPoint s= new ECPoint();
         key=e.multiply(e.getBasePoint(), ECDHController.sa);
         s=e.multiply(key, ECDHController.sb);
         
         System.out.print(s.toString(16));
         skey.setText(s.toString(16));
         textdes.setText("        CONGRATULATIONS !!!  you helped Alice and Bob establishing their Session Key  ");
          
 		 
	

}}
