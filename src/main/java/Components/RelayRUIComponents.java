package Components;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import resources.base;

public class RelayRUIComponents extends base{
	public WebDriver driver;
	public static Logger log=LogManager.getLogger(base.class.getName());	
	public RelayRUIComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	base b = new base();
	
	public void SearforRelayRUI() throws IOException, InterruptedException {
		
		String searchelement=prop.getProperty("searchelement");
		
		//Enter search element in search box
		b.SetText("eltsearchtextbox", searchelement);
		//Click on search button
		b.click("btnsearch");
		
		//Click on the first result
		b.drawHighlight("lnksearchresult");
		b.click("lnksearchresult");
		
	}
		
	
	public void ClosePopup() throws IOException, InterruptedException {
		//driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		
		//Accept cookie
		b.click("btnAcceptcookie");
		//Capturing screenshot before closing popup
		//	b.getScreenshot("PopupScreenshot");
	/*		try {
				b.click("closeregionpopup");
				log.info("Closed the popup Successfully");
				System.out.println("Closed the popup Successfully");
				b.getScreenshot("afterpopupclosed");
				
			} catch (Exception e) {

				log.error("No such popup to close "+e);
				System.out.println("No such popup to close "+e);
				b.getScreenshot("Nosuchpopup");
			}*/
			Thread.sleep(3000);
	}

	//Verify header elements
	public void verifyHeaderElements() throws SQLException, IOException {
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		String[] ExpectedHeaderelemnts=null;
		String datbasename=prop.getProperty("datbasename");
		String querytoexecute=prop.getProperty("querytoexecute");
		
		ExpectedHeaderelemnts=GetdbRecords(datbasename, querytoexecute);
		
		for(int i=0;i<5;i++)
		{
		System.out.println("Expected Header element"+i+"is "+ExpectedHeaderelemnts[i]);	
		}
		
		String ActualHeaderelements[]={"abc","abc","abc","abc","abc"};
		ActualHeaderelements[0]=b.getelementtext("Headerelement1");
		ActualHeaderelements[1]=b.getelementtext("Headerelement2");
		ActualHeaderelements[2]=b.getelementtext("Headerelement3");
		ActualHeaderelements[3]=b.getelementtext("Headerelement4");
		ActualHeaderelements[4]=b.getelementtext("Headerelement5");
		
		String Highlighelement="Headerelement";
		int j=1;
		//verify if the text of each header element is displayed correctly or not
		for(int i=0;i<5;i++)
		{
			try {
				
				Assert.assertTrue(ActualHeaderelements[i].equalsIgnoreCase(ExpectedHeaderelemnts[i]));
				Highlighelement=Highlighelement+j;
				System.out.println("Element to highlight is"+Highlighelement);
				b.drawHighlight(Highlighelement);
				log.info("Text is displayed correctly for the the element "+ExpectedHeaderelemnts[i]);
				System.out.println("Text is displayed correctly for the the element "+ExpectedHeaderelemnts[i]);
				Highlighelement="Headerelement";
				j++;
			} catch (Exception e) {

				log.error("Text is displayed wrongly for the the element "+ExpectedHeaderelemnts[i]+e);
				System.out.println("Text is displayed wrongly for the the element "+ExpectedHeaderelemnts[i]);
			}

			
		}
		
		}	
	
	//Verify AboutUs-Companies Sub link
	public void VerifyCompaniesSublink() throws IOException, InterruptedException {
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		
		//Click About Us link
		b.click("lnkAboutUs");
		
		//Click Companies link
		b.drawHighlight("lnkCompanies");
		b.click("lnkCompanies");
				
		//Get expected text
		String Expectedtext=prop.getProperty("CompaniespageExpectedtext");
		System.out.println("Expected text is "+Expectedtext);
		Thread.sleep(3000);
		
		//Capture screenshot of result
		b.getScreenshot("Companieslinkpage");
		//verify if navgated to our offers page correctly or not
		String Actualtext = b.getelementtext("Companiespageresult");
		
		System.out.println("Actual text is"+Actualtext);
		
		try {
			Assert.assertTrue(Actualtext.contains(Expectedtext));
			log.info("Text is displayed correctly for Companies Sub link page. ");
			System.out.println("Text is displayed correctly for Companies Sub link page. ");
			b.drawHighlight("Companiespageresult");
		} catch (Exception e) {

			log.error("Text is displayed wrongly for Companies Sub link page "+e);
			System.out.println("Text is displayed wrongly for Companies Sub link page ");
		}	
		Thread.sleep(3000);
		
	}



}
