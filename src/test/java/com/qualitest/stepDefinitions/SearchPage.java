package com.qualitest.stepDefinitions;


import com.qualitest.pages.SearchPendingPage;
import com.qualitest.stepDefinitions.lifecycle.BeforeFeature;
import com.qualitest.utils.selenium.WaitDriverUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.apache.log4j.Logger;

public class SearchPage {

    Logger logger= Logger.getLogger(SearchPage.class);


    public SearchPage(){


    }

    @Then("^I verify the first grid raw is displayed$")
    public void I_verify_the_first_grid_raw_is_displayed(){

        WaitDriverUtility.waitForElementBeDisplayed(BeforeFeature.driver, SearchPendingPage.first_table_row_By,30);

    }


    @And("^I click on Create New tab$")
    public void I_click_on_Create_New_tab(){

        Shell.searchPendingPage.clickOnCreateNew(Shell.createNewPage);

    }

    @And("^I search for the unique id$")
    public void i_search_for_the_unique_id(){

        Shell.searchPendingPage.search(CreateOrEdit.currentResourceID);

    }

    @And("^I click the offer link$")
    public void i_click_the_offer_link(){

        Shell.searchPendingPage.clickOnanOffer(CreateOrEdit.currentOfferName,Shell.createNewPage);

    }

}
