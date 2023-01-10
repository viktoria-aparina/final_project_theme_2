package org.pm.mobile;

import org.assertj.core.api.Assertions;
import org.pm.mobile.pages.LoginPage;
import org.pm.mobile.pages.SportPage;
import org.testng.annotations.Test;

public class TestCaseScenarioTest extends BaseTest {

    @Test
    public void loginWindowTestWithFirstLoginPage() {
        new LoginPage().clickCloseButtonDisplayed();
        new SportPage().clickSportTabDisplayed();

        Assertions.assertThat(new SportPage().isSportTabOpened())
                .as("The tab \"Sport\" wasn't opened")
                .isTrue();

        new SportPage().clickP1BetFirstGame();

        Assertions.assertThat(new LoginPage().isLoginPageOpened())
                .as("The LoginPage wasn't opened")
                .isTrue();
    }
}
