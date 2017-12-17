package com.qualitest.stepDefinitions;



import com.qualitest.utils.java.PropertiesGetter;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.apache.log4j.Logger;
import cucumber.api.java.en.Given;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;
import com.qualitest.pages.*;
import com.qualitest.stepDefinitions.lifecycle.BeforeFeature;
import com.qualitest.utils.selenium.*;


import java.io.IOException;
import java.util.UUID;

import static org.junit.Assert.assertTrue;


public class Shell {




    Logger logger = Logger.getLogger(Shell.class);

    public static LoginPage loginPage;

    public static LandingPage landingPage;

    public static SearchPendingPage searchPendingPage;

    public static CreateNewPage createNewPage;

    PropertiesGetter prop=null;






    public Shell() throws IOException {


            loginPage = PageFactory.initElements(BeforeFeature.driver, LoginPage.class);

            landingPage = PageFactory.initElements(BeforeFeature.driver, LandingPage.class);

            searchPendingPage =PageFactory.initElements(BeforeFeature.driver, SearchPendingPage.class);

            createNewPage = PageFactory.initElements(BeforeFeature.driver, CreateNewPage.class);

            prop=new PropertiesGetter();




    }


 /*   @tmsLink=TEG-87599
    @severity=blocker*/

    @When("^I login as \"([^\"]*)\"")
    public void login_as_user_expect_to_succeed(String user) throws IOException {

        String password="111";

        String[] userNameAndPass=loginPage.getUsernameAndPassword(user);

        loginPage.openLink(prop.getProp("MAT_ENVIRONMENT"));

        loginPage.waitForPageToLoad();

        loginPage.loginWithParamsAndExpectToSucceed( userNameAndPass[0] , userNameAndPass[1],landingPage,searchPendingPage );

    }


    @Then("^I verify \"([^\"]*)\" picture is displayed$")
    public void I_verify_right_picture_is_displayed(String picType) throws Throwable {

       boolean result=landingPage.checkPic(picType);

        if(!result)
            Assert.assertTrue("picture fail",result);

    }



    @Then("^I verify the \"([^\"]*)\" value is displayed in the username field$")
    public void I_verify_the_property_is_displayed_in_the_username_field(String usernameType) throws IOException {

        if(System.getProperty("suitename").equals("test_windows_10_firefox"))
          Assert.assertTrue("failed in purpose",false);

        String[] userNameAndPass=new String[2];

        if(!usernameType.equals("fullname"))
            userNameAndPass=loginPage.getUsernameAndPassword(usernameType);
        else
            userNameAndPass[0]="fullname";

        boolean result=landingPage.checkUsername( "Welcome, "+userNameAndPass[0]+"!");

        if(!result)
            Assert.assertTrue("username fail",result);


    }



    @And("^I sign out$")
    public void I_sign_out() throws Exception {

        ActionUtility.clickOn(BeforeFeature.driver,loginPage.getWait(),LandingPage.logout_button_By);

    }


    @Given("^I open the offer setup page$")
    public void I_open_the_offer_setup_page() {

        BeforeFeature.driver.get(prop.getProp("OST_ENVIRONMENT"));

    }

    @Given("^I open the MAT login page$")
    public void I_open_the_MAT_login_page() {

        BeforeFeature.driver.get(prop.getProp("MAT_ENVIRONMENT"));

    }


    @Then("^I verify MAT login page is loaded successfully$")
    public void I_verify_MAT_login_page_is_loaded_successfully() {

        loginPage.waitForPageToLoad();

    }


    @And("^I login with \"([^\"]*)\" user with username in \"([^\"]*)\" and password in \"([^\"]*)\"$")
    public void i_type_user_name_and_password_in_given_case(String user, String usernameCase, String passwordCase) throws IOException {

       String[] usernameAndPassArray=loginPage.getUsernameAndPassword(user);

       String username=usernameAndPassArray[0];
       String password=usernameAndPassArray[1];

       if(usernameCase.toLowerCase().equals("lowercase"))
           username=usernameAndPassArray[0].toLowerCase();
       else if(usernameCase.toLowerCase().equals("uppercase"))
           username=usernameAndPassArray[0].toUpperCase();

       if(passwordCase.toLowerCase().equals("lowercase"))
           password=usernameAndPassArray[1].toLowerCase();
       else if(passwordCase.toLowerCase().equals("uppercase"))
           password=usernameAndPassArray[1].toUpperCase();

       loginPage.loginWithParamsWithoutExpectations(username,password);

    }

    @Given("^I login with \"([^\"]*)\" user$")
    public void login_with_user_without_expectation(String user) throws IOException {

        loginPage.openLink(prop.getProp("MAT_ENVIRONMENT"));

        loginPage.waitForPageToLoad();

        String[] usernameAndPassArray=loginPage.getUsernameAndPassword(user);

        String username=usernameAndPassArray[0];

        String password=usernameAndPassArray[1];

        loginPage.loginWithParamsWithoutExpectations(username,password);

    }


    @Then("^I verify i logged in successfully$")
    public void I_verify_i_logged_in_successfull(){

        landingPage.waitForPageToLoad();

        searchPendingPage.waitForPageToLoad();

    }

    @Then("^I verify login failed$")
    public void I_verify_login_failed(){

        landingPage.waitForPageNotToLoad();

    }

  /*  @Then("^I verify the offer was saved$")
    public void i_verify_the_offer_was_saved(){

        if(!createNewPage.getEditPageGridOfferSetupElement("0","Type").getText().equals("instructor"))
            assertTrue("Instructor offer wasn't saved",false);

    }*/
























}
