package com.qualitest.reports;


import com.qualitest.pages.Page;
import com.qualitest.utils.java.PropertiesGetter;
import cucumber.api.Scenario;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class Report {

    Logger logger = Logger.getLogger(Report.class);


    public  void takeScreenShot(Scenario scenario, WebDriver driver){
        if (scenario.isFailed()) {

            try {

                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

                scenario.embed(screenshot, "image/png");

            } catch (Exception e) {

                logger.fatal("After Class Failed. Exception:"+e.getStackTrace());

                e.printStackTrace();
            }
        }


    }


    public void setResultDirectory(){

      //  System.setProperty("allure.results.directory", "target/"+System.getProperty("suitename"));

    }

    public void setPropertyFile() throws IOException {

        try {

            PropertiesGetter prop=new PropertiesGetter();

            new File("target/allure-results").mkdirs();;//+System.getProperty("suitename")).mkdirs();

            Properties props = new Properties();

            props.setProperty("OST Version", "1.0.43");

            props.setProperty("MAT Version", "1.0");

            props.setProperty("OST Environment", prop.getProp("OST_ENVIRONMENT"));

            props.setProperty("MAT Environment", prop.getProp("MAT_ENVIRONMENT"));

            File f = new File("target/allure-results/environment.properties");

            OutputStream out = new FileOutputStream(f);

            props.store(out, "This is an optional header comment string");

        }catch (Exception e){

            logger.info("Execption: "+e.getMessage());

        }





    }

}
