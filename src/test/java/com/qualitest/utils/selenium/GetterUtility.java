package com.qualitest.utils.selenium;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class GetterUtility {

    static Logger logger = Logger.getLogger(GetterUtility.class);


    public static String getCursorType(WebElement element) {
        String cursor = element.getCssValue("cursor");
        if (cursor != null) {
            return cursor;
        }
        throw new RuntimeException("Couldn't get the cursor type !");
    }

    public static WebElement getElementParent(WebElement element) {

        return element.findElement(By.xpath(".."));
    }

    public static String getElementAttibuteValue(WebElement element, String attribute){
    try {

         return element.getAttribute(attribute);

        }catch (Exception e) {

         System.out.println(e.getMessage());

         Assert.assertTrue("Attribute wasn't found "+e.getMessage(), false);

         return null;

        }
    }

    public static WebElement getElementChildrenOfType(WebElement parent ,String elementType){

        try{

            logger.info("Finding children of type: "+elementType+" of parent: "+parent.toString());

            String tempLocator="//"+elementType;

            return parent.findElement(By.xpath(tempLocator));

        }catch (Exception e){

            return null;

        }




    }
}
