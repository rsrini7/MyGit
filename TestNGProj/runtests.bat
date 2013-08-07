REM set the classpath, this tells java where to look for the library files, the project bin folder is adde as it will store the .class file after compile
set classpath=%CLASSPATH%;.\bin;.\Lib\junit-4.8.1.jar;.\Lib\jxl.jar;.\Lib\selenium-java-client-driver.jar;.\Lib\selenium-server.jar;.\Lib\testng-5.11-jdk15.jar
REM compile the dataProviderExample.java file, the -d parameter tells javac where to put the .class file that is created on compile
javac -verbose .\test\script\dataProviderExample.java -d .\bin
REM execute testng framework by giving the path of the testng.xml file as a parameter. The xml file tells testng what test to run
java org.testng.TestNG .\testng.xml
pause