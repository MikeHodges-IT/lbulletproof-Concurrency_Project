package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.FlowPane;

/**
 * 
 * This is the Concurrency Project. 
 * This class extends Application for javaFx.
 * 
 * @author Mike Hodges
 * @version 1.0000001
 * 
 * 
 */
public class Main extends Application {
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	/**
	 * This is the start method that calls the Launch method to start the stand
	 * alone application.
	 */
	public void start(Stage primaryStage) throws Exception {

		int availableProcessors = Runtime.getRuntime().availableProcessors();
		int cores = availableProcessors / 2;

		System.out.println("Available Processors " + availableProcessors);
		System.out.println("Processor Count = " + cores);

		primaryStage.setTitle("Concurrency Experiment");

//          Setup chart	 label etc... 

		NumberAxis xAxis = new NumberAxis();
		xAxis.setLabel("Array size");
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Time in nano sec");
		LineChart chart = new LineChart(xAxis, yAxis);
		chart.setPrefSize(350, 300);
		chart.setTitle("20 to 200,000 elaments in array");
		XYChart.Series series1 = new XYChart.Series();
		series1.setName("Single Thread");
//		XYChart.Series series2 = new XYChart.Series();
//		series2.setName("Two Threads");
		XYChart.Series series4 = new XYChart.Series();
		series4.setName("Four Threads");

//	        Iterate while adding 10 times the previous amount starting at 20 and ending at sizeArrayMax. 
//	        Add data to the chart with each iteration. 
//          Max size of array

		int sizeArrayMax = 200000;
		for (int i = 20; i <= sizeArrayMax; i *= 10) {
			int[] randArray = new int[i];
			System.out.println("Array Size " + randArray.length);
			for (int a = 0; a < randArray.length; a++) {
				randArray[a] = (int) ((Math.random() * 9) + 1);
			}

			series1.getData().add(new XYChart.Data(i, AddArray.timeTestThreads(randArray, 1)));
//			series2.getData().add(new XYChart.Data(i, AddArray.timeTestThreads(randArray, 2)));
			series4.getData().add(new XYChart.Data(i, AddArray.timeTestThreads(randArray, 4)));

		}
		chart.getData().addAll(series1,  series4);

//          Setup chart	 label etc... 
		NumberAxis xAxis2 = new NumberAxis();
		xAxis2.setLabel("Array size");
		NumberAxis yAxis2 = new NumberAxis();
		yAxis2.setLabel("Time in nano sec");
		LineChart chart2 = new LineChart(xAxis2, yAxis2);
		chart2.setPrefSize(350, 300);
		chart2.setTitle("200,000 to 200,000,000 elaments in array");
		XYChart.Series series12 = new XYChart.Series();
		series12.setName("Single Thread");
//		XYChart.Series series22 = new XYChart.Series();
//		series22.setName("Two Threads");
		XYChart.Series series42 = new XYChart.Series();
		series42.setName("Four Threads");

//	        Iterate while adding 10 times the previous amount starting at 200000 and ending at sizeArrayMax. 
//	        Add data to the chart with each iteration. 
		sizeArrayMax = 200000000;
		for (int i = 200000; i <= sizeArrayMax; i *= 10) {
			int[] randArray = new int[i];
			System.out.println("Array Size " + randArray.length);
			for (int a = 0; a < randArray.length; a++) {
				randArray[a] = (int) ((Math.random() * 9) + 1);
			}

			series12.getData().add(new XYChart.Data(i, AddArray.timeTestThreads(randArray, 1)));
//			series22.getData().add(new XYChart.Data(i, AddArray.timeTestThreads(randArray, 2)));
			series42.getData().add(new XYChart.Data(i, AddArray.timeTestThreads(randArray, 4)));

		}
		chart2.getData().addAll(series12, series42);

		FlowPane root = new FlowPane();
		root.getChildren().addAll(chart, chart2);
		Scene scene = new Scene(root, 700, 325);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	 /**
	  * 
	  * The application's entry point
     * @param args an array of command-line arguments for the application
	  *  
	  */
	public static void main(String[] args) {
		launch(args);
	}
}
