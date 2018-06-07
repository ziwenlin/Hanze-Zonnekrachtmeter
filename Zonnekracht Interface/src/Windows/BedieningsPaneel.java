package Windows;
import java.util.ArrayList;
import java.util.List;
import processing.core.PApplet;
import processing.serial.Serial;

public class BedieningsPaneel extends PApplet {

	private Serial myPort; // The serial port
	private String str = "";
	private List<String> list = new ArrayList<>();
	private List<Float> zonnekracht = new ArrayList<>();
	private List<Float> temperatuur = new ArrayList<>();

	public void settings() {
		size(900, 600);
	}

	public void setup() {
		
	}

	public void draw() {
		background(20);
		serialMonitor();

	}
	
	public void serialInit() {
		if (Serial.list().length > 0) {
			myPort = new Serial(this, Serial.list()[0], 9600);
			myPort.bufferUntil(10);
		}
	}
	
	public void serialMonitor() {
		
		if (myPort == null || !myPort.active()) {
			serialInit();
			return;
		}
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
		for (int i = 0; i < list.size(); i++) {
			text(list.get(i), 10, 20 * (i + 1));
		}
	}

	
	
	public void serialEvent(Serial p) {
		list.add(p.readString());
		str += p.readString();
	}

	public Serial getMyPort() {
		return myPort;
	}

	public void setMyPort(Serial myPort) {
		this.myPort = myPort;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public List<Float> getZonnekracht() {
		return zonnekracht;
	}

	public void setZonnekracht(List<Float> zonnekracht) {
		this.zonnekracht = zonnekracht;
	}

	public List<Float> getTemperatuur() {
		return temperatuur;
	}

	public void setTemperatuur(List<Float> temperatuur) {
		this.temperatuur = temperatuur;
	}
	
}
