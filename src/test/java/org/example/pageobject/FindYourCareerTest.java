package org.example.pageobject;

import org.example.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FindYourCareerTest extends BaseTest {

    @Test
    public void findAnyJobOffersForPoland() {
        String job = "Senior Software Development Engineer in Test";
        String county = "Poland";
        String notExpectedResult ="We found 0 jobs based on your search criteria";
        setPOMObjects.careerPage.inputJobTitle(job);
        setPOMObjects.careerPage.selectCounty(county);
        setPOMObjects.careerPage.search();
        String result = setPOMObjects.careerPage.resultText();
        Assert.assertNotEquals(notExpectedResult, result);
    }


    @Test
    public void findZeroJobOffer() {
        String job = "XXX";
        String expectedResult ="We found 0 jobs based on your search criteria";
        setPOMObjects.careerPage.inputJobTitle(job);
        setPOMObjects.careerPage.search();
        String result = setPOMObjects.careerPage.resultText();
        Assert.assertEquals(expectedResult, result);
    }
}
