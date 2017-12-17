package com.qualitest.thirdpartytools;

import com.deque.axe.AXE;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;


import java.net.URL;

import static org.junit.Assert.assertTrue;

public class AxeHandler {

    static Logger  logger = Logger.getLogger(AxeHandler.class);


    public static final String AXE_MIN_JS_PATH="/axe.min.js";


    public static void runGeneralAccessibilityTest(WebDriver driver, URL scriptUrl ){

        JSONObject responseJSON = new AXE.Builder(driver, scriptUrl).analyze();

        JSONArray violations = responseJSON.getJSONArray("violations");

        if (violations.length() == 0) {
            assertTrue("No violations found", true);
        } else {
            AXE.writeResults("Test Accessibility", responseJSON);

            //AsserationManager.assertEquals(AXE.report(violations), true, "Accessibility Test failed:  "+AXE.report(violations), "Accessability Test succeeded ");

            assertTrue(AXE.report(violations), false);
        }

    }


    public static void testAccessibilityWithWebElement(WebDriver driver, URL scriptUrl, By webelement_By) {

        try {

            logger.info("Starting to verify accessibility for element: " + webelement_By.toString());

        JSONObject responseJSON = new AXE.Builder(driver, scriptUrl)
                .analyze(driver.findElement(webelement_By));

        JSONArray violations = responseJSON.getJSONArray("violations");

        if (violations.length() == 0) {
            assertTrue("No violations found", true);
        } else {
            AXE.writeResults("Test Accessibility", responseJSON);

            assertTrue(AXE.report(violations), false);
        }
        }catch(WebDriverException e){

            logger.error("Accessibility verification failed. Exception: "+e.getMessage());

        }
    }

    public static void testAccessibilityWithIncludesAndExcludes(WebDriver driver, URL scriptUrl,  String exclude_By) {

        try {

            logger.info("Starting to verify accessibility for element: " + exclude_By.toString());

            JSONObject responseJSON = new AXE.Builder(driver, scriptUrl)
                    .exclude(exclude_By)
                    .analyze();

            JSONArray violations = responseJSON.getJSONArray("violations");

            if (violations.length() == 0) {
                assertTrue("No violations found", true);
            } else {
                AXE.writeResults("Test Accessibility", responseJSON);

                assertTrue(AXE.report(violations), false);
            }

    }catch(WebDriverException e){

            logger.error("Accessibility verification failed. Exception: "+e.getMessage());

    }
    }
}
