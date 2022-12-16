package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features="src/test//resources/Features",
		tags="@tag2",
		glue= {"StepDefinitions","Hooks"},
		plugin={"html:target/cucumber-html-report","json:target/cucumber.json", "pretty:target/cucumber-pretty.txt"})


public class Runner extends AbstractTestNGCucumberTests {

}
