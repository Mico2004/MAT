package com.qualitest.pages;

import com.qualitest.stepDefinitions.lifecycle.BeforeFeature;
import com.qualitest.utils.selenium.ActionUtility;
import com.qualitest.utils.selenium.GetterUtility;
import com.qualitest.utils.selenium.WaitDriverUtility;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.annotation.Resource;
import java.util.UUID;

public class CreateNewPage extends OfferPage {

    Logger logger = Logger.getLogger(CreateNewPage.class);

    public static By offer_name_textbox_By = By.cssSelector(".offerInput");
    WebElement offer_name_textbox;

    public static By create_offer_button_By = By.xpath(".//button[text()=\"Create Offer\"]");
    WebElement create_offer_button;







    public CreateNewPage(WebDriver driver) {

        super(driver);


    }


    // [ M.E] - initilize all page elements, called on waitForPageToLoad()
    public void initializeElements() {

        try {

            logger.info("Initializing Create New Page elements");

     //       offer_name_textbox = driver.findElement(offer_name_textbox_By);

            offer_setup_grid_header = driver.findElement(offer_setup_grid_header_By);

            resource_bundle_grid_header = driver.findElement(resource_bundle_grid_header_By);

            cancel_button = driver.findElement(cancel_button_By);

    //        save_offer_button = driver.findElement(save_offer_button_By);


        } catch (WebDriverException e) {

            logger.error("Initializing Create New Page elements. Exception: " + e.getMessage());

            Assert.assertTrue("Initializing Create New Page elements. Exception: " + e.getMessage(), false);

        }
    }


    // [M.E] - Wait for login page to
    public void waitForPageToLoad() {

        try {

            logger.info("Wait for the Create New Page elements to load");

            super.waitForPageToLoad();

       //     wait.until(ExpectedConditions.visibilityOfElementLocated(offer_name_textbox_By));

            wait.until(ExpectedConditions.visibilityOfElementLocated(resource_bundle_grid_header_By));

            wait.until(ExpectedConditions.visibilityOfElementLocated(offer_setup_grid_header_By));

            wait.until(ExpectedConditions.visibilityOfElementLocated(cancel_button_By));

           // wait.until(ExpectedConditions.visibilityOfElementLocated(save_offer_button_By));

            initializeElements();


        } catch (WebDriverException e) {

            logger.error("Create New Page loading failed. Exception: " + e.getMessage());

            Assert.assertTrue("Create New Page loading failed. Exception: " + e.getMessage(), false);


        }
    }

    public void clickOnCreateOffer(SearchPendingPage searchPendingPage){

        try{

            logger.info("Click on create offer");

            ActionUtility.clickOn(driver,wait,create_offer_button_By);

            WaitDriverUtility.waitForElementBeDisplayed(driver,close_dialog_button_By);

            ActionUtility.clickOn(driver,wait,close_dialog_button_By);

            searchPendingPage.waitForPageToLoad();


        }catch (WebDriverException e){

            logger.error("Click on create offer failed. Exception:"+e.getMessage());

            Assert.assertTrue("Click on create offer failed. Exception:"+e.getMessage(),false);

        }




    }

    public void nameOffer(String offerName){

        try {

            logger.info("Name the offer: "+offerName);

            wait.until(ExpectedConditions.visibilityOfElementLocated(CreateNewPage.offer_name_textbox_By));

            ActionUtility.sendKeysToElement(BeforeFeature.driver, wait, offerName, CreateNewPage.offer_name_textbox_By, false);

        }catch (WebDriverException e){

            logger.error("Name the offer: "+offerName+" failed. Exception:"+e.getMessage());

            Assert.assertTrue("Name the offer: "+offerName+" failed. Exception:"+e.getMessage(),false);

        }

    }





















}
