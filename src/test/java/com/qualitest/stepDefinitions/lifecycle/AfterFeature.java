package com.qualitest.stepDefinitions.lifecycle;


import com.qualitest.reports.ChangeTestCaseTitleEvent;
import com.qualitest.reports.SaucelabsParams;
import com.qualitest.utils.java.PropertiesGetter;
import cucumber.api.Scenario;
import cucumber.api.java.After;

import com.qualitest.reports.Report;
import io.qameta.allure.AllureResultsWriter;
import io.qameta.allure.model.TestResult;
import io.qameta.allure.model.TestResultContainer;
import io.qameta.allure.model.TestRunResult;
import org.apache.log4j.Logger;
import ru.yandex.qatools.allure.Allure;
import ru.yandex.qatools.allure.model.TestCaseResult;

import java.io.InputStream;


public class AfterFeature  {

    Logger logger = Logger.getLogger(AfterFeature.class);


    @After
    public void killBrowser(Scenario scenario){
        try {

            logger.info("Ending scenario: " + scenario.getName() + " , scenario " + scenario.getStatus());

            Report r=new Report();

            r.takeScreenShot(scenario, BeforeFeature.driver);

            PropertiesGetter prop=new PropertiesGetter();

            if(prop.getProp("driverType").equals("remote"))

            {

                SaucelabsParams saucelabsparams=new SaucelabsParams();

                saucelabsparams.setSaucelabParamAfterTest(BeforeFeature.driver,!scenario.isFailed());

            }

            Thread.sleep(4000);


            Allure.LIFECYCLE.fire(new ChangeTestCaseTitleEvent(System.getProperty("suitename")+" :  "));




            BeforeFeature.driver.close();




        } catch (Exception e){

            logger.fatal("error after class of Cucumber: "+e.getMessage());

        }

    }




}




