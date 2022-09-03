package application;


import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;


public class ChooseparametersController implements Initializable{
	Pane view ;
	@FXML
	public TextField aval;
	@FXML
	public TextField bval;
	@FXML
	public TextField modval;
	@FXML
	public Label warning;
	@FXML
	public TextField generatorx;
	public TextField generatory;
	
	@FXML
	private Button finish;
	@FXML
	private Button cancel;
	@FXML
	private ChoiceBox<String> curvechoicebox;
	 
	public  ECDHController controller;

	Window owner;
		  
		
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		 
		finish.setVisible(false);
		
    curvechoicebox.setItems(FXCollections.observableArrayList("brainpoolP160r1","brainpoolP192r1","brainpoolP224r1","brainpoolP256r1","brainpoolP320r1","brainpoolP384r1",
 				"brainpoolP512r1","secp112r1","secp112r2","secp128r1","secp128r2","secp160k1","secp160r1","secp160r2",
 				"secp192k1","secp224k1","secp224r1","secp256k1","secp384r1","secp521r1"));
    curvechoicebox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
        @Override
       public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
        	
	     curvechoicebox.setOnAction((Event)->{
	    	
	    	    	finish.setVisible(true);
    	 int selectedIndex = curvechoicebox.getSelectionModel().getSelectedIndex();
    	  
    	    
    	 switch(selectedIndex) {
    	 
    	    case 0:
    	    			   
    	    	filllabels(EllipticCurve.brainpoolP160r1);
    	    	break ;
            case 1:
                filllabels(EllipticCurve.brainpoolP192r1);
    	    	break ;
             case 2:
                  filllabels(EllipticCurve.brainpoolP224r1);
    	          break ;
              case 3:
	          filllabels(EllipticCurve.brainpoolP256r1);
	          break ;
              case 4:
              filllabels(EllipticCurve.brainpoolP320r1);
	          break ;
            case 5:
               filllabels(EllipticCurve.brainpoolP384r1);
	           break ;
	        case 6:
              filllabels(EllipticCurve.brainpoolP512r1);
	          break ;
            case 7:
	          filllabels(EllipticCurve.secp112r1);
	          break ;
            case 8:
	          filllabels(EllipticCurve.secp112r2);
	          break ;
            case 9:
	           filllabels(EllipticCurve.secp128r1);
	           break ;
            case 10:
	            filllabels(EllipticCurve.secp128r2);
	            break ;
            case 11:
	             filllabels(EllipticCurve.secp160k1);
	              break ;
             case 12:
                    	   
	           filllabels(EllipticCurve.secp160r1);
	           break ;
             case 13:
	
	            filllabels(EllipticCurve.secp160r2);
               break ;
            case 14:
	
	           filllabels(EllipticCurve.secp192k1);
	           break ;
           case 15:
	 
	           filllabels(EllipticCurve.secp224k1);
	           break ;
          case 16:

	           filllabels(EllipticCurve.secp224r1);
               break ;
         case 17:
	
	           filllabels(EllipticCurve.secp256k1);
	           break ;
       case 18:
	
	      filllabels(EllipticCurve.secp384r1);
	                      break ;
       case 19:
          filllabels(EllipticCurve.secp521r1);
              break;
	          
    	 }
    	 });}});
    
    
	
	}

         
		
	
    
    public void filllabels(EllipticCurve e) {
    	aval.setText((e.getA()).toString(16));
    	bval.setText((e.getB()).toString(16));
    	modval.setText((e.getP()).toString(16));
    	generatorx.setText(((e.getBasePoint()).x).toString(16));
    	generatory.setText((((e.getBasePoint()).y)).toString(16));
    	
    };
    	
    
    
	public void choicebox() {}
	public void finishbutton(ActionEvent event) throws IOException {
		
		try {
	   		URL fileurl = ECDHController.class.getResource("ECDH"+".fxml");
	    	 if (fileurl == null) {
	    	  throw new java.io.FileNotFoundException("Fxml file cannot be found");}
	    	  FXMLLoader loader=new FXMLLoader(fileurl);
	    	   view = loader.load();
	         
	          controller = loader.getController(); 
	          controller.a=new BigInteger(aval.getText(),16);
	          controller.b=new BigInteger(bval.getText(),16);
	          controller.p=new BigInteger(modval.getText(),16);
	          controller.gx=new BigInteger(generatorx.getText(),16);
	          controller.gy=new BigInteger(generatory.getText(),16);
	          ECDHController.index=curvechoicebox.getSelectionModel().getSelectedIndex();
	         
	          System.out.println(ECDHController.index);
	 		 controller.choosecurve.setVisible(false);

		}
	 catch (Exception e)
    	{
    	System.out.println("No Page"+"Please check fxml loader");
    	}
		
        Stage stage =(Stage) finish.getScene().getWindow();
		 
		 stage.close();
		  
	}
	public void cancelbutton(ActionEvent event) {
		 Stage stage =(Stage) cancel.getScene().getWindow();
		
		 owner = stage.getOwner();
		 Stage n = (Stage) owner;
		 stage.close();
		 n.close();
		  
		
		
		
	}
   public void nextbutton(ActionEvent event) throws IOException {
	   Parent blah=FXMLLoader.load(getClass().getResource("Secretkey.fxml"));
		Scene scene = new Scene(blah);
		Stage appstage =(Stage) ((Node) event.getSource()).getScene().getWindow();
		appstage.setScene(scene);
		appstage.show();
	   
	
}}
	
	
	

		
		
		
		
		
		
		



