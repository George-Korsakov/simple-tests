package ru.experimental.selenidetest.simpletest.pages;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import ru.experimental.selenidetest.simpletest.TestBase;

import static com.codeborne.selenide.Selenide.*;
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

}
