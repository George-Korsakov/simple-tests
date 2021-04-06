package ru.experimental.selenidetest.simpletest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.hasWebDriverStarted;

public class TestBase {

    @BeforeAll
    static void setUp() {
        Configuration.fastSetValue = false;
        if (hasWebDriverStarted()) {
            clearBrowserCookies();
            clearBrowserLocalStorage();
            refresh();
        }
        else {
           // open("https://todomvc.com/examples/react/#/completed");
            open("https://www.globalsqa.com/angularJs-protractor/BankingProject");
            sleep(500);
        }
    }

    @AfterAll
    static  void tearDown() {
        clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();

    }

    public void loginBank() {
        open("https://www.globalsqa.com/angularJs-protractor/BankingProject");

    }
}
