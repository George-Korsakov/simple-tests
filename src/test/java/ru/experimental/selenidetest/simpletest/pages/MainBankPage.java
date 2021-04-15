package ru.experimental.selenidetest.simpletest.pages;

import org.openqa.selenium.WebElement;
import ru.experimental.selenidetest.simpletest.TestBase;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainBankPage extends TestBase {

    WebElement buttonManager = $(byXpath(".//button[@ng-click='manager()']")); // кнопка логина Менеджером
    WebElement buttonClient = $(byXpath(".//button[@ng-click='customer()']")); // кнопка логина Клиентом
    WebElement buttonHome = $("button[class='btn home']"); // кнопка Домой
    WebElement mainHeader = $(byXpath(".//strong[@class='mainHeading']")); // заголовок

    public void clickHomeButton () {
        buttonHome.isDisplayed();
        buttonHome.click();
    }

    public void clickButtonManager () {
        buttonManager.isDisplayed();
        buttonManager.click();
    }

    public void clickButtonClient() {
        buttonClient.isDisplayed();
        buttonClient.click();
    }

    public void containsHeader (String bankName) {
        mainHeader.isDisplayed();
        assertEquals(bankName, mainHeader.getText());
    }

}
