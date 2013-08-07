package com.srini.flex;

public class HelloIntegratedBean {

	public String sayHello(){
		return "Hello From Spring+Flex Integration";
	}
	
	public String exceptionTest(){
		throw new CustomServiceException("Excep1","Custom Exception Thrown from HelloIntegratedBean");
	}
	
}
