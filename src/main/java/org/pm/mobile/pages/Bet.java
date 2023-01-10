package org.pm.mobile.pages;

public enum Bet {
    P1("(*//android.widget.LinearLayout[contains(@content-desc, 'outcome_1')])"),
    P2("(*//android.widget.LinearLayout[contains(@content-desc, 'outcome_2')])"),
    X("(*//android.widget.LinearLayout[contains(@content-desc, 'outcome_3')])");

    private final String locator;

    Bet(String locator) {
        this.locator = locator;
    }

    public String getLocator() {
        return locator;
    }
}
