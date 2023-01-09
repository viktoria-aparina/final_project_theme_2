package org.pm.ui.pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.TimeoutException;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;

@Log4j2
public class SportPage extends BasePage {
    @Override
    public boolean isPageOpened() {
        try {
            $(id("line-holder")).shouldBe(visible);
            log.info("The SportPage was opened successfully");
            return true;
        } catch (TimeoutException exception) {
            log.error("The SportPage wasn't open because of error {}", exception.getCause());
            return false;
        }
    }

    public BetslipPage selectEventAndOutcome(int eventIndex, Bet bet) {
        $$(xpath("//div[@data-id='event-card-container-event']"))
                .get(eventIndex)
                .find(xpath(bet.getLocator())).shouldBe(visible)
                .click();
        log.info("The bet was chosen successfully");
        $(xpath("//div[@data-id='betslip-outcome-block']")).shouldBe(visible);
        return new BetslipPage();
    }

    public double getOddFromOutcome(int eventIndex, Bet bet) {
        String odd = $$(xpath("//div[@data-id='event-card-container-event']"))
                        .get(eventIndex)
                        .find(xpath(bet.getLocator())).shouldBe(visible)
                        .find(xpath(".//div[@data-id=\"animated-odds-value\"]//span")).getText();
        return Double.parseDouble(odd);
    }
}
