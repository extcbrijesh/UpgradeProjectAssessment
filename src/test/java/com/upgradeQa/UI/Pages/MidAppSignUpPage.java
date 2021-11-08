package com.upgradeQa.UI.Pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class MidAppSignUpPage {

    public SelenideElement header = $("h1");

    public SelenideElement createAccountHeader = $("h2");

    public SelenideElement usernameInput = $(byName("username"));

    public SelenideElement username9Label = $("#username-9");

    public SelenideElement passwordInput = $(byName("password"));

    public SelenideElement password10Label = $("#password-10");

    public SelenideElement agreementsInput = $(byName("agreements"));

    public SelenideElement submitButton = $("button");
}