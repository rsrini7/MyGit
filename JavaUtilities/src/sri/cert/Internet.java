package sri.cert;

public class Internet {
	private int y = 8;

	public static void main(String[] args) {
		new Internet().go();
	}

	void go() {
		final int x = 7;
		
		
		class TCPIP {
			void doit() {
				System.out.println(y + x);
			}
		}
		TCPIP ip = new TCPIP();
		
		ip.doit();
	}
}
