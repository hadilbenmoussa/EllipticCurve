package application;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.*;


import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
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
    private RadioButton addition;
    @FXML
    private ToggleGroup operation;
    @FXML
    private RadioButton  multiplication;
    @FXML
	private BorderPane mainPane;
    @FXML 
    private Label alabel;
    @FXML 
    private Label blabel;
    @FXML 
    private Label chooseplabel;
    @FXML 
    private Label Pval;
    @FXML 
    private Label Qval;
    @FXML 
    private Label Rval;
   @FXML 
   private Spinner<Integer> aspinner;
   @FXML 
   private Spinner<Integer> bspinner;
   @FXML 
   private Spinner<Integer> kspinner;
   @FXML 
   private Label curvattlabel;
   @FXML 
   private Label standardlabel;
   @FXML 
   private Spinner<Integer> k2spinner;
   @FXML
   private RadioButton addPQ;
   @FXML
   private ToggleGroup operationmod;
   @FXML
   private RadioButton  MultiplyPk;
   @FXML 
   public ChoiceBox<String> choicebox;
   public int k=0;
  
   public Integer bval;
public Integer aval;
   private Parent root;
   private Pane view;
   public  EccoverfinitefieldController controller= new EccoverfinitefieldController();
public GraphViewController controllerg=new GraphViewController();
	  
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		
		
	}
	


 
    @FXML
    public void submitCurvesize(ActionEvent event) throws IOException {
    	
    	//Option 2: call "isSelected" on each of the radio buttons
    	if(small.isSelected()){
    		aspinner.setVisible(true);
    		curvattlabel.setVisible(true);
    		multiplication.setVisible(true);
    		alabel.setVisible(true);
    		blabel.setVisible(true);
    		chooseplabel.setVisible(true);
    		Pval.setVisible(true);
    		Qval.setVisible(true);
    		Rval.setVisible(true);
    		
    		bspinner.setVisible(true);
    		kspinner.setVisible(true);
    		addition.setVisible(true);
    		k2spinner.setVisible(false);
    		addPQ.setVisible(false);
    		MultiplyPk.setVisible(false);
    		standardlabel.setVisible(false);
    		choicebox.setVisible(false);
    		
    		URL fileurl = BasicOperationsController.class.getResource("graph view"+".fxml");
    		System.out.print(fileurl);
    		JavaFxmlLoader2 object = new JavaFxmlLoader2();
    		Pane view = object.getpage("graph view");
    		 try {
    		   		URL fileurlg = BasicOperationsController.class.getResource("graph view"+".fxml");
    		    	 if (fileurl == null) {
    		    	  throw new java.io.FileNotFoundException("Fxml file cannot be found");}
    		    	  FXMLLoader loader=new FXMLLoader(fileurlg);
    		    	  view = loader.load();
    		          controllerg = loader.getController();}
    		          catch (Exception e)
      		    	{
      		    	System.out.println("No Page"+"Please check fxml loader");
      		    	
      		    	}
  
    		mainPane.setCenter(view);
   SpinnerValueFactory<Integer> valueFactory =new SpinnerValueFactory.IntegerSpinnerValueFactory(-30, 30);
    valueFactory.setValue(0);
    aspinner.setValueFactory(valueFactory);
    aspinner.setEditable(true);
    aspinner.valueProperty().addListener((obs, a1, aval) -> {
    System.out.println("New value: "+aval);
    controllerg.set_a(aval);
    		    		  });
 	SpinnerValueFactory<Integer> valueFactory2 =new SpinnerValueFactory.IntegerSpinnerValueFactory(-30, 30);
    valueFactory2.setValue(0);
    bspinner.setValueFactory(valueFactory2);
    bspinner.setEditable(true);
   bspinner.valueProperty().addListener((obs, b1, bval) -> {
    System.out.println("New value: "+bval);
    	controllerg.set_b(bval);	 
   });	
   
  
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	}
    		
 
    	
				
    		  
    		
    		
 else if(large.isSelected()){
    		
  try {
   		URL fileurl = BasicOperationsController.class.getResource("Eccoverfinitefield"+".fxml");
    	 if (fileurl == null) {
    	  throw new java.io.FileNotFoundException("Fxml file cannot be found");}
    	  FXMLLoader loader=new FXMLLoader(fileurl);
    	  view = loader.load();
          controller = loader.getController();
     		
      choicebox.setItems(FXCollections.observableArrayList("brainpoolP160r1","brainpoolP192r1","brainpoolP224r1","brainpoolP256r1","brainpoolP320r1","brainpoolP384r1",
    	    				"brainpoolP512r1","secp112r1","secp112r2","secp128r1","secp128r2","secp160k1","secp160r1","secp160r2",
    	    				"secp192k1","secp224k1","secp224r1","secp256k1","secp384r1","secp521r1"));
      choicebox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
           @Override
      public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
    		 controller.i= number2;
              controller.Px.clear();
    	     controller.Qx.clear();
    		 controller.Py.clear();
    		 controller.Qy.clear(); 
    		 controller.Rx.clear();
    		 controller.Ry.clear();
    		 controller.randomq.setDisable(true);
    		 controller.generatorq.setDisable(true);
    		 }});	
     choicebox.setOnAction((Event)->{
    	 int selectedIndex = choicebox.getSelectionModel().getSelectedIndex();
    	 switch(selectedIndex) {
    	    case 0:
    	    			   
    	    	filllabels(EllipticCurve.brainpoolP160r1,controller);
    	    	break ;
            case 1:
                filllabels(EllipticCurve.brainpoolP192r1,controller);
    	    	break ;
             case 2:
                  filllabels(EllipticCurve.brainpoolP224r1,controller);
    	          break ;
              case 3:
	          filllabels(EllipticCurve.brainpoolP256r1,controller);
	          break ;
              case 4:
              filllabels(EllipticCurve.brainpoolP320r1,controller);
	          break ;
            case 5:
               filllabels(EllipticCurve.brainpoolP384r1,controller);
	           break ;
	        case 6:
              filllabels(EllipticCurve.brainpoolP512r1,controller);
	          break ;
            case 7:
	          filllabels(EllipticCurve.secp112r1,controller);
	          break ;
            case 8:
	          filllabels(EllipticCurve.secp112r2,controller);
	          break ;
            case 9:
	           filllabels(EllipticCurve.secp128r1,controller);
	           break ;
            case 10:
	            filllabels(EllipticCurve.secp128r2,controller);
	            break ;
            case 11:
	             filllabels(EllipticCurve.secp160k1,controller);
	              break ;
             case 12:
                    	   
	           filllabels(EllipticCurve.secp160r1,controller);
	           break ;
             case 13:
	
	           filllabels(EllipticCurve.secp160r2,controller);
               break ;
            case 14:
	
	           filllabels(EllipticCurve.secp192k1,controller);
	           break ;
           case 15:
	 
	           filllabels(EllipticCurve.secp224k1,controller);
	           break ;
          case 16:
	
	           filllabels(EllipticCurve.secp224r1,controller);
               break ;
         case 17:
	
	           filllabels(EllipticCurve.secp256k1,controller);
	           break ;
       case 18:
	
	      filllabels(EllipticCurve.secp384r1,controller);
	                      break ;
       case 19:
          filllabels(EllipticCurve.secp521r1,controller);
              break;
	          
    	 }} );

         }
    				
    catch (Exception e)
    	{
    	System.out.println("No Page"+"Please check fxml loader");
    	}
    mainPane.setCenter(view);
 
	curvattlabel.setVisible(false);
	multiplication.setVisible(false);
    		
    k2spinner.setVisible(true);
    addPQ.setVisible(true);
    MultiplyPk.setVisible(true);
    standardlabel.setVisible(true);
    choicebox.setVisible(true);
			
   alabel.setVisible(false);
   blabel.setVisible(false);
   chooseplabel.setVisible(false);
   Pval.setVisible(false);
   Qval.setVisible(false);
   Rval.setVisible(false);
   aspinner.setVisible(false);
   bspinner.setVisible(false);
   kspinner.setVisible(false);
   addition.setVisible(false);
    		
    	
    	};}
    		
 private void filllabels(EllipticCurve curve,EccoverfinitefieldController controller) {
    	
    	controller.changelabel(curve.getBasePoint().x,controller.Gx);

    	controller.changelabel(curve.getA(),controller.val_a);
		controller.changelabel(curve.getB(),controller.val_b);
		controller.changelabel(curve.getP(),controller.val_mod);

		
		controller.changelabel(curve.getBasePoint().y,controller.Gy);

		controller.changelabel(curve.getOrder(),controller.order);
		
		}
  public void handleoperation(ActionEvent event) throws IOException{
	 
	  if (addPQ.isSelected()) {
		  k2spinner.setDisable(true);
		
	  }
     else if (MultiplyPk.isSelected()) {
    	 
    	 k2spinner.setDisable(false);
    	 controller.Qx.clear();
    	 controller.Qy.clear();
    	 controller.Rx.clear();
    	 controller.Ry.clear();
    	 controller.Qx.setDisable(true);
    	 controller.Qy.setDisable(true);
    	 controller.generatorq.setDisable(true);
    	 controller.randomq.setDisable(true);
    	 SpinnerValueFactory<Integer> valueFactory = 
 				new SpinnerValueFactory.IntegerSpinnerValueFactory(-10, 10);
 		
 		valueFactory.setValue(0);
 		k2spinner.setValueFactory(valueFactory);
 		
 		k2spinner.setEditable(true);
 		k2spinner.valueProperty().addListener((obs, oldValue, newValue) -> {
 	    System.out.println("New value: "+newValue);
 	    ECPoint M=new ECPoint(controller.P.x,controller.P.y);
 	    ECPoint M2=controller.curves.get((controller.i).intValue()).multiply(M,newValue);
 		controller.changelabel(M2.x,controller.Rx);
 		controller.changelabel(M2.x,controller.Ry);
 		

 		
 		 
    	 
    	 
 		} );}};
   
 

}

 
    
    




