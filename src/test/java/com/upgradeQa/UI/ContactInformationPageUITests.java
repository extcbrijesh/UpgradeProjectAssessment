package com.upgradeQa.UI;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.upgradeQa.UI.Pages.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.open;

/**
 * @author by brijesh on 11/7/21.
 */
public class ContactInformationPageUITests extends NonDMFunnelLandingPage {

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        Configuration.reportsFolder = "test-result/reports";
        ChromeOptions chrome = new ChromeOptions();
        chrome.addArguments("--user-agent=Mozilla/5.0 (iPad; CPU OS 6_0 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10A5376e Safari/8536.25");
    }

    @BeforeEach
    public void setUp() {
        open("https://www.credify.tech/funnel/nonDMFunnel");

        homePage = new NonDmFunnelLandingPage();
        contactInformationPage = new ContactInformationPage();
        incomePage = new IncomePage();
        signUpPage = new MidAppSignUpPage();
        loginPage = new LoginPage();
        loanOfferPage = new ApprovedLoanOffersPage();
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
