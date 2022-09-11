package application;

import java.math.BigInteger;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class EccoverfinitefieldController implements Initializable{
	
	
	public TextField val_a = new TextField();

	public Label curve = new Label();
	public TextField val_b = new TextField();
	public TextField val_mod = new TextField();
	public TextField Gx = new TextField();
	public TextField Gy = new TextField();
	public TextField order = new TextField();
	public TextField Px = new TextField();
	public TextField Py = new TextField();
	public TextField Qx = new TextField();
	public TextField Qy = new TextField();
	public TextField Rx = new TextField();
	public TextField Ry= new TextField();
	public Button randomp= new Button();
	public Button randomq= new Button();
	public Button generatorp= new Button();
	public Button generatorq= new Button();
	public  Pane view;
	public Number i=0;
 	public List<EllipticCurve> curves= new ArrayList<EllipticCurve>();
	ECPoint P =new ECPoint();
	ECPoint Q=new ECPoint();
	


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		curves.add(EllipticCurve.brainpoolP160r1);
		curves.add(EllipticCurve.brainpoolP192r1);
		curves.add(EllipticCurve.brainpoolP224r1);
		curves.add(EllipticCurve.brainpoolP256r1);
		curves.add(EllipticCurve.brainpoolP320r1);
		curves.add(EllipticCurve.brainpoolP384r1);
		curves.add(EllipticCurve.brainpoolP512r1);
		curves.add(EllipticCurve.secp112r1);
		curves.add(EllipticCurve.secp112r2);
		curves.add(EllipticCurve.secp128r1);
		curves.add(EllipticCurve.secp128r2);
		curves.add(EllipticCurve.secp160k1);
		curves.add(EllipticCurve.secp160r1);
	
		curves.add(EllipticCurve.secp160r2);
		curves.add(EllipticCurve.secp192k1);
		curves.add(EllipticCurve.secp224k1);
		curves.add(EllipticCurve.secp224r1);
		curves.add(EllipticCurve.secp256k1);
		curves.add(EllipticCurve.secp384r1);
		curves.add(EllipticCurve.secp521r1);
		Px.setEditable(false);
		Py.setEditable(false);
		Qx.setEditable(false);
		Qy.setEditable(false);
		Rx.setEditable(false);
		Ry.setEditable(false);
		Gx.setEditable(false);
		Gy.setEditable(false);
		val_a.setEditable(false);
		val_b.setEditable(false);
		order.setEditable(false);
		val_mod.setEditable(false);
	
	
	};
	
   
   public void changelabel(BigInteger bigInteger,TextField t) {
		String ch=bigInteger.toString(16);
		t.setText(ch);
		}
    
   public void handlerandomp(ActionEvent event) {
    	  
        
        
    	  ECPoint G=curves.get(i.intValue()).getBasePoint();
  		  long r=Math.round(100*Math.random());
		  P=curves.get(i.intValue()).multiply(G,r);
		  Px.setText((P.x).toString(16));
		  Py.setText((P.y).toString(16));
		  randomq.setDisable(false);
		  generatorq.setDisable(false);
		  
	      fillRQ();
	      
		  
		  }
    
		 
		
		    
		
   public void handlerandomq(ActionEvent event) {

		ECPoint K=curves.get(i.intValue()).getBasePoint();
	  	   long r=Math.round(100*Math.random());
	       Q=curves.get(i.intValue()).multiply(K,r);
	       Qx.setText((Q.x).toString(16));
		   Qy.setText((Q.y).toString(16));
			 fillRQ(); };
					 
   public void handlegeneratorp(ActionEvent event) {
		  P=curves.get(i.intValue()).getBasePoint();
		  Px.setText((P.x).toString(16));
		  Py.setText((P.y).toString(16));
				    
				 };
   public void handlegeneratorq(ActionEvent event) {
			
          Q=curves.get(i.intValue()).getBasePoint();
          Qx.setText((Q.x).toString(16));
	      Qy.setText((Q.y).toString(16));
	        fillRQ();
				  };
     
					  

   public void fillRQ() {
	  Qx.textProperty().addListener(new ChangeListener<String>() {
		  @Override
	  public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {   
         Q.x=new BigInteger(newValue,16); }});
	  Qy.textProperty().addListener(new ChangeListener<String>() {
		  @Override
	  public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {   
         Q.y=new BigInteger(newValue,16); }});
	 
	  Px.textProperty().addListener(new ChangeListener<String>() {
		  @Override
	  public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {   
         P.x=new BigInteger(newValue,16); }});
	 Py.textProperty().addListener(new ChangeListener<String>() {
		  @Override
	  public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {   
         P.y=new BigInteger(newValue,16);}});
	     ECPoint R= curves.get((i).intValue()).add(P, Q); 
	     changelabel(R.x,Rx);
	     changelabel(R.y,Ry);

	 }
     
		
	
   
  
}