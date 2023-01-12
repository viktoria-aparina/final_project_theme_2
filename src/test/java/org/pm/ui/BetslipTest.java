package org.pm.ui;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.pm.ui.pages.Bet;
import org.pm.ui.pages.BetslipPage;
import org.pm.ui.pages.HomePage;
import org.pm.ui.pages.SportPage;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BetslipTest extends BaseTest {

  @TmsLink("7")
  @Story("4-write-ui-autotests")
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

  @TmsLink("13")
  @Story("4-write-ui-autotests")
  @Description("UI: Removing all bets from the betslip using button \"Remove\"")
  @Test(groups = { "Aparina UI tests" })
  public void removeAllBetsFromBetslip() {
    BetslipPage betslipPage = new BetslipPage();
    SportPage sportPage = new HomePage().selectSport("soccer");
    assertSportPageOpened(sportPage);

    assertCoefficient(sportPage.selectEventAndOutcome(Map.of(Bet.P1, 4, Bet.P2, 3, Bet.X, 2)));

    betslipPage.clickRemoveButton().clickYesInNotificationMessage();
    assertThat(betslipPage.getEmptyAlert()).as("All bets weren't removed from the betslip" +
        "The text in alert is differ from expected").isEqualTo("Your betslip is empty");
  }

  @TmsLink("10")
  @Story("4-write-ui-autotests")
  @Description("Filling the field \"Bet total\" with valid data")
  @Test(groups = { "Riabtseva UI tests" })
  public void testBetWithValidData() {
    int eventIndex = 1;
    Bet bet = Bet.P1;

    BetslipPage betslipPage = new BetslipPage();

    new HomePage().selectSport("soccer").selectEventAndOutcome(Map.of(bet, eventIndex));

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

  @TmsLink("6")
  @Story("4-write-ui-autotests")
  @Description("Filling the field \"Bet total\" with invalid data")
  @Test(groups = { "Riabtseva UI tests" })
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

  @TmsLink("15")
  @Story("4-write-ui-autotests")
  @Description("Creating a parlay with valid data")
  @Test(groups = { "Volosiuk UI tests"})
  public void parlayWithValidDataTest() {
    String sportName = "soccer";
    int firstEventIndex = 0;
    Bet firstBet = Bet.P1;
    int secondEventIndex = 1;
    Bet secondBet = Bet.P2;

    BetslipPage betslipPage = new BetslipPage();

    SportPage sportPage = new HomePage().selectSport(sportName);
    assertSportPageOpened(sportPage);

    ArrayList<Double> coefficientsFromSportPage = sportPage
        .selectEventAndOutcome(Map.of(firstBet, firstEventIndex, secondBet, secondEventIndex));

    assertCoefficient(coefficientsFromSportPage);

    betslipPage.enterValueBet("20");
    assertThat(Math.round(betslipPage.getPossibleWin() * 100.) / 100.)
        .as("Coefficients in betslip and sport page are different")
        .isEqualTo(BetslipPage.calculatePossibleWinningAmount(BetslipPage.getValueBet(),
            coefficientsFromSportPage));

    betslipPage.clickPlaceBetButtonOnBetslip();
    assertAcceptedBet(betslipPage);
  }

  @TmsLink("16")
  @Story("4-write-ui-autotests")
  @Description("Creating a system with valid data")
  @Test(groups = { "Volosiuk UI tests"})
  public void systemWithValidDataTest() {
    String sportName = "soccer";
    int firstEventIndex = 0;
    Bet firstBet = Bet.P1;
    int secondEventIndex = 1;
    Bet secondBet = Bet.P2;
    int thirdEventIndex = 3;
    Bet thirdBet = Bet.X;

    BetslipPage betslipPage = new BetslipPage();

    SportPage sportPage = new HomePage().selectSport(sportName);
    assertSportPageOpened(sportPage);

    ArrayList<Double> coefficientsFromSportPage = sportPage
        .selectEventAndOutcome(Map.of(firstBet, firstEventIndex, secondBet, secondEventIndex,
            thirdBet, thirdEventIndex));

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
