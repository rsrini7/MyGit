package test.SampleTest;

import java.io.File;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.openqa.selenium.server.SeleniumServer;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.SeleneseTestCase;


public class DataProviderExample extends SeleneseTestCase{
    
    @BeforeClass
    public void setUp() throws Exception {
        SeleniumServer seleniumserver=new SeleniumServer();
        seleniumserver.boot();
        seleniumserver.start();
        setUp("http://www.imdb.com/", "*firefox E:\\Installs\\FirefoxPortable\\App\\Firefox\\firefox.exe");
        selenium.open("/");
        selenium.windowMaximize();
        selenium.windowFocus();
    }
    
    @DataProvider(name = "DP1")
    public Object[][] createData1() {
        Object[][] retObjArr=getTableArray("G:\\STSWorkSpace\\TestNGProj\\DATA.xls",
                "Sheet1", "TAG");
        return(retObjArr);
    }
    
    @Test (dataProvider = "DP1")
    public void testDataProviderExample(String movieTitle, 
            String directorName, String moviePlot, String actorName) throws Exception {    
        //enter the movie title 
        selenium.type("q", movieTitle);
        //they keep switching the go button to keep the bots away
        if (selenium.isElementPresent("nb15go_image"))
            selenium.click("nb15go_image");
        else
        selenium.click("xpath=/descendant::button[@type='submit']");
        
        selenium.waitForPageToLoad("32000");
        //click on the movie title in the search result page
        selenium.click("xpath=/descendant::a[text()='"+movieTitle+"']");
        selenium.waitForPageToLoad("32000");
        //verify director name is present in the movie details page 
        verifyTrue(selenium.isTextPresent(directorName));
        //verify movie plot is present in the movie details page
        verifyTrue(selenium.isTextPresent(moviePlot));
        //verify movie actor name is present in the movie details page
        verifyTrue(selenium.isTextPresent(actorName));
        
        //selenium.cap
    }
    
    @AfterClass
    public void tearDown(){
        selenium.close();
        selenium.stop();
    } 
    
    public String[][] getTableArray(String xlFilePath, String sheetName, String tagName){
        String[][] tabArray=null;
        try{
            Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
            Sheet sheet = workbook.getSheet(sheetName); 
            int startRow,startCol, endRow, endCol,ci,cj;
            Cell tableStart=sheet.findCell(tagName);
            startRow=tableStart.getRow();
            startCol=tableStart.getColumn();

            Cell tableEnd= sheet.findCell(tagName, startCol+1,startRow+1, 100, 64000,  false);                

            endRow=tableEnd.getRow();
            endCol=tableEnd.getColumn();
            System.out.println("startRow="+startRow+", endRow="+endRow+", " +
                    "startCol="+startCol+", endCol="+endCol);
            tabArray=new String[endRow-startRow-1][endCol-startCol-1];
            ci=0;

            for (int i=startRow+1;i<endRow;i++,ci++){
                cj=0;
                for (int j=startCol+1;j<endCol;j++,cj++){
                    tabArray[ci][cj]=sheet.getCell(j,i).getContents();
                }
            }
        }
        catch (Exception e)    {
            System.out.println("error in getTableArray()");
        }

        return(tabArray);
    }
    
    
}//end of class