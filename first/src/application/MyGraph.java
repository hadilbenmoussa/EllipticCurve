package application;
import java.util.function.Function;
import javafx.scene.chart.XYChart;

public class MyGraph {
    private XYChart<Double, Double> graph;
	private double range;
    public MyGraph(XYChart<Double, Double> graph,  double range) {
		this.graph = graph;
		this.range = range;
	}

    public void plotLine( Function<Double, Double> function) {
		 XYChart.Series<Double, Double> series = new XYChart.Series<Double, Double>();
		for (double x = -range/2; x <= range/2; x = x + 0.001) {
			plotPoint(x, function.apply(x), series);
		}
		for (double x = -range; x <= -range/2; x = x + 0.1) {
			
			plotPoint(x, function.apply(x), series);
		}
		for (double x = range/2; x <= range; x = x + 0.1) {
			plotPoint(x, function.apply(x), series);
			
		}
		graph.getData().add(series);
	}

	private void plotPoint( double x, double y,
		 XYChart.Series<Double, Double> series) {
		series.getData().add(new XYChart.Data<Double, Double>(x, y));
	}
	

	public void plotL( double Px, double Py,double Qx,double Qy)
 {
		 XYChart.Series<Double, Double> series = new XYChart.Series<Double, Double>();
			series.getData().add(new XYChart.Data<Double, Double>(Px, Py));
			series.getData().add(new XYChart.Data<Double, Double>(Qx, Qy));
			series.getData().add(new XYChart.Data<Double, Double>(10.0,((Qy-Py)/(Qx-Px))*(10-Px)+Py));
			series.getData().add(new XYChart.Data<Double, Double>(-10.0,((Qy-Py)/(Qx-Px))*(-10-Px)+Py));

			graph.getData().add(series);
}
	public void plotProjection(double Rx,double Ry) {
		 XYChart.Series<Double, Double> series = new XYChart.Series<Double, Double>();
			series.getData().add(new XYChart.Data<Double, Double>(Rx, Ry));
			series.getData().add(new XYChart.Data<Double, Double>(Rx, 0.0));
			series.getData().add(new XYChart.Data<Double, Double>(Rx, -10.0));
			series.getData().add(new XYChart.Data<Double, Double>(Rx, 10.0));
			graph.getData().add(series);
	}
	public void plottangent(Integer a,Integer b,double Px,double Py) {
		double dfp=(3*Px*Px+a)/(2*Math.sqrt(Math.pow(Px, 3)+a*Px+b));
		 XYChart.Series<Double, Double> series = new XYChart.Series<Double, Double>();
			series.getData().add(new XYChart.Data<Double, Double>(Px, Py));
			series.getData().add(new XYChart.Data<Double, Double>(Px*(-10), Py+(-11)*dfp*Px));
			series.getData().add(new XYChart.Data<Double, Double>(Px*10, Py+9*dfp*Px));
			graph.getData().add(series);
	}
	

	public void clear() {
		graph.getData().clear();
	}
}
