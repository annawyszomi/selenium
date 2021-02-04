import org.junit.jupiter.api.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class RegisterTest {

    @BeforeEach
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "/home/awyszomirska/selenium/chromedriver");
    }

    @Test
    public void createAmazonAccountTest() {
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://www.amazon.com/ap/register?showRememberMe=true&openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.com%2Fref%3Dnav_ya_signin&prevRID=0A4TPFSGTTF7FQZ6C3HD&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=usflex&openid.mode=checkid_setup&prepopulatedLoginId=&failedSignInCount=0&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&pageId=usflex&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0");

        WebElement username=driver.findElement(By.id("ap_customer_name"));
        username.sendKeys("name");

        WebElement email=driver.findElement(By.id("ap_email"));
        email.sendKeys("example@gmail.com");

        WebElement password=driver.findElement(By.id("ap_password"));
        password.sendKeys("Password0987!");

        WebElement rePassword=driver.findElement(By.id("ap_password_check"));
        rePassword.sendKeys("Password0987!");

        WebElement button=driver.findElement(By.id("continue"));
        button.click();
        System.out.println("Successfully created user");
        driver.quit();
    }

    @Test
    public void findElementsOnCreateAmazonAccountPageTest() {
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://www.amazon.com/ap/register?showRememberMe=true&openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.com%2Fref%3Dnav_ya_signin&prevRID=0A4TPFSGTTF7FQZ6C3HD&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=usflex&openid.mode=checkid_setup&prepopulatedLoginId=&failedSignInCount=0&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&pageId=usflex&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0");

        WebElement username=driver.findElement(By.id("ap_customer_name"));

        WebElement email=driver.findElement(By.id("ap_email"));

        WebElement password=driver.findElement(By.id("ap_password"));

        WebElement rePassword=driver.findElement(By.id("ap_password_check"));

        WebElement button=driver.findElement(By.id("continue"));

        System.out.println("All elements found on the page");
    }

    @AfterEach
    public  void  closeBrowser(){
        ChromeDriver driver = new ChromeDriver();
        driver.quit();
    }

}
