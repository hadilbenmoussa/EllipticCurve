package application;

import java.net.URL;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.function.Function;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;

public class GraphViewController implements Initializable{
	@FXML
	private LineChart<Double, Double> lineGraph;
	@FXML
	private AreaChart<Double, Double> areaGraph;
	@FXML
	private Button lineGraphButton;

	@FXML
	private Button areaGraphButton;
	
   @FXML
	private Label cursorCoords;
   
   
	private MyGraph mathsGraph;
	private MyGraph areaMathsGraph;
	public Integer a;
	public Integer b;



@Override
public void initialize(final URL url, final ResourceBundle rb) {
	    
	mathsGraph = new MyGraph(lineGraph, 10);
	areaMathsGraph = new MyGraph(areaGraph, 10);
		
	}



public void set_a(Integer aval) {
	this.a=aval;
};
public void set_b(Integer bval) {
	this.a=bval;
};
@FXML
private void handleLineGraphButtonAction(final ActionEvent event) {
	lineGraph.setVisible(true);
	areaGraph.setVisible(false);
	plotLine(x ->Math.sqrt(Math.pow(x, 3)+a*Math.pow(x, 2)+b));
	plotLine(x -> -(Math.sqrt(Math.pow(x, 3)+a*Math.pow(x, 2)+b)));
	}
private void plotLine(Function<Double, Double> function) {
	if (lineGraph.isVisible()) {
			mathsGraph.plotLine(function);
			cursorCoords = createCursorGraphCoordsMonitorLabel(lineGraph);}
   else {areaMathsGraph.plotLine(function);
		}
	}
@FXML
private void handleAreaGraphButtonAction( ActionEvent event) {
	areaGraph.setVisible(true);
	lineGraph.setVisible(false);
	plotLine(x ->Math.sqrt(Math.pow(x, 3)+Math.pow(x, 2)+15));
	plotLine(x -> -(Math.sqrt(Math.pow(x,3)+Math.pow(x,2)+15)));}
	

private Label createCursorGraphCoordsMonitorLabel(LineChart<Double, Double> lineChart){
	final Axis<Double> xAxis = lineChart.getXAxis();
	final Axis<Double> yAxis = lineChart.getYAxis();
    final Label cursorCoords = new Label();
    Node chartBackground = lineGraph.lookup(".chart-series-line.series0");
    for (Node n: chartBackground.getParent().getChildrenUnmodifiable()) {
	      if (n != chartBackground && n != xAxis && n != yAxis) {
	    	  }};
	    
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
	  
	  
	chartBackground.addEventFilter(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>(){
	    Double Px,Qx,Py,Qy;
	 	Button cleaning = new Button();
	 	List<Double> pointspq = new ArrayList<>();
	 	   
	 	 
	   @Override
	   public void handle(MouseEvent event) {
	        pointspq.add(xAxis.getValueForDisplay(event.getX()));
            pointspq.add(yAxis.getValueForDisplay(event.getY()));
	        if(pointspq.size() ==4) {
	        	event.consume();
	            Px=pointspq.get(0);
	            Py=pointspq.get(1);
	            Qx=pointspq.get(2);
	            Qy=pointspq.get(3);
	            System.out.println("px=");
	            System.out.println(Px);
	            System.out.println("py=");
	            System.out.println(Py);
	            System.out.println("qx=");
        		System.out.println(Qx);
        	    System.out.println("qy=");
                System.out.println(Qy);
                third_intersection(Px,Py,Qx,Qy,1,15);
                mathsGraph.plotL(Px,Py,Qx,Qy);
           }}});
	  
	    
     chartBackground.setOnMouseExited(new EventHandler<MouseEvent>() {
	      @Override
	      public void handle(MouseEvent mouseEvent) {
	        cursorCoords.setVisible(false);
	      }});

	    xAxis.setOnMouseEntered(new EventHandler<MouseEvent>() {
	      @Override public void handle(MouseEvent mouseEvent) {
	        cursorCoords.setVisible(true);
	      }
	    });

	    xAxis.setOnMouseMoved(new EventHandler<MouseEvent>() {
	      @Override 
	      public void handle(MouseEvent mouseEvent) {
	        cursorCoords.setText(
	          String.format(
	            "x = %.2f",
	            xAxis.getValueForDisplay(mouseEvent.getX())
	          ));
	      }
	    });

	    xAxis.setOnMouseExited(new EventHandler<MouseEvent>() {
	      @Override 
	      public void handle(MouseEvent mouseEvent) {
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
	      @Override
	      public void handle(MouseEvent mouseEvent) {
	        cursorCoords.setVisible(false);
	      }
	    });
	    return cursorCoords; };
    

private void third_intersection(double Px, double Py, double Qx, double Qy, double a, double b) {
	 
    double beta =  3*Px*Px*Qx + 2*Px*a - Py*Py - 2*Py*Qy + Qx*a + 3*b;;
	double gamma = 3*Px*Qx*Qx + 2*Qx*a - Qy*Qy - 2*Py*Qy + Px*a + 3*b; ;
    double denominator = gamma - beta; 
    double Rx = (gamma*Px - beta*Qx)/denominator;
    double Ry = (gamma*Py - beta*Qy)/denominator; 
	System.out.println("Rx=");
    System.out.println(Rx);
	System.out.println("Ry= ");
	System.out.print( Ry);
	mathsGraph.plotProjection(Rx,Ry);}
}
	    
	  