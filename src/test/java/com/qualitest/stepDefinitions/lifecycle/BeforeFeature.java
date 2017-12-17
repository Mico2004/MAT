package com.qualitest.stepDefinitions.lifecycle;

import com.qualitest.platforms.DriverRemote;
import com.qualitest.utils.java.PropertiesGetter;
import com.qualitest.reports.Report;
import com.qualitest.reports.SaucelabsParams;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import io.qameta.allure.AllureConstants;
import io.qameta.allure.AllureResultsWriter;
import io.qameta.allure.model.TestResult;
import io.qameta.allure.model.TestResultContainer;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import com.qualitest.pages.LoginPage;
import com.qualitest.pages.Page;
import com.qualitest.platforms.DriverLocal;
import ru.yandex.qatools.allure.Allure;
import ru.yandex.qatools.allure.model.TestCaseResult;

import java.io.InputStream;
import java.net.MalformedURLException;


public class BeforeFeature {

    Logger logger = Logger.getLogger(BeforeFeature.class);

    public static WebDriver driver=null;

    @Before
    public void Before(Scenario scenario) throws MalformedURLException {

        try {

            logger.info("Starting scenario: " + scenario.getName());

            PropertiesGetter prop=new PropertiesGetter();

            //  if(Page.getDriverType().equals("local"))
            // for local webdriver
            //  else
            //  // for remote webdriver



            if(prop.getProp("driverType").equals("remote")) {

                driver = DriverRemote.setDriver();

                Report report=new Report();

                report.setPropertyFile();

                report.setResultDirectory();

                SaucelabsParams saucelabsparams=new SaucelabsParams();

                saucelabsparams.setSaucelabParamBeforeTest(driver,"MAT."+scenario.getName(), scenario.getSourceTagNames().toString());

            }else{
                driver = DriverLocal.setDriver();}


        }catch (Exception e){

            logger.fatal("Before scenario failed:  "+e.getMessage());

        }

    }

}




