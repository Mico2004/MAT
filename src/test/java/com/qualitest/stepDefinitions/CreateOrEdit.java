package com.qualitest.stepDefinitions;


import com.qualitest.utils.java.Time;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.apache.log4j.Logger;
import java.util.*;



public class CreateOrEdit {

    Logger logger=Logger.getLogger(CreateOrEdit.class);

    public static List <Map> bundleList1=new ArrayList<Map>();

    public static List <Map> offerList1=new ArrayList<Map>();

    public static String currentResourceID = "";

    public static String currentOfferName="";

    @And("^I add a resource$")
    public void i_add_a_resource(DataTable parameters) throws Exception {

        List<List<String>> list=parameters.raw();

        Map bundleMap1 = new HashMap();

        String title=list.get(1).get(0).equals("default")? "default" : "Title:"+UUID.randomUUID();

        String id=list.get(1).get(1).equals("default")? "default" : ""+UUID.randomUUID();

        String resourceType=list.get(1).get(2).equals("default") ? "default" : list.get(2).toString();

        bundleMap1.put("title",title);

        bundleMap1.put("id",id);

        currentResourceID=id;

        bundleMap1.put("resourcetype",list.get(1).get(2));

        bundleList1.add(bundleMap1);

        Shell.createNewPage.addAresourceBundle(title,id,resourceType);

    }
  // [M.E] - duration and isbn can accept 'default' values
    @And("^I add an Offer$")
    public void i_add_an_offer(DataTable parameters) throws Exception {

        List<List<String>> list=parameters.raw();

        Map offerMap1 = new HashMap();

        String offerType=list.get(1).get(0).toString();

        String status=list.get(1).get(1).toString();

        String individual=list.get(1).get(2).toString();

        String buisness=list.get(1).get(3).toString();

        String duration=list.get(1).get(4).toString();

        String isbn=list.get(1).get(5).toString();

        offerMap1.put("offertype",offerType);

        offerMap1.put("status",status);

        offerMap1.put("individual",individual);

        offerMap1.put("buisness",buisness);

        offerMap1.put("duration",duration);

        offerMap1.put("isbn",isbn);

        offerList1.add(offerMap1);

        Shell.createNewPage.addSetupOffer(offerType,status,individual,buisness,duration,isbn);

    }

    @Then("^I click on Save Offer$")
    public void i_click_on_save_offer(){

        Shell.createNewPage.clickOnCreateOffer(Shell.searchPendingPage);

    }


    // [M.E] Should receive 'resources' \ 'offers'
    @Then("^I verify the \"([^\"]*)\" cells content is correct$")
    public void I_verify_the_cells_content_is_correct(String resourcesOrOffers){

       if( resourcesOrOffers.toLowerCase().equals("resources") )
           Shell.createNewPage.verifyBundleCellContent(bundleList1);

       else
           Shell.createNewPage.verifyOfferCellContent(offerList1);

    }

    // [M.E] Should receive 'resources' \ 'offers'
    @Then("^I verify the \"([^\"]*)\" rows count is correct$")
    public void I_verify_the_rows_count_is_correct(String resourcesOrOffers){

        Shell.createNewPage.verifyRowCount(resourcesOrOffers);

    }


    // [M.E] - can get - unique \ anything else
    @And("^I name the offer - \"([^\"]*)\"$")
    public void I_name_the_offer(String offerName){

        if(offerName.equals("unique")) {

            currentOfferName=System.getProperty("suitename") + " - " + Time.getDateAndTime() + " - " + UUID.randomUUID();

            Shell.createNewPage.nameOffer(currentOfferName);

        }
        else {

            Shell.createNewPage.nameOffer(offerName);

            currentOfferName=offerName;

        }
    }




}
