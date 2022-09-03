
package application;
import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class menuoperationsController implements Initializable{
	@FXML
	private Hyperlink hyp01,hyp02;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		assert hyp01 != null : "fx:id=\"hyp01\" was not injected: check your FXML file 'Hyperlink.fxml'.";
		assert hyp02 != null : "fx:id=\"hyp01\" was not injected: check your FXML file 'Hyperlink.fxml'.";
	}
	@FXML
	void hyp01OnAction(ActionEvent event) throws IOException {	
	FXMLLoader loader = new FXMLLoader(getClass().getResource("basicoperations.fxml"));
	Parent root = loader.load();
    Stage stage= new Stage();
    stage.initModality(Modality.WINDOW_MODAL);
    stage.initOwner(((Node) event.getSource()).getScene().getWindow());
    stage.setScene(new Scene(root));
    stage.show();
		;
	}
	@FXML
	void hyp02OnAction(ActionEvent event) throws IOException {	
	FXMLLoader loader = new FXMLLoader(getClass().getResource("basicoperations.fxml"));
	Parent root = loader.load();
    Stage stage= new Stage();
    stage.initModality(Modality.WINDOW_MODAL);
    stage.initOwner(((Node) event.getSource()).getScene().getWindow());
    stage.setScene(new Scene(root));
    stage.show();
		;
	}
	@FXML
private void sendtobasicoperations(ActionEvent event) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("basicoperations.fxml"));
		Parent root = loader.load();
	    Stage stage= new Stage();
	    stage.setScene(new Scene(root));
	    stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node) event.getSource()).getScene().getWindow());
	    stage.show();
	    
	}
}