package sri.cert;

import java.util.*;

public class VLA2 implements Comparator<VLA2> {
	Integer dishSize;

	public VLA2() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		VLA2[] va = { new VLA2(40), new VLA2(200), new VLA2(80) };

		Arrays.sort(va, new VLA2());
		int index = Arrays.binarySearch(va, new VLA2(40), va[0]);
		System.out.print(index + " ");
		index = Arrays.binarySearch(va, new VLA2(201), va[0]);
		System.out.print(index);

		ArrayList arrayList = new ArrayList(); 
		arrayList.add("1");    arrayList.add("4");    arrayList.add("2");    arrayList.add("5");    arrayList.add("3");
		 Collections.sort(arrayList);
		 System.out.println("Sorted ArrayList contains : "+ arrayList); 
		 int index1 = Collections.binarySearch(arrayList,"4");
		 System.out.println("Element found at : " + index1);
	}

	public int compare(VLA2 a, VLA2 b) {
		return b.dishSize.compareTo(a.dishSize);
	}

	VLA2(int d) {
		dishSize = d;
	}
}
