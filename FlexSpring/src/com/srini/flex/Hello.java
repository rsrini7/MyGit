package com.srini.flex;

public class Hello {

	public String sayHello(){
		return "Hello From Java";
	}
	
	public String exceptionTest(){
		throw new CustomException("Excep1","Custom Exception Thrown from Hello");
	}
	
}
