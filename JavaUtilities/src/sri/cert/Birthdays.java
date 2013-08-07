package sri.cert;

import java.util.*;

public class Birthdays {
	public static void main(String[] args) {
		Map<Friends, String> hm = new HashMap<Friends, String>();
		hm.put(new Friends("Charis"), "Summer 2009");
		hm.put(new Friends("Draumur"), "Spring 2002");
		Friends f = new Friends("Draumur");
		System.out.println(hm.get(f));
	}
}

class Friends {
	String name;

	Friends(String n) {
		name = n;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (obj.getClass() != this.getClass()))
			return false;
		// object must be Test at this point
		Friends test = (Friends) obj;
		return name == test.name;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 31 * hash + name.hashCode();
		return hash;
	}
}