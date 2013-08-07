package sri.cert;


public class RediMix extends Concrete {
	RediMix() {
		System.out.println("r ");
	}

	public static void main(String[] args) {
		new RediMix();
	}
}

class Concrete extends Sand {
	Concrete() {
		System.out.print("c ");
	}

	private Concrete(String s) {
	}
}

abstract class Sand {
	Sand() {
		System.out.print("s ");
	}
}