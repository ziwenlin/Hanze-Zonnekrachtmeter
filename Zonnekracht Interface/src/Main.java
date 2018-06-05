import processing.core.PApplet;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main(WindowZonnekrachtNetto.class.getName());


		String[] args2 = { "SecondApplet" };
		SecondApplet sa = new SecondApplet();
		PApplet.runSketch(args2, sa);

		String[] args1 = { "ThirdApplet" };
		ThirdApplet sb = new ThirdApplet();
		PApplet.runSketch(args1, sb);
	}

}
