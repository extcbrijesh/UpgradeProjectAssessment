package com.upgradeQa.UI.Enums;

/**
 * @author by brijesh on 11/7/21.
 */
public enum LoanPurposeEnums {
    CREDIT_CARD("Pay off Credit Cards"),
    DEBT_CONSOLIDATION("Pay off Credit Cards"),
    SMALL_BUSINESS("Pay off Credit Cards"),
    HOME_IMPROVEMENT("Pay off Credit Cards"),
    LARGE_PURCHASE("Pay off Credit Cards"),
    OTHER("Pay off Credit Cards");

    final String value;

    LoanPurposeEnums(String value) {
        this.value = value;
    }

    public String getValue() {return this.value;}
}