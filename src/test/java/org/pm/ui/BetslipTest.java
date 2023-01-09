package org.pm.ui;

import io.qameta.allure.Description;
import org.pm.ui.pages.Bet;
import org.pm.ui.pages.HomePage;
import org.testng.annotations.Test;

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
}
