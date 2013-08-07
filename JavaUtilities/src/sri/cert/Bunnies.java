package sri.cert;

 public class Bunnies {
	 private static int count = 0;
	 Bunnies() {
	 while(count < 10){
		 new Bunnies(count++);
		 System.out.println("loop"+count);
	 }
	 }
	 Bunnies(int x) { super(); }
	 public static void main(String[] args) {
	 System.out.println("1: "+count);
	 new Bunnies();
	 System.out.println("2: "+count);
	 new Bunnies(count);
	 System.out.println("3: "+count);
	 System.out.println(count++);
	}
	}
