package com.qualitest.pages;

import com.qualitest.stepDefinitions.Shell;
import com.qualitest.utils.selenium.ActionUtility;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Map;

public abstract class OfferPage extends Page {

    public static By resource_bundle_grid_header_By = By.cssSelector("h2.gridHeader:nth-child(4)");
    WebElement resource_bundle_grid_header;

    public static By offer_setup_grid_header_By = By.cssSelector("h2.gridHeader:nth-child(7)");
    WebElement offer_setup_grid_header;

    public static By cancel_button_By = By.xpath(".//button[text()=\"Cancel\"]");
    WebElement cancel_button;

    public static By add_offer_By = By.xpath(".//button[text()=\"Add Offer\"]");
    WebElement add_offer;

    public static By close_dialog_button_By = By.xpath(".//button[text()=\"Close\"]");
    WebElement close_dialog;

    public static By add_offer_instructor_offer_dropdown_By=By.xpath(".//li//span[text()=\"Instructor\"]");
    WebElement add_offer_instructor_offer_dropdown;

    public static By add_offer_trial_offer_dropdown_By=By.xpath(".//li//span[text()=\"Trial\"]");
    WebElement add_offer_trial_offer_dropdown;

    public static By add_offer_claim_offer_dropdown_By=By.xpath(".//li//span[text()=\"Claim\"]");
    WebElement add_offer_claim_offer_dropdown;

    public static By add_offer_purchase_offer_dropdown_By=By.xpath(".//li//span[text()=\"Purchase\"]");
    WebElement add_offer_purchase_offer_dropdown;

    public static By add_resource_By = By.xpath(".//button[text()=\"Add Resource\"]");
    WebElement add_resource;

    // Genric locator for the two grids in the create and edit pages, the <x> placeholder should be populated according to the given context
    public static String gridElementLocatorTemplate_clickOn ="//div[contains(@ui-grid,'<gridTypePlaceholder>')]//div[contains(@role,'presentation')]/div[contains(@role,'rowgroup')]/div[contains(@class,'ui-grid-canvas')]/div[<gridRowPlaceholder>]/div/div[<gridcellPlaceholder>]/div";

    public static String gridElementLocatorTemplate_sendKeysTo ="//div[contains(@ui-grid,'<gridTypePlaceholder>')]//div[contains(@role,'presentation')]/div[contains(@role,'rowgroup')]/div[contains(@class,'ui-grid-canvas')]/div[<gridRowPlaceholder>]/div/div[<gridcellPlaceholder>]/div[2]";


    // Genric locator for the two grids in the create and edit pages, the <x> placeholder should be populated according to the given context
    public static String gridRowsLocatorTemplate ="//div[contains(@ui-grid,\"<gridTypePlaceholder>\")]//div[contains(@role,\"presentation\")]/div[contains(@role,\"rowgroup\")]/div[contains(@class,\"ui-grid-canvas\")]/div";

    // bundle value for the <gridTypePlaceholder> in gridElementLocatorTemplate_clickOn
    public static String bundle_gridTypePlaceholder="grdBundleRes";

    // offer value for the <gridTypePlaceholder> in gridElementLocatorTemplate_clickOn
    public static String offer_gridTypePlaceholder="grdOfferSetup";

    public static int resourceCount=0;

    public static int offerCount=0;





    public OfferPage(WebDriver driver) {
        super(driver);
    }




    public void addAresourceBundle(String title, String id ,String ResourceType)  {

        try{

            logger.info("Adding a resource with title:"+title+ ", id: "+id+" ResourceType: "+ ResourceType);

            wait.until(ExpectedConditions.elementToBeClickable(add_resource_By));

            String currentRowCount = Integer.toString((getGridRowCount(bundle_gridTypePlaceholder) + 1));

            ActionUtility.clickOn(driver,getWait(),add_resource_By);

            wait.until(ExpectedConditions.presenceOfElementLocated(getGridResourceBundleElementBy(gridElementLocatorTemplate_clickOn,currentRowCount ,"ResourceID")));

            if( title != "default" )
                ActionUtility.sendKeysToElement(driver,wait,title,getGridResourceBundleElementBy(gridElementLocatorTemplate_clickOn, currentRowCount,"title"),getGridResourceBundleElementBy(gridElementLocatorTemplate_sendKeysTo, currentRowCount,"title"),true);

            if( id != "default" )
                ActionUtility.sendKeysToElement(driver,wait,id,getGridResourceBundleElementBy(gridElementLocatorTemplate_clickOn, currentRowCount,"ResourceID"),getGridResourceBundleElementBy(gridElementLocatorTemplate_sendKeysTo, currentRowCount,"ResourceID"),true);

            if(ResourceType != null){
                // DO Something
            }

            resourceCount++;


        }catch (WebDriverException e){

            logger.info("Adding a resource with title:"+title+ ", id: "+id+" ResourceType: "+ ResourceType+" failed. Exception: "+e.getMessage());

            Assert.assertTrue("Adding a resource with title:"+title+ ", id: "+id+" ResourceType: "+ ResourceType+" failed. Exception: "+e.getMessage(),false);

        }





    }



    public void addSetupOffer(String offerType, String status, String individual, String buisness, String duration, String isbn) throws Exception {

        try{

            logger.info("Adding a setup offer of type "+offerType);

            String currentRowCount=Integer.toString((getGridRowCount(offer_gridTypePlaceholder)+1));

            ActionUtility.clickOn(driver,getWait(),add_offer_By);

            if(offerType.toLowerCase().equals("instructor"))
                ActionUtility.clickOn(driver,getWait(),add_offer_instructor_offer_dropdown_By);

            else if(offerType.toLowerCase().equals("trial"))
                ActionUtility.clickOn(driver,getWait(),add_offer_trial_offer_dropdown_By);

            else if(offerType.toLowerCase().equals("claim"))
                ActionUtility.clickOn(driver,getWait(),add_offer_claim_offer_dropdown_By);

            else if(offerType.toLowerCase().equals("purchase"))
                ActionUtility.clickOn(driver,getWait(),add_offer_purchase_offer_dropdown_By);

            wait.until(ExpectedConditions.presenceOfElementLocated(getGridOfferSetupElementBy(gridElementLocatorTemplate_clickOn,currentRowCount,"offertype")));

            wait.until(ExpectedConditions.textToBePresentInElementLocated(getGridOfferSetupElementBy(gridElementLocatorTemplate_clickOn,currentRowCount,"offertype"),offerType.toLowerCase()));

            if(!status.toLowerCase().equals("off")){

                logger.info("Click on status element to be: "+status);

                ActionUtility.clickOn(driver,wait, getGridOfferSetupElementBy(gridElementLocatorTemplate_clickOn,currentRowCount,"status"));

            }

            if(!individual.toLowerCase().equals("instructor")){

                logger.info("Selecting individual option: "+individual);

                //...

            }

            if(!buisness.toLowerCase().equals("he")){

                logger.info("Selecting bui option: "+buisness);

                //...

            }

            if(!duration.toLowerCase().equals("default")){

                logger.info("Typing duration: "+duration);

                //...


            }

            if(!isbn.toLowerCase().equals("default")){

                logger.info("Typing isbn: "+isbn);

                //...

            }

            offerCount++;

        }catch (WebDriverException e){

            logger.error(("Adding a setup offer of type "+offerType+" failed. Exception:"+e.getMessage()));

            Assert.assertTrue("Adding a setup offer of type "+offerType+" failed. Exception:"+e.getMessage(),false);


        }




    }

    //[ M.E ] - Get the element's By object of the offer Bundle grid given a row number and the column name
    // builds the xpath locator according to the parameters
    // row,column - possible values 1 - n (NOT 0!)
    // locatorTemplate - in case the input and actual elements are different like in the grids
    public By getGridResourceBundleElementBy(String locatorTemplate, String row, String column) {

        try {

            logger.info("Starting to build xpath locator for Offer Setup row: " + row + " and column " + column);

            String tempColumn = "Bad Parameter";

            switch (column.toLowerCase()) {

                case "title":
                    tempColumn = "1";
                    break;

                case "resourceid":
                    tempColumn = "2";
                    break;

                case "resourcetype":
                    tempColumn = "3";
                    break;

                case "action":
                    tempColumn = "4";
                    break;

            }

            String tempLocator="";

            tempLocator = locatorTemplate.replace("<gridTypePlaceholder>",bundle_gridTypePlaceholder);

            tempLocator = tempLocator.replace("<gridRowPlaceholder>",row);

            tempLocator = tempLocator.replace("<gridcellPlaceholder>",tempColumn);

            By by= By.xpath(tempLocator);

          /*  String resource_bundle_box_By=" div[ui-grid=grdBundleRes] .ui-grid-row:nth-child("+row+") > div > div:nth-child("+tempColumn+")> div";

            By by= By.cssSelector(resource_bundle_box_By);*/ // CSS Selector for the grid

            return by;

        } catch (WebDriverException e) {

            logger.error("finding locator for Offer Setup row: " + row + " and column " + column + " failed. Exception:" + e.getMessage());

            Assert.assertTrue("finding locator for Offer Setup row: " + row + " and column " + column + " failed. Exception:" + e.getMessage(), false);

            return null;

        }


    }

    //[ M.E ] - Get the element's By object of the offer Bundle grid given a row number and the column name
    // builds the xpath locator according to the parameters
    // row,column - possible values 1 - n (NOT 0!)
    public By getGridOfferSetupElementBy(String locatorTemplate,String row, String column) {

        try {

            logger.info("Starting to build xpath locator for Offer Setup row: " + row + " and column " + column);

            String tempColumn = "Bad Parameter";

            switch (column.toLowerCase()) {

                case "status":
                    tempColumn = "1";
                    break;

                case "offertype":
                    tempColumn = "2";
                    break;

                case "individual":
                    tempColumn = "3";
                    break;

                case "buisness":
                    tempColumn = "4";
                    break;

                case "duration":
                    tempColumn = "5";
                    break;

                case "isbn":
                    tempColumn = "6";
                    break;

                case "restriction":
                    tempColumn = "7";
                    break;

            }

            String tempLocator="";

            tempLocator = locatorTemplate.replace("<gridTypePlaceholder>",offer_gridTypePlaceholder);

            tempLocator = tempLocator.replace("<gridRowPlaceholder>",row);

            tempLocator = tempLocator.replace("<gridcellPlaceholder>",tempColumn);

            By by= By.xpath(tempLocator);

            return by;

        } catch (WebDriverException e) {

            logger.error("finding locator for Offer Setup row: " + row + " and column " + column + " failed. Exception:" + e.getMessage());

            Assert.assertTrue("finding locator for Offer Setup row: " + row + " and column " + column + " failed. Exception:" + e.getMessage(), false);

            return null;

        }


    }

    // counts the current rows of the bundle\Offer grid and returns the number
    // gridType - grdOfferSetup\grdBundleRes
    public int getGridRowCount(String gridType){

        try{

            logger.info("get grid: "+gridType+" count");

            if(gridType.toLowerCase().equals(bundle_gridTypePlaceholder))
                gridType=bundle_gridTypePlaceholder;

            else
                gridType=offer_gridTypePlaceholder;

            gridRowsLocatorTemplate=gridRowsLocatorTemplate.replace("<gridTypePlaceholder>",gridType);

            By by=By.xpath(gridRowsLocatorTemplate);

            List<WebElement> rows=driver.findElements(by);

            if(rows==null)
                return 0;

            else
                return rows.size();


        }catch (WebDriverException e){

            logger.info("getting grid: "+gridType+" count failed: Exception:"+e.getMessage());

            return 0;

        }


    }


    public void verifyRowCount(String resourcesOrOffers){

        try{

            logger.info("Checking if '"+resourcesOrOffers+"' row count is: "+ OfferPage.resourceCount);

            if( resourcesOrOffers.toLowerCase().equals("resources") ) {
                if( Shell.createNewPage.getGridRowCount("grdBundleRes")!=OfferPage.resourceCount )
                    Assert.assertFalse("Checking if '"+resourcesOrOffers+"' row count is: "+ OfferPage.resourceCount+" failed",false);

            }else{
                if( Shell.createNewPage.getGridRowCount("grdOfferSetup")!=OfferPage.offerCount )
                    Assert.assertFalse("Checking if '"+resourcesOrOffers+"' row count is: "+ OfferPage.offerCount+" failed",false);
            }

        }catch (WebDriverException e){

            Assert.assertFalse("Checking if '"+resourcesOrOffers+"' row count is: "+ OfferPage.offerCount+" failed. Exception:"+e.getMessage(),false);

        }
    }

    public void verifyBundleCellContent(List <Map> list){

        try{

            logger.info("Verfying bundle cell content");

            for(int i=0; i<list.size(); i++){
                int z=i+1;

                try {

                    String title = list.get(i).get("title").equals("default") ? "[Title]" : list.get(i).get("title").toString();

                    if (!title.equals(driver.findElement(getGridResourceBundleElementBy(gridElementLocatorTemplate_clickOn, Integer.toString(z), "title")).getText()))
                        Assert.assertTrue("Expected Title: " + list.get(i).get("title") + " ,Actual: " + driver.findElement(getGridResourceBundleElementBy(gridElementLocatorTemplate_clickOn, Integer.toString(z), "title")).getText(), false);
                }catch (WebDriverException e){

                    Assert.assertTrue("Expected Title: " + list.get(i).get("title") + " ,Actual: " + driver.findElement(getGridResourceBundleElementBy(gridElementLocatorTemplate_clickOn, Integer.toString(z), "title")).getText(), false);

                }

                try {
                    String id = list.get(i).get("id").equals("default") ? "[Resource ID]" : list.get(i).get("id").toString();

                    if (!id.equals(driver.findElement(getGridResourceBundleElementBy(gridElementLocatorTemplate_clickOn, Integer.toString(z), "resourceid")).getText()))
                        Assert.assertTrue("Expected id: " + list.get(i).get("id") + " ,Actual: " + driver.findElement(getGridResourceBundleElementBy(gridElementLocatorTemplate_clickOn, Integer.toString(z), "id")).getText(), false);
                }catch (WebDriverException e){

                    Assert.assertTrue("Expected id: " + list.get(i).get("id") + " ,Actual: " + driver.findElement(getGridResourceBundleElementBy(gridElementLocatorTemplate_clickOn, Integer.toString(z), "id")).getText(), false);

                }

                try {
                    String resourceType = list.get(i).get("resourcetype").equals("default") ? "course" : list.get(i).get("resourcetype").toString();

                    if (!resourceType.equals(driver.findElement(getGridResourceBundleElementBy(gridElementLocatorTemplate_clickOn, Integer.toString(z), "resourcetype")).getText()))
                        Assert.assertTrue("Expected id: " + list.get(i).get("resourcetype") + " ,Actual: " + driver.findElement(getGridResourceBundleElementBy(gridElementLocatorTemplate_clickOn, Integer.toString(z), "resourcetype")).getText(), false);
                }catch (WebDriverException e){

                    Assert.assertTrue("Expected id: " + list.get(i).get("resourcetype") + " ,Actual: " + driver.findElement(getGridResourceBundleElementBy(gridElementLocatorTemplate_clickOn, Integer.toString(z), "resourcetype")).getText(), false);

                }
            }


        }catch (Exception e){

            logger.info("Verfying bundle cell content failed. Exception: "+e.getMessage());

            Assert.assertTrue("Verfying bundle cell content failed. Exception: "+e.getMessage(),false);

        }



    }


    public void verifyOfferCellContent(List<Map> list) {

        try {

            logger.info("Verfying offer cell content");

            for (int i = 0; i < list.size(); i++) {


                int z = i + 1;
                // Status - do something


                try {
                    if (!list.get(i).get("offertype").equals(driver.findElement(getGridOfferSetupElementBy(gridElementLocatorTemplate_clickOn,Integer.toString(z), "offertype")).getText()))
                        Assert.assertTrue("Expected offertype: " + list.get(i).get("offertype") + " ,Actual: " + driver.findElement(getGridOfferSetupElementBy(gridElementLocatorTemplate_clickOn, Integer.toString(z), "offertype")).getText(), false);

                } catch (WebDriverException e) {

                    Assert.assertTrue("Expected offertype: " + list.get(i).get("offertype") + " ,Actual: " + driver.findElement(getGridOfferSetupElementBy(gridElementLocatorTemplate_clickOn, Integer.toString(z), "offertype")).getText(), false);

                }

                String individual="";

                try {
                    individual = list.get(i).get("individual").toString().toUpperCase();

                    if (!individual.equals(driver.findElement(getGridOfferSetupElementBy(gridElementLocatorTemplate_clickOn, Integer.toString(z), "individual")).getText()))
                        Assert.assertTrue("Expected individual: " + list.get(i).get("individual") + " ,Actual: " + driver.findElement(getGridOfferSetupElementBy(gridElementLocatorTemplate_clickOn, Integer.toString(z), "individual")).getText(), false);
                } catch (WebDriverException e) {

                    Assert.assertTrue("Expected individual: " + individual + " ,Actual: " + driver.findElement(getGridOfferSetupElementBy(gridElementLocatorTemplate_clickOn, Integer.toString(z), "individual")).getText(), false);

                }

                String buisness="";

                try {
                    buisness = list.get(i).get("buisness").toString().toUpperCase();

                    if (!buisness.equals(driver.findElement(getGridOfferSetupElementBy(gridElementLocatorTemplate_clickOn, Integer.toString(z), "buisness")).getText()))
                        Assert.assertTrue("Expected buisness: " + list.get(i).get("buisness") + " ,Actual: " +driver.findElement(getGridOfferSetupElementBy(gridElementLocatorTemplate_clickOn, Integer.toString(z), "buisness")).getText(), false);
                } catch (WebDriverException e) {

                    Assert.assertTrue("Expected buisness: " + list.get(i).get("buisness") + " ,Actual: " + driver.findElement(getGridOfferSetupElementBy(gridElementLocatorTemplate_clickOn, Integer.toString(z), "buisness")).getText(), false);

                }

                String duration="";

                try {
                    duration = list.get(i).get("offertype").equals("instructor") ? "Perpetual" : list.get(i).get("duration").toString();

                    if (!duration.equals(driver.findElement(getGridOfferSetupElementBy(gridElementLocatorTemplate_clickOn, Integer.toString(z), "duration")).getText()))
                        Assert.assertTrue("Expected duration: " + list.get(i).get("duration") + " ,Actual: " + driver.findElement(getGridOfferSetupElementBy(gridElementLocatorTemplate_clickOn, Integer.toString(z), "duration")).getText(), false);
                } catch (WebDriverException e) {

                    Assert.assertTrue("Expected duration: " + list.get(i).get("duration") + " ,Actual: " + driver.findElement(getGridOfferSetupElementBy(gridElementLocatorTemplate_clickOn, Integer.toString(z), "duration")).getText(), false);

                }

                try {
                    String isbn = list.get(i).get("offertype").toString().toLowerCase().equals("purchase") ? list.get(i).get("isbn").toString() : "";
                    if (!isbn.equals(driver.findElement(getGridOfferSetupElementBy(gridElementLocatorTemplate_clickOn, Integer.toString(z), "isbn")).getText()))
                        Assert.assertTrue("Expected isbn: " + list.get(i).get("isbn") + " ,Actual: " + driver.findElement(getGridOfferSetupElementBy(gridElementLocatorTemplate_clickOn, Integer.toString(z), "isbn")).getText(), false);
                } catch (WebDriverException e) {

                    Assert.assertTrue("Expected isbn: " + list.get(i).get("isbn") + " ,Actual: " + driver.findElement(getGridOfferSetupElementBy(gridElementLocatorTemplate_clickOn, Integer.toString(z), "isbn")).getText(), false);

                }
            }


        } catch (Exception e) {

            logger.info("Verfying offer cell content failed. Exception: " + e.getMessage());

            Assert.assertTrue("Verfying offer cell content failed. Exception: " + e.getMessage(), false);

        }


    }


/*    //[ M.E ] - Get the element of the offer Bundle grid given a row number and the column name
    // builds the xpath locator according to the parameters
   public WebElement getGridOfferSetupElement(String row, String column) {

        try {

            logger.info("Starting to build xpath locator for Offer Setup row: " + row + " and column " + column);

            String xpathXpression = "-" + row + "-uiGrid-004";

            String tempColumn = "Bad Parameter";

            switch (column) {

                case "Status":
                    tempColumn = "H";
                    break;

                case "Type":
                    tempColumn = "I";
                    break;

                case "Individual":
                    tempColumn = "J";
                    break;

                case "Business":
                    tempColumn = "K";
                    break;

                case "Duration":
                    tempColumn = "L";
                    break;

                case "ISBN":
                    tempColumn = "M";
                    break;

                case "Restriction":
                    tempColumn = "N";
                    break;

            }

            xpathXpression = xpathXpression + tempColumn + "-cell";

            return driver.findElement(By.xpath("//div[contains(@id,\""+xpathXpression+"\")]/div"));

        } catch (WebDriverException e) {

            logger.error("finding locator for Offer Setup row: " + row + " and column " + column + " failed. Exception:" + e.getMessage());

            Assert.assertTrue("finding locator for Offer Setup row: " + row + " and column " + column + " failed. Exception:" + e.getMessage(), false);

            return null;

        }


    }*/

/*    //[ M.E ] - Get the element of the resource bundle grid given a row number and the column name
    // builds the xpath locator according to the parameters
    public WebElement getGridResourceBundleElement(String row, String column) {

        try {

            logger.info("Starting to build xpath locator for resource bundle row: " + row + " and column " + column);

            String xpathXpression = "-" + row + "-uiGrid-004";

            String tempColumn = "Bad Parameter";

            switch (column) {

                case "Title":
                    tempColumn = "B";
                    break;

                case "ResourceID":
                    tempColumn = "C";
                    break;

                case "ResourceType":
                    tempColumn = "D";
                    break;

                case "Action":
                    tempColumn = "E";
                    break;

            }

            xpathXpression = xpathXpression + tempColumn + "-cell";

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@id,\""+xpathXpression+"\")]")));

            return driver.findElement(By.xpath("//div[contains(@id,\""+xpathXpression+"\")]/div"));

        } catch (WebDriverException e) {

            logger.error("finding locator for resource bundle row: " + row + " and column " + column + " failed. Exception:" + e.getMessage());

            Assert.assertTrue("finding locator for resource bundle row: " + row + " and column " + column + " failed. Exception:" + e.getMessage(), false);

            return null;

        }


    }*/


/*    //[ M.E ] - Get the element of the resource bundle grid given a row number and the column name
    // builds the xpath locator according to the parameters
    public WebElement getEditPageGridResourceBundleElement(String row, String id) {

        try {

            logger.info("Starting to build xpath locator for resource bundle row: " + row + " and id " + id);
//div[contains(@id,'-0-uiGrid-')]/div[text()='4bee091a-faa4-42d5-9cdf-fda1eb2c8d30']

            //    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@id,\""+xpathXpression+"\")]/div")));

            return driver.findElement(By.xpath("//div[contains(@id,\"-0-uiGrid-00\")]/div"));

        } catch (WebDriverException e) {

            logger.error("finding locator for resource bundle row: " + row + " and id " + id + " failed. Exception:" + e.getMessage());

            Assert.assertTrue("finding locator for resource bundle row: " + row + " and id " + id + " failed. Exception:" + e.getMessage(), false);

            return null;

        }


    }*/








}
