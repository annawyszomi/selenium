package org.example.utils;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.example.pageobject.BasePage;
import org.example.pageobject.pageactions.FindYourCareerPage;
import org.openqa.selenium.WebDriver;

@Slf4j
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class SetPOMObjects {

    public BasePage basePage;
    public FindYourCareerPage careerPage;

    public SetPOMObjects(WebDriver driver) {
        basePage = new BasePage(driver);
        careerPage = new FindYourCareerPage(driver);
    }
}
