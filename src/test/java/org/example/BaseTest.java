package org.example;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.example.utils.SetPOMObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.qatools.properties.utils.PropertiesUtils;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    public BaseTest() {
        super();
    }

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    @NonNull
    public static WebDriver driver ;

    @NonNull
    protected static SetPOMObjects setPOMObjects;
    @NonNull
    protected static String url;


    private static void setPOMVariables(WebDriver driver) {
        setPOMObjects = new SetPOMObjects(driver);
    }

    @BeforeMethod
    public void beforeTest() {

        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");
        driver = new ChromeDriver();
        url = "https://akamaicareers.inflightcloud.com/";
        driver.get(url);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        setPOMObjects = new SetPOMObjects(driver);
    }

    @AfterMethod
    public void afterTest() {
        driver.quit();
    }
}
