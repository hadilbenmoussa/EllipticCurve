package controllers;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LineChartWithHoverCoords extends Application {

  @Override public void start(Stage stage) {
    stage.setTitle("Line Chart Sample");

    final LineChart<Number, Number> lineChart = createChart();
    Label cursorCoords = createCursorGraphCoordsMonitorLabel(lineGraph);
   

    stage.setScene(
      new Scene(
        layoutScene(
          lineChart, 
          cursorCoords)
        
      )
    );
    stage.show();
  }

  private VBox layoutScene(LineChart<Double, Double> lineChart, Label cursorCoords) {
    VBox layout = new VBox(10);
    layout.setPadding(new Insets(10));
    layout.setAlignment(Pos.CENTER);
    layout.getChildren().setAll(
      cursorCoords,
      lineChart
    );
    return layout;
  }

  private LineChart<Double, Double> createChart() {
    final NumberAxis xAxis = new NumberAxis();
    final NumberAxis yAxis = new NumberAxis();
    xAxis.setLabel("Number of Month");
    final LineChart<Number,Number> lineChart =
        new LineChart<>(xAxis,yAxis);

    lineChart.setTitle("Stock Monitoring, 2010");
    XYChart.Series<Double, Double> series = new XYChart.Series<>(
      "My portfolio", FXCollections.<XYChart.Data<Number, Number>>observableArrayList(
        new XYChart.Data<Number, >(1, 23),
        new XYChart.Data<Number, Number>(2, 14),
        new XYChart.Data<Number, Number>(3, 15),
        new XYChart.Data<Number, Number>(4, 24),
        new XYChart.Data<Number, Number>(5, 34),
        new XYChart.Data<Number, Number>(6, 36),
        new XYChart.Data<Number, Number>(7, 22),
        new XYChart.Data<Number, Number>(8, 45),
        new XYChart.Data<Number, Number>(9, 43),
        new XYChart.Data<Number, Number>(10, 17),
        new XYChart.Data<Number, Number>(11, 29),
        new XYChart.Data<Number, Number>(12, 25)
    )
    );

    lineChart.getData().add(series);
    return lineChart;
  }

  private Label createCursorGraphCoordsMonitorLabel(LineChart<Number, Number> lineChart) {
	  Glow glow= new Glow();
    final Axis<Number> xAxis = lineChart.getXAxis();
    final Axis<Number> yAxis = lineChart.getYAxis();

    final Label cursorCoords = new Label();

    final Node chartBackground = lineChart.lookup(".chart-series-line.series0");
	
    for (Node n: chartBackground.getParent().getChildrenUnmodifiable()) {
      if (n != chartBackground && n != xAxis && n != yAxis) {
    	 System.out.println(n); 
       
       

      }
    }

    chartBackground.setOnMouseEntered(new EventHandler<MouseEvent>() {
      @Override public void handle(MouseEvent mouseEvent) {
        cursorCoords.setVisible(true);
      }
    });
   

    chartBackground.setOnMouseMoved(new EventHandler<MouseEvent>() {
      @Override public void handle(MouseEvent mouseEvent) {
    	  
        cursorCoords.setText(
          String.format(
            "(%.2f, %.2f)",
            xAxis.getValueForDisplay(mouseEvent.getX()),
            yAxis.getValueForDisplay(mouseEvent.getY())
          ));
 
      } }
    );
  
   




  
    
    
   chartBackground.addEventFilter(MouseEvent.MOUSE_CLICKED,new 
   EventHandler<MouseEvent>(){
 	   Button cleaning = new Button();
 	   List<Double> pointspq = new ArrayList<>();
 	   
 	 
          @Override
        public void handle(MouseEvent event) {
         
             
              pointspq.add(event.getScreenX());
              pointspq.add(event.getScreenY());
              
              if(pointspq.size() ==4) {event.consume();
            	  System.out.print(  
            	      pointspq.get(0));
            	      System.out.print( pointspq.get(1));
            	      System.out.print(pointspq.get(2));
            	      System.out.print(pointspq.get(3));};
             
            
              


         }
     });
  
    

    chartBackground.setOnMouseExited(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent mouseEvent) {
        cursorCoords.setVisible(false);
      }
    });

    xAxis.setOnMouseEntered(new EventHandler<MouseEvent>() {
      @Override public void handle(MouseEvent mouseEvent) {
        cursorCoords.setVisible(true);
      }
    });

    xAxis.setOnMouseMoved(new EventHandler<MouseEvent>() {
      @Override public void handle(MouseEvent mouseEvent) {
        cursorCoords.setText(
          String.format(
            "x = %.2f",
            xAxis.getValueForDisplay(mouseEvent.getX())
          )
        );
      }
    });

    xAxis.setOnMouseExited(new EventHandler<MouseEvent>() {
      @Override public void handle(MouseEvent mouseEvent) {
        cursorCoords.setVisible(false);
      }
    });

    yAxis.setOnMouseEntered(new EventHandler<MouseEvent>() {
      @Override public void handle(MouseEvent mouseEvent) {
        cursorCoords.setVisible(true);
      }
    });

    yAxis.setOnMouseMoved(new EventHandler<MouseEvent>() {
      @Override public void handle(MouseEvent mouseEvent) {
        cursorCoords.setText(
          String.format(
            "y = %.2f",
            yAxis.getValueForDisplay(mouseEvent.getY())
          )
        );
      }
    });

    yAxis.setOnMouseExited(new EventHandler<MouseEvent>() {
      @Override public void handle(MouseEvent mouseEvent) {
        cursorCoords.setVisible(false);
      }
    });
    

    return cursorCoords;
  }
  
  

  public static void main(String[] args) {
    launch(args);
  }
}