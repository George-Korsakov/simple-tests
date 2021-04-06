package ru.experimental.selenidetest.simpletest.pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.experimental.selenidetest.simpletest.TestBase;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.assertEquals;
import ru.experimental.selenidetest.simpletest.TestBase;

import java.util.List;

public class CustomerMainPage extends TestBase
{
    WebElement buttonHome = $("button[class='btn home']"); // кнопка Домой
    WebElement buttonLogout = $x("//button[normalize-space()='Logout']"); // кнопка выход
    WebElement buttonTransaction = $x("//button[contains(text(),'Transactions')]"); // кнопка "Перевод"
    WebElement buttonDeposit = $x("//button[contains(text(),'Deposit')]"); // кнопка "Депозит"
    WebElement buttonDWithdraw = $x("//button[contains(text(),'Withdraw')]"); // кнопка "Снять со счета"
    WebElement listAccountNumber = $x("//select[@id='accountSelect']"); // выпадающий список выбор счета

    WebElement accountNumber = $x(".//div[@ng-hide='noAccount']//strong[1]"); // значение номер счета
    WebElement accountBalance = $x(".//div[@ng-hide='noAccount']//strong[2]"); // значение баланса
    WebElement accountCurrency= $x(".//div[@ng-hide='noAccount']//strong[3]"); // значение валюта счета

    WebElement mainHeader = $(byXpath(".//strong[@class='mainHeading']")); // заголовок
    WebElement headerWelcomeUserName = $("span[class='fontBig ng-binding']"); // заголовок приветствия пользователя

    WebElement inputAmount = $x(".//input[@placeholder='amount']"); // поле для ввода значения
    WebElement buttonSubmit = $x(".//button[@type='submit']"); // кнопка подтверждения
    WebElement errMsg = $x("//span[@class='error ng-binding']"); // сообщение после нажаьтя кнопки подтверждения

    WebElement buttonBack =$x("//button[@ng-click='back()']"); // кнопка возврат
    WebElement buttonReset =$x("//button[@ng-click='reset()']"); // кнопка сброс
    WebElement filedStartDate = $x("//input[@id='start']"); // поле дата начала периода
    WebElement filedEndDate = $x("//input[@id='end']"); // поле дата окончание периода

    WebElement buttonRight = $x("//button[@ng-show='right']"); // кнопка переход вправо
    WebElement buttonLeft = $x("//button[@ng-click='scrollLeft()']"); // кнопка переход в лево
    WebElement buttonTop = $x("//button[@ng-click='scrollTop()']"); // кнопка Топ
    WebElement columnHeaderDataTime = $x("//thead//[contains(text(),'Date-Time')]"); // наименование колоник Дата время

    // работа с таблицей транзакций
    WebElement columnDateTime = $x("//tr[*>0]//td[1]"); // все значения колоник Дата
    WebElement columnAmount = $x("//tr[*>0]//td[2]"); // все значенимя колонки сумма
    WebElement columnTransactionType = $x("//tr[*>0]//td[1]"); // все значения колонки тип транзакции
    // получить знаечние ячейки по индексу
    public String getCellValue(int row, int col) {
        return $x("//tr[@id='anchor"+ row +"']//td["+ col +"]").getText();
    }
    // получить значение по кредита
    public String getCreditValue(){
        return $x("//tr[*]//td[contains(text(),'Credit')]/../td[2]").getText();
    }
    // получить значение дебета
    public String getDebitValue(){
        return $x("//tr[*]//td[contains(text(),'Debit')]/../td[2]").getText();
    }

    // список счетов клиента
    public List<WebElement> accounts () {
        listAccountNumber.isDisplayed();
        WebElement accountList = $x(".//select[@id='accountSelect']");
        Select se = new Select(accountList);
        List<WebElement> accounts = se.getOptions();
        return accounts;
    }
    // кол-во счетов в списке
    public int accountListSize() {
        WebElement accountList = $x(".//select[@id='accountSelect']");
        Select se = new Select(accountList);
        List<WebElement> accounts = se.getOptions();
        return accounts.size();
    }
    // проверяем текст заголовка
    public void containsHeader (String bankName) {
        mainHeader.isDisplayed();
        assertEquals(bankName, mainHeader.getText());
    }
    // нажатие кнопок
    public void clickHomeButton () {
        buttonHome.isDisplayed();
        buttonHome.click();
    }

    public void clickButtonLogout () {
        buttonLogout.isDisplayed();
        buttonLogout.click();
    }

    public void clickButtonTransaction () {
        buttonTransaction.isDisplayed();
        buttonTransaction.click();
    }

    public void clickButtonDeposit () {
        buttonDeposit.isDisplayed();
        buttonDeposit.click();
    }

    public void clickButtonDWithdraw () {
        buttonDWithdraw.isDisplayed();
        buttonDWithdraw.click();
    }
    // проверяем имя пользователя в заголовке приветствия
    public void checkWelcomeHeader (String name) {
        assertEquals(name, headerWelcomeUserName.getText());
    }
    // номер счета в таблице
    public String getAccountNumber () {
        accountNumber.isDisplayed();
        return accountNumber.getText();
    }
    // получаем значение баланса счета в таблице
    public String getAccountBalance () {
        accountBalance.isDisplayed();
        return  accountBalance.getText();
    }
    // получаем валюту счета
    public String getAccountCurrency () {
        accountCurrency.isDisplayed();
        return  accountCurrency.getText();
    }
    // выбор счета в списке
    public void selectAccount (String number) {
        listAccountNumber.isDisplayed();
        $(listAccountNumber).selectOptionContainingText(number);
    }
    // возвразает цифры номера выбранного счета
    public String getSelectedAccountValue () {
        listAccountNumber.isDisplayed();
        return $(listAccountNumber).getSelectedValue().replaceAll("[^0-9]", "");
    }

    // выбираем случайный номер счета
    public void selectRndAccount() {
        int rnd = (int) (Math.random()*accountListSize());
        $(listAccountNumber).selectOption(rnd);
    }
    // получаем номер счета по номеру в списке (от 0 до accountListSize)
    public String getAccountNumByIndex(int index){
        if (index < accountListSize()) {
            return accounts().get(index).getText();
        }
        else {return "указанное значение" + index + " не существует в списке"; }
    }
    // ввести сумму в поле ввода значения
    public void setInputAmount (int summ) {
        inputAmount.isDisplayed();
        inputAmount.click();
        inputAmount.clear();
        $(inputAmount).setValue(String.valueOf(summ));
    }

    public String getInputAmount () {
        inputAmount.isDisplayed();
        return $(inputAmount).getValue();
    }
    // нажать кнопку подтверждения
    public void clickButtonSubmit () {
        buttonSubmit.isDisplayed();
        buttonSubmit.click();
    }

    // пролучить текст сообщение после нажаьтя кнопки подтверждения
    public String checkErrMsg () {
        errMsg.isDisplayed();
        return errMsg.getText();
    }

    // нажаь кнопку вернуться
    public void clickButtonBack () {
        buttonBack.isDisplayed();
        buttonBack.click();
    }
    // нажаь кнопку сброс
    public void clickButtonResetAndBack() {

        if (buttonReset.isDisplayed())
        {
            buttonReset.click();
            buttonBack.click();
        }
        else buttonBack.click();
    }
    // нажаь кнопку в право
    public void clickButtonRight () {
        buttonRight.isDisplayed();
        buttonRight.click();
    }
    // нажаь кнопку в лево
    public void clickButtonLeft () {
        buttonLeft.isDisplayed();
        buttonLeft.click();
    }
    // нажаь кнопку топ
    public void clickButtonTop () {
        buttonTop.isDisplayed();
        buttonTop.click();
    }
    // нажать на заголовок колоники даты-времени
    public void clickDataTime () {
        columnHeaderDataTime.isDisplayed();
        columnHeaderDataTime.click();
    }

    // задать значение начала интервала
    public void setStartDate (String date) {
        filedStartDate.isDisplayed();
        $(filedStartDate).setValue(date);
    }
    // получить значение начала интервала
    public String getStartDate() {
        filedStartDate.isDisplayed();
        return $(filedStartDate).getValue();
    }
    // задать значение окончания интервала
    public void setEndtDate (String date) {
        filedEndDate.isDisplayed();
        $(filedEndDate).setValue(date);
    }
    // задать получить окончания интервала
    public String getEndDate() {
        filedEndDate.isDisplayed();
        return $(filedEndDate).getValue();
    }
}
