package com.qualitest.platforms;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

/*import io.appium.java_client.android.AndroidDriver;*/

import java.util.logging.Level;

import static com.qualitest.platforms.DriverRemote.caps;

public class DriverLocal {

    static Logger logger = Logger.getLogger(DriverLocal.class);

    private static String os = System.getProperty("os.name").toLowerCase();

    private static DesiredCapabilities desiredCapabilities;

    public static WebDriver driver;

    public static WebDriver setDriver() {

        setEnvironmentVeriables();

        System.setProperty("browser", "firefox");

        String browser = System.getProperty("browser");




        browser = browser.toLowerCase();

        driver = null;

        switch (browser) {
            case "chrome":
                driver = getChromeDriver();
                break;
            case "firefox":
                driver = getFirefoxDriver();
                break;
            case "ie":
                driver = getIEDriver();
                break;
            case "edge":
                driver = getEdgeDriver();
                break;
            case "safari":
                driver = getSafariDriver();
                break;
            case "android":
                driver = getAndriodDriver();
                break;
            default:
                System.out.println("An invalid browser requested: " + browser);
                return null;
        }
        return driver;
    }

    private static WebDriver getChromeDriver() {
        //	https://github.com/SeleniumHQ/selenium/wiki/ChromeDriver

        if (os.contains("win")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/Windows/32/chromedriver.exe");
        }
        else if (os.contains("osx")){
            System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/OS X/chromedriver");
        }
        else{
            System.out.println("Unknown or incompatible OS detected: " + os + ". Can't pick the correct WebDriver!");
            return null;
        }
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        desiredCapabilities = DesiredCapabilities.chrome();
        desiredCapabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        ChromeOptions options = new ChromeOptions();
        //  options.addArguments("--start-maximized");
        //	"--disable-infobars" removes "Chrome is being controlled by automated test software"
        options.addArguments("--disable-infobars");

        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options);

        return new ChromeDriver(desiredCapabilities);
    }

    private static WebDriver getFirefoxDriver() {
        //	https://github.com/SeleniumHQ/selenium/wiki/FirefoxDriver

        if (os.contains("win")) {
            System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/Windows/32/geckodriver.exe");
        } else if (os.contains("osx")) {
            System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/OS X/geckodriver");
        } else {
            System.out.println("Unknown or incompatible OS detected: " + os + ". Can't pick the correct WebDriver!");
            return null;
        }
        desiredCapabilities = DesiredCapabilities.firefox();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        desiredCapabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        FirefoxDriver driver = new FirefoxDriver(desiredCapabilities);



        //driver.manage().window().maximize();	crashes the driver for some reason

        return driver;
    }

    private static WebDriver getIEDriver() {
        //	https://github.com/SeleniumHQ/selenium/wiki/InternetExplorerDriver
        if (os.contains("win")) {
            System.setProperty("webdriver.ie.driver", "src/main/resources/drivers/Windows/32/IEDriverServer.exe");
        } else {
            System.out.println("Unknown or incompatible OS detected: " + os + ". Can't pick the correct WebDriver!");
            return null;
        }
        desiredCapabilities = DesiredCapabilities.internetExplorer();

        LoggingPreferences logPrefs = new LoggingPreferences();

        logPrefs.enable(LogType.BROWSER, Level.ALL);

        desiredCapabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME, "IE");

        desiredCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);

        desiredCapabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);

        InternetExplorerDriver driver = new InternetExplorerDriver(desiredCapabilities);

        //  driver.manage().window().maximize();
        return driver;
    }

    private static WebDriver getEdgeDriver() {
        //	????????????? github wiki does not exist...

        if (os.contains("win")) {
            System.setProperty("webdriver.edge.driver", "src/main/resources/drivers/Windows/MicrosoftWebDriver.exe");
        } else {
            System.out.println("Unknown or incompatible OS detected: " + os + ". Can't pick the correct WebDriver!");
            return null;
        }
        desiredCapabilities = DesiredCapabilities.edge();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        EdgeDriver driver = new EdgeDriver(desiredCapabilities);
        desiredCapabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        //  driver.manage().window().maximize();
        return driver;
    }

    private static WebDriver getSafariDriver() {
        //	https://github.com/SeleniumHQ/selenium/wiki/SafariDriver

        if (os.contains("osx")) {
            // ToDo
        } else {
            System.out.println("Unknown or incompatible OS detected: " + os + ". Can't pick the correct WebDriver!");
            return null;
        }
        desiredCapabilities = DesiredCapabilities.safari();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        SafariDriver driver = new SafariDriver(desiredCapabilities);
        desiredCapabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        return new SafariDriver(desiredCapabilities);
    }

    //get Android driver
    private static WebDriver getAndriodDriver() {
  /*      String platformVersion = ProcessManager.executeProccess("adb", "shell", "getprop", "ro.build.version.release");
        desiredCapabilities.setCapability("deviceName", "lgg2");
        desiredCapabilities.setCapability("app", "src/test/resources/apps/AndroidFlowRunner.apk");
        desiredCapabilities.setCapability(CapabilityType.VERSION, platformVersion);
        desiredCapabilities.setCapability("platformName", "AndroidBrowser");
        return new AndroidDriver(desiredCapabilities);*/

         return null;
    }

    private static void setEnvironmentVeriables(){

        try{

            System.setProperty("Environment","qa");

            System.setProperty("LTIEnvironment","qa");

            System.setProperty("LTIBranch","master");

        }catch (Exception e){

            logger.fatal(" Setting Environment variables failed. Exception: "+e.getMessage());

        }




    }

}