import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class FinalProjectTests {

    String path()
    {
        return "C:\\Users\\sakid\\Desktop\\SELENIUM_VERSION\\chromedriver_win32\\chromedriver.exe";
    }


    @Test
    public void Case2Test() //Omer test 2
    {
        System.out.println("Test: add 2 products, remove 1 and check if cart updated");
        Case2 test = new Case2(path());
        test.openTestSite("https://www.saucedemo.com/");
        test.UserLogin("standard_user","secret_sauce");
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String actualUrl= test.getCurrentUrl();
        Assert.assertEquals(expectedUrl,actualUrl);
        System.out.println("User login test --> Complete");
        test.addProduct(2,1);
        test.addProduct(2,2);
        test.addProduct(1,2);
        double priceBeforeRemove = test.properDouble(test.totalPrice());
        double ProductPriceRemove = test.properDouble(test.removeItemFromCart());
        double priceAfterRemove = test.properDouble(test.totalPrice());
        test.closeDriver();
        Assert.assertEquals((priceBeforeRemove-ProductPriceRemove),priceAfterRemove,0.0);
        System.out.println("Update cart prices test --> Complete");
    }

    @Test
    public void Case3Test(){/////orya
        System.out.println("Test: Login with locked out user (N)");
        Case3Orya orya = new Case3Orya(path());
        orya.openTestSite("https://www.saucedemo.com/");
        orya.UserLogin("locked_out_user","secret_sauce");
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String actualUrl= orya.getCurrentUrl();
        orya.closeDriver();
        Assert.assertNotEquals(expectedUrl,actualUrl);
        System.out.println("locked_out user login test --> Complete (Failed)");
    }

    @Test
    public void Case1Test() //Idan test 1
    {
        System.out.println("Test: complete purchase in site");
        Case1Idan idan = new Case1Idan(path());
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
        double ExpectedPrice,resultPrice;
        Case2 test = new Case2(path());
        test.openTestSite("https://www.saucedemo.com/");
        test.UserLogin("problem_user","secret_sauce");
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String actualUrl= test.getCurrentUrl();
        Assert.assertEquals(expectedUrl,actualUrl);
        System.out.println("User login test --> Complete");
        ExpectedPrice = test.properDouble(test.addProduct(1,1)+test.addProduct(2,2));
        resultPrice = test.properDouble(test.totalPrice());
        test.closeDriver();
        Assert.assertEquals(ExpectedPrice,resultPrice,0.0);
        System.out.println("add two products test --> Complete");
    }

    @Test
    public void BugTestCheckoutLastNameBox()
    {
        System.out.println("Test: compare between last name string with last name in site");
        Case1Idan idan = new Case1Idan(path());
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
        System.out.println("last name textBox test --> Complete");
    }

    @Test
    public void BugTestSorting()
    {
        System.out.println("Test: Sorting A-Z and Z-A and compare first product name (N)");
        Case3Orya orya = new Case3Orya(path());
        orya.openTestSite("https://www.saucedemo.com/");
        orya.UserLogin("problem_user","secret_sauce");
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String actualUrl= orya.getCurrentUrl();
        Assert.assertEquals(expectedUrl,actualUrl);
        System.out.println("User login test --> Complete");
        orya.sortAtoZ();
        String expectedFirstProduct = orya.getFirstProduct();
        orya.sortZtoA();
        String actualFirstProduct = orya.getFirstProduct();
        orya.closeDriver();
        Assert.assertNotEquals(expectedFirstProduct,actualFirstProduct);
        System.out.println("Sorting items (N) --> Complete");
    }
}

