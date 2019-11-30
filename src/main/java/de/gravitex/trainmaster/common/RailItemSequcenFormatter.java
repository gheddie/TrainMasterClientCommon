package de.gravitex.trainmaster.common;

import java.util.List;

public class RailItemSequcenFormatter {

	public static String format(String trackName, List<String> identifiers) {

		String result = "["+trackName+"]::";
		for (String identifier : identifiers) {
			result += "["+identifier+"]";
		}
		return result;
	}
}