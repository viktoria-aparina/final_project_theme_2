package org.pm.ui;

import io.qameta.allure.Description;
import org.pm.ui.pages.Bet;
import org.pm.ui.pages.BetslipPage;
import org.pm.ui.pages.HomePage;
import org.pm.ui.pages.SportPage;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BetslipTest extends BaseTest {

    @Description("UI: Place a single bet using proposed bet amount")
    @Test(groups = "Aparina UI tests")
    public void addSingleBetFromProposedBetsTest() {
        new HomePage().selectSport("soccer");
        assertThat(new SportPage().isPageOpened()).as("The Sport page wasn't opened").isTrue();

        ArrayList<Double> coefficientsFromSportPage = new SportPage().selectEventAndOutcome(Map.of(Bet.X, 2));
        assertThat(new BetslipPage().getCoefficientsFromBetslip())
                .as("Coefficients in betslip and sport page are different")
                .isEqualTo(coefficientsFromSportPage);

        new BetslipPage().chooseProposedAmountForBet().clickPlaceBetButtonOnBetslip();
        assertThat(new BetslipPage().getSuccessAlert()).as("Single bet was created successfully! " +
                "The text in alert is differ from expected").isEqualTo("Bet accepted");
    }

    @Description("UI: Removing all bets from the betslip using button \"Remove\"")
    @Test(groups = "Aparina UI tests")
    public void removeAllBetsFromBetslip() {
        new HomePage().selectSport("soccer");
        assertThat(new SportPage().isPageOpened()).as("The Sport page wasn't opened").isTrue();

        ArrayList<Double> coefficientsFromSportPage = new SportPage().selectEventAndOutcome(Map.of(Bet.P1, 4, Bet.P2, 3));
        assertThat(new BetslipPage().getCoefficientsFromBetslip())
                .as("Coefficients in betslip and sport page are different")
                .isEqualTo(coefficientsFromSportPage);

        new BetslipPage().clickRemoveButton().clickYesInNotificationMessage();
        assertThat(new BetslipPage().getEmptyAlert()).as("All bets weren't removed from the betslip" +
                "The text in alert is differ from expected").isEqualTo("Your betslip is empty");
    }
}
