package ru.experimental.selenidetest.simpletest.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.experimental.selenidetest.simpletest.TestBase;

import java.util.List;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CustomerLoginPage extends TestBase
{
    WebElement listUserName = $x(".//select[@id='userSelect']"); // выпадающий список пользователей
    WebElement buttonLogin = $(byXpath(".//button[normalize-space()='Login']")); // кнопка Логин
    WebElement buttonHome = $("button[class='btn home']"); // кнопка Домой
    WebElement mainHeader = $(byXpath(".//strong[@class='mainHeading']")); // заголовок

    // список клиентов
    public List<WebElement> users () {
        listUserName.isDisplayed();
        WebElement usersList = $x(".//select[@id='userSelect']");
        Select se = new Select(usersList);
        List<WebElement> users = se.getOptions();
        return users;
    }

    public int usersListSize() {
        listUserName.isDisplayed();
        WebElement usersList = $x(".//select[@id='userSelect']");
        Select se = new Select(usersList);
        List<WebElement> users = se.getOptions();
        return users.size();
    }
    // проверка заголовка
    public void containsHeader (String bankName) {
        mainHeader.isDisplayed();
        assertEquals(bankName, mainHeader.getText());
    }
    // нажатия кнопок
    public void clickHomeButton () {
        buttonHome.isDisplayed();
        buttonHome.click();
    }

    public void clickButtonLogin () {
        buttonLogin.isDisplayed();
        buttonLogin.click();
    }
    // выбираем пользователя по имени
    public void selectUser (String name) {
        listUserName.isDisplayed();
        $(listUserName).selectOptionContainingText(name);
        buttonLogin.isDisplayed();
    }

    // выбираем случайного пользователя
    public void selectRndUser() {
        int rnd = (int) (Math.random()*usersListSize());
        $(listUserName).selectOption(rnd);
    }
    // проверка наличия пользователя в списке
    public boolean checkUserExist (String name) {
        return users().contains(name);
    }

    public String getSelecetedUser() {
        return $(listUserName).getText();
    }
}
