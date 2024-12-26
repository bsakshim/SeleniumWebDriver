package rahulshettyacademy.Tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageObjects.CartPage;
import rahulshettyacademy.pageObjects.CheckOutPage;
import rahulshettyacademy.pageObjects.ConfirmationPage;
import rahulshettyacademy.pageObjects.LandingPage;
import rahulshettyacademy.pageObjects.OrderPage;
import rahulshettyacademy.pageObjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest{
    
	String productName = "ZARA COAT 3";
	@Test(dataProvider = "getData", groups = {"purchase"})
	public void submitOrder(HashMap<String,  String> input) throws InterruptedException, IOException{
		// TODO Auto-generated method stub
		//loginpage
		
		ProductCatalogue productCatalogue = landingPage.logInApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		
	
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();
		
		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.goToCheckOut();
		checkOutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkOutPage.submitOrder();
		String confirmMeassage = confirmationPage.getConfirmationMessage();
		AssertJUnit.assertTrue(confirmMeassage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	
	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTest(){
		ProductCatalogue productCatalogue = landingPage.logInApplication("samroy123@gmail.com", "India@11");
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
		
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException{
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
		
	}
	
//	@DataProvider
//	public Object[][] getData(){
//		return new Object[][] {{"anshika@gmail.com", "India@11", "ZARA COAT 3"}, {"samroy123@gmail.com", "India@11", "ADIDAS ORIGINAL"}};
//	}
	
//	HashMap<String, String> map = new HashMap<String, String>();
//	map.put("email", "shetty@gmail.com");
//	map.put("password", "Iamking@000");
//	map.put("product", "ZARA COAT 3");
//	
//	HashMap<String, String> map1 = new HashMap<String, String>();
//	map1.put("email", "samroy123@gmail.com");
//	map1.put("password", "India@11");
//	map1.put("product", "IPHONE 13 PRO");
//	return new Object[][]  {{map}, {map1}};
}
