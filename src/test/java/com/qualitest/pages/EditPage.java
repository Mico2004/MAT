package com.qualitest.pages;

import com.qualitest.utils.selenium.ActionUtility;
import com.qualitest.utils.selenium.WaitDriverUtility;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EditPage extends OfferPage {

    Logger logger = Logger.getLogger(CreateNewPage.class);

    public static By offer_name_textbox_By = By.cssSelector(".offerName");
    WebElement offer_name_textbox;

    public static By save_offer_button_By = By.xpath(".//button[text()=\"Save Offer\"]");
    WebElement save_offer_button;


    public EditPage(WebDriver driver) {

        super(driver);


    }


    // [ M.E] - initilize all page elements, called on waitForPageToLoad()
    public void initializeElements() {

        try {

            logger.info("Initializing Edit Page elements");

            //       offer_name_textbox = driver.findElement(offer_name_textbox_By);

            offer_setup_grid_header = driver.findElement(offer_setup_grid_header_By);

            resource_bundle_grid_header = driver.findElement(resource_bundle_grid_header_By);

            cancel_button = driver.findElement(cancel_button_By);

            //        save_offer_button = driver.findElement(save_offer_button_By);


        } catch (WebDriverException e) {

            logger.error("Initializing Edit Page elements. Exception: " + e.getMessage());

            Assert.assertTrue("Initializing Edit Page elements. Exception: " + e.getMessage(), false);

        }
    }


    // [M.E] - Wait for login page to
    public void waitForPageToLoad() {

        try {

            logger.info("Wait for the Edit Page elements to load");

            super.waitForPageToLoad();

            //     wait.until(ExpectedConditions.visibilityOfElementLocated(offer_name_textbox_By));

            wait.until(ExpectedConditions.visibilityOfElementLocated(resource_bundle_grid_header_By));

            wait.until(ExpectedConditions.visibilityOfElementLocated(offer_setup_grid_header_By));

            wait.until(ExpectedConditions.visibilityOfElementLocated(cancel_button_By));

            // wait.until(ExpectedConditions.visibilityOfElementLocated(save_offer_button_By));

            initializeElements();


        } catch (WebDriverException e) {

            logger.error("Edit loading failed. Exception: " + e.getMessage());

            Assert.assertTrue("Edit loading failed. Exception: " + e.getMessage(), false);


        }
    }

    public void clickOnSaveOffer(SearchPendingPage searchPendingPage) throws Exception {

        try {

            logger.info("Click on save offer");

            ActionUtility.clickOn(driver, wait, save_offer_button_By);

            WaitDriverUtility.waitForElementBeDisplayed(driver, close_dialog_button_By);

            ActionUtility.clickOn(driver, wait, close_dialog_button_By);

            searchPendingPage.waitForPageToLoad();


        } catch (WebDriverException e) {

            logger.error("Click on save offer failed. Exception:" + e.getMessage());

            Assert.assertTrue("Click on save offer failed. Exception:" + e.getMessage(), false);

        }


    }

}
