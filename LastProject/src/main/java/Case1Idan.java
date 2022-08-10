import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Case1Idan {
    WebDriver driver;

    //constructor
    public Case1Idan(String path) {
        System.setProperty("webdriver.chrome.driver", path);
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
    }

    void openTestSite(String url) {
        this.driver.get(url);
    }

    String getCurrentUrl(){
        return this.driver.getCurrentUrl();
    }

    void UserLogin(String username, String password) {
        WebElement UsernameTextBox = this.driver.findElement(By.cssSelector("#user-name"));
        WebElement PasswordTextBox = this.driver.findElement(By.cssSelector("#password"));
        WebElement loginButton = this.driver.findElement(By.cssSelector("#login-button"));
        UsernameTextBox.sendKeys(username);
        PasswordTextBox.sendKeys(password);
        loginButton.click();
    }

    void closeDriver(){
        this.driver.quit();
    }

    void addTwoProducts(){
        WebElement addParts1 = this.driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-backpack"));
        WebElement addParts2 = this.driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-bike-light"));
        addParts1.click();
        addParts2.click();
    }

    void cartButton(){
        WebElement shopping_cart = this.driver.findElement(By.cssSelector("#shopping_cart_container > a"));
        shopping_cart.click();
    }

    void checkoutButton(){
        WebElement CheckOutButton = this.driver.findElement(By.cssSelector("#checkout"));
        CheckOutButton.click();
    }

    void checkoutDetails(String firstName,String lastName, String zipCode){
        WebElement CheckOutFirstName = driver.findElement(By.cssSelector("#first-name"));
        WebElement CheckOutLastName = driver.findElement(By.cssSelector("#last-name"));
        WebElement CheckOutZipCode = driver.findElement(By.cssSelector("#postal-code"));
        CheckOutFirstName.sendKeys(firstName);
        CheckOutLastName.sendKeys(lastName);
        CheckOutZipCode.sendKeys(zipCode);
    }

    void continueAfterCartDetailsButton(){
        WebElement ContinueButton = this.driver.findElement(By.cssSelector("#continue"));
        ContinueButton.click();
    }

    void finishCheckoutButton(){
        WebElement FinishButton = this.driver.findElement(By.cssSelector("#finish"));
        FinishButton.click();
    }

    String getLastNameTextBox(){
        WebElement lastNameTextBox = this.driver.findElement(By.cssSelector("#last-name"));
        return lastNameTextBox.getText();
    }

}

