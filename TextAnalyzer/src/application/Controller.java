package application;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Controller {
	String str;
	String urlStr = null;
	String tempString;
	String selection;
	String infoTxtStr;
	
	@FXML
    private WebView infoWeb;
			  
    @FXML
    private SplitPane splitHelp;
    
    @FXML
    private Button btnHelp;

    @FXML
    private Button btnChart;

    @FXML
    private Button btnSelect;

    @FXML
    private Button btnStart;
    
    @FXML
    private Button btnFormSelect;

    @FXML
    private Button btnAbout;

    @FXML
    private BarChart<?, ?> barChart;

    @FXML
    private TextArea textarea1;

    @FXML
    private WebView webView;

    @FXML
    private AnchorPane urlAnchor;

    @FXML
    private TextField urlTxt;


    @FXML
    private AnchorPane infoWebAnc;
 
 @FXML   
 public void initialize(){
 final WebEngine webEngine = infoWeb.getEngine();   	 
webEngine.loadContent(
		  "<Center><H1><b>FX Text Analyzer</b></H1></Center>"
		+ " Enter a URL or use the default URL below. <BR>"
		+ " Then click the Select button below.<BR>");
urlStr = "https://www.gutenberg.org/files/1065/1065-h/1065-h.htm";
urlTxt.setText(urlStr);
btnSelect.setVisible(false);
btnChart.setVisible(false);
   
 }

 @FXML  
    void onActionBtn(ActionEvent event) {
	    final WebEngine webEngine = infoWeb.getEngine();  
    	str = event.getSource().toString().substring(event.getSource().toString().indexOf("=")+1, event.getSource().toString().indexOf(","));
    	
    	System.out.println(str + " Clicked");
    	System.out.println(event.getSource().toString());
    	System.out.println(splitHelp.toString());
    	

     	   
    	switch(str) {
    	case "btnStart":
    		HideALl();
    		webEngine.loadContent(
    				  "<Center><H1><b>FX Text Analyzer</b></H1></Center>"
    				+ " Enter a URL or use the default URL below. <BR>"
    				+ " Then click the select button below. <BR>");
    		urlStr = "https://www.gutenberg.org/files/1065/1065-h/1065-h.htm";
    		urlAnchor.setVisible(true);
    		break;
    		
    	case "btnFormSelect":
    		HideALl();
    		webEngine.loadContent(
  				  "<Center><H1><b>FX Text Analyzer</b></H1></Center>"
  				+ " Select the text to be counted.  <BR>"
  				+ " After selection is made click Select text button that will appear above. <BR>");
    		urlStr = urlTxt.getText();
    		webView.setVisible(true);
    	    webView.getEngine().load(urlStr);     	    
    		break;
    	case "btnSelect":
    		HideALl();
    		webEngine.loadContent(
  				  "<Center><H1><b>FX Text Analyzer</b></H1></Center>"
  				+ " Approve your selection and click Graph Data button above.  <BR>"
  				+ " Or click select the Select URL button above to start over <BR>"
  				);
    		if (webView != null) {
            	selection = (String) webView.getEngine().executeScript("window.getSelection().toString()");
            	textarea1.setText(selection);
            }
        	   textarea1.setVisible(true);
   	     	   tempString = selection;
    		btnSelect.setVisible(false);
    		btnChart.setVisible(true);
    		
    		break;
    	case "btnChart":
    		HideALl();
       		webEngine.loadContent(
  				  "<Center><H1><b>FX Text Analyzer</b></H1></Center>"
  				+ " View charted results. <BR>"
  				+ " Then click the Select URL button to start again. <BR>"
  				);
    		barChart.getData().clear();
            XYChart.Series dataSeries1 = new XYChart.Series();
            dataSeries1.setName("2014");
    		
    		final List<String> wordlist = Stream.of
    				(
    				tempString
    				.replaceAll("&mdash;" , " ")
    				.replaceAll("<[^>]*>"," ")
    				.replaceAll("[\\s+\\W\\d]", " ")
    				.trim()			
    				.toLowerCase()
    				.split("\\s+"))
    				.collect(Collectors.toList()
    				);
    		final Map<String, Long> wordFreqMap = 
    				wordlist
    				.stream()
    				.collect(
    						Collectors.groupingBy( 
    								Function.identity(), 
    								Collectors.counting()
    								)
    						);
    		wordFreqMap
    		.entrySet()
    		.stream()
    		.sorted(
    				Map
    				.Entry
    				.<String, Long>comparingByValue()
    				.reversed()
    				)
    		.limit(20)
    		.forEach(
    				e -> { dataSeries1.getData().add(new XYChart.Data(e.getKey(),e.getValue())); 
    				       System.out.println(str + " Clicked");
    				}
    				);
    		
    		
    		barChart.getData().add(dataSeries1);
    		barChart.setVisible(true);

    		break;
    	case "btnHelp":
    		textarea1.appendText(str + " Clicked");

    	    if(infoWebAnc.isVisible()) {
    	    	btnHelp.setText("View Help");
    	    	infoWebAnc.setVisible(false);
    	        splitHelp.setDividerPositions(0.0);
    	    }else{
    	    	btnHelp.setText("Hide Help");
    	    	infoWebAnc.setVisible(true);
    	    	splitHelp.setDividerPositions(0.16);
    	    }
    	    
    		break;
    	case "btnAbout":
    		HideALl();
   		if(infoWebAnc.isVisible() == false) {
	    	btnHelp.setText("Hide Help");
	    	infoWebAnc.setVisible(true);
	    	splitHelp.setDividerPositions(0.16);   			
   		} 
    	webEngine.loadContent(		
		  "<Center><H1><b>FX Text Analyzer</b></H1></Center>"
		+ " Created by Mike Hodges <BR>"
		+ " For Assignment Six <BR>"
		+ " ");
		break;
    		    	
    	} 
    }
 @FXML
 void onMouseDrag(MouseEvent event) {
	 btnSelect.setVisible(true);
	 final WebEngine webEngine = infoWeb.getEngine();
	 webEngine.loadContent( "<Center><H1><b>FX Text Analyzer</b></H1></Center>"
		+ " Click Select Text above.<BR>"
		+ " <BR>");

 }
void HideALl() {
	
    barChart.setVisible(false);
	textarea1.setVisible(false);
	urlAnchor.setVisible(false);
	webView.setVisible(false);
	btnSelect.setVisible(false);
	btnChart.setVisible(false);
	
}


}