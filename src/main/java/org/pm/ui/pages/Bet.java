package org.pm.ui.pages;

public enum Bet {

    P1("//div[@data-id='outcome'][1]"),
    X("//div[@data-id='outcome'][2]"),
    P2("//div[@data-id='outcome'][3]");

    private final String locator;

    Bet(String locator) {
        this.locator = locator;
    }

    public String getLocator() {
        return locator;
    }
}

