package rahulshettyacademy.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents{
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".mb-3")
	List<WebElement> products;
	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	
	@FindBy(css = ".ng-animating")
	WebElement spiner;
	
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMeassage = By.cssSelector("#toast-container");
			
	public List<WebElement> getProductList() {
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) throws InterruptedException {
		WebElement prod = getProductByName(productName);// the scope of search is within product that's why instead using driver. we are using getProductByName
	    prod.findElement(addToCart).click();
	    waitForElementToAppear(toastMeassage);
	    waitForElementToDisappear(spiner);
	    Thread.sleep(5000);
	}
}
