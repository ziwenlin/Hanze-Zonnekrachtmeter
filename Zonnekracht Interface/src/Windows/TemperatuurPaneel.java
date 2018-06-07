package Windows;
import java.util.ArrayList;
import java.util.List;
import org.gicentre.utils.stat.XYChart;

import Buttons.Extras;
import processing.core.PApplet;

public class TemperatuurPaneel extends PApplet {

	XYChart lineChart;
	String getal = "0";
	float x = 0;
	List<Float> xValues = new ArrayList<Float>();
	List<Float> yValues = new ArrayList<Float>();

	public void settings() {
		size(600, 600);									// De grootte van de window

	}

	public void setup() {
		frameRate(1);									// Framerate(1) zorgt ervoor dat er een waarde per seconde wordt doorgegeven. 
		textFont(createFont("Arial", 10), 10);			// Geeft de lettertype weer
		
		// Both x and y data set here.
		lineChart = new XYChart(this);	

		// Assen en tekst bij assen.
		lineChart.setAxisColour(255);					// Kleur van de X en Y as
		lineChart.setAxisLabelColour(255);				// Kleur van de labels bij de assen
		lineChart.setAxisValuesColour(255);				// Kleur van de waardes bij de assen
		lineChart.showXAxis(true); 						// Laten zien(true) of verbergen(false) van de X-as
		lineChart.showYAxis(true); 						// Laten zien of verbergen van de Y-as
		lineChart.setMinY(0); 							// Een minimale waarde instellen voor de Y-as
		lineChart.setMinX(0);							// Een minimale waarde instellen voor de X-as
		lineChart.setXAxisLabel("Tijd [s]"); 			// Tekst voor op de X-as
		lineChart.setYAxisLabel("Zonkracht [W/m²]\n"); 	// Tekst voor op de Y-as

		lineChart.setLineWidth(1); 						// Dikte van de lijn
		lineChart.setPointSize(0); 						// Dikte van de punten op de lijn
		lineChart.setLineColour(255);					// Kleur van de lijn (wit)

	}

	public void draw() {
		background(55); 								// Kleur voor de achtergrond (donkergrijs)
		textSize(14);									// Grootte van de tekst van de label
		lineChart.draw(30, 30, width - 30, height - 30);// Tekent de grafiek tussen het gebied die is aangegeven

		fill(200, 200, 0); 								// Kleur tekst titel
		textSize(20); 									// Grootte tekst titel
		text("Zonnekracht", 70, 30); 					// De tekst en de positie
		textSize(11);									// Grootte tekst onder titel
		text("Real-Time data van de zonnekracht in Volt.", 70, 45);		//De tekst en positie onder titel
		xValues.add(x);
		yValues.add((float) Extras.stringToInteger(getal));
		float xValueArray[] = new float[xValues.size()];
		for (int i = 0; i < xValues.size(); i++)
			xValueArray[i] = xValues.get(i);
		float yValueArray[] = new float[yValues.size()];
		for (int i = 0; i < yValues.size(); i++)
			yValueArray[i] = yValues.get(i);

		lineChart.setData(xValueArray, yValueArray);
		x++;
	}
}
