package sri.cert;

import java.util.concurrent.atomic.AtomicLong;

public class Dec26 {
	public static void main(String[] args) {
		short a1 = 6;
		AtomicLong atomicLong = new AtomicLong();
		int j = 4;
//		byte k = 10;
//		new Dec26().go(a1);
		new Dec26().go(j);
//		new Dec26().go(k);
		new Dec26().go(new Integer(7));
//		new Dec26().go(new Long(7));
		System.out.println(atomicLong.compareAndSet(0, 3));
		System.out.println(atomicLong);
	}

	void go(Short x) {
		System.out.print("S ");
	}

	void go(Long x) {
		System.out.print("L ");
	}

	void go(int x) {
		System.out.print("i ");
	}

	void go(Number n) {
		System.out.print("N ");
	}
}
