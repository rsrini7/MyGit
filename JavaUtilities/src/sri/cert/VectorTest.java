package sri.cert;

import java.util.Vector;

public class VectorTest {

	
	public static void main(String[] args) {
		Vector v = null;
		go(v);
		v.add("1");
		System.out.println(v.size());
	}
	
	private static void go(Vector v){
		v = new Vector();
		v.add("2");
	}
}
