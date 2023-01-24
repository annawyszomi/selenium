package org.example.pageobject.pageactions;

import org.example.pageobject.locators.FindYourCareerSelectors;
import org.openqa.selenium.WebDriver;

public class FindYourCareerPage extends FindYourCareerSelectors {

    public void inputJobTitle(String jobTitle) {
        sendText(jobTitle, inputKeyword);
    }

    public void selectCounty(String county) {
        sendText(county, inputLocation);
        clickElementByActions(selectPoland);
    }

    public void search() {
        clickElement(selectSearchButton);
    }

    public String resultText() {
        return textZeroResults.getText();
    }


    public FindYourCareerPage(WebDriver driver) {
        super(driver);
    }
}
