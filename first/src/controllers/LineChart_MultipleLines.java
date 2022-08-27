package controllers;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.Axis;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
public class LineChart_MultipleLines extends Application {
   public void start(Stage stage) {
      //Defining the x an y axes
      Axis<Number> xAxis = new NumberAxis();
      NumberAxis yAxis = new NumberAxis();
      //Setting labels for the axes
      xAxis.setLabel("Month");
      yAxis.setLabel("Temperature(°C)");
      //Creating a line chart
      LineChart<Number, Number> linechart = new LineChart<Number, Number>(xAxis, yAxis);
      //Preparing the data points for the line1
      XYChart.Series series1 = new XYChart.Series();
      series1.getData().add(new XYChart.Data(0, 0));
      series1.getData().add(new XYChart.Data(10, 10));
     
      //Preparing the data points for the line2
      XYChart.Series series2 = new XYChart.Series();
      series2.getData().add(new XYChart.Data(0, 2));
      series2.getData().add(new XYChart.Data(10, 2));
     
      //Preparing the data points for the line3
      XYChart.Series series3 = new XYChart.Series();
      series3.getData().add(new XYChart.Data(1, 3.9));
      series3.getData().add(new XYChart.Data(6, 4.2));
     
     
      //Setting the name to the line (series)
      series1.setName("Tokyo");
      series2.setName("New York");
      series3.setName("London");
      //Setting the data to Line chart
      linechart.getData().addAll(series1, series2);
      //Creating a stack pane to hold the chart
      StackPane pane = new StackPane(linechart);
      pane.setPadding(new Insets(15, 15, 15, 15));
      pane.setStyle("-fx-background-color: BEIGE");
      List<XYChart.Data> list1 = XYChart.Data;
      List<XYDataItem> list2 = two.getItems();
      for (XYDataItem i : list1) {
          for (XYDataItem j : list2) {
              if (i.equals(j)) {
                  System.out.println(i);
              }
          }
      }
      
      //Setting the Scene
      Scene scene = new Scene(pane, 595, 350);
      stage.setTitle("Line Chart");
      stage.setScene(scene);
      stage.show();
   }
   public static void main(String args[]){
      launch(args);
   }
}