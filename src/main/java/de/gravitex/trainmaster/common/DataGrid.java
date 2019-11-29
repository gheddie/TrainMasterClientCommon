package de.gravitex.trainmaster.common;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataGrid {

	private List<String> headers;

	private List<Object> entities;

	private HashMap<String, Integer> headersMaxLengths = new HashMap<String, Integer>();

	private int dividerLength;

	private void print() {

		for (int headerIndex = 0; headerIndex < headers.size(); headerIndex++) {
			headersMaxLengths.put(headers.get(headerIndex), getMaxIndexLength(headerIndex));
		}

		String divider = generateDivider();

		System.out.println(divider);
		for (String h : headers) {
			System.out.print("|");
			System.out.print(extend(h, headersMaxLengths.get(h)));
		}
		System.out.print("|");
		System.out.println();
		System.out.println(divider);

		for (Object e : entities) {
			System.out.print("|");
			for (String h : headers) {
				System.out.print(extend(getFieldValue(e, h), headersMaxLengths.get(h)));
				System.out.print("|");
			}
			System.out.println();
			System.out.println(divider);
		}
	}

	private String generateDivider() {
		for (String h : headers) {
			dividerLength += headersMaxLengths.get(h);
		}
		dividerLength += headers.size() + 1;
		String div = "";
		for (int i = 0; i < dividerLength; i++) {
			div += "-";
		}
		return div;
	}

	private String extend(String fieldValue, Integer extendToLength) {
		int extendBy = extendToLength - fieldValue.length();
		for (int i = 0; i < extendBy; i++) {
			fieldValue += " ";
		}
		return fieldValue;
	}

	private int getMaxIndexLength(int index) {

		int maxLength = Integer.MIN_VALUE;

		if (headers.get(index).length() > maxLength) {
			maxLength = headers.get(index).length();
		}
		String value = null;
		for (Object e : entities) {
			value = getFieldValue(e, headers.get(index));
			if (value.length() > maxLength) {
				maxLength = value.length();
			}
		}
		return maxLength;
	}

	private String getFieldValue(Object o, String header) {
		try {
			Field declaredField = null;
			if (header.contains("@")) {
				String[] spl = header.split("@");
				Field subField = o.getClass().getDeclaredField(spl[0]);
				subField.setAccessible(true);
				Object subValue = subField.get(o);
				declaredField = subValue.getClass().getDeclaredField(spl[1]);
				declaredField.setAccessible(true);
				return String.valueOf(declaredField.get(subValue));
			} else {
				declaredField = o.getClass().getDeclaredField(header);
				declaredField.setAccessible(true);
				return String.valueOf(declaredField.get(o));
			}
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}

	public DataGrid withEntities(List<Object> entities) {
		this.entities = entities;
		return this;
	}

	public DataGrid withHeaders(List<String> headers) {
		this.headers = headers;
		return this;
	}

	// ---

	public static void main(String[] args) {

		List<String> headers = new ArrayList<String>();
		headers.add("name");
		headers.add("value");
		headers.add("someSubEntity@subInt");

		List<Object> entities = new ArrayList<Object>();
		entities.add(new DataGridTestEntity("abc", 12, new SomeSubEntity("ej", 1)));
		entities.add(new DataGridTestEntity("def", 13, new SomeSubEntity("wekrjhwejr", 12)));
		entities.add(new DataGridTestEntity("ghiwekrjhwekrjhwer", 14, new SomeSubEntity("weree", 12345678)));

		new DataGrid().withHeaders(headers).withEntities(entities).print();
	}
}