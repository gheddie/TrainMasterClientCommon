package de.gravitex.trainmaster.common;

import lombok.Data;

@Data
public class DataGridTestEntity {

	private String name;
	
	private Integer value;
	
	private SomeSubEntity someSubEntity;

	public DataGridTestEntity(String name, Integer value, SomeSubEntity someSubEntity) {
		super();
		this.name = name;
		this.value = value;
		this.someSubEntity = someSubEntity;
	}
}