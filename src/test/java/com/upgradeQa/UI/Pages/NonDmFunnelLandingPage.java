package com.upgradeQa.UI.Pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class NonDmFunnelLandingPage {

    public SelenideElement homePageHeader = $("h1");

    public SelenideElement submitButton = $("button");

    public SelenideElement loanPurposeSelect = $(byName("loan-purpose"));

    public SelenideElement desiredAmountInput = $("#aria-autogenerated-id3");

    public SelenideElement invalidLoanAmountNotification = $x("//*[@id=\"root\"]/div/main/div/div/div/div[2]/div[2]/div[2]/form/div/div/div[1]/div/div/div[2]");
}