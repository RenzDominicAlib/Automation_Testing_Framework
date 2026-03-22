package com.renz.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/website/saucelabdemo", // Path to feature files
        glue = "com.renz.projects.website.saucelabdemo.stepdefinitions",// Package name for step definitions
        plugin = {"pretty", "html:target/cucumber-reports.html", "json:target/cucumber-reports/Cucumber.json"},
        monochrome = true
//        tags = "@SmokeTest" // Optional: run scenarios with specific tags
)
public class TestRunnerSauceLab extends AbstractTestNGCucumberTests {
    // This class will be executed by TestNG. No additional methods are strictly necessary for basic execution.
}
