package org.example.pageobject;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.StopWatch;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

@Slf4j
public class JsExecuteCommand {
    protected WebDriver driver;
    private static JavascriptExecutor jsExec;
    private static WebDriverWait jsWait;

    public JsExecuteCommand(WebDriver driver) {
        this.driver = driver;
        jsExec = (JavascriptExecutor) driver;
        jsWait = new WebDriverWait(driver, 10);
    }

    public void scrollIntoView(WebElement element) {
        log.debug("JsExecuteCommand - scrollIntoView: {}", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void clickByJS(WebElement element) {
        log.debug("JsExecuteCommand - clickByJS");
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public String getValueByJS(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        String text = (String) executor.executeScript("return arguments[0].value;", element);
        log.debug("JsExecuteCommand - getValueByJS: {}", text);
        return text;
    }

    public String sendText(String text, WebElement element) {
        log.debug("JsExecuteCommand - sendText: {}", text);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        return (String) executor.executeScript("arguments[0].value='" + text + "';", element);
    }

    public void waitUntilAngularReady() {
        log.debug("JsExecuteCommand - waitUntilAngularReady");
        StopWatch watch = new StopWatch();
        watch.start();
        try {
            Boolean angularUnDefined =
                    (Boolean) jsExec.executeScript("return window.angular === undefined");
            if (!angularUnDefined) {
                Boolean angularInjectorUnDefined =
                        (Boolean)
                                jsExec.executeScript("return angular.element(document).injector() === undefined");
                if (!angularInjectorUnDefined) {
                    poll(20);
                    waitForAngularLoad();
                    poll(20);
                }
            }
        } catch (WebDriverException ignored) {
            log.warn("JsExecuteCommand - waitUntilAngularReady - ignore");
        } finally {
            watch.stop();
            log.debug(
                    "StopWatch - JsExecuteCommand - waitUntilAngularReady: {} s", watch.getTime() / 1000.0);
        }
    }

    public void waitUntilAngular5Ready() {
        log.debug("JsExecuteCommand - waitUntilAngular5Ready");
        StopWatch watch = new StopWatch();
        watch.start();
        try {
            Object angular5Check =
                    jsExec.executeScript("return getAllAngularRootElements()[0].attributes['ng-version']");
            if (angular5Check != null) {
                Boolean angularPageLoaded =
                        (Boolean)
                                jsExec.executeScript(
                                        "return window.getAngularTestability(window.getAllAngularRootElements()[0])._ngZone.isStable === true;");
                if (!angularPageLoaded) {
                    poll(20);
                    waitForAngular5Load();
                    poll(20);
                }
            }
        } catch (WebDriverException ignored) {
            log.warn("JsExecuteCommand - waitUntilAngular5Ready - ignore");
        } finally {
            watch.stop();
            log.debug(
                    "StopWatch - JsExecuteCommand - waitUntilAngular5Ready: {} s", watch.getTime() / 1000.0);
        }
    }

    private void angularLoads(String angularReadyScript) {
        log.debug("JsExecuteCommand - angularLoads");
        try {
            ExpectedCondition<Boolean> angularLoad =
                    driver ->
                            Boolean.valueOf(
                                    ((JavascriptExecutor) driver).executeScript(angularReadyScript).toString());

            boolean angularReady = Boolean.valueOf(jsExec.executeScript(angularReadyScript).toString());

            if (!angularReady) {
                jsWait.until(angularLoad);
            }
        } catch (WebDriverException ignored) {
            log.warn("JsExecuteCommand - angularLoads - ignore");
        }
    }

    private void waitForAngularLoad() {
        log.debug("JsExecuteCommand - waitForAngularLoad");
        String angularReadyScript =
                "return angular.element(document).injector().get('$http').pendingRequests.length === 0";
        angularLoads(angularReadyScript);
    }

    private void waitForAngular5Load() {
        log.debug("JsExecuteCommand - waitForAngular5Load");
        String angularReadyScript =
                "return window.getAngularTestability(window.getAllAngularRootElements()[0])._ngZone.isStable === true;";
        angularLoads(angularReadyScript);
    }

    private void poll(long milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            log.warn("poll - ignore");
        }
    }
}
