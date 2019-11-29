package test;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"}
        ,features="src/test/resources/features/scan.feature"
        ,glue="test.cucumber.stepdefs"
//        ,tags = "@test"
        // Commented out line tags, allows user to test individual scenarios by adding a tag to said scenario.
    )

public class CucumberRunner {

}