import org.junit.Assert;
import org.junit.Test;

public class FinalProjectTests {
    double properDouble(double value){
        double properValue = (int)(Math.round(value * 100))/100.0;
        return properValue;
    }

    @Test
    public void Case2Test() //Omer test 2
    {
        System.out.println("Test: add 2 products, remove 1 and check if cart updated");
        Case2 test = new Case2("C:\\Users\\sakid\\Desktop\\SELENIUM_VERSION\\chromedriver_win32\\chromedriver.exe");
        test.openTestSite("https://www.saucedemo.com/");
        test.UserLogin("standard_user","secret_sauce");
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String actualUrl= test.getCurrentUrl();
        Assert.assertEquals(expectedUrl,actualUrl);
        System.out.println("User login test --> Complete");
        test.addProduct(2,1);
        test.addProduct(2,2);
        test.addProduct(1,2);
        double priceBeforeRemove = properDouble(test.totalPrice());
        double ProductPriceRemove = properDouble(test.removeItemFromCart());
        double priceAfterRemove = properDouble(test.totalPrice());
        test.closeDriver();
        Assert.assertEquals((priceBeforeRemove-ProductPriceRemove),priceAfterRemove,0.0);
        System.out.println("Update cart prices test --> Complete");
    }


    @Test
    public void Case1Test() //Idan test 1
    {
        System.out.println("Test: complete purchase in site");
        Case1Idan idan = new Case1Idan("C:\\Users\\sakid\\Desktop\\SELENIUM_VERSION\\chromedriver_win32\\chromedriver.exe");
        idan.openTestSite("https://www.saucedemo.com/");
        idan.UserLogin("standard_user","secret_sauce");
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String actualUrl= idan.getCurrentUrl();
        Assert.assertEquals(expectedUrl,actualUrl);
        System.out.println("User login test --> Complete");
        idan.addTwoProducts();
        idan.cartButton();
        idan.checkoutButton();
        idan.checkoutDetails("Idan","sakat","11223344");
        idan.continueAfterCartDetailsButton();
        idan.finishCheckoutButton();
        expectedUrl = "https://www.saucedemo.com/checkout-complete.html";
        actualUrl = idan.getCurrentUrl();
        idan.closeDriver();
        Assert.assertEquals(expectedUrl,actualUrl);
        System.out.println("Full order test --> Complete");
    }

    @Test
    public void BugTestAddTwoProducts()
    {
        System.out.println("Test: add 2 products to cart");
        double ExpectedPrice = 0,resultPrice = 0;
        Case2 test = new Case2("C:\\Users\\sakid\\Desktop\\SELENIUM_VERSION\\chromedriver_win32\\chromedriver.exe");
        test.openTestSite("https://www.saucedemo.com/");
        test.UserLogin("problem_user","secret_sauce");
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String actualUrl= test.getCurrentUrl();
        Assert.assertEquals(expectedUrl,actualUrl);
        System.out.println("User login test --> Complete");
        ExpectedPrice = properDouble(test.addProduct(1,2)+test.addProduct(2,2));
        resultPrice = properDouble(test.totalPrice());
        test.closeDriver();
        Assert.assertEquals(ExpectedPrice,resultPrice,0.0);
        System.out.println("add two products test --> Complete");
    }

    @Test
    public void BugTestCheckoutLastNameBox()
    {
        System.out.println("Test: compare between last name string with last name in site");
        Case1Idan idan = new Case1Idan("C:\\Users\\sakid\\Desktop\\SELENIUM_VERSION\\chromedriver_win32\\chromedriver.exe");
        idan.openTestSite("https://www.saucedemo.com/");
        idan.UserLogin("problem_user","secret_sauce");
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String actualUrl= idan.getCurrentUrl();
        Assert.assertEquals(expectedUrl,actualUrl);
        System.out.println("User login test --> Complete");
        idan.addTwoProducts();
        idan.cartButton();
        idan.checkoutButton();
        String expectedLastName = "sakat";
        idan.checkoutDetails("Idan",expectedLastName,"11223344");
        String actualLastName = idan.getLastNameTextBox();
        idan.closeDriver();
        Assert.assertEquals(expectedLastName,actualLastName);
        System.out.println("last name textbox test --> Complete");
    }
}

