package Main;

import java.util.ArrayList;
import java.util.List;

public class ExtraFuncties {

	/**
	 * Zet een string om naar een integer
	 * @param text
	 * @return Waarde van text (int)
	 */
	public static int stringToInteger(String text) {
		int waarde = 0; // 
		for (int i = 0; i < text.length(); i++) { // Iteratie van elke karakter in de string
			char l = text.charAt(i); // Iteratie van elke karakter in de String
			if (l >= 48 && l <= 57) { // Als het getal een van deze cijfers is
				waarde = (waarde * 10) + (l - 48); // Waarde wordt met dat getal verhoogd.
			}
		}
		return waarde;
	}

	/**
	 * Zet een string om naar een float
	 * @param text
	 * @return Waarde van text (float)
	 */
	public static float stringToFloat(String text) {
		float waarde = 0.0f, d = 1; boolean c = false, n = false; // Declaratie van locale variablen
		for (int i = 0; i < text.length(); i++) { // Iteratie van de tekst
			char l = text.charAt(i); // Krijg de karakter van de tekst van positie i
			if (!c && l >= 48 && l <= 57) { // Als het getal een van deze cijfers is en de komma flag uit is
				waarde = (waarde * 10) + (l - 48); // Ga weer verder met de volgende karakter
				continue; // Ga weer verder met de volgende karakter
			}
			if (c && (l >= 48 && l <= 57) ) { // Als het getal een van deze cijfers is en de komma flag aan is
				d *= 10; // Positie achter de komma
				waarde += (l - 48) / d; // Getal toevoegen aan de waarde
				continue; // Ga weer verder met de volgende karakter
			}
			if (l == '.') c = true; // Komma notatie flag
			if (l == '-') n = true; // Negative waarde notatie
		}
		return waarde * (n ? -1 : 1); // Stuur waarde door (negatief of positief)
	}

	/**
	 * Analyseert de text en verdeeld de tekst in een lijst van strings
	 * @param text
	 * @return Lijst van strings
	 */
	public static List<String> stringCutter(String text) {
		List<String> strings = new ArrayList<String>(); // Declaratie van de String lijst
		strings.add(""); int lijstNummer = 0; // 
		for (char karakter : text.toCharArray()) {
			if (karakter == ' ' || karakter == '\t') {
				strings.add("");
				lijstNummer++;
				continue;
			}
			strings.set(lijstNummer, strings.get(lijstNummer) + karakter);
		}
		return strings;
	}
	
	/*
	 * 
	 * Niet gebruikt
	 * 
	 */
//	public static List<String> stringDateTimeCutter(String text) {
//		List<String> list = new ArrayList<>();
//		list.add(""); int i = 0;
//		for (char c : text.toCharArray()) {
//			if (c == ':' || c == '-' || c == '/') {
//				list.add("");
//				i++;
//				continue;
//			}
//			list.set(i, list.get(i) + c);
//		}
//		return list;
//	}
//	
//	public static List<String> splitDateTime(String string) {
//		List<String> a = ExtraFuncties.stringDateTimeCutter(string);
//		return a;
//	}
//	
//	public static float getMinute(DateTime startTijd, List<String> list) {		
//		int year = ExtraFuncties.stringToInteger(list.get(0));
//		int month = ExtraFuncties.stringToInteger(list.get(1));
//		int day = ExtraFuncties.stringToInteger(list.get(2));
//		int hour = ExtraFuncties.stringToInteger(list.get(3));
//		int minute = ExtraFuncties.stringToInteger(list.get(4));
//		int second = ExtraFuncties.stringToInteger(list.get(5));
//		return (float)(day - startTijd.day)*1440 + (float)(hour - startTijd.hour)*60 + (float)(minute - startTijd.minute) + (float)(second - startTijd.second)/60;
//	}
}
