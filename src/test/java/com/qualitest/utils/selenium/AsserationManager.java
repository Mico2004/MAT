package com.qualitest.utils.selenium;

import org.apache.log4j.Logger;
import org.junit.Assert;

/**
 * Created by Lenovo on 18/09/2017.
 */
public class AsserationManager {

    private static Logger logger = Logger.getLogger(AsserationManager.class);

    public static void assertEquals(Object actual, Object expected, String failedMessage, String successMessage) {

        try {

            if (!actual.equals(expected)) {
                throw new AssertionError(failedMessage);
            }
            logger.info(successMessage);
        }catch(Exception e){

            Assert.assertTrue("Assertion failed "+e.getMessage(), false);

        }
    }
}
