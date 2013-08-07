package org.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"applicationContext.xml"})
public final class AdvancedAutowireTest {

	@Autowired
	@Qualifier("deliveryService")
	private MyService service;

	@Test
	public void testServiceName() {
		assertEquals("delivery service", service.getName());
	}
}