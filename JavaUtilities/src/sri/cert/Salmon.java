package sri.cert;

public class Salmon extends Thread {
	public static long id;
	int i;

	public void run() {
		for (i = 0; i < 4; i++) {
			// insert code here
			new Thread(new Salmon()).start();
			throw new Error();
		}
		System.out.print(i + " ");
	}

	public static void main(String[] args) {
		Thread t1 = new Salmon();
		id = t1.getId();
		t1.start();
	}
}
