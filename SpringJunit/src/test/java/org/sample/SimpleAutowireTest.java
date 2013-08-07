package org.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration 
public final class SimpleAutowireTest {

	@Autowired
	private MyService service;

	@Test
	public void testServiceName() {
		assertEquals("simple service", service.getName());
	}
}