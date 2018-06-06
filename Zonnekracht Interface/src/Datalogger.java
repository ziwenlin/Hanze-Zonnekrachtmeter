import processing.core.PApplet;
import processing.serial.*;

public class Datalogger extends PApplet {

	Serial myPort; // The serial port

	public Datalogger() {
		if (Serial.list().length > 0) {
			myPort = new Serial(this, Serial.list()[0], 9600);
			myPort.bufferUntil(10);
		} else {
			myPort = new Serial(this);
		}
	}

}
