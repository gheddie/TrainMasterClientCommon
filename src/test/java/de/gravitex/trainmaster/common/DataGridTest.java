package de.gravitex.trainmaster.common;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class DataGridTest {
	
	@Test
	public void testDataGridWithoutHeaders() {
		
		List<Object> entities = new ArrayList<Object>();
		entities.add(new DataGridTestEntity("abc", 12, new SomeSubEntity("ej", 1)));
		entities.add(new DataGridTestEntity("def", 13, new SomeSubEntity("wekrjhwejr", 12)));
		entities.add(new DataGridTestEntity("ghiwekrjhwekrjhwer", 14, new SomeSubEntity("weree", 12345678)));

		List<String> conf;
		new DataGrid().print(entities);
	}

	@Test
	public void testDataGridWithDedicatedHeaders() {

		List<String> headers = new ArrayList<String>();
		headers.add("name");
		headers.add("value");
		headers.add("someSubEntity@subInt");

		List<Object> entities = new ArrayList<Object>();
		entities.add(new DataGridTestEntity("abc", 12, new SomeSubEntity("ej", 1)));
		entities.add(new DataGridTestEntity("def", 13, new SomeSubEntity("wekrjhwejr", 12)));
		entities.add(new DataGridTestEntity("ghiwekrjhwekrjhwer", 14, new SomeSubEntity("weree", 12345678)));

		List<String> conf;
		new DataGrid().withConfiguration(DataGridConfiguration.fromValues(headers)).print(entities);
	}
}