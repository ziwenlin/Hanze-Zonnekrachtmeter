package Windows;
import java.util.ArrayList;
import java.util.List;

import org.gicentre.utils.stat.XYChart;

import Serial.SerialManager;
import processing.core.PApplet;

public class TemperatuurPaneel extends PApplet {

	XYChart lineChart;
	String getal = "0";
	float x = 0;
	List<Float> xValues = new ArrayList<Float>();
	List<Float> yValues = new ArrayList<Float>();

	public void settings() {
		size(600, 600);
	}

	public void setup() {
		surface.setResizable(true); 					// Venster grootte kan worden aangepast
		frameRate(1); // tijd per seconde
		textFont(createFont("Arial", 10), 10);

		// Both x and y data set here.
		lineChart = new XYChart(this);

		// Assen en tekst bij assen.
		lineChart.setAxisColour(255);					// Kleur van de X en Y as
		lineChart.setAxisLabelColour(255);				// Kleur van de labels bij de assen
		lineChart.setAxisValuesColour(255);				// Kleur van de waardes bij de assen
		lineChart.showXAxis(true); 						// Laten zien(true) of verbergen(false) van de X-as
		lineChart.showYAxis(true); 						// Laten zien of verbergen van de Y-as
		lineChart.setMinX(0);							// Een minimale waarde instellen voor de X-as
		lineChart.setXAxisLabel("Tijd [s]"); 			// Tekst voor op de X-as
		lineChart.setYAxisLabel("Temperatuur [°C]\n"); 	// Tekst voor op de Y-as
		lineChart.setLineWidth(1); 						// Dikte van de lijn
		lineChart.setPointSize(0); 						// Dikte van de punten op de lijn
		lineChart.setLineColour(255);					// Kleur van de lijn
	}

	public void draw() {
		background(55); 								// Kleur voor de achtergrond
		textSize(14);
		lineChart.draw(30, 30, width - 30, height - 30);

		fill(200, 200, 0); 								// Kleur tekst titel en onder titel
		textSize(20); 									// Grootte tekst titel
		text("Temperatuur sensor", 70, 30); 			// De tekst en de positie
		textSize(11);									// Grootte tekst onder titel
		text("Data van de Temperatuursensor in graden Celcius", 70, 45); // Tekst onder titel
		xValues.add(x);
		float xValueArray[] = new float[xValues.size()];
		for (int i = 0; i < xValues.size() && i < SerialManager.temperatuur.size(); i++)
			xValueArray[i] = xValues.get(i);
		float yValueArray[] = new float[xValues.size()];
		for (int i = 0; i < xValues.size() && i < SerialManager.temperatuur.size(); i++)
			yValueArray[i] = SerialManager.temperatuur.get(i);
		lineChart.setData(xValueArray, yValueArray);
		x++;
	}
}
