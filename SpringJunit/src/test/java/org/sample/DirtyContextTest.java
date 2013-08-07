package org.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"applicationContext.xml"})
public final class DirtyContextTest {

	@Autowired
	private MyConfigurableService service;

	@Test
	@DirtiesContext
	public void testServiceName1() {
		assertEquals("configurable service", service.getName());
		service.setName("Updating Name");
	}

	@Test
	@DirtiesContext
	public void testServiceName2() {
		assertEquals("configurable service", service.getName());
		service.setName("Updating Name");
	}
}