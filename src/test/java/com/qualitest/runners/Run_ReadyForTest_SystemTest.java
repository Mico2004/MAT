package com.qualitest.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;




/*@RunWith(ExtendedCucumber.class)
@ExtendedCucumberOptions(jsonReport = "target/cucumber.json",
		retryCount = 3,
		detailedReport = true,
		detailedAggregatedReport = true,
		overviewReport = true,
		//coverageReport = true,
		jsonUsageReport = "target/cucumber-usage.json",
		usageReport = true,
		toPDF = true,
		excludeCoverageTags = {"@flaky" },
		includeCoverageTags = {"@passed" },
		outputFolder = "target")
@CucumberOptions(plugin = { "html:target/cucumber-html-report",
		"json:target/cucumber.json", "pretty:target/cucumber-pretty.txt",
		"usage:target/cucumber-usage.json", "junit:target/cucumber-results.xml" },
		features = { "./src/test/java/com/github/mkolisnyk/cucumber/features" },
		glue = { "com/github/mkolisnyk/cucumber/steps" },
		tags = {"@consistent"})*/

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/com/qualitest/features"},
        glue = {"com.qualitest.stepDefinitions"},
        monochrome = true,	//	this removes the gibberish (ansi characters)
        //	use this to get the automated step definitions
        strict=true,
        tags = {"@rft"},
        plugin = {
                "ru.yandex.qatools.allure.cucumberjvm.AllureReporter",

        }
)


public class Run_ReadyForTest_SystemTest {

/*	Logger logger = Logger.getLogger(Run_ReadyForTest.class);
	public static DesiredCapabilities caps = DesiredCapabilities.chrome();


	@Parameters({"operationsystem","browser","browserversion"})
	@BeforeClass
	public void Before(String operationsystem, String browser, String   browserversion){

		System.setProperty("operationsystem",operationsystem);

		System.setProperty("browser",browser);

		System.setProperty("browserversion",browserversion);



	}



	@AfterClass
	public void tearDown() throws Exception {
		try {

			ITestResult result = Reporter.getCurrentTestResult();

			logger.info("TestNG Result:" + result.isSuccess());


			BeforeFeature.driver.close();

		}catch(Exception e){

			logger.fatal("TestNG after class failed: "+e.getMessage());

		}


	}*/
}