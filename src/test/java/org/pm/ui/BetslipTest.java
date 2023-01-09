package org.pm.ui;

import io.qameta.allure.Description;
import org.pm.ui.pages.Bet;
import org.pm.ui.pages.BetslipPage;
import org.pm.ui.pages.HomePage;
import org.pm.ui.pages.SportPage;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BetslipTest extends BaseTest {

    @Description("UI: Place a single bet using proposed bet amount")
    @Test(groups = "Aparina UI tests")
    public void addSingleBetFromProposedBetsTest() {
        new HomePage().selectSport("soccer")
                .selectEventAndOutcome(1, Bet.P1)
                .chooseProposedAmountForBet()
                .clickPlaceBetButtonOnBetslip();
        /*assertThat(new BetslipPage().getAlert()).as("Single bet was created successfully! " +
          "The text in alert is differ from expected").isEqualTo("Bet accepted");*/
    }

    @Description("C10.Filling the field \"Bet total\" with valid data")
    @Test(groups = "Riabtseva UI tests")
    public void testBetWithValidData() {
        int eventIndex = 1;
        Bet bet = Bet.P1;
        BetslipPage betslipPage = new BetslipPage();

        new HomePage().selectSport("soccer")
                .selectEventAndOutcome(eventIndex, bet);

        double oddFromOutcome = new SportPage().getOddFromOutcome(eventIndex, bet);
        double oddFromBetSlip = betslipPage.getOddsInBetslip().get(0);

        assertThat(oddFromOutcome).isEqualTo(oddFromBetSlip);

        for (double betAmount : new double[]{20., 21., 45.76}) {
            betslipPage.enterValueBet(String.valueOf(betAmount));
            assertThat(betslipPage.isPlaceBetButtonEnabled()).isEqualTo(true);

            double possibleWin = betslipPage.getPossibleWin();
            double expectedPossibleWin = Math.round(betAmount * oddFromBetSlip * 100.) / 100.;

            assertThat(possibleWin).isEqualTo(expectedPossibleWin);
        }

        betslipPage.clickPlaceBetButtonOnBetslip();

    }

    @Description("C6.Filling the field \"Bet total\" with invalid data")
    @Test(groups = "Riabtseva UI tests")
    public void testBetWithInvalidData() {
        int eventIndex = 0;
        Bet bet = Bet.X;
        BetslipPage betslipPage = new BetslipPage();

        new HomePage().selectSport("basketball")
                .selectEventAndOutcome(eventIndex, bet);

        double oddFromOutcome = new SportPage().getOddFromOutcome(eventIndex, bet);
        double oddFromBetSlip = betslipPage.getOddsInBetslip().get(0);

        assertThat(oddFromOutcome).isEqualTo(oddFromBetSlip);

        for (String betAmount : new String[]{"aaa", "!@#$%^&*()_+", "", "9"}) {
            betslipPage.enterValueBet(betAmount);
            assertThat(betslipPage.isPlaceBetButtonEnabled()).isEqualTo(false);
        }
    }
}
