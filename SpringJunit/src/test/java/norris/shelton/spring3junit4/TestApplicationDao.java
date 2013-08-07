package norris.shelton.spring3junit4;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;


/**
 * This class uses the default for the context configuration and the transaction manager.  It is set-up to work as a
 * unit test instead of an integration test.  It only has what it needs.  However, this would probably lead to some
 * duplication if common parts are not imported by the context file or specified in the context configuration.
 * @author Norris Shelton
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({TransactionalTestExecutionListener.class,
                         DependencyInjectionTestExecutionListener.class})
@ContextConfiguration
@Transactional
public class TestApplicationDao {

    @Autowired
    private ApplicationDao applicationDao;

    @Test
    public void testGet() {
        Application application = applicationDao.get(1);
        commonAssertions(application);
    }

    @Test
    public void testGetAll() {
        List<Application> applications = applicationDao.getAll();
        assertNotNull(applications);
        for (Application application : applications) {
            commonAssertions(application);
        }
    }

    @Test
    public void testGetActive() {
        List<Application> applications = applicationDao.getActive();
        for (Application application : applications) {
            commonAssertions(application);
            assertTrue(application.isStatus());
        }
    }

    @Test
    public void testGetDisplayable() {
        List<Application> applications = applicationDao.getDisplayable();
        for (Application application : applications) {
            commonAssertions(application);
            assertTrue(application.isDisplay());
        }
    }

    @Test
    public void testUpdate() {
        Application application = applicationDao.get(1);
        assertNotNull("Unable to get the initial record to update");
        assertFalse("found that the aka1 value was still set from the last test run", "unused".equals(application.getAka1()));

        application.setAka1("unused");
        int numUpdated = applicationDao.update(application);
        assertEquals(1, numUpdated);
    }

    @Test
    public void testInsert() {
        Application application = new Application();
        application.setName("Test Name");

        int id = applicationDao.insert(application);
        assertTrue(id > 0);
    }


    private void commonAssertions(Application application) {
        assertNotNull(application);
        assertTrue(application.getId() > 0);
        assertNotNull(application.getName());
        //note name (application) is the only required property
        //assertNotNull(application.getDescription());
        //assertNotNull(application.getEntityIds());
        //assertNotNull(application.getAcronym());
        //assertNotNull(application.getAka1());
        //assertNotNull(application.getAka2());
        //assertNotNull(application.getAka3());
        //assertNotNull(application.isStatus());
        //assertNotNull(application.getHelpGroup());
        //assertNotNull(application.getVersion());
        //assertNotNull(application.getDateUpdated());
        //assertNotNull(application.getVendor());
    }
}
