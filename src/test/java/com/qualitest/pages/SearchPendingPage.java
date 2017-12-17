package com.qualitest.pages;

import com.qualitest.utils.selenium.ActionUtility;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPendingPage extends Page {


    Logger logger = Logger.getLogger(SearchPendingPage.class);

    public CreateNewPage createNewPage=null;

    public static By first_table_row_By = By.xpath(".//div[contains(@class, 'ui-grid-canvas')]//div[contains(@class, 'ui-grid-row')][1]");
    WebElement first_table_row;

    public static By create_new_By = By.id("create-new-tab");
    WebElement create_new;

    public static By search_by_courseware_textbox_By=By.xpath(".//input[contains(@title,\"Search by courseware ID\")]");
    WebElement search_by_courseware_textbox;

    public static By search_by_courseware_button_By=By.xpath(".//button[contains(@title,\"Search by courseware ID\")]");
    WebElement search_by_courseware_button;






    public SearchPendingPage(WebDriver driver){

        super(driver);


    }


    // [ M.E] - initilize all page elements, called on waitForPageToLoad()
    public void initializeElements() {

        try {

            logger.info("Initializing Search Page elements");

            first_table_row = driver.findElement(first_table_row_By);

            create_new = driver.findElement(create_new_By);

            search_by_courseware_textbox = driver.findElement(search_by_courseware_textbox_By);


               }catch(WebDriverException e){

            logger.error("Initializing Search Page elements. Exception: "+e.getMessage());

            Assert.assertTrue("Initializing Search Page elements. Exception: "+e.getMessage(), false);

        }
    }


    // [M.E] - Wait for login page to
    public void waitForPageToLoad() {

        try {

            logger.info("Wait for the Search Page elements to load");

            super.waitForPageToLoad();

            wait.until(ExpectedConditions.elementToBeClickable(first_table_row_By));

            wait.until(ExpectedConditions.elementToBeClickable(create_new_By));

            wait.until(ExpectedConditions.elementToBeClickable(search_by_courseware_textbox_By));

            initializeElements();


        } catch (WebDriverException e) {

            logger.error("Search page loading failed. Exception: "+e.getMessage());

            Assert.assertTrue("Search page loading failed. Exception: "+e.getMessage(), false);



        }
    }


    public void clickOnCreateNew(CreateNewPage createNewPage)  {

        try{

            logger.info("Click on create new tab");

            ActionUtility.clickOn(driver,getWait(),create_new_By);

            createNewPage.waitForPageToLoad();


        }catch (WebDriverException e){

            logger.error("Click on create new tab failed. Exception: "+e.getMessage());

            Assert.assertTrue("Click on create new tab failed. Exception: "+e.getMessage(),false);

        }

    }

    //[ M.E ] - Get the element of the Pending Search grid given a row number and the column name
    // builds the xpath locator according to the parameters
    public WebElement getGridResourceBundleElement(String row, String column) {

        try {

            logger.info("Starting to build xpath locator for Pending Search row: " + row + " and column " + column);

            String xpathXpression = "-" + row + "-uiGrid-004";

            String tempColumn = "";

            switch (column) {

                case "Offer Name":
                    tempColumn = "E";
                    break;

                case "Courseware ID":
                    tempColumn = "F";
                    break;

            }

            xpathXpression = xpathXpression + tempColumn + "-cell";

            return driver.findElement(By.xpath("//div[contains(@id,\"-0-uiGrid-004I-cell\")]"));

        } catch (WebDriverException e) {

            logger.error("finding locator for Pending Search row: " + row + " and column " + column + " failed. Exception:" + e.getMessage());

            Assert.assertTrue("finding locator for Pending Search row: " + row + " and column " + column + " failed. Exception:" + e.getMessage(), false);

            return null;

        }


    }

    public void search(String id)  {

        try{

            logger.info("Searching for - "+id);

        //  ActionUtility.sendKeysToElement(driver,getWait(),id,search_by_courseware_button_By,false);

            ActionUtility.sendKeysToElement(driver,getWait(),id,search_by_courseware_textbox_By,false);

            ActionUtility.clickOn(driver,getWait(),search_by_courseware_button_By);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(id)));


        }catch (WebDriverException e){

            logger.info("Searching for - "+id+" failed");

        }





    }

    public void clickOnanOffer(String offername, CreateNewPage createNewPage){

        try{

            logger.info("Clicking on offer name -> "+offername);

            ActionUtility.clickOnAlink(driver,getWait(),offername);

            createNewPage.waitForPageToLoad();


        }catch (WebDriverException e){

            logger.error("Clicking on offer name -> "+offername+ " failed. Exception: "+e.getMessage());

            Assert.assertTrue("Clicking on offer name -> "+offername+ " failed. Exception: "+e.getMessage(),false);

        }
    }
}
