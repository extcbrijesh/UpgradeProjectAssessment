package com.upgradeQa.UI.Pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class ContactInformationPage {

    public SelenideElement headerTag = $("h1");

    public SelenideElement individualLabel = $("label[data-checked='true']");

    public SelenideElement jointApplicationLabel = $("label[data-checked='false']");

    public SelenideElement borrowerFirstNameInput = $(byName("borrowerFirstName"));

    public SelenideElement borrowerLastNameInput = $(byName("borrowerLastName"));

    public SelenideElement borrowerDateOfBirthInput = $(byName("borrowerDateOfBirth"));

    public SelenideElement borrowerStreetAddress = $(By.cssSelector("input#geosuggest__input--borrowerStreet"));

    public SelenideElement borrowerCityInput = $(byName("borrowerCity"));

    public SelenideElement borrowerStateInput = $(byName("borrowerState"));

    public SelenideElement borrowerZipCodeInput = $(byName("borrowerZipCode"));

    public SelenideElement continueButton = $(byText("Continue"));

    public SelenideElement suggestion = $("#geosuggest__list--borrowerStreet");
}