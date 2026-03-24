package com.renz.hooks;

import com.renz.driver.DriverManager;
import com.renz.utils.DBUtils;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    // ThreadLocal ensures each parallel test has its own Scenario object
    private static ThreadLocal<Scenario> scenarioThread = new ThreadLocal<>();

    // Static counters to track overall progress across all parallel threads
    private static int passed = 0;
    private static int failed = 0;

    // ANSI Colors
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String PURPLE = "\u001B[35m"; // color for Feature name


    public static Scenario getScenario() {
        return scenarioThread.get(); // Allow Step Defs to "grab" the current scenario
    }

    @Before
    public void setUp(Scenario scenario) {
        scenarioThread.set(scenario); // Store the scenario for this thread
        DriverManager.initDriver(); // Starts a fresh browser before every Scenario
    }


    @After(order = 1)
    public void logScenarioResult(Scenario scenario) {

        //Extract Feature Name from the URI (File Path)
        String featurePath = scenario.getUri().toString();
        String featureName = featurePath.substring(featurePath.lastIndexOf("/") + 1).replace(".feature", "");

        String scenarioName = scenario.getName();
        String status = scenario.getStatus().toString();
        String color = scenario.isFailed() ? RED : GREEN;

        // 1. Update the static counters
        if (scenario.isFailed()) {
            failed++;
            // 2. Attach screenshot to JSON for Masterthought
            byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failed_Screenshot");
        } else {
            passed++;
        }

        // 3. Live Log with Colors
        System.out.println(color + "-------------------------------------------------------" + RESET);
        System.out.println(PURPLE + " FEATURE : " + featureName.toUpperCase() + RESET);
        System.out.println(color + " SCENARIO: " + scenarioName + RESET);
        System.out.println(color + " RESULT  : " + status + RESET);
        System.out.println(color + "-------------------------------------------------------" + RESET);


    }


    @After(order = 0)
    public void tearDown(Scenario scenario) {
        // Always close the browser to free up memory
        DriverManager.quitDriver();
        scenarioThread.remove(); // Clean up thread memory
        DBUtils.closeConnection();
    }

    @AfterAll
    public static void printSummary() {
        int total = passed + failed;
        String reportPath = System.getProperty("user.dir") + "/target/masterthought-report/cucumber-html-reports/overview-features.html";

        System.out.println(YELLOW + "\n===============================================");
        System.out.println("          OVERALL TEST EXECUTION SUMMARY");
        System.out.println("===============================================");
        System.out.println(" TOTAL SCENARIOS : " + total);
        System.out.print(RESET + GREEN + " PASSED          : " + passed + "\n" + RESET);
        System.out.print(RED + " FAILED          : " + failed + "\n" + RESET);
        System.out.println(YELLOW + "-----------------------------------------------");
        System.out.println(" MASTERTHOUGHT REPORT LINK:");
        System.out.println(RESET + " file://" + reportPath.replace("\\", "/"));
        System.out.println(YELLOW + "===============================================\n" + RESET);
    }
}
