package sri.cert;

public class InheritanceTest {

	public static void main(String[] args) {
		//Animal animal = new Dog();
		Animal animal1 = new Jimmy();	
	}
	 
	
}

 interface Animal{
	void bark();
}
 
 class Dog {

	
	public void bark() {
		System.out.println("Dog Bark");
		
	}
	 
 }
 
 class Jimmy extends Dog implements Animal{
	 
 }
