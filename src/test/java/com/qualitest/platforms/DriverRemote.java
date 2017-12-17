package com.qualitest.platforms;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

public class DriverRemote {

    static Logger logger = Logger.getLogger(DriverRemote.class);

    public static final String USERNAME = "DPGSL12";

    public static final String ACCESS_KEY = "00f186ee-2a87-473a-b55a-bc0cca94e68b";

    public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";

    public static DesiredCapabilities caps=null;

    public static RemoteWebDriver driver=null;

    public static WebDriver setDriver() throws MalformedURLException {

       // setEnvironmentVeriables();



        System.setProperty("browserName","safari");
        System.setProperty("platform","macOS 10.12");
        System.setProperty("version","latest");



        ////
        switch ( System.getProperty("browserName")){

            case "chrome":

                caps = DesiredCapabilities.chrome();

                break;

            case "firefox":

                caps = DesiredCapabilities.firefox();

                break;

            case "edge":

                caps = DesiredCapabilities.edge();

                break;

            case "safari":

                caps = DesiredCapabilities.safari();

                break;

            case "ie":

                caps = DesiredCapabilities.internetExplorer();

                break;

                default:
                    logger.fatal("Something went wrong with setting the driver browser. Browser passed: " + System.getProperty("browser"));

                    break;
        }

        LoggingPreferences logPrefs = new LoggingPreferences();

        logPrefs.enable(LogType.BROWSER, Level.ALL);

        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        caps.setCapability("platform",  System.getProperty("platform"));

        caps.setCapability("version", System.getProperty("version"));


        driver = new RemoteWebDriver(new URL(URL),caps);

        return  driver;


    }

/*
    private static void setEnvironmentVeriables(){

        try{

            System.setProperty("Environment","qa");

            System.setProperty("LTIEnvironment","qa");

            System.setProperty("LTIBranch","master");

        }catch (Exception e){

            logger.fatal(" Setting Environment variables failed. Exception: "+e.getMessage());

        }




    }*/


}
