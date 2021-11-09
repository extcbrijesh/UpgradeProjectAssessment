package com.upgradeQa.UI;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.upgradeQa.UI.Enums.LoanPurposeEnums;
import com.upgradeQa.UI.Pages.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;

/**
 * @author by brijesh on 11/7/21.
 */
public class NonDMFunnelLandingPageUITests extends NonDMFunnelLandingPage {

    public static final Logger logger = LoggerFactory.getLogger(NonDMFunnelLandingPageUITests.class);

    public static final String LOAN_AMOUNT_SHOULD_BE_BETWEEN_1K_TO_50K = "Please enter loan amount between $1,000 and $50,000.";
    public static final String FIELD_IS_REQUIRED = "This field is required";

    @BeforeMethod
    public void setUp() {
        open(NON_DM_FUNNEL_LANDING_PAGE);
        Configuration.browserSize = "1280x800";
        Configuration.reportsFolder = "test-result/reports";
        ChromeOptions chrome = new ChromeOptions();
        chrome.addArguments("--user-agent=Mozilla/5.0 (iPad; CPU OS 6_0 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10A5376e Safari/8536.25");

        homePage = new NonDmFunnelLandingPage();
        contactInformationPage = new ContactInformationPage();
        incomePage = new IncomePage();
        signUpPage = new MidAppSignUpPage();
        loginPage = new LoginPage();
        loanOfferPage = new ApprovedLoanOffersPage();
    }

    @AfterMethod
    public void tearDown() {
        Selenide.closeWebDriver();
    }

    @Test
    public void loanAmountLessThan1000shouldBeRejected() {
        logger.info("Amount Less than 1000$ should be rejected");
        homePageInputData("999", LoanPurposeEnums.CREDIT_CARD.getValue());

        //then
        homePage.desiredAmountInput.shouldBe(value("999"));
        homePage.submitButton.click();

        homePage.invalidLoanAmountNotification.shouldBe(visible).shouldHave(text(LOAN_AMOUNT_SHOULD_BE_BETWEEN_1K_TO_50K));
    }

    @Test
    public void loanAmountMoreThan50KDefaultToMaxAllowedValue() {
        logger.info("Amount more than 50K should default to 50K");
        homePageInputData("50001", LoanPurposeEnums.CREDIT_CARD.getValue());

        //then
        homePage.desiredAmountInput.shouldNot(value("50001"));
        homePage.desiredAmountInput.shouldNot(value("999"));
        homePage.desiredAmountInput.shouldBe(value("50,000"));

    }

    @Test
    public void loanAmountMissingShouldBeRejected() {
        logger.info("Loan Amount not filled should give appropriate notification");
        homePageInputData("", LoanPurposeEnums.CREDIT_CARD.getValue());

        homePage.submitButton.click();
        homePage.invalidLoanAmountNotification.shouldBe(visible).shouldHave(text(FIELD_IS_REQUIRED));
    }

    @Test
    public void symbolsAndAlphabetsShouldBeIgnored() {
        logger.info("Symbols should be rejected from Text box for numbers");
        homePageInputData("#$%^asdaf", LoanPurposeEnums.CREDIT_CARD.getValue());

        homePage.desiredAmountInput.shouldBe(empty);

        homePage.submitButton.click();
        homePage.invalidLoanAmountNotification.shouldBe(visible).shouldHave(text(FIELD_IS_REQUIRED));
    }


    @Test
    public void decimalPointShouldBeIgnored() {
        logger.info("Non Whole Integers should be rejected / Ignored ");
        homePageInputData("5.00", LoanPurposeEnums.CREDIT_CARD.getValue());

        homePage.desiredAmountInput.shouldBe(value("500"));
        homePage.desiredAmountInput.shouldNotBe(value("5.00"));

        homePage.submitButton.click();
        homePage.invalidLoanAmountNotification.shouldBe(visible).shouldHave(text(LOAN_AMOUNT_SHOULD_BE_BETWEEN_1K_TO_50K));
    }
}
