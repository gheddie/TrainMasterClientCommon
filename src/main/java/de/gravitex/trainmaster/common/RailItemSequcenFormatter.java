package de.gravitex.trainmaster.common;

import java.util.List;

public class RailItemSequcenFormatter {

	public static String format(String trackName, List<String> identifiers) {

		String result = "["+trackName+"]::";
		if ((identifiers == null) || (identifiers.size() == 0)) {
			return result + "#BLANK#";	
		}
		for (String identifier : identifiers) {
			result += "["+identifier+"]";
		}
		return result;
	}
}