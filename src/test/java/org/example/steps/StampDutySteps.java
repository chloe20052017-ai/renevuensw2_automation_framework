package org.example.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StampDutySteps {
    private WebDriver driver() {
        return Hooks.getDriver();
    }

    private WebDriverWait driverWait() {
        return new WebDriverWait(driver(), Duration.ofSeconds(15));
    }

    @Given("I open the vehicle stamp duty page")
    public void iOpenTheVehicleStampDutyPage() {
        driver().get("https://www.service.nsw.gov.au/transaction/check-motor-vehicle-stamp-duty");
    }

    @When("I open the Revenue NSW calculator")
    public void iOpenTheRevenueNswCalculator() {
        WebElement checkOnline = driverWait().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[normalize-space()='Check online']")));
        checkOnline.click();
        driverWait().until(ExpectedConditions.urlContains("revenue.nsw.gov.au"));
    }

    @When("I select business use {string}")
    public void iSelectBusinessUse(String selection) {
        String normalized = selection.trim().toLowerCase();
        String label = normalized.equals("yes") ? "Yes" : "No";
        WebElement option = driverWait().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[normalize-space()='" + label + "']")));
        option.click();
    }

    @When("I enter vehicle amount {string}")
    public void iEnterVehicleAmount(String amount) {
        WebElement amountInput = driverWait().until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("input[inputmode='numeric'], input[type='text']")));
        amountInput.clear();
        amountInput.sendKeys(amount);
    }

    @When("I calculate the stamp duty")
    public void iCalculateTheStampDuty() {
        WebElement calculate = driverWait().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space()='Calculate']")));
        calculate.click();
    }

    @Then("the calculation popup is displayed")
    public void theCalculationPopupIsDisplayed() {
        WebElement dialog = driverWait().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@role='dialog' or @aria-modal='true']")));
        assertTrue(dialog.isDisplayed(), "Expected calculation popup to be visible.");
    }
}
