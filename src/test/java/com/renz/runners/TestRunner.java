package com.renz.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features/website/saucelabdemo", // Path to feature files
        glue = {"com.renz.projects.website.saucelabdemo.stepdefinitions", "com.renz.common.website", "com.renz.hooks"},// Package name for step definitions
        plugin = {"pretty",
                "html:target/cucumber-reports/report.html",
                "json:target/cucumber-reports/cucumber.json"},
        monochrome = true
//        tags = "@SmokeTest" // Optional: run scenarios with specific tags
)
public class TestRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true) // This enables parallel execution of scenarios
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
