package com.renz.common.website;

import com.renz.hooks.Hooks;
import io.cucumber.java.en.Then;

public class CommonStepDefs {
    BasePage basePage = new BasePage();

    @Then("I take a screenshot for evidence")
    public void i_take_a_screenshot_for_evidence() {
        // 1. Get bytes from BasePage
        byte[] screenshot = basePage.captureScreenshot();
        // 2. Attach to the report using the Scenario stored in Hooks
        Hooks.getScenario().attach(screenshot, "image/png", "Manual_Evidence");
    }
}
