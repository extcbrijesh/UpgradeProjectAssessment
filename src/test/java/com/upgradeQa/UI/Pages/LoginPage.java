package com.upgradeQa.UI.Pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage {

    public SelenideElement secondaryHeader = $("h2");

    public SelenideElement usernameInput = $(byName("username"));

    public SelenideElement passwordInput = $(byName("password"));

    public SelenideElement signInButton = $x("//button[@type='submit']");
}