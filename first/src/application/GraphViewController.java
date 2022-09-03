package application;

import java.net.URL;
import java.text.DecimalFormat;

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

import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class GraphViewController implements Initializable{
	@FXML
	public LineChart<Double, Double> lineGraph;

	@FXML
	private Button lineGraphButton;

	@FXML
	private Button areaGraphButton;
	
   @FXML
	public Label cursorCoords;
   private static DecimalFormat df =new DecimalFormat("#.##");
   
	public MyGraph mathsGraph;

	public Integer a;
	public Integer b;
	public Integer k;
    public boolean op=true/*true sig l'operation add est selectionné*/;
    public double Rx,Ry;
@FXML 
public Label Rval;
@FXML 
public Label Qval;
@FXML 
public Label Pval;



@Override
public void initialize(final URL url, final ResourceBundle rb) {
	    
	mathsGraph = new MyGraph(lineGraph, 10);
    lineGraph.setVisible(true);
	
	
		
	}

public void set_op(boolean op) {
	this.op=op;
}
public void set_k(Integer k) {
	this.k=k;
};

public void set_a(Integer a) {
	this.a=a;
};
public void set_b(Integer b) {
	this.b=b;
};

public void plotLine(Function<Double, Double> function) {
	
			mathsGraph.plotLine(function);
			cursorCoords = createCursorGraphCoordsMonitorLabel(lineGraph);}
	
public Label createCursorGraphCoordsMonitorLabel(LineChart<Double, Double> lineChart){
	final Axis<Double> xAxis = lineChart.getXAxis();
	final Axis<Double> yAxis = lineChart.getYAxis();
    final Label cursorCoords = new Label();
    Node chartBackground = lineGraph.lookup(".chart-series-line.series0");
    for (Node n: chartBackground.getParent().getChildrenUnmodifiable()) {
	      if (n != chartBackground && n != xAxis && n != yAxis) {
	    	  }};
	    
	chartBackground.addEventFilter(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>(){
	    Double Px,Qx,Py,Qy;
	 	Button cleaning = new Button();
	 	List<Double> pointspq = new ArrayList<>();
	 	   
	 	 
	   @Override
	   public void handle(MouseEvent event) {
	        pointspq.add(xAxis.getValueForDisplay(event.getX()));
            pointspq.add(yAxis.getValueForDisplay(event.getY()));
	        if((op==true) &&(pointspq.size() ==4)) {
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
                Qval.setVisible(true);
                Pval.setText("P = ("+df.format(Px)+" ,"+df.format(Py)+")");
                Qval.setText("Q = ("+df.format(Qx)+" ,"+df.format(Qy)+")");
                mathsGraph.plotL(Px,Py,Qx,Qy);
                ECPoint R=third_intersection(new ECPoint(Px,Py),new ECPoint(Qx,Qy),a,b);
                Rval.setText("R = ("+df.format(Rx)+" ,"+df.format(Ry)+")");
                mathsGraph.plotProjection(R.xp,R.yp);
                pointspq.clear();
                }
	        
                else if ((op==false)&&(pointspq.size()==2)) {
            	event.consume();
            	Qval.setVisible(false);
	            Px=pointspq.get(0);
	            Py=pointspq.get(1);
	            pointspq.clear();
	            System.out.println("px=");
	            System.out.println(Px);
	            System.out.println("py=");
	            System.out.println(Py);
	            Pval.setText("P = ("+df.format(Px)+" ,"+df.format(Py)+")");
	            mathsGraph.plottangent(a,b,Px,Py);
	            ECPoint R1=intersctiondoubling(new ECPoint(Px,Py),a,b);
	            mathsGraph.plotProjection(R1.xp,R1.yp);
	            ECPoint R=doubleandadd(new ECPoint(Px,Py),k,a,b);
                Rval.setText("R = ("+df.format(R.xp)+" ,"+df.format(R.yp)+")");
	            
	            pointspq.clear();
            }
           }});
	 
	return cursorCoords; 
	};

private ECPoint third_intersection(ECPoint P, ECPoint Q ,Integer a, Integer b) {
	 
    double beta = (Q.yp-P.yp)/(Q.xp-P.xp) ;

    Rx = beta*beta-P.xp-Q.xp;
    Ry = beta*(P.xp-Rx)-P.yp;
	System.out.println("Rx=");
    System.out.println(Rx);
	System.out.println("Ry= ");
	System.out.print(Ry);
	return new ECPoint(Rx,Ry);
	}
;
private ECPoint intersctiondoubling(ECPoint P,Integer a, Integer b) {
	 
    double beta =  (3*P.xp*P.xp+a)/(2*P.yp);
	Rx = beta*beta-P.xp-P.xp;
    Ry = beta*(P.xp-Rx)-P.yp; 
	System.out.println("Rx=");
    System.out.println(Rx);
	System.out.println("Ry= ");
	System.out.print( Ry);
	return new ECPoint(Rx,Ry);
	}

private ECPoint doubleandadd(ECPoint P,int k,Integer a,Integer b)
{   if(k==1) {return P;}
   else if((k%2)==1) {
	   return third_intersection(P,doubleandadd(P,k-1,a,b),a,b);
   }
   else {
	
	return intersctiondoubling(doubleandadd(P,k/2,a,b),a,b);}
	 }
	}

	    
