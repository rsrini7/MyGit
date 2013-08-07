package sri.cert;

class A {
}

class B extends A {
}

class C extends B {
}

public class Carpet<V extends B> {
	public <X extends V> Carpet<? extends V> method(Carpet<? super X> e) {
		Carpet c = null;
		c = new Carpet<X>();
		// c = new Carpet<V>();
		// c = new Carpet<A>(); // Bound mismatch: The type A is not a valid
		// substitute for the bounded parameter <V extends B> of the type
		// Carpet<V>
		// c= new Carpet<B>();
		// c= new Carpet<C>();
		return c;
	}
}
