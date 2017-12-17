package com.qualitest.pages;

import junitx.util.PropertyManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.qualitest.utils.selenium.WaitDriverUtility;

public abstract class  Page   {

    Logger logger = Logger.getLogger(Page.class);

    WebDriver driver;

    public static WebDriverWait wait;

    public static boolean version_registered;

    public static String tempAssignment_A;




    public Page(WebDriver driver){

        this.driver =driver;

        wait= new WebDriverWait(driver, 30);

        System.setProperty("PropertyManager.file", "src/main/resources/local.properties");


    }



    // [M.E] - wait for most basic elements of the page to load (Title, Buttons and etc) and JS complete event from the page
    public void waitForPageToLoad(){
        try {

            WaitDriverUtility.waitToPageBeLoaded(driver);



        }catch(WebDriverException e){

            logger.error("Waiting for the page state to be ready failed. Exception:"+e.getMessage());

            Assert.assertTrue("Waiting for the page state to be ready failed. Exception:"+e.getMessage(), false);


        }
    }



    public WebDriverWait getWait(){

        return wait;

    }

    public boolean openLink(String link){

        try{

            logger.info("Navigate to the following address:" +link);

            driver.get(link);



            return true;

        }catch(WebDriverException e){

            Assert.assertTrue("Navigation to "+link+" failed. Exception: "+e.getMessage(), false);

            return false;

        }

    }





}
