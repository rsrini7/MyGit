package sri.cert;

import java.util.Date;

class Mosey implements Runnable {
	public void run() {
		for (int i = 0; i < 1000; i++) {
			System.out.print(Thread.currentThread().getId() + "-" + i + " ");
		}
	}
}

public class Stroll {
	public static void main(String[] args) throws Exception {
		//Thread t1 = new Thread(new Mosey());
//		t1.setPriority(1);
//		new Mosey().run();
//		t1.start();
//		t1.setPriority(9);
//		new Mosey().run();
//		t1.start();
//		t1.setPriority(1);
//		t1.start();
//		new Mosey().run();
		//t1.setPriority(1);
		//new Mosey().run();
		//t1.start();
		
//		boolean b = false;
//		 int i = 7;
//		 Double d = 1.23d;
//		 float f = 4.56f;
//		
//		 System.out.printf(" %b", b);
//		 //System.out.printf(" %i", i);
//		 //System.out.format(" %d", d);
//		 System.out.format(" %d", i);
//		 System.out.format(" %f", f);
		
//		 char three[] = { 't', 'h', 'r', 'e', 'e' };
//		    System.out.printf("%b %n %c %n %s %n %s %n %d %n"
//		        + "%d %n %g %n %g %n %s %n", !false, '3', new String(three), "Three",
//		        3, Long.MAX_VALUE, Math.PI, Double.MAX_VALUE, new Object());
//		  
	
//		long x = 123456789;
//		short y = 22766; // maximum value of a short is 32767
//		System.out.printf("%1$+10d %2$+1d ", x, Long.MAX_VALUE - y);
//		System.out.println();
//		System.out.println(Long.MAX_VALUE - y);
//		System.out.println(new Date());
		
	}
}