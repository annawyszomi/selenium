package org.example.pageobject.locators;

import org.example.pageobject.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FindYourCareerSelectors extends BasePage {

    @FindBy(css = "#keywordLocation")
    protected WebElement inputKeyword;

    @FindBy(css = "#location")
    protected WebElement inputLocation;

    @FindBy(xpath = "//li[contains(.,'Poland')]")
    protected WebElement selectPoland;

    @FindBy(css = "i[class='ifc-icon ifc-search']")
    protected WebElement selectSearchButton;

    @FindBy(css = ".text-muted > span")
    protected WebElement textZeroResults;


    public FindYourCareerSelectors(WebDriver driver) {
        super(driver);
    }
}
