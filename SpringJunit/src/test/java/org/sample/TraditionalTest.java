package org.sample;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public final class TraditionalTest {

	private MyService service;

	@Before
	public void setUp() {
		service = new MyService("service");
	}


	@Test
	public void testClassName() {
		assertEquals("service", service.getName());
	}
}