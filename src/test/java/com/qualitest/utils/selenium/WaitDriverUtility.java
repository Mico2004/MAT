package com.qualitest.utils.selenium;


import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.*;

import java.util.List;
import java.util.NoSuchElementException;

import static java.util.concurrent.TimeUnit.SECONDS;

public class WaitDriverUtility {

    static Logger logger = Logger.getLogger(WaitDriverUtility.class);



    // [M.E] -  Wait for element's text to be as expected given specific timeout, pooling and text
    public static void waitFluentForElementTextToBe(WebDriver driver , WebElement element, int timeout, int pooling,String text){

        try{

            logger.info("Waiting for element - "+element.getText()+" text to be - "+text);

            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                    .withTimeout(timeout, SECONDS)
                    .pollingEvery(pooling, SECONDS);

            wait.until(ExpectedConditions.textToBePresentInElement(element,text));

        }catch(WebDriverException e){

            logger.error("Waiting for element - "+element.getText()+" text to be - "+text+" After "+timeout+" seconds and "+pooling+" pooling interval. Exception: "+e.getMessage());

            Assert.assertTrue("Waiting for element - "+element.getText()+" text to be - "+text+" After "+timeout+" seconds and "+pooling+" pooling interval. Exception: "+e.getMessage(), false);



        }
    }

    // [M.E] -  Wait for element to be visible to be as expected given specific timeout, pooling and text
    public static void waitFluentForElementToBeVisible(WebDriver driver , By element_By, int timeout, int pooling){

        try{

            logger.info("Waiting for element - "+element_By.toString()+" to be visible - ");

            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                    .withTimeout(timeout, SECONDS)
                    .pollingEvery(pooling, SECONDS)
                    .ignoring(NoSuchElementException.class);

            wait.until(ExpectedConditions.presenceOfElementLocated(element_By));




        }catch(WebDriverException e){

            logger.error("Waiting for element - "+element_By.toString()+" to be visible After "+timeout+" seconds and "+pooling+" pooling interval. Exception: "+e.getMessage());

            Assert.assertTrue("Waiting for element - "+element_By.toString()+" to be visible After "+timeout+" seconds and "+pooling+" pooling interval. Exception: "+e.getMessage(), false);



        }
    }


    // [M.E] - Wait for an element to be displayed given specific timeout
    public static void waitForElementBeDisplayed(WebDriver webDriver, By by, long timeoutInSeconds) {

        try {
            logger.info("Wait for an element - "+by.toString()+ " to be displayed given specific timeout - "+timeoutInSeconds);

            WebDriverWait wait = new WebDriverWait(webDriver, timeoutInSeconds);

            wait.until(ExpectedConditions.visibilityOfElementLocated(by));

        } catch(Exception e){

            logger.error("Wait for an element - "+by.toString()+ " to be displayed given specific timeout - "+timeoutInSeconds);

            Assert.assertTrue("Wait for an element - "+by.toString()+ " to be displayed given specific timeout - "+timeoutInSeconds,false);
        }

    }

    // [M.E] - Wait for an element to be displayed given specific timeout
    public static void waitForElementBeDisplayed(WebDriver webDriver, By by) {

        try {
            logger.info("Wait for an element - "+by.toString()+ " to be displayed given specific timeout - 30 seconds ");

            WebDriverWait wait = new WebDriverWait(webDriver, 30);

            wait.until(ExpectedConditions.visibilityOfElementLocated(by));

            Thread.sleep(5000);



        } catch(Exception e){

            logger.error("Wait for an element - "+by.toString()+ " to be displayed given specific timeout - 30 seconds");

            Assert.assertTrue("Wait for an element - "+by.toString()+ " to be displayed given specific timeout - 30 seconds",false);
        }

    }

    // [M.E] - Wait for an element to not be displayed given specific 30 seconds timeout
    public static void waitForElementNotToBeDisplayed(WebDriver webDriver, By by) {

        try {
            logger.info("Wait for an element - "+by.toString()+ " to be displayed given specific timeout - 30 seconds ");

            WebDriverWait wait = new WebDriverWait(webDriver, 30);

            wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(by)));

            Thread.sleep(5000);



        } catch(Exception e){

            logger.error("Wait for an element - "+by.toString()+ " to be displayed given specific timeout - 30 seconds");

            Assert.assertTrue("Wait for an element - "+by.toString()+ " to be displayed given specific timeout - 30 seconds",false);
        }

    }

    // [M.E] - Wait for an element to be displayed given specific timeout
    public static void waitForElementBeDisplayed(WebDriver webDriver, WebElement element, long timeoutInSeconds) {

        try {
            logger.info("Wait for an element - "+element.toString()+ " to be displayed given specific timeout - "+timeoutInSeconds);

            WebDriverWait wait = new WebDriverWait(webDriver, timeoutInSeconds);

            wait.until(ExpectedConditions.visibilityOf(element));

        } catch(Exception e){

            logger.error("Wait for an element - "+element.toString()+ " to be displayed given specific timeout - "+timeoutInSeconds);

            Assert.assertTrue("Wait for an element - "+element.toString()+ " to be displayed given specific timeout - "+timeoutInSeconds,false);
        }

    }

    // [M.E] - Wait for an element to be displayed given specific timeout
    public static void waitForElementBeDisplayed(WebDriver webDriver, WebElement element) {

        try {
            logger.info("Wait for an element - "+element.toString()+ " to be displayed given specific timeout - 30 seconds");

            WebDriverWait wait = new WebDriverWait(webDriver, 30);

            wait.until(ExpectedConditions.visibilityOf(element));

        } catch(Exception e){

            logger.error("Wait for an element - "+element.toString()+ " to be displayed given specific timeout - 30 seconds");

            Assert.assertTrue("Wait for an element - "+element.toString()+ " to be displayed given specific timeout - 30 seconds",false);
        }

    }







// [M.E] - Wait for document readyState to be complete
    public static void waitToPageBeLoaded(WebDriver driver) {

        WebDriverWait wait = new WebDriverWait(driver, 30);

        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver wdriver) {
                return ((JavascriptExecutor) wdriver).executeScript(
                        "return document.readyState"
                ).equals("complete");
            }
        });
    }

    public static void sleepInSeconds(int timeInSeconds) {
        try {

            logger.info("Sleep for "+timeInSeconds);

            Thread.sleep(1000 * timeInSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }






    public static void waitForChildElementBeDisplayed(WebElement parent,By by){
        int timeOut = 10;
        while (timeOut > 0) {
            List<WebElement> elements = parent.findElements(by);
            if (elements.size()>0){
                return;
            }
            sleepInSeconds(1);
            timeOut--;
        }
        throw new RuntimeException("the child element does not located !" + by);

    }




    public static void waitForWebElementsListBePopulated(WebDriver driver,By by) {
        int timeout = 20;
        List<WebElement> elements1 = driver.findElements(by);
        while ( timeout > 0) {
            if (elements1.size()>0){
                return;
            }
            timeout--;
            sleepInSeconds(1);
        }
        throw new RuntimeException("Couldn't located the list of element " + by);

    }

    // [M.E] - Wait for the frame to load and switch to it given an frame is
    public static void waitForIframeToLoadAndSwitchToIt(WebDriver driver, WebDriverWait wait, String frame){

        try {

            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));

        }catch (WebDriverException e){

            logger.error("frame "+frame+" load failed. Exception: "+e.getMessage());

            Assert.assertTrue("frame "+frame+" load failed. Exception: "+e.getMessage(),false);

        }

    }

    public static void waitForIframeToLoadAndSwitchToIt(WebDriver driver, WebDriverWait wait, By frame){

        try {

            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));

        }catch (Exception e){

            System.out.println(e.getMessage());

        }

    }

    public static void waitForTitleToBe(WebDriver driver,WebDriverWait wait, String title){

        try{

            if(!(driver instanceof InternetExplorerDriver))
                 wait.until(ExpectedConditions.titleIs(title));


        }catch (Exception e){

            logger.error("Title is not as expectd: "+title);

        }


    }
}
