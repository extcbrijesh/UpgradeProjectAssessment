package com.upgradeQa.UI.Pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

/**
 * @author by brijesh on 11/4/21.
 */
public class ApprovedLoanOffersPage {

    public SelenideElement header = $("h2");

    public SelenideElement loanAmount = $x("//*[@id=\"root\"]/div/main/div/div[2]/div[1]/div/div[1]/div[1]/div[1]/div[2]/span[2]");

    public SelenideElement fasterMonthlyPayOffAmount = $x("//*[@id=\"root\"]/div/main/div/div[2]/div[1]/div/div[1]/div[1]/div[3]/div[1]/div/div/div[2]/div/div/ul/li[1]/div");

    public SelenideElement fasterMonthlyPayOffTerm = $x("//*[@id=\"root\"]/div/main/div/div[2]/div[1]/div/div[1]/div[1]/div[3]/div[1]/div/div/div[2]/div/div/ul/li[2]/div");

    public SelenideElement fasterMonthlyPayOffInterestRate = $x("//*[@id=\"root\"]/div/main/div/div[2]/div[1]/div/div[1]/div[1]/div[3]/div[1]/div/div/div[2]/div/div/ul/li[3]/div");

    public SelenideElement fasterMonthlyPayOffAPR = $x("//*[@id=\"root\"]/div/main/div/div[2]/div[1]/div/div[1]/div[1]/div[3]/div[1]/div/div/div[2]/div/div/ul/li[4]/div");

    public SelenideElement burgerMenu = $x("//*[@id=\"root\"]/div/main/div/header/div/label");

    public SelenideElement signOut = $x("//*[@id=\"root\"]/div/main/div/header/div/nav/ul/li[2]/a");
}
