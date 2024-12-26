package rahulshettyacademy.Tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageObjects.CartPage;
import rahulshettyacademy.pageObjects.ProductCatalogue;

public class ErrorValidations extends BaseTest{

	@Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void LogInErrorValidation() throws InterruptedException, IOException{
		
		String productName = "ZARA COAT 3";
		landingPage.logInApplication("samroy123@gmail.com", "India@10");	
	    Assert.assertEquals("Incorrect email password.", landingPage.getErrorMessage());
	}

	@Test
	public void ProductErrorValidation() throws InterruptedException {
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.logInApplication("samroy123@gmail.com", "India@11");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertFalse(match);
	}
}
