package org.example.pageobject;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static java.lang.String.format;
import static java.util.concurrent.TimeUnit.SECONDS;

@Slf4j
public class BasePage {

    private static final int IMPLICITLY_WAIT_DRIVER = 4;
    private static final int WAIT_UNTIL_DISPLAY = 1;
    private static final int WAIT_TIME_OUT_IN_SECONDS = 4;
    private static final int LONG_WAIT_TIME_OUT_IN_SECONDS = 15;
    private static final int MAX_EXECUTION_TIME = 8;
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected WebDriverWait longWait;

    protected JsExecuteCommand jsExecuteCommand;
    protected SoftAssertions soft;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS);
        longWait = new WebDriverWait(driver, LONG_WAIT_TIME_OUT_IN_SECONDS);
        driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_DRIVER, SECONDS);

        jsExecuteCommand = new JsExecuteCommand(driver);
        soft = new SoftAssertions();
    }

    protected void clickElement(WebElement element) {
        log.debug("clickElement: {}", element);
        longWait.until(ExpectedConditions.visibilityOf(element));
        jsExecuteCommand.scrollIntoView(element);
        element.click();
    }

    protected void clickElementByActions(WebElement element) {
        log.debug("clickElementByActions: {}", element);
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.click();
        actions.build().perform();
    }

    protected void sendText(String text, WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            element.clear();
            element.sendKeys(text);
            log.debug("send text: {}", text);
        } catch (WebDriverException e) {
            Assert.fail(format("Action \"sendText\": %s failed", text));
        }
    }

}
