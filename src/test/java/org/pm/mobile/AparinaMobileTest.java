package org.pm.mobile;

import io.qameta.allure.Description;
import org.assertj.core.api.Assertions;
import org.pm.mobile.pages.Bet;
import org.pm.mobile.pages.BetslipPage;
import org.pm.mobile.pages.LoginPage;
import org.pm.mobile.pages.SportPage;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AparinaMobileTest extends BaseTest {

    @Description("Mobile: Place a single bet with the smallest amount from proposed")
    @Test(groups = "Aparina MOBILE tests")
    public void addSingleBetWithSmallestAmountFromProposed() {
        new SportPage().clickSportTab();
        Assertions.assertThat(new SportPage().isSportTabOpened())
                .as("The tab \"Sport\" wasn't opened")
                .isTrue();

        ArrayList<Double> coefficientsFromSportPage = new SportPage().clickBasketballSport()
                .clickFilter12HButton()
                .clickEventAndOutcome(Map.of(Bet.P1, 3));
        assertThat(new BetslipPage().getCoefficientsFromBetslip())
                .as("Coefficients in betslip and sport page are different")
                .isEqualTo(coefficientsFromSportPage);

        new BetslipPage().clickSmallestProposedAmount().clickPlaceBetButton();
        assertThat(new BetslipPage().getSuccessAlert()).as("Single bet with smallest amount was created successfully! " +
                "The text in alert is differ from expected").isEqualTo("BET ACCEPTED");
    }
}
