package org.pm.ui;

import org.pm.ui.pages.Bet;
import org.pm.ui.pages.HomePage;
import org.testng.annotations.Test;

public class BetslipTest extends BaseTest {

    @Test
    public void addSingleBetFromProposedBetsTest() {
        HomePage.open().clickOnLoginButton().chooseLoginTypeById()
                .loginWithTestCredentialId()
                .selectSport("soccer")
                .selectEventAndOutcome(1, Bet.P1)
                .chooseProposedAmountForBet()
                .clickPlaceBetButtonOnBetslip();
        //assertThat(new BetslipPage().getAlert()).as("Single bet was created successfully! " +
        //    "The text in alert is differ from expected").isEqualTo("Bet accepted");*/
    }
}
