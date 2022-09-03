package application;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class ECDHController implements Initializable {
	@FXML 
	public Button choosecurve;
	public BigInteger a,b,p,gx,gy;
	@FXML 
	public Button next;
	Parent root;
	@FXML
	BorderPane borderpane;
	public static long sa=0;
	public static long sb=0;
	public static int index;
	public static List<EllipticCurve> curves= new ArrayList<EllipticCurve>();
	
	
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
		
		

			next.setDisable(true);
		
	}
	public void openchoosecurvewindow(ActionEvent event)   {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Chooseparameters.fxml"));
		
		 try {
			root = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 root.setStyle("-fx-padding: 10;" 
	                + "-fx-border-style: solid outside;" 
	                + "-fx-border-width: 2;" 
	                + "-fx-border-insets: 5;" 
	                + "-fx-border-radius: 5;" 
	                + "-fx-border-color: white;"); 
           
	    Stage stage= new Stage();
	    
	    stage.setScene(new Scene(root));
	    stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node) event.getSource()).getScene().getWindow());
      stage.initStyle(StageStyle.UNDECORATED);
     
        stage.setOnHidden(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
            	next.setDisable(false);
            	choosecurve.setVisible(false);
System.out.println(index);
            }
        });
	    stage.show();
			;
		}
	   public void nextbutton(ActionEvent event) throws IOException {
		   javaFxmlLoader object = new javaFxmlLoader();
			Pane view = object.getpage("Secretkey");
			borderpane.setCenter(view);
			
		
	}
	
	}
	


