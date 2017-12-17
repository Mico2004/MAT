package com.qualitest.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.qualitest.utils.selenium.WaitDriverUtility;

public class LandingPage extends Page implements PageInterface {


    static Logger logger = Logger.getLogger(LandingPage.class);

    public static String TITLE="Offer Setup";

    public static By username_textfield_By = By.id("user_name");
    WebElement username_textfield;


    public static By user_pic_By = By.id("user_pic_url");
    WebElement user_pic;

    public static By logout_button_By = By.id("mat_logout");
    WebElement logout_button;







    public LandingPage(WebDriver driver) {

        super(driver);

    }


    // [ M.E] - initilize all page elements, called on waitForPageToLoad()
    public void initializeElements() {

        try {

            logger.info("Initializing Landing Page elements");

            username_textfield = driver.findElement(username_textfield_By);

            user_pic = driver.findElement(user_pic_By);

            logout_button = driver.findElement(logout_button_By);



        }catch(WebDriverException e){

            logger.error("Initializing Landing Page elements. Exception: "+e.getMessage());

            Assert.assertTrue("Initializing Landing Page elements. Exception: "+e.getMessage(), false);

        }
    }


    // [M.E] - Wait for login page to
    public void waitForPageToLoad() {

        try {

            logger.info("Wait for the Login Page elements to load");

            super.waitForPageToLoad();

            wait.until(ExpectedConditions.titleIs(TITLE));

            wait.until(ExpectedConditions.elementToBeClickable(logout_button_By));

            wait.until(ExpectedConditions.elementToBeClickable(user_pic_By));

            wait.until(ExpectedConditions.elementToBeClickable(username_textfield_By));

            initializeElements();


        } catch (WebDriverException e) {

            logger.error("Login page loading failed. Exception: "+e.getMessage());

            Assert.assertTrue("Login page loading failed. Exception: "+e.getMessage(), false);



        }
    }

    // [M.E] - Wait for login page to not load
    public void waitForPageNotToLoad() {

        try {

            logger.info("Wait for the Login Page elements to not load");

            super.waitForPageToLoad();

            wait.until(ExpectedConditions.not(ExpectedConditions.titleIs(TITLE)));



        } catch (WebDriverException e) {

            logger.error("Login page not loading failed. Exception: "+e.getMessage());

            Assert.assertTrue("Login page not loading failed. Exception: "+e.getMessage(), false);



        }
    }

// [M.E ] - Check a given picture type exist
    // pcType - default \ other
    public boolean checkPic(String picType){

        try{

            logger.info("Check if photo is of type ->"+picType);

            WaitDriverUtility.waitForElementBeDisplayed(driver, user_pic_By,30);

            String userPic=driver.findElement(user_pic_By).getAttribute("style");

            if(picType.equals("default")){

                if(userPic.contains("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAGQAAABkCAMAAABHPGVmAAAABGdBTUEAALGPC/xhBQAAAwBQTFRF3yMp3yQr3yUs3yYs3yow3yox3y0z3zE43zI43zg+4R4l4B8m4CAn4CEo4CIo4CIp4CQq4CUs4CYs4CYt4Cox4Ssy4Cwy4C414C824TI44jM54jQ64TU74Dg+3jpA3TtB3z1D3z5F4TpA4T1D4z9F3llf31pf5kFG5kJH4EJI4EVL40ZM40hN4UpP5ElO5EpP40tR4k5T4k5U4U9V5UxS5k9U4lJX4VNY51NY5VVa5lVb5FZb41le4Fpf5Vle6FBV6VVa6Vdc4Fth5Fxh5V5j5F9k5GBl5mFm42Rp5WRp5Ghs5mht5Wpv6Wlu6mtv525y6Wxx43B05nB05nN353V65Xd76HB16Xd76Hl953yA54CE54GF64GF64OH7IOG6IeK6oeL64mM64mN6IuO7IuO642Q6I+T65aZ7ZWY7Zea6pqd65ue7pqd7Jyf6Z2h6p+i7Z+i8Z2g7KOm7qWo7Kao7aeq6Kqt7air7ams76qs7q6w7q+x7bCy7rG07rO17LO277a4772/8aGk8Kap8K2v8K+x8LGz8LK187e58ry+777A7r/B6sLE78LF7sTG787Q7tHT7NTV79XX8sDC8MLE88PF98DC9cTF9cTG8sbI9cfJ8sjJ8snL8srM9cjK9c3O8s7Q89LT89XX9tDS9dPU9NTV9dbX8dbY99jZ99na9tzd9t3e9t7f9uDh9eHi9uLj9uTl9OXm9ebn9efo9+rr9+7v+eDh+OLj++Pk+eTl+uXm+ufn+Ofo+ujo+urq+urr+Ozs+u7v/Ojp/O7v+u/w+PHx+vLy+/Pz+vT0+vX1+/X2+fb2+/f3/PDx/PPz/PT1/Pb2/Pf3+vj4/Pj4/Pj5/fn6/Pr6/fr7/Pz8/f39/v3+/f7+AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAJRKJJwAAAQB0Uk5T////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////AFP3ByUAAAAJcEhZcwAADsIAAA7CARUoSoAAAAAZdEVYdFNvZnR3YXJlAHBhaW50Lm5ldCA0LjAuMTnU1rJkAAADk0lEQVRoQ+2Y9VcUURiG7VpWsRAUUcHEFgvFVixswRa7CwMUxW5RUSxM7Aa7FVExUcFgWeefcYF3YXZ3vvvd2V09Hs8+P8597/ucMzP3TpRS/gIeiS48El14JLrwSHThkejCI9HFvycxp62JHhXWN3zsgv0fcUgKHZIXSxsbq1ipMCbFhOM80pK3U8qj30rzExhikZXs8kO1CkNkJkYZ5CTm6QYU2xJ0BQExUpKckSh1wPcsIkJkJOZhqNSgzgWERMhIZqNQk7qPkRIgITlcDX3ahJqRo+Eln4PQRrEMQRpeMhVdJLUzkCRhJY9qoItmJqIkrGQWmgTU+oAsBSfJq48mEasQpuAkp9AjJBRhCk4yDT1Cyr5HmoCTdECPmENIEzCSXPv9XZu5iBMwklteqBETiTgBIzmIFoZ+iBMwkiS0MDRFnICR7EQLQxPECRjJAbQw9ECcgJGcQwvDYMQJGMl9ubtrIuIEjCQ/ADViliNOwEiU/qgRk4o0ASdZjBohFfORJuAkt0veTGmYBc9KlBAUiWD2R14SjyIBwT+RpWAleS1QRbMBURJWouxAFUlL5rLLSEzdUEZgPIogDS9RnvmjTpvJiAmQkCgpor1lEHuy5CTKNnqxNPuCjAgpibK9Jjrt6fkKCSFyEuV0PbTaYBiXg3ExkhIlI8rxwvjvxiCHrERRjne1vTL+S7IxwiIvUZRrM7pULxJ4VYrY+ANHJdAjsZB1Mj42Nm5TuvyPggL0SL6lX96buCVmc0zivtTrn/ivuGLkJOa7W6MHNCqt/pgv1SBs0uqLcueMl/x6snZ0ZWLNG3yGrLyDnABOkhnXvSoaCYytFz5EmEIoMV+KKocqIcbwZOEOJpCYj/TS/qOiRcgewY1AS64OxHxJ2idjoiOUJHsO/2ltT8RLTLaHkJwJxkRd+CVguh2aEtN8b0zTiWGC5rasJfk+AnOcoO1TlKjRkGR2xASnCLqJGhWOkjedEXeSQEeLgyS3E8JOE/gAVcXYS0wRiLpAq9cos2IvmYegSwxFmRU7yXnqtUQf61AHbCV5bZByEd/nKCzCVrICIZcZjsIibCRfNf6VO4f3DVQWYiNJQMQNjEdlITaSdki4AR/1fza1JE3+GcWzHqUFqCWLMO4W1GtFLXHDYi8hQPU4VknMDTHuFrzuodaCSvKuDMbdQxJqLagkWX16u5NjqLWgviZ/DI9EFx6JLv4XiaL8Bndk+gTpY4kMAAAAAElFTkSuQmCC"))
                    return true;

                else
                    return false;

            }else{

                if(userPic.equals("background-image: url(\"https://www.askideas.com/media/15/Boxer-Dog-Funny-Face.jpg\");"))
                    return true;

                else
                    return false;
            }




        }catch (WebDriverException e){

            logger.error("Check if photo is of type ->"+picType+" Failed!. Exception: "+e.getMessage());

            Assert.assertTrue("Check if photo is of type ->"+picType+" Failed!. Exception: "+e.getMessage(),false);

            return false;

        }



    }

    String username_field_text="";

    public boolean checkUsername(String username){

        try{


                logger.info("Check if user name text value is ->"+username);

            WaitDriverUtility.waitForElementBeDisplayed(driver, username_textfield_By,30);

            username_field_text=driver.findElement(username_textfield_By).getText();

            if(username_field_text.toLowerCase().equals(username.toLowerCase()))
                return true;
            else {

                logger.error("Check if user name field value is failed: Expected: "+username+",Actual: "+username_field_text);

                return false;

            }

        }catch (WebDriverException e){

            logger.error("Check if user name field value is failed: Expected: "+username+",Actual: "+username_field_text+". Exception: "+e.getMessage());

            Assert.assertTrue("Check if user name field value is failed: Expected: "+username+",Actual: "+username_field_text+". Exception: "+e.getMessage(),false);

            return false;

        }



    }







}
