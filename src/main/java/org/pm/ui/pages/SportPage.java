package org.pm.ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.TimeoutException;

import java.util.ArrayList;
import java.util.Map;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;

@Log4j2
public class SportPage extends BasePage {

    ElementsCollection events = $$(xpath("//div[@data-id='event-card-container-event']"));

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

    public ArrayList<Double> selectEventAndOutcome(Map<Bet, Integer> parameterCoefficients) {
        ArrayList<Double> coefficientsFromSportPage = new ArrayList<>();
        for (Map.Entry<Bet, Integer> entry : parameterCoefficients.entrySet()) {
                SelenideElement coefficient = events.get(entry.getValue()).find(xpath(entry.getKey().getLocator()));
                coefficient.shouldBe(enabled).click();
                coefficientsFromSportPage.add(Double.valueOf(coefficient.getText()));
            }

        log.info("The bet was chosen successfully");
        $(xpath("//div[@data-id='betslip-outcome-block']")).shouldBe(visible);
        return coefficientsFromSportPage;
    }

    public double getOddFromOutcome(int eventIndex, Bet bet) {
        String odd = $$(xpath("//div[@data-id='event-card-container-event']"))
                        .get(eventIndex)
                        .find(xpath(bet.getLocator())).shouldBe(visible)
                        .find(xpath(".//div[@data-id=\"animated-odds-value\"]//span")).getText();
        return Double.parseDouble(odd);
    }
}
