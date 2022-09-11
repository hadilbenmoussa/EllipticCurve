package application;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;

public class AboutController implements Initializable{
	@FXML
	private Hyperlink hyp1;
	@FXML
	private Hyperlink hyp2;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		assert hyp1 != null : "fx:id=\"hyp03\" was not injected: check your FXML file 'Hyperlink.fxml'.";
		assert hyp2 != null : "fx:id=\"hyp03\" was not injected: check your FXML file 'Hyperlink.fxml'.";
	
	}
	@FXML
	void hyp1OnAction(ActionEvent event) throws IOException, URISyntaxException{	
	Desktop.getDesktop().browse(new URI("https://hackernoon.com/what-is-the-math-behind-elliptic-curve-cryptography-f61b25253da3"));

}
	@FXML
	void hyp2OnAction(ActionEvent event) throws IOException, URISyntaxException{	
	Desktop.getDesktop().browse(new URI("https://avinetworks.com/glossary/elliptic-curve-cryptography/#:~:text=Elliptic%20Curve%20Cryptography%20(ECC)%20is,Adleman%20(RSA)%20cryptographic%20algorithm."));

}
	}
