package Main;

import java.util.ArrayList;
import java.util.List;

import Serial.DateTime;

public class ExtraFuncties {

	public static int stringToInteger(String text) {
		int waarde = 0;
		for (int i = 0; i < text.length(); i++) {
			char l = text.charAt(i);
			if (l >= 48 && l <= 57) {
				waarde = (waarde * 10) + (l - 48);
			}
		}
		return waarde;
	}

	public static float stringToFloat(String text) {
		float waarde = 0.0f, d = 1; boolean c = false, n = false;
		for (int i = 0; i < text.length(); i++) {
			char l = text.charAt(i);
			if (!c && l >= 48 && l <= 57) {
				waarde = (waarde * 10) + (l - 48);
				continue;
			}
			if (c && (l >= 48 && l <= 57) ) {
				d *= 10;
				waarde += (l - 48) / d;
				continue;
			}
			if (l == '.') c = true;
			if (l == '-') n = true;
		}
		return waarde * ((n) ? -1 : 1);
	}

	public static List<String> stringCutter(String text) {
		List<String> list = new ArrayList<String>();
		list.add(""); int i = 0;
		for (char c : text.toCharArray()) {
			if (c == ' ' || c == '\t') {
				list.add("");
				i++;
				continue;
			}
			list.set(i, list.get(i) + c);
		}
		return list;
	}
	
	public static List<String> stringDateTimeCutter(String text) {
		List<String> list = new ArrayList<>();
		list.add(""); int i = 0;
		for (char c : text.toCharArray()) {
			if (c == ':' || c == '-' || c == '/') {
				list.add("");
				i++;
				continue;
			}
			list.set(i, list.get(i) + c);
		}
		return list;
	}
	
	public static List<String> splitDateTime(String string) {
		List<String> a = ExtraFuncties.stringDateTimeCutter(string);
		return a;
	}
	
	public static float getMinute(DateTime startTijd, List<String> list) {		
//		int year = ExtraFuncties.stringToInteger(list.get(0));
//		int month = ExtraFuncties.stringToInteger(list.get(1));
		int day = ExtraFuncties.stringToInteger(list.get(2));
		int hour = ExtraFuncties.stringToInteger(list.get(3));
		int minute = ExtraFuncties.stringToInteger(list.get(4));
		int second = ExtraFuncties.stringToInteger(list.get(5));
		return (float)(day - startTijd.day)*1440 + (float)(hour - startTijd.hour)*60 + (float)(minute - startTijd.minute) + (float)(second - startTijd.second)/60;
	}
}
