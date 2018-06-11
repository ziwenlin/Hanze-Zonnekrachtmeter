package Main;

import java.util.ArrayList;
import java.util.List;

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
		float waarde = 0.0f, d = 1; boolean c = false;
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
		}
		return waarde;
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
}
