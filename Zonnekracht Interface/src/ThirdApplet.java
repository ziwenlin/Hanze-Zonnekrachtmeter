import java.util.ArrayList;
import java.util.List;
import org.gicentre.utils.stat.XYChart;
import processing.core.PApplet;
import processing.serial.Serial;

public class ThirdApplet extends PApplet {

    XYChart lineChart;
    Serial myPort;    // The serial port

  
    String getal = "0";
    float x = 0;
    List<Float> xValues = new ArrayList<Float>();
    List<Float> yValues = new ArrayList<Float>();


// Vul een String met een getal in 
// Deze functie converteert getallen naar Integers
// int getal = stringToInteger("9292");
int stringToInteger(String text) {
  int waarde = 0;
  for (int i = 0; i < text.length(); i++) {
    char l = text.charAt(i);
    if (l >= 48 && l <= 57) {
      waarde = ( waarde * 10 ) + ( l - 48 );
    }
  }  
  return waarde;
}
  public void settings(){
    size(600, 600);
  }
  public void setup(){
    textFont(createFont("Arial",10),10);

  // Both x and y data set here.  
  lineChart = new XYChart(this);


   
  // Assen en tekst bij assen.
  lineChart.showXAxis(true);       //Laten zien(true) of verbergen(false) van de X-as
  lineChart.showYAxis(true);       //Laten zien of verbergen van de Y-as
  lineChart.setMinY(0);            //Een minimale waarde instellen voor de Y-as
  lineChart.setMinX(0);
  lineChart.setXAxisLabel("Tijd in seconden");      //Tekst voor op de X-as
  lineChart.setYAxisLabel("Zonnekracht\n");           //Tekst voor op de Y-as
    
  lineChart.setYFormat("####");  // Hoeveel decimalen Y-as
  lineChart.setXFormat("0");    // Hoeveel decimalen X-as
   
  lineChart.setLineWidth(1);       //Dikte van de lijn
  lineChart.setPointSize(0);       //Dikte van de punten op de lijn
}
  public void draw() {
  background(255);                  //Kleur voor de achtergrond
  textSize(14);
  lineChart.draw(30,30,width-10,height-30);
   
  // Draw a title over the top of the chart.
  fill(13,0,117);                    //Kleur tekst
  textSize(20);                      //Grootte tekst
  text("Bruto zonnekracht", 70,30);        //De tekst en de positie
  textSize(11);
  text("Bruto zonnekracht temperatuur gecorrigeerd.", 70,45);
  xValues.add(x);
  yValues.add((float) stringToInteger(getal));
  float xValueArray[] = new float[xValues.size()];
  for (int i = 0; i < xValues.size(); i++) xValueArray[i] = xValues.get(i);
  float yValueArray[] = new float[yValues.size()];
  for (int i = 0; i < yValues.size(); i++) yValueArray[i] = yValues.get(i);        
        
  lineChart.setData(xValueArray, yValueArray);
  x++;
  }
}
