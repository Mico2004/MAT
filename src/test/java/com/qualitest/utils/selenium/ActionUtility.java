package com.qualitest.utils.selenium;

import com.qualitest.pages.OfferPage;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

public class ActionUtility {

    static Logger logger = Logger.getLogger(ActionUtility.class);



    public static void performHoverOnElement(WebDriver webDriver, WebElement element) {

        try {

            logger.info("Hove over element");

            Actions action = new Actions(webDriver);

            action.moveToElement(element).build().perform();

        }catch (Exception e){

            logger.error("Failed to hover element");

            Assert.assertTrue("Failed to hover element",false);

        }

    }



    public static void performHoverOnElement(WebDriver webDriver, By element_By) {

        try {

            logger.info("Hove over element");

            Actions action = new Actions(webDriver);

            action.moveToElement(webDriver.findElement(element_By)).build().perform();

        }catch (Exception e){

            logger.error("Failed to hover element");

            Assert.assertTrue("Failed to hover element",false);

        }

    }


    public static void  testf(){

        int i=5/0;

    }

// [M.E ] - Given a local path to local file, upload the file
    public static void uploadAfile(WebDriver driver, WebDriverWait wait, By element_By,String path){

        try{

            logger.info("Upload file: "+path);

            File file = new File(path);

            String absolutePath = file.getAbsolutePath();

            Thread.sleep(4000);

            logger.info("Absolute Upload file: "+absolutePath);

            WebElement element=driver.findElement(element_By);

            wait.until(ExpectedConditions.elementToBeClickable(element));

            element.sendKeys(absolutePath);


        }catch(WebDriverException e){

            logger.error("Upload file failed. Exception:"+e.getMessage());


            Assert.assertTrue("Upload Failed "+e.getMessage(), false);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    // [M.E ] - Click on a given element
    public static void clickOn(WebDriver driver, WebDriverWait wait, By element_by)  {

        WebElement element=null;

        try{

            logger.info("Click on element "+element_by.toString());

            wait.until(ExpectedConditions.presenceOfElementLocated(element_by));

            element=driver.findElement(element_by);

            element.click();

        }catch(WebDriverException e){

            try {

                logger.error("Clicking on element " + element_by.toString() + " using native selenium failed, trying with ACTIONS. Exception: " + e.getMessage());

                js_clickElement(driver, element_by);

                // focusAndClick(driver, wait, element_by);

            }catch (Exception e1){

                logger.error("Clicking on element "+element_by.toString()+" using native selenium failed, trying with JS. Exception: "+e.getMessage());





            }
        }catch(Exception ex){

                logger.error("Clicking on element "+element_by.toString()+" failed using native selenium and JS. "+ex.getMessage());

                Assert.assertTrue("Clicking on element "+element_by.toString()+" failed using native selenium and JS. "+ex.getMessage(), false);

            }





        }




    // [M.E] - Send Keys to non form element
    private static void focusAndClick(WebDriver webDriver, WebDriverWait wait, By elementBy) throws WebDriverException {

        logger.info("Starting focus and Click operation");

        try {

            WebElement element= webDriver.findElement(elementBy);

            Actions actions = new Actions(webDriver);

            actions.moveToElement(element).click().build().perform();

        }catch (Exception e){

            logger.error("Failed To Focus and click. Exception: "+e.getMessage());

        }



        //action.perform();

    }


    // [M.E] - Trying to click on element giving his by with native JS
    private static void  js_clickElement(WebDriver driver, By by)  // clicking element
    {


try {

    String s = "";

    int locatorStart;

    int locatorEnd;

    String locatorType;

    String locator = "";

    s = by.toString();

    locatorStart = s.indexOf(".");

    locatorEnd = s.indexOf(":");

    locatorType = s.substring(locatorStart + 1, locatorEnd);

    locator = s.substring(locatorEnd + 1, s.length());

    locator = locator.trim();

    logger.info("JS Locator:" + locator + ".");

    switch (locatorType.toLowerCase()) {
        case "id":
            ((JavascriptExecutor) driver).executeScript("document.getElementById('" + locator + "').click();");
            break;

        case "xpath":

            ((JavascriptExecutor) driver).executeScript("document.evaluate(\"" + locator + "\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click();");
            //    (JavascriptExecutor)driver).executeScript("document.evaluate("+locator+", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;");
            break;

        case "CSS":
            ((JavascriptExecutor) driver).executeScript("document.getElementsByClassName(\"" + locator + "\")[0].click();");
            // (JavascriptExecutor) driver).executeScript("document.getElementsByClassName(\"" + locator + "\").click();");
            break;
        default:
            logger.info("Failed to click the button");
            break;
    }

}catch (Exception e){


}
        }


    // [M.E] - Select  an option from a given drop down

    public static void chooseAnDropdownOption(WebDriver driver, WebDriverWait wait, Select selectList, String option){

        try{

            logger.info("Choose "+option+" from "+selectList.toString()+" drop down list");

            selectList.selectByVisibleText(option);

            Assert.assertEquals("Drop down option was selected", option, selectList.getFirstSelectedOption().getText());



        }catch (WebDriverException e){

            logger.error("Choose "+option+" from "+selectList.toString()+" drop down list failed. Exception:"+e.getMessage());

            Assert.assertTrue("Choose "+option+" from "+selectList.toString()+" drop down list failed. Exception:"+e.getMessage(),false);

        }
    }



// [M.E] - Get out of iframe

public static void getOutOfIframe(WebDriver driver) {

    try {
        logger.info("Tryin to get out of iframe");

        driver.switchTo().defaultContent();

    } catch (WebDriverException e) {

        logger.error("Failed to get out of iframe. Exception:" + e.getMessage());


    }
}






    // [M.E] - Click on a link giving it's text
    public static void clickOnAlink(WebDriver driver,WebDriverWait wait ,String text){

        try{

            logger.info("click on link with text -> "+text);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(text)));

            driver.findElement(By.linkText(text)).click();

        }catch (Exception e){

            logger.error("click on link with text -> "+text+ " failed. Exception: "+e.getMessage());

            Assert.assertTrue("click on link with text -> "+text+ " failed"+e.getMessage(),false);

        }


    }


    // [M.E] - Send keys to element given his By and text, given the element to focus and to send keys are THE SAME
   // method has rely on two fallback methods
    // Verify boolean for element that isn't containing their text
    public static void sendKeysToElement(WebDriver driver,WebDriverWait wait ,String text,  By by, boolean verifyText){


        try {

            WebDriverWait waitd=new WebDriverWait(driver,3);

            try {

                logger.info("Send Keys: " + text + " to element:" + by.toString());

                waitd.until(ExpectedConditions.presenceOfElementLocated(by));

                ActionUtility.clickOn(driver,wait,by);

                driver.findElement(by).sendKeys(text);

                if(verifyText)
                    waitd.until(ExpectedConditions.textToBe(by,text));

            } catch (Exception e) {

                try {

                    logger.warn("Send Keys failed, trying to send keys with fallback 'focus and send'");

                    focusAndSendKeys(driver, wait, by, text);

                    if(verifyText)
                        waitd.until(ExpectedConditions.textToBe(by,text));

                } catch (Exception ex) {

                    logger.warn("Send Keys failed, trying to send keys with fallback 'js inner HTML'");

                    sendKeysWithJS(driver, wait, text, by);

                    if(verifyText)
                        waitd.until(ExpectedConditions.textToBe(by,text));

                }

            }
        }catch (Exception ex1){

            logger.error("Send Keys failed fallback FAILED. Exception:"+ex1.getMessage());

            Assert.assertTrue("Send Keys failed fallback FAILED. Exception:"+ex1.getMessage(),false);

        }
    }


    // [M.E] - Send keys to element given his By and text, given the element to focus and to send keys are THE DIFFERENT (required in case the bySendKeys is not visible before focus)
    // method has rely on two fallback methods
    // Verify boolean for element that isn't containing their text
    // bySendkeys - element to send keys to
    // by - element to focus before sending the keys
    public static void sendKeysToElement(WebDriver driver,WebDriverWait wait ,String text,  By byClickOn,By bySendkeys, boolean verifyText){


        try {

            WebDriverWait waitd=new WebDriverWait(driver,3);

            try {

                logger.info("Send Keys: " + text + " to element:" + byClickOn.toString());

                waitd.until(ExpectedConditions.presenceOfElementLocated(byClickOn));

                ActionUtility.clickOn(driver,wait,byClickOn);

                driver.findElement(bySendkeys).sendKeys(text);

                if(verifyText)
                    waitd.until(ExpectedConditions.textToBe(byClickOn,text));

            } catch (Exception e) {

                try {

                    logger.warn("Send Keys failed, trying to send keys with fallback 'focus and send'");

                    focusAndSendKeys(driver, wait, bySendkeys, text);

                    if(verifyText)
                        waitd.until(ExpectedConditions.textToBe(byClickOn,text));

                } catch (Exception ex) {

                    logger.warn("Send Keys failed, trying to send keys with fallback 'js inner HTML'");

                    sendKeysWithJS(driver, wait, text, bySendkeys);

                    if(verifyText)
                        waitd.until(ExpectedConditions.textToBe(byClickOn,text));

                }

            }
        }catch (Exception ex1){

            logger.error("Send Keys failed fallback FAILED. Exception:"+ex1.getMessage());

            Assert.assertTrue("Send Keys failed fallback FAILED. Exception:"+ex1.getMessage(),false);

        }
    }


    // [M.E] - Send Keys to non form element
    private static void focusAndSendKeys(WebDriver webDriver, WebDriverWait wait, By elementBy, String text) throws WebDriverException {

            logger.info("fallback to focusAndSendKeys()  (for firefox fix)");

            try {

                WebElement element= webDriver.findElement(elementBy);

                Actions actions = new Actions(webDriver);

                actions.sendKeys(text).build().perform();

            }catch (Exception e){

                logger.error("Failed To Focus. Exception: "+e.getMessage());

            }





    }


    // [M.E] - Setting a element inner html text(usually for setting non form element text)
    private static void sendKeysWithJS(WebDriver driver, WebDriverWait wait, String text, By by){



        String s = "";

        int locatorStart;

        int locatorEnd;

        String locatorType;

        String locator = "";

        s = by.toString();

        locatorStart = s.indexOf(".");

        locatorEnd = s.indexOf(":");

        locatorType = s.substring(locatorStart + 1, locatorEnd);

        locator = s.substring(locatorEnd + 1, s.length());

        locator=locator.trim();

        logger.info("JS Locator:"+locator);

        switch (locatorType.toLowerCase()) {
            case "id":
                ((JavascriptExecutor) driver).executeScript("document.getElementById('" + locator + "').innerHTML = '"+text+"';");
                break;

            case "xpath":

                ((JavascriptExecutor) driver).executeScript( "document.evaluate(\"" + locator + "\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.innerHTML = '"+text+"';");
                //    (JavascriptExecutor)driver).executeScript("document.evaluate("+locator+", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;");
                break;

            case "CSS":
                ((JavascriptExecutor) driver).executeScript("document.querySelector(\"" + locator + "\").innerHTML = '"+text+"';");
                // (JavascriptExecutor) driver).executeScript("document.getElementsByClassName(\"" + locator + "\").click();");
                break;
            default:
                logger.info("Failed to click the button");
                break;
        }


    }

}
