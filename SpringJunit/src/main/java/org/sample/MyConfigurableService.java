
package org.sample;

public class MyConfigurableService {

	private String name;

	public MyConfigurableService(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
