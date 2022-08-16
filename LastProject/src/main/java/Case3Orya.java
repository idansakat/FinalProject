import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Case3Orya {
    WebDriver driver;

    //constructor
    public Case3Orya(String path) {
        System.setProperty("webdriver.chrome.driver", path);
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
    }

    void openTestSite(String url) {
        this.driver.get(url);
    }

    void UserLogin(String username, String password) {
        WebElement UsernameTextBox = this.driver.findElement(By.cssSelector("#user-name"));
        WebElement PasswordTextBox = this.driver.findElement(By.cssSelector("#password"));
        WebElement loginButton = this.driver.findElement(By.cssSelector("#login-button"));
        UsernameTextBox.sendKeys(username);
        PasswordTextBox.sendKeys(password);
        loginButton.click();
    }

    String getCurrentUrl(){
        return this.driver.getCurrentUrl();
    }


    void closeDriver(){
        this.driver.quit();
    }

    String getFirstProduct(){
        WebElement firstProduct = this.driver.findElement(By.cssSelector("#item_0_title_link > div"));
        return firstProduct.getText();
    }

    void sortAtoZ(){
        WebElement sort = this.driver.findElement(By.cssSelector("#header_container > div.header_secondary_container > div.right_component > span > select > option:nth-child(1)"));
        sort.click();
    }

    void sortZtoA(){
        WebElement sort = this.driver.findElement(By.cssSelector("#header_container > div.header_secondary_container > div.right_component > span > select > option:nth-child(2)"));
        sort.click();
    }
}