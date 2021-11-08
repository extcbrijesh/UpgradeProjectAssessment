package com.upgradeQa.UI;

import com.upgradeQa.UI.Pages.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.screenshot;

/**
 * @author by brijesh on 11/4/21.
 */
public class NonDMFunnelLandingPage {

    public static final String PERSONAL_LOANS_UP_TO_50K = "Personal Loans up to $50,000";
    // TODO : Move PAY_OFF_CREDIT_CARDS to its own LOAN_PURPOSE ENUM class
    //public static final String PAY_OFF_CREDIT_CARDS = "Pay off Credit Cards";
    public static final String PERSONAL_PAGE_HEADER = "Let's get started with some basic information";
    public static final String INCOME_PAGE_HEADER = "How much money do you make in a year?";

    NonDmFunnelLandingPage homePage;
    ContactInformationPage contactInformationPage;
    IncomePage incomePage;
    MidAppSignUpPage signUpPage;
    LoginPage loginPage;
    ApprovedLoanOffersPage loanOfferPage;


    void homePageInputData(String loanAmount, String payOffCreditCards) {
        homePage.homePageHeader.shouldHave(text(PERSONAL_LOANS_UP_TO_50K));
        homePage.desiredAmountInput.setValue(loanAmount);
        homePage.loanPurposeSelect.selectOption(payOffCreditCards);
        screenshot("home_page_app_filled");
    }

}
