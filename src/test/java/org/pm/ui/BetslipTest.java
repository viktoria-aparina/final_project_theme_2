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
    BetslipPage betslipPage = new BetslipPage();
    SportPage sportPage = new HomePage().selectSport("soccer");
    assertSportPageOpened(sportPage);

    assertCoefficient(sportPage.selectEventAndOutcome(Map.of(Bet.X, 2)));

    betslipPage.chooseProposedAmountForBet().clickPlaceBetButtonOnBetslip();
    assertAcceptedBet(betslipPage);

  }

  @Description("UI: Removing all bets from the betslip using button \"Remove\"")
  @Test(groups = "Aparina UI tests")
  public void removeAllBetsFromBetslip() {
    BetslipPage betslipPage = new BetslipPage();
    SportPage sportPage = new HomePage().selectSport("soccer");
    assertSportPageOpened(sportPage);

    assertCoefficient(sportPage.selectEventAndOutcome(Map.of(Bet.P1, 4, Bet.P2, 3, Bet.X, 2)));

    betslipPage.clickRemoveButton().clickYesInNotificationMessage();
    assertThat(betslipPage.getEmptyAlert()).as("All bets weren't removed from the betslip" +
        "The text in alert is differ from expected").isEqualTo("Your betslip is empty");
  }

  @Description("C10.Filling the field \"Bet total\" with valid data")
  @Test(groups = "Riabtseva UI tests")
  public void testBetWithValidData() {
    int eventIndex = 1;
    Bet bet = Bet.P1;

    BetslipPage betslipPage = new BetslipPage();

    new HomePage().selectSport("soccer")
        .selectEventAndOutcome(Map.of(bet, eventIndex));

    double oddFromOutcome = new SportPage().getOddFromOutcome(eventIndex, bet);
    double oddFromBetSlip = betslipPage.getOddsInBetslip().get(0);

    assertThat(oddFromOutcome).isEqualTo(oddFromBetSlip);

    for (double betAmount : new double[] {20., 21., 45.76}) {
      betslipPage.enterValueBet(String.valueOf(betAmount));
      assertThat(betslipPage.isPlaceBetButtonEnabled()).isTrue();

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
        .selectEventAndOutcome(Map.of(bet, eventIndex));

    double oddFromOutcome = new SportPage().getOddFromOutcome(eventIndex, bet);
    double oddFromBetSlip = betslipPage.getOddsInBetslip().get(0);

    assertThat(oddFromOutcome).isEqualTo(oddFromBetSlip);

    for (String betAmount : new String[] {"aaa", "!@#$%^&*()_+", "", "9"}) {
      betslipPage.enterValueBet(betAmount);
      assertThat(betslipPage.isPlaceBetButtonEnabled()).isFalse();
    }
  }

  @Description("UI: Creating a parlay with valid data")
  @Test(groups = "Volosiuk UI tests")
  public void parlayWithValidDataTest() {
    BetslipPage betslipPage = new BetslipPage();

    SportPage sportPage = new HomePage().selectSport("soccer");
    assertSportPageOpened(sportPage);

    ArrayList<Double> coefficientsFromSportPage = sportPage
        .selectEventAndOutcome(Map.of(Bet.P1, 0, Bet.P2, 1));

    assertCoefficient(coefficientsFromSportPage);

    betslipPage.enterValueBet("20");
    assertThat(Math.round(betslipPage.getPossibleWin() * 100.) / 100.)
        .as("Coefficients in betslip and sport page are different")
        .isEqualTo(BetslipPage.calculatePossibleWinningAmount(BetslipPage.getValueBet(),
            coefficientsFromSportPage));

    betslipPage.clickPlaceBetButtonOnBetslip();
    assertAcceptedBet(betslipPage);
  }

  @Description("UI: Creating a system with valid data")
  @Test(groups = "Volosiuk UI tests")
  public void systemWithValidDataTest() {
    BetslipPage betslipPage = new BetslipPage();

    SportPage sportPage = new HomePage().selectSport("soccer");
    assertSportPageOpened(sportPage);

    ArrayList<Double> coefficientsFromSportPage = sportPage
        .selectEventAndOutcome(Map.of(Bet.P1, 0, Bet.P2, 1, Bet.X, 2));

    assertCoefficient(coefficientsFromSportPage);

    betslipPage.enterValueBet("20").clickSystemButton();
    assertThat(Math.round(betslipPage.getPossibleWin() * 100.) / 100.)
        .as("Coefficients in betslip and sport page are different")
        .isEqualTo(BetslipPage.calculatePossibleSystemWinningAmount(BetslipPage.getValueBet(),
            coefficientsFromSportPage));

    betslipPage.clickPlaceBetButtonOnBetslip();
    assertAcceptedBet(betslipPage);
  }
}
