package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SecretkeyController implements Initializable{
	@FXML
	BorderPane borderpane;
	@FXML
	Button next2 ;
	@FXML
	private Hyperlink alicesecret;
	@FXML
	private Hyperlink bobsecret;
	@FXML
	private ImageView view;
	@FXML
	private ImageView view2;
	public int enteralice=0;
	public int enterbob=0;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		next2.setDisable(true);
		assert alicesecret != null : "fx:id=\"hyp01\" was not injected: check your FXML file 'Hyperlink.fxml'.";
		assert bobsecret != null : "fx:id=\"hyp01\" was not injected: check your FXML file 'Hyperlink.fxml'.";
		InputStream stream = null;
		try {
			stream = new FileInputStream("C:\\Users\\Iyed\\git\\EllipticCurve\\first\\src\\images\\icons8-key-50.png");
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 Image image = new Image(stream);
	      view.setImage(image);
	      view.setFitHeight(46);
	      view.setFitWidth(46);
	      //Setting the graphic to the hyperlink
	      alicesecret.setGraphic(view);
	      InputStream stream2 = null;
			try {
				stream2 = new FileInputStream("C:\\Users\\Iyed\\git\\EllipticCurve\\first\\src\\images\\icons8-key-50.png");
			
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 Image image2 = new Image(stream2);
		      view2.setImage(image2);
		      view2.setFitHeight(46);
		      view2.setFitWidth(46);
		      //Setting the graphic to the hyperlink
		      bobsecret.setGraphic(view2);
	}
	@FXML
	void aliceOnAction(ActionEvent event) throws IOException {	
		enteralice++;
	FXMLLoader loader = new FXMLLoader(getClass().getResource("alicekey.fxml"));
	Parent root = loader.load();
    Stage stage= new Stage();
    stage.initModality(Modality.WINDOW_MODAL);
    stage.initOwner(((Node) event.getSource()).getScene().getWindow());
	 root.setStyle("-fx-padding: 10;" 
             + "-fx-border-style: solid outside;" 
             + "-fx-border-width: 2;" 
             + "-fx-border-insets: 5;" 
             + "-fx-border-radius: 5;" 
             + "-fx-border-color: white;"); 
    stage.setScene(new Scene(root));
    stage.initStyle(StageStyle.UNDECORATED);
    stage.setResizable(false);
    stage.show();
		if ((enteralice >1) && (enterbob >1)) {
			next2.setVisible(false);
		};
	
	}
	@FXML
	void bobOnAction(ActionEvent event) throws IOException {
		enterbob++;
	FXMLLoader loader = new FXMLLoader(getClass().getResource("bobkey.fxml"));
	Parent root = loader.load();
    Stage stage= new Stage();
	 root.setStyle("-fx-padding: 10;" 
             + "-fx-border-style: solid outside;" 
             + "-fx-border-width: 2;" 
             + "-fx-border-insets: 5;" 
             + "-fx-border-radius: 5;" 
             + "-fx-border-color: white;"); 
    stage.initModality(Modality.WINDOW_MODAL);
    stage.initOwner(((Node) event.getSource()).getScene().getWindow());
    stage.initStyle(StageStyle.UNDECORATED);
    stage.setScene(new Scene(root));
    stage.show();
    if ((enteralice >=1) && (enterbob >=1)) {
		next2.setDisable(false);
	};

	}
	
	  public void nextbutton(ActionEvent event) throws IOException {
		   javaFxmlLoader object = new javaFxmlLoader();
			Pane view = object.getpage("sharedkey");
			borderpane.setCenter(view);
			
		
	}

}
