package com.upgradeQa.UI.Pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;


public class IncomePage {

    public SelenideElement header = $("h1");

    public SelenideElement borrowerIncomeInput = $(byName("borrowerIncome"));

    public SelenideElement borrowerAdditionalIncomeInput = $(byName("borrowerAdditionalIncome"));

    public SelenideElement continueButtonByText = $(byText("Continue"));
}