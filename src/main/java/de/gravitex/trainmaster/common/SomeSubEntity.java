package de.gravitex.trainmaster.common;

import lombok.Data;

@Data
public class SomeSubEntity {

	private String subVal;
	
	private Integer subInt;

	public SomeSubEntity(String subVal, Integer subInt) {
		super();
		this.subVal = subVal;
		this.subInt = subInt;
	}
}