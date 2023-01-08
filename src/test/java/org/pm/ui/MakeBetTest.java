package org.pm.ui;

import org.pm.ui.pages.Parimatch;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MakeBetTest {
    final static int eventIndex = 1;
    final static int outcomeIndex = 1;
    Parimatch pm = new Parimatch();

    // Preconditions
    @BeforeMethod
    public void openAndLogIn() {
        pm.open();

//        pm.loginFromStartPage();
//
//        // 10 sec to manually enter CAPTCHA
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        if (!pm.checkIfLoggedIn()) {
//            pm.enterCredentials();
//        }
    }

    // Postconditions
    @AfterMethod
    public void close() {
        pm.close();
    }

    //C5 test
    @Test
    public void testBetWithValidData() {

        // Step 1-2

        pm.selectSport("soccer")
                .clickOutcome(eventIndex, outcomeIndex);

        // Step 4
        double oddFromOutcome = pm.getOutcomeCoefficient(eventIndex, outcomeIndex);
        double oddFromBetSlip = pm.getIndividualOdds().get(0);

        assertThat(oddFromOutcome).isEqualTo(oddFromBetSlip);

        // Step 3
        for (double betAmount: new double[]{20., 21., 45.76, 999999999}) {
            pm.editSumOfBet(String.valueOf(betAmount));
            assertThat(pm.isPlaceBetButtonEnabled()).isEqualTo(true);

//            double possibleWin = pm.getPossibleWin();

//            assertThat(possibleWin).isEqualTo(betAmount * oddFromBetSlip);
        }

        // Step 6
//        pm.clickPlaceBetButton();
    }

    @Test
    public void testBetWithInvalidData() {
        pm.selectSport("soccer").clickOutcome(eventIndex, outcomeIndex);

        double oddFromOutcome = pm.getOutcomeCoefficient(eventIndex, outcomeIndex);
        double oddFromBetSlip = pm.getIndividualOdds().get(0);
        assertThat(oddFromOutcome).isEqualTo(oddFromBetSlip);

        for (String betAmount: new String[]{"aaa", "!@#$%^&*()_+", "-35", "", "19"}) {
            pm.editSumOfBet(betAmount);
            assertThat(pm.isPlaceBetButtonEnabled()).isEqualTo(false);
        }
    }
}
