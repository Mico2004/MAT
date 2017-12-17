package com.qualitest.utils.selenium;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.awt.event.KeyEvent;

public class TabUtility {

    public static void switchToNewTab(WebDriver driver, String expectedBrowserTitle) {
        try {
            int timeOut = 10;

            WaitDriverUtility.sleepInSeconds(2);
            while (timeOut > 0) {
                String currentTab = driver.getWindowHandle();
                for (String tab : driver.getWindowHandles()) {
                    if (!tab.equals(currentTab)) {
                        driver.close();
                        driver.switchTo().window(tab);
                        System.out.println("waiting 5 sec and then switching tabs");
                        WaitDriverUtility.sleepInSeconds(5);

                        if (driver.getTitle().equals(expectedBrowserTitle)) {
                            System.out.println("Switching tabs successfully completed");
                            return;
                        }
                        timeOut--;
                    }
                }
            }
        } catch (Exception e) {

            Assert.assertTrue("Switch to new tab failed"+e.getMessage(), false);
        }
    }

    public static void switchToNewTabAndDoNotCloseOther(WebDriver driver, String expectedBrowserTitle) {

        try {

            int timeOut = 10;
            WaitDriverUtility.sleepInSeconds(2);
            while (timeOut > 0) {
                String currentTab = driver.getWindowHandle();
                for (String tab : driver.getWindowHandles()) {
                    if (!tab.equals(currentTab)) {
                        driver.switchTo().window(tab);
                        if (driver.getTitle().equals(expectedBrowserTitle)) {
                            return;
                        }
                        WaitDriverUtility.sleepInSeconds(1);
                        timeOut--;
                    }
                }
            }
        }catch(Exception e){

            Assert.assertTrue("Switch to tab failed"+e.getMessage(), false);

        }
    }

    public static void switchToMainTabAndCloseOthersTabs(WebDriver driver, String impersonateTabId) {

        try {

            for (String tab : driver.getWindowHandles()) {
                if (!tab.equals(impersonateTabId)) {
                    driver.close();
                }
            }
            driver.switchTo().window(impersonateTabId);
            WaitDriverUtility.sleepInSeconds(1);
        }catch(Exception e){

            Assert.assertTrue("Switch to tab failed"+e.getMessage(), false);

        }
    }


    // [M.E] - Open a new tab
    public static boolean openNewEmptyTab(){

        try {

            Robot r = new Robot();

            r.keyPress(KeyEvent.VK_CONTROL);

            r.keyPress(KeyEvent.VK_T);

            r.keyRelease(KeyEvent.VK_CONTROL);

            r.keyRelease(KeyEvent.VK_T);

            return true;

        }catch(Exception e){

            System.out.println(e.getMessage());

            Assert.assertTrue("Open a new tab failed"+e.getMessage(), false);

            return false;

        }

    }
}
