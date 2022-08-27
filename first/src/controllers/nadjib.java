package controllers;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 *
 * @author Nadjib
 */
public class nadjib extends Application {

     public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle(" hello  ! ");
        FlowPane flowflow = new FlowPane();
        Scene scene = new Scene(flowflow,500,500);
        Label label = new Label("label man ");




       scene.addEventFilter(MouseEvent.MOUSE_CLICKED,new 
      EventHandler<MouseEvent>(){
    	   Button cleaning = new Button();
    	   List<Double> pointspq = new ArrayList<>();
    	   
    	 
         
          
    	  

            @Override
           public void handle(MouseEvent event) {
            
                 label.setText("position x = : " + event.getScreenX() 
             + " positoon y = " + event.getScreenY() );
                 pointspq.add(event.getScreenX());
                 pointspq.add(event.getScreenY());
                 
                 if(pointspq.size() ==2) event.consume();
                
               
                 


            }
        });
     

        flowflow.getChildren().add(label);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
