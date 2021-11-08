package com.upgradeQa.UI;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.upgradeQa.UI.DataModel.LoanData;
import com.upgradeQa.UI.Enums.LoanPurposeEnums;
import com.upgradeQa.UI.Pages.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.screenshot;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DMFunnelUITests extends NonDMFunnelLandingPage {

    public static final String PASSWORD = "1myPassWord!@#$";

    // Given Test Data Set which can be moved to its own resource.
    String AcceptedLoanAmount = "2000";
    String borrowerIncome = "120000";
    String additionalIncome = "5000";

    @BeforeClass
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        Configuration.reportsFolder = "test-result/reports";
        ChromeOptions chrome = new ChromeOptions();
        chrome.addArguments("--user-agent=Mozilla/5.0 (iPad; CPU OS 6_0 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10A5376e Safari/8536.25");
    }

    @BeforeTest
    public void setUp() {
        open("https://www.credify.tech/funnel/nonDMFunnel");

        homePage = new NonDmFunnelLandingPage();
        contactInformationPage = new ContactInformationPage();
        incomePage = new IncomePage();
        signUpPage = new MidAppSignUpPage();
        loginPage = new LoginPage();
        loanOfferPage = new ApprovedLoanOffersPage();
    }

    @Test
    public void individualApp_HappyPathTest() {
        String userEmail = createUniqueEmailForUserName();

        // landing page / home page function
        homePageInputData(AcceptedLoanAmount, LoanPurposeEnums.CREDIT_CARD.getValue());
        homePage.submitButton.click();

        // validate personal information page appeared.
        fillPersonalPageForIndividualApplication(); // all the values in this function can we provided from resource file

        // income page
        incomePageDataFeed(borrowerIncome, additionalIncome);

        //verify sign up page
        signUpToSeeOffer(userEmail);

        // NOTE : When you run locally currently its asking to confirm that we are not ROBOT.
        //TODO: Fix when we run locally "check your rate" should not ask to verify as HUMAN and Not ROBOT.

        // Capture Offer page and Sign Out
        LoanData loanData = new LoanData();
        offerPageLoadDataCapture(loanData);

        // Re-login page and Validate data on Offer Page
        reLogInAndValidateLoanOfferData(userEmail, loanData);
    }

    private void reLogInAndValidateLoanOfferData(String userEmail, LoanData loanData) {
        open("https://www.credify.tech/portal/login");
        SelenideElement selenideElement = loginPage.secondaryHeader.shouldBe(visible);
        selenideElement.shouldHave(text("Welcome Back!"));

        loginPage.usernameInput.sendKeys(userEmail);
        loginPage.passwordInput.sendKeys(PASSWORD);

        loginPage.signInButton.shouldBe(enabled);
        loginPage.signInButton.should(have(text("Sign in to your account")));
        loginPage.signInButton.pressEnter();

        loanOfferPage.header.shouldBe(visible).shouldHave(text("You qualify for a discount on your debt payoff loan!"), Duration.ofSeconds(30));

        assertEquals(loanData.getLoanAmount(), loanOfferPage.loanAmount.shouldBe(visible).shouldNot(empty).getText());
        assertEquals(loanData.getTerm(), loanOfferPage.fasterMonthlyPayOffTerm.shouldBe(visible).shouldNot(empty).getText());
        assertEquals(loanData.getApr(), loanOfferPage.fasterMonthlyPayOffAPR.shouldBe(visible).shouldNot(empty).getText());
        assertEquals(loanData.getInterestRate(), loanOfferPage.fasterMonthlyPayOffInterestRate.shouldBe(visible).shouldNot(empty).getText());
    }

    private void offerPageLoadDataCapture(LoanData loanData) {
        loanOfferPage.header.shouldBe(visible).shouldHave(text("You qualify for a discount on your debt payoff loan!"), Duration.ofSeconds(40));
        loanData.setLoanAmount(loanOfferPage.loanAmount.shouldBe(visible).shouldNot(empty).getText());
        loanData.setTerm(loanOfferPage.fasterMonthlyPayOffTerm.shouldBe(visible).shouldNot(empty).getText());
        loanData.setInterestRate(loanOfferPage.fasterMonthlyPayOffInterestRate.shouldBe(visible).shouldNot(empty).getText());
        loanData.setApr(loanOfferPage.fasterMonthlyPayOffAPR.shouldBe(visible).shouldNot(empty).getText());

        printLoanDataValues(loanData);

        loanOfferPage.burgerMenu.shouldBe(visible).click();
        loanOfferPage.signOut.shouldBe(visible).click();
    }

    /**
     * Logging for information purposes only.
     * @param loanData
     */
    private void printLoanDataValues(LoanData loanData) {
        Logger logger = LoggerFactory.getLogger(DMFunnelUITests.class);

        logger.info("loan Amount " + loanData.getLoanAmount());
        logger.info("term " + loanData.getTerm());
        logger.info("apr " + loanData.getApr());
        logger.info("Interest Rate " + loanData.getInterestRate());
    }

    private void signUpToSeeOffer(String userEmail) {
        signUpPage.createAccountHeader.shouldBe(visible).shouldHave(text("Create an account"));
        boolean has = signUpPage.header.shouldBe(visible).has(text("Last step before you get your rate"));
        assertTrue(has);

        signUpPage.usernameInput.sendKeys(userEmail);
        SelenideElement passwordInput = signUpPage.passwordInput;
        passwordInput.sendKeys(PASSWORD);
        passwordInput.pressTab();

        signUpPage.agreementsInput.shouldBe(enabled);
        assertTrue(signUpPage.agreementsInput.isEnabled());

        signUpPage.agreementsInput.sendKeys(Keys.SPACE);

        Assertions.assertTrue(signUpPage.submitButton.exists());
        Assertions.assertTrue(signUpPage.submitButton.has(text("Check Your Rate")));
        screenshot("login_page_filled");
        signUpPage.submitButton.pressEnter();
    }

    private void incomePageDataFeed(String borrowerIncome, String additionalIncome) {
        // Verify income page
        incomePage.header.shouldBe(visible).shouldHave(text(INCOME_PAGE_HEADER));

        incomePage.borrowerIncomeInput.setValue(borrowerIncome);
        incomePage.borrowerAdditionalIncomeInput.setValue(additionalIncome);

        Assertions.assertTrue(incomePage.continueButtonByText.exists());
        Assertions.assertTrue(incomePage.continueButtonByText.has(text("Continue")));
        screenshot("income_page_filled");
        incomePage.continueButtonByText.pressEnter();
    }

    private void fillPersonalPageForIndividualApplication() {
        String selectedText = contactInformationPage.headerTag.getText();
        Assertions.assertEquals(selectedText, PERSONAL_PAGE_HEADER, "Does not match personal Page Header/ Title");

        contactInformationPage.individualLabel.shouldBe(enabled);

        SelenideElement borrowerFirstNameInput = contactInformationPage.borrowerFirstNameInput;
        borrowerFirstNameInput.click();
        borrowerFirstNameInput.setValue("Namey");

        SelenideElement borrowerLastNameInput = contactInformationPage.borrowerLastNameInput;
        borrowerLastNameInput.click();
        borrowerLastNameInput.setValue("Namerson");

        SelenideElement borrowerAddress = contactInformationPage.borrowerStreetAddress;
        borrowerAddress.sendKeys("91 Market St");

        SelenideElement borrowerCityName = contactInformationPage.borrowerCityInput;
        borrowerCityName.click();
        borrowerCityName.setValue("San Francisco");

        SelenideElement borrowerStateInput = contactInformationPage.borrowerStateInput;
        borrowerStateInput.sendKeys("CA");

        SelenideElement borrowerZipCodeInput = contactInformationPage.borrowerZipCodeInput;
        borrowerAddress.should(visible).pressTab();
        borrowerZipCodeInput.setValue("95043");

        SelenideElement borrowerDateOfBirthInput = contactInformationPage.borrowerDateOfBirthInput;
        borrowerDateOfBirthInput.click();
        borrowerDateOfBirthInput.setValue("01011999"); // as of now bug exist

        assertTrue(contactInformationPage.continueButton.exists());
        assertTrue(contactInformationPage.continueButton.has(text("Continue")));
        screenshot("personal_page_filled");
        contactInformationPage.continueButton.click();
    }

    private String createUniqueEmailForUserName() {
        return "brijesh+" + (int) Math.floor(Math.random() * 1000000) + "@upgrade-challenge.com";
    }
}
