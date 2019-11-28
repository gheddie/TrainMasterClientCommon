package de.gravitex.trainmaster.common;

import java.util.List;

public class RailItemSequcenFormatter {

	public static String format(List<String> identifiers) {

		String result = "";
		for (String identifier : identifiers) {
			result += "["+identifier+"]";
		}
		return result;
	}
}