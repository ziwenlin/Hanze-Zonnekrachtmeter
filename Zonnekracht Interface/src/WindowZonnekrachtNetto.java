import java.util.ArrayList;
import java.util.List;
import processing.core.PApplet;
import processing.serial.Serial;

public class WindowZonnekrachtNetto extends PApplet {

	Serial serial;
	String serialbuffer;
	List<String> list = new ArrayList<String>();

	void serialConnect() {
		if (Serial.list().length > 0) {
			serial = new Serial(this, Serial.list()[0], 9600);
			// serial.buffer(16);
			serial.bufferUntil(10);
		}
	}

	public void settings() {
		size(900, 600);
	}

	public void setup() {
		serialConnect();
	}

	public void draw() {
		background(20);
		while (list.size() > 28) {
			list.remove(0);
		}
		textAlign(CENTER);
		textSize(60);
		if (list.size() > 27) {
			text(list.get(27), 450, 100);
		}
		textAlign(LEFT);
		textSize(24);
		if (serial != null && serial.active()) {
			for (int i = 0; i < list.size(); i++) {
				text(list.get(i), 10, 20 * (i + 1));
			}
		} else {
			text("No COM detected", 40, 200);
			println("No COM detected");
			// textSize(70);
			delay(1000);
			exit();
		}
	}

	void serialEvent(Serial p) {
		list.add(p.readString());
	}

}
