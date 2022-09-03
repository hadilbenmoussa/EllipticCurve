package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class menuecdhController {
	@FXML
	private Hyperlink hyp03;
	public void initialize(URL arg0, ResourceBundle arg1) {
		assert hyp03 != null : "fx:id=\"hyp03\" was not injected: check your FXML file 'Hyperlink.fxml'.";
	}
	@FXML
	void hyp03OnAction(ActionEvent event) throws IOException{	
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ECDH.fxml"));
		Parent root = loader.load();
	    Stage stage= new Stage();
	    stage.setScene(new Scene(root));
	    stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node) event.getSource()).getScene().getWindow());
	    stage.show();
			;
	}
	}

