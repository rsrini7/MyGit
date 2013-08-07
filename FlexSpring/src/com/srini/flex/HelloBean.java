package com.srini.flex;

public class HelloBean {

	public String sayHello(){
		return "Hello From Spring";
	}

	public String exceptionTest(){
		throw new CustomException("Excep1","Custom Exception Thrown from HelloBean");
		//throw new RuntimeException("Runtime Exception Thrown from HelloBean");
	}
	
}
