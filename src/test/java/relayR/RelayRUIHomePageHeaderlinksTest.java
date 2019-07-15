package relayR;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Components.RelayRUIComponents;
import resources.base;

@Test
public class RelayRUIHomePageHeaderlinksTest extends base{
	public static Logger log=LogManager.getLogger(base.class.getName());
	
	@BeforeTest()
	public void initialize() throws IOException{
		
		driver=initializeDriver();
		log.info("Driver is initialized");
		driver.get(prop.getProperty("URL"));
		log.info("Url opened is "+prop.getProperty("URL"));
		//log.error("error");
		

	}

@Test()
public void VerifyHomePageHeaderlinks() throws IOException, InterruptedException, SQLException{
	//driver.get(prop.getProperty("URL"));
	log.info("Navigated to Homepage");
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
	RelayRUIComponents RUI=new RelayRUIComponents(driver);
	
	RUI.SearforRelayRUI();
	RUI.ClosePopup();
	RUI.verifyHeaderElements();
	RUI.VerifyCompaniesSublink();
	

}


@AfterTest()
public void teardown()
{
	driver.close();
	driver=null;
}


/*
	//Below is a sample DataProvider Annotation defined to use to provide inut data 
	@DataProvider
	public Object[][] getdata(){
		//Row stands for how many different data types test should run
		//column stands for how many values per each test
		Object[][] data = new Object[2][3];
		
		//data - oth row
		data[0][0] = "nonrestricteduser@qw.com";
		data[0][1] = "123456";
		data[0][2]= "Non Restricted user";
		
/*		//data - 1st row
		data[1][0] = "restricteduser@qw.com";
		data[1][1] = "123456";
		data[1][2]= "Restricted user";
		
		return data;	
	
	}*/
}
