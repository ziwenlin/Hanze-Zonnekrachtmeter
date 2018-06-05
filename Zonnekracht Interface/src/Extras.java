
public class Extras {

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

}
