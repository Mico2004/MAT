package com.qualitest.reports;

import com.qualitest.utils.selenium.ActionUtility;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class SaucelabsParams {

    Logger logger = Logger.getLogger(ActionUtility.class);



    public SaucelabsParams(){


    }


    public void setSaucelabParamBeforeTest(WebDriver driver , String scenarioName, String tags){


        try {

            logger.info("Setting sauce labs params before test: scenarioName"+ scenarioName+"tags: "+tags);

            ((JavascriptExecutor) driver).executeScript("sauce:job-name=" + scenarioName);

            ((JavascriptExecutor) driver).executeScript("sauce:job-tags=" + tags);


        }catch (Exception e){

            logger.error("failed to set sauce labes params. Exception: "+e.getMessage());

        }


    }


    public void setSaucelabParamAfterTest(WebDriver driver , boolean scenarioResult){


        try {

            logger.info("Setting sauce labs test result: test succeeded:"+scenarioResult);

            ((JavascriptExecutor) driver).executeScript("sauce:job-result=" + (scenarioResult ? "passed" : "failed"));

            ((JavascriptExecutor) driver).executeScript("sauce:job-build=" + "MAT_1.0.43");


        }catch (Exception e){

            logger.error("failed Setting sauce labs test result. Exception: "+e.getMessage());

        }


    }



    



}
