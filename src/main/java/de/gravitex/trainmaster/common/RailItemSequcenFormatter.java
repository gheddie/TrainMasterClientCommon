package de.gravitex.trainmaster.common;

import java.util.List;

public class RailItemSequcenFormatter {

	public static String format(String trackName, List<String> sequenceStrings) {

		String result = "["+trackName+"]::";
		if ((sequenceStrings == null) || (sequenceStrings.size() == 0)) {
			return result + "#BLANK#";	
		}
		for (String identifier : sequenceStrings) {
			result += identifier;
		}
		return result;
	}
}