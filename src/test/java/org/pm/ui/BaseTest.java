package org.pm.ui;

import static org.assertj.core.api.Assertions.assertThat;

import com.codeborne.selenide.Selenide;
import java.util.ArrayList;
import org.pm.ui.pages.BetslipPage;
import org.pm.ui.pages.HomePage;
import org.pm.ui.pages.SportPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
  @BeforeMethod
  public void loginWithTestCredentialsId() {
    HomePage.open()
        .clickOnLoginButton()
        .chooseLoginTypeById()
        .loginWithTestCredentialId();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() {
    Selenide.closeWebDriver();
  }

  public void assertSportPageOpened(SportPage sportPage) {
    assertThat(sportPage.isPageOpened()).as("The Sport page wasn't opened").isTrue();
  }

  public void assertCoefficient(ArrayList<Double> coefficientsFromSportPage) {
    assertThat(new BetslipPage().getCoefficientsFromBetslip())
        .as("Coefficients in betslip and sport page are different")
        .isEqualTo(coefficientsFromSportPage);
  }

  public void assertAcceptedBet(BetslipPage betslipPage) {
    assertThat(betslipPage.getSuccessAlert()).as("Single bet was created successfully! " +
        "The text in alert is differ from expected").isEqualTo("Bet accepted");
  }


}
