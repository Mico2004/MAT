package com.qualitest.pages;

import com.qualitest.utils.java.PropertiesGetter;
import com.qualitest.utils.selenium.ActionUtility;
import com.qualitest.utils.selenium.WaitDriverUtility;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;

public class LoginPage extends Page implements PageInterface  {

    static Logger logger = Logger.getLogger(LoginPage.class);

    public static String TITLE="MHE Admin Tool - Login Page";

    public static By emailOrUsername_By = By.id("usr");
    WebElement emailOrUserName;


    public static By password_By = By.id("pwd");
    WebElement password;

    public static By loginButton_By = By.xpath("./html//button[1]");
    WebElement loginButton;



    public LoginPage(WebDriver driver) {

        super(driver);

    }


    // [ M.E] - initilize all page elements, called on waitForPageToLoad()
    public void initializeElements() {

        try {

            logger.info("Initializing Login Page elements");

            emailOrUserName = driver.findElement(emailOrUsername_By);

            password = driver.findElement(password_By);

            loginButton = driver.findElement(loginButton_By);



        }catch(WebDriverException e){

            logger.error("Initializing Login Page elements. Exception: "+e.getMessage());

            Assert.assertTrue("Initializing Login Page elements. Exception: "+e.getMessage(), false);

        }
    }


    // [M.E] - Wait for login page to
    public void waitForPageToLoad() {

        try {

            logger.info("Wait for the Login Page elements to load");

            super.waitForPageToLoad();

            WaitDriverUtility.waitForTitleToBe(driver,wait,TITLE);

            wait.until(ExpectedConditions.visibilityOfElementLocated(emailOrUsername_By));

            wait.until(ExpectedConditions.visibilityOfElementLocated(password_By));

            wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton_By));

            initializeElements();


        } catch (WebDriverException e) {

            logger.error("Login page loading failed. Exception: "+e.getMessage());

            Assert.assertTrue("Login page loading failed. Exception: "+e.getMessage(), false);



        }
    }

 // [M.E] - Given username and password, login to connect2 with expectation to succeed
    public void loginWithParamsAndExpectToSucceed(String name, String pass, LandingPage landingPage, SearchPendingPage searchPendingPage) {

        try {

            logger.info("Login as "+name+ "with password"+pass);

            waitForPageToLoad();

            emailOrUserName.sendKeys(name);

            password.sendKeys(pass);

            loginButton.click();

            landingPage.waitForPageToLoad();

            searchPendingPage.waitForPageToLoad();


        } catch (WebDriverException e) {

            logger.error("Login as "+name+ "with password"+pass+" failed. Exception: "+e.getMessage());

            Assert.assertTrue("Login as "+name+ "with password"+pass+" failed. Exception: "+e.getMessage(), false);

        }


    }

    // [M.E] - Given username and password, login to connect2 with expectation to succeed
    public void loginWithParamsWithoutExpectations(String name, String pass) {

        try {

            logger.info("Login as "+name+ "with password"+pass);

            waitForPageToLoad();

            emailOrUserName.sendKeys(name);

            password.sendKeys(pass);

            loginButton.click();


        } catch (WebDriverException e) {

            logger.error("Login as "+name+ "with password"+pass+" failed. Exception: "+e.getMessage());

            Assert.assertTrue("Login as "+name+ "with password"+pass+" failed. Exception: "+e.getMessage(), false);

        }


    }

    public String[] getUsernameAndPassword(String user) throws IOException {

        try{

            String password="";

            PropertiesGetter prop=null;

            prop=new PropertiesGetter();

            logger.info("Get user name and pass for user identifier: "+user);

            switch (user.toLowerCase()) {
                case "bothusernameandfullname":
                    user=prop.getProp("user_bothUsernameAndFullname");
                    password="111";
                    break;

                case "onlyusername":
                    user=prop.getProp("user_OnlyUsername");
                    password="111";
                    break;

                case "withoutprofilepic":
                    user=prop.getProp("user_WithoutProfilePic");
                    password="111";
                    break;

                case "withprofilepic":
                    user=prop.getProp("user_WithProfilePic");
                    password="111";
                    break;

                case "nonadmin":
                    user=prop.getProp("user_nonadmin");
                    password="111";
                    break;

                case "longstring":
                    user=prop.getProp("user_LongString");
                    password=prop.getProp("pass_LongString");
                    break;

                case "mailformat":
                    user=prop.getProp("user_MailFormat");
                    password=prop.getProp("pass_MailFormat");
                    break;

                case "undescore":
                    user=prop.getProp("user_undercore");
                    password=prop.getProp("pass_undercore");
                    break;

                case "keyboardspecialcharacters":
                    user=prop.getProp("user_keyboardspecialCharacters");
                    password=prop.getProp("user_keyboardspecialCharacters");
                    break;

                case "mixedcaseuserandpass":
                    user=prop.getProp("user_mixedcase");
                    password=prop.getProp("pass_mixedcase");
                    break;

                default:
                    logger.fatal("Error: User doesn't exist ");
                    user="Error: User doesn't exist";
                    break;
            }

            String[] nameAndPassArray={user,password};

            return nameAndPassArray;



        }catch (WebDriverException e){

            logger.error("Failed to get username and password");

            Assert.assertTrue("Failed to get username and password",false);

            return null;
        }


    }
}