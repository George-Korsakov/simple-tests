package ru.experimental.selenidetest.simpletest.pages;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.experimental.selenidetest.simpletest.TestBase;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static java.lang.Math.random;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ManagerMainPage extends TestBase {

    WebElement buttonAddCustomer = $x("//button[contains(text(),'Add Customer')]"); // кнопка добавить клиента
    WebElement buttonOpenAccount = $x("//button[contains(text(),'Open Account')]"); // кнопка открыть счет
    WebElement buttonCustomers = $x("//button[contains(text(),'Customers')]"); // кнопка клиенты
    WebElement buttonHome = $x("//button[@class='btn home']"); // кнопка домой
    WebElement buttonSubmit = $x("//button[@type='submit']"); // кнопка подтверждение

    WebElement fieldFirstName = $x("//input[@ng-model='fName']"); // поле имя
    WebElement fieldLastName = $x("//input[@ng-model='lName']"); // поле фамилия
    WebElement fieldPostCode = $x("//input[@ng-model='postCd']"); // проле индекс

    WebElement listCustomerName = $x("//select[@ng-model='custId']"); // выпадающий список клиентов
    WebElement listCurrency = $x("//select[@id='currency']"); // выпдающий список валют

    WebElement fieldSearchCustomer = $x("//input[@type='text']");



    // список клиентов
    public List<WebElement> customers () {
        listCustomerName.isDisplayed();
        Select se = new Select(listCustomerName);
        List<WebElement> customers = se.getOptions();
        return customers;
    }
    // кол-во клиентов в списке
    public int customersListSize() {
        listCustomerName.isDisplayed();
        Select se = new Select(listCustomerName);
        List<WebElement> customers = se.getOptions();
        return customers.size();
    }

    // список валют
    public List<WebElement> currency () {
        listCurrency.isDisplayed();
        Select se = new Select(listCurrency);
        List<WebElement> currency = se.getOptions();
        return currency;
    }
    // кол-во валют в списке
    public int currencyListSize() {
        listCurrency.isDisplayed();
        Select se = new Select(listCurrency);
        List<WebElement> currency = se.getOptions();
        return currency.size();
    }

    // нажатие кнопоку бавить клиента
    public void clickButtonAddCustomer () {
        buttonAddCustomer.isDisplayed();
        buttonAddCustomer.click();
    }
    // нажатие кнопоку открыть счет
    public void clickButtonOpenAccount () {
        buttonOpenAccount.isDisplayed();
        buttonOpenAccount.click();
    }
    // нажатие кнопоку клиенты
    public void clickButtonCustomers () {
        buttonCustomers.isDisplayed();
        buttonCustomers.click();
    }
    // нажатие кнопоку домой
    public void clickHomeButton () {
        buttonHome.isDisplayed();
        buttonHome.click();
    }
    // нажатие кнопоку подтверждение
    public void clickButtonSubmit () {
        buttonSubmit.isDisplayed();
        buttonSubmit.click();
    }
    // ввод значения в поле
    public void setFirstName (String name) {
        fieldFirstName.isDisplayed();
        $(fieldFirstName).setValue(name);
    }
    public void setLastName (String name) {
        fieldLastName.isDisplayed();
        $(fieldLastName).setValue(name);
    }
    public void setPostCode (String index) {
        fieldPostCode.isDisplayed();
        $(fieldPostCode).setValue(index);
    }
    // получение значения в поле
    public String getFirstName () {
        fieldFirstName.isDisplayed();
        return $(fieldFirstName).getText();
    }
    public String getLastName () {
        fieldLastName.isDisplayed();
        return $(fieldLastName).getText();
    }
    public String getPostCode () {
        fieldPostCode.isDisplayed();
        return $(fieldPostCode).getText();
    }
    // нажать подтверждение в всплывающем окне при добавлении клиента
    public void clickOKinNotificationNewCustomer () {
        assertTrue( Selenide.switchTo().alert().getText().contains("Customer added successfully with customer id"));
        Selenide.switchTo().alert().accept();
    }
    public void clickOKinNotificationDuplicateCustomer () {
        assertTrue( Selenide.switchTo().alert().getText().contains("Please check the details. Customer may be duplicate."));
        Selenide.switchTo().alert().accept();
    }
    public void clickOKinNotificationAccountCreate () {
        assertTrue( Selenide.switchTo().alert().getText().contains("Account created successfully with account Number"));
        Selenide.switchTo().alert().accept();
    }

    // выбор имя клиента в списке
    public void selectCustomerName (String name) {
        listCustomerName.isDisplayed();
        $(listCustomerName).selectOptionContainingText(name);
    }
    // получить выбранное значение в списке
    public String getSelectedAccountValue () {
        listCustomerName.isDisplayed();
        return $(listCustomerName).getSelectedValue();
    }

    // выбираем случайную позицию в списке
    public void selectRndAccount() {
        int rnd =  (int) Math.random()*customersListSize();
        $(listCustomerName).selectOption(rnd);
    }

    // выбор валююты в списке
    public void selectCurrency (String Currency) {
        listCurrency.isDisplayed();
        $(listCurrency).selectOptionContainingText(Currency);
    }
    // получить выбранное значение в списке
    public String getSelectedCurrencyValue () {
        listCurrency.isDisplayed();
        return $(listCurrency).getSelectedValue();
    }

    // выбираем случайную позицию в списке
    public void selectRndCurrency() {
        int rnd =  (int) Math.random()*currencyListSize();
        $(listCurrency).selectOption(rnd);
    }

    public void clickButtonDelInCustomerListByIndex (String index) {
        $x("//tbody//td[contains(text(),'"+ index +"')]//..//td[5]/button[1]").isDisplayed();
        $x("//tbody//td[contains(text(),'"+ index +"')]//..//td[5]/button[1]").click();
    }

    public void clickButtonDel () {
        $x("//tbody//td[5]/button[1]").isDisplayed();
        $x("//tbody//td[5]/button[1]").click();
    }

    public void setValueAndSearchCustomer (String name) {
        fieldSearchCustomer.isDisplayed();
        $(fieldSearchCustomer).setValue(name);
    }
}
