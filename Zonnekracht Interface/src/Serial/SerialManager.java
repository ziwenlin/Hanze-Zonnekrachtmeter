package Serial;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Main.ExtraFuncties;
import processing.core.PApplet;
import processing.serial.Serial;

public class SerialManager {

	public static PApplet p;
	public static Serial myPort;
	public static long startTijd = System.currentTimeMillis();
//	public static LocalDateTime now = LocalDateTime.now();
//	public static DateTime startTijd = new DateTime(now.getYear(), now.getMonthValue(), now.getDayOfMonth(), now.getHour(), now.getMinute(), now.getSecond());
	public static List<String> inputSerial = new ArrayList<>();
	public static List<Float> tijd = new ArrayList<>();
	public static List<Float> zonnekracht = new ArrayList<>();
	public static List<Float> temperatuur = new ArrayList<>();
	public static boolean canSplit = false;

	public SerialManager(PApplet p) {
		SerialManager.p = p;
	}

	public void serialInit() {
		if (myPort != null) {
			myPort.stop();
			myPort.dispose();
		}
		if (Serial.list().length > 0) {
			myPort = new Serial(p, Serial.list()[0], 9600);
			myPort.bufferUntil('\n');
		}
	}

	public void serialLoop() {

		if (myPort == null || !myPort.active()) {
			serialInit();
		}
		
		while (inputSerial.size() > 25) {
			inputSerial.remove(0);
		}

//		list.add("2018/06/06-15:30:45   Temperatuur: 26.00 C   Voltage: 0.30 V");
//		textMonitor(p);
		stringsplitter();

	}

	public static void stringsplitter() {
		if (inputSerial.size() == 0 && !canSplit ) {			// Nullpointerexception voorkomen
			return;
		}
		List<String> b = ExtraFuncties.stringCutter(inputSerial.get(inputSerial.size() - 1));
		if (b.size() < 7) {
			return;
		}
		canSplit = false;
		temperatuur.add(ExtraFuncties.stringToFloat(b.get(4)));
		zonnekracht.add(ExtraFuncties.stringToFloat(b.get(9)));
//		tijd.add(ExtraFuncties.getMinute(startTijd, ExtraFuncties.splitDateTime(b.get(0))));
		tijd.add((float) (System.currentTimeMillis() - startTijd)/60000.0f);
	}



}
