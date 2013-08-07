package test.SampleTest;



import junit.framework.Assert;
import junit.framework.TestCase;

public class JunitTest extends TestCase {
	
	public void testTest(){
		int i =11;
		 Assert.assertEquals(11, i);
	}

}
