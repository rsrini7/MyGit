package com.ecomputercoach.file;

import org.springframework.batch.core.launch.support.CommandLineJobRunner;

public class DriverClass {


	public static void main(String[] args) {

		try {
			CommandLineJobRunner.main(new String[] { "FileToFile.xml",
					"careerProcessorJob" });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
