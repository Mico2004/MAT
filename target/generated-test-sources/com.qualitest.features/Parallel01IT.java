import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        features = {"C:/Users/mickaele/Google Drive/Main Workspace/MATAutomation/src/test/java/com/qualitest/features/CreateNew.feature:4"},
        plugin = {"json:C:/Users/mickaele/Google Drive/Main Workspace/MATAutomation/target/cucumber-parallel/1.json"},
        monochrome = true,
        glue = {"com.qualitest.com.qualitest.stepDefinitions"})
public class Parallel01IT {
}
