import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
public class Case2 {
    WebDriver driver;
    double FirstPrice;
    public Case2(String path) {
        System.setProperty("webdriver.chrome.driver", path);
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
    }
    String[] productsArrayRemover(){
        String[] priceDivXPATHremove = new String[6];
        priceDivXPATHremove[0] = "//*[@id=\"remove-sauce-labs-backpack\"]";
        priceDivXPATHremove[1] = "//*[@id=\"remove-sauce-labs-bike-light\"]";
        priceDivXPATHremove[2] = "//*[@id=\"remove-sauce-labs-bolt-t-shirt\"]";
        priceDivXPATHremove[3] = "//*[@id=\"remove-sauce-labs-fleece-jacket\"]";
        priceDivXPATHremove[4] = "//*[@id=\"remove-sauce-labs-onesie\"]";
        priceDivXPATHremove[5] = "//*[@id=\"remove-test.allthethings()-t-shirt-(red)\"]";
        return  priceDivXPATHremove;
    }
    String[] priceDivXPATH(){
        String[] priceDivXPATH = new String[6];
        priceDivXPATH[0] = "//*[@id=\"cart_contents_container\"]/div/div[1]/div[3]/div[2]/div[2]/div";
        priceDivXPATH[1] = "//*[@id=\"cart_contents_container\"]/div/div[1]/div[4]/div[2]/div[2]/div";
        priceDivXPATH[2] = "//*[@id=\"cart_contents_container\"]/div/div[1]/div[5]/div[2]/div[2]/div";
        priceDivXPATH[3] = "//*[@id=\"cart_contents_container\"]/div/div[1]/div[6]/div[2]/div[2]/div";
        priceDivXPATH[4] = "//*[@id=\"cart_contents_container\"]/div/div[1]/div[7]/div[2]/div[2]/div";
        priceDivXPATH[5] = "//*[@id=\"cart_contents_container\"]/div/div[1]/div[8]/div[2]/div[2]/div";
        return priceDivXPATH;
    }
    String getCurrentUrl(){
        return this.driver.getCurrentUrl();
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
    double addProduct(int row, int column) {
        WebElement Product;
        int counter = 0;
        double priceProduct = 0;
        String RowCol = String.valueOf(row) + "," + String.valueOf(column);
        if((row==1||row==2||row==3)&&(column==1||column==2))
        {
            switch (RowCol) {
                case "1,1":
                    Product = this.driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-backpack"));
                    Product.click();
                    priceProduct = 29.99;
                    if(counter == 0)
                        this.FirstPrice = priceProduct;
                    break;
                case "1,2":
                    Product = this.driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-bike-light"));
                    Product.click();
                    priceProduct = 9.99;
                    if(counter == 0)
                        this.FirstPrice = priceProduct;
                    break;
                case "2,1":
                    Product = this.driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-bolt-t-shirt"));
                    Product.click();
                    priceProduct = 15.99;
                    if(counter == 0)
                        this.FirstPrice = priceProduct;
                    break;
                case "2,2":
                    Product = this.driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-fleece-jacket"));
                    Product.click();
                    priceProduct = 49.99;
                    if(counter == 0)
                        this.FirstPrice = priceProduct;
                    break;
                case "3,1":
                    Product = this.driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-onesie"));
                    Product.click();
                    priceProduct = 7.99;
                    if(counter == 0)
                        this.FirstPrice = priceProduct;
                    break;
                case "3,2":
                    Product = this.driver.findElement(By.cssSelector("#add-to-cart-test\\.allthethings\\(\\)-t-shirt-\\(red\\)"));
                    Product.click();
                    priceProduct = 15.99;
                    if(counter == 0)
                        this.FirstPrice = priceProduct;
                    break;
            }
        }
        else
            System.out.println("out of range, try row:(1-3) and column:(1-2)");
        RowCol = null;
        return priceProduct;
    }
    void shoppingCart() {
        WebElement ShoppingCartButton = driver.findElement(By.cssSelector("#shopping_cart_container > a"));
        ShoppingCartButton.click();
    } //enter shopping cart
    double totalPrice() {
        if(!this.driver.getCurrentUrl().equals("https://www.saucedemo.com/cart.html"))
            shoppingCart();
        double totalPrice = 0;
        String[] priceDivXPATH = priceDivXPATH();

        for (int i = 0; i < 6; i++) {
            try {
                WebElement Price = driver.findElement(By.xpath(priceDivXPATH[i]));
                double priceDouble = Double.parseDouble(Price.getText().substring(1, Price.getText().length()));
                totalPrice += priceDouble;
            } catch (Exception e) {
            }
        }
        return totalPrice;
    } //total price of shopping cart
    void closeDriver(){
        this.driver.quit();
    }
    double removeItemFromCart() {
        WebElement ProductRemoveButton,Price;
        String[] priceDivXPATHremove = productsArrayRemover();
        String[] priceDivXPATH = priceDivXPATH();


        boolean flag = false;
        int i = 0;

        while (!flag) {
            try {
                ProductRemoveButton = driver.findElement(By.xpath(priceDivXPATHremove[i]));
                ProductRemoveButton.click();
                flag = true;
            } catch (Exception e) {
                i++;
            }
            if (i == 6) {
                System.out.println("cart is empty");
                flag = true;
            }
        }
        return  this.FirstPrice;
    } //Always remove the first Item
}

