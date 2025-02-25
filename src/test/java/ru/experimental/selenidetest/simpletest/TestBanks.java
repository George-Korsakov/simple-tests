package ru.experimental.selenidetest.simpletest;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.experimental.selenidetest.simpletest.pages.CustomerLoginPage;
import ru.experimental.selenidetest.simpletest.pages.CustomerMainPage;
import ru.experimental.selenidetest.simpletest.pages.MainBankPage;
import ru.experimental.selenidetest.simpletest.pages.ManagerMainPage;

import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBanks extends TestBase{

    MainBankPage mainBankPage = new MainBankPage();
    CustomerLoginPage customerLoginPage = new CustomerLoginPage();
    CustomerMainPage customerMainPage = new CustomerMainPage();
    ManagerMainPage managerMainPage = new ManagerMainPage();
    SimpleDB simpleDB = new SimpleDB();
// AI асистент написал бы это быстрее и лучше, чем когда я учился )))
    @Test
    public void testCustomer() {
        String name  = "Harry Potter";
        loginBank();
        $(byXpath(".//button[normalize-space()='Customer Login']")).click(); // кнопка клиент
        //$(byXpath("//button[normalize-space()='Bank Manager Login']")).click(); // кнопка менеджер

        $(byXpath(".//select[@id='userSelect']")).shouldBe(visible);
        //$$(byXpath("//select[@id='userSelect']")).shouldHave(CollectionCondition.size(5));
        $(byXpath(".//select[@id='userSelect']")).selectOptionByValue("1");
        // выбор пользователя
        $(byXpath(".//select[@id='userSelect']")).selectOptionContainingText(name);
        $(byXpath(".//button[normalize-space()='Login']")).click();
        sleep(3000);
        assertEquals(name, $("span[class='fontBig ng-binding']").text());
        //получаем список счетов
        WebElement drop_down =$x(".//select[@id='accountSelect']");
        Select se = new Select(drop_down);
        List<WebElement> options = se.getOptions();
        int s =  options.size();
        int i = 0;
        while ( i < s) {
            String opt = options.get(i).getText();
            System.out.println("TEST size = " + s + " option " + i + " = " + opt);
            i++;
        }
    }

    @Test
    public void testLogin() {
        String name  = "Harry Potter";
        loginBank();
        // переход на главную страницу и проверка текста загловока
        mainBankPage.clickHomeButton();
        mainBankPage.containsHeader("XYZ Bank");
        // зайти менеджером
        mainBankPage.clickButtonManager();
        mainBankPage.clickHomeButton();
        // зайти клиентом
        mainBankPage.clickButtonClient();
        customerLoginPage.containsHeader("XYZ Bank");
        customerLoginPage.selectUser(name);
        customerLoginPage.clickButtonLogin();
        customerLoginPage.clickHomeButton();
    }

    @Test
    public void testCustomerPage() {
        String name  = "Harry Potter";

        // зайти как клиент (поля, сообщения меняются в звисимости от выбранного пользователя)
        mainBankPage.clickButtonClient();
        sleep(1000);
        customerLoginPage.containsHeader("XYZ Bank");
        // выбор пользоватлея и вход
        customerLoginPage.selectUser(name);
        // выбор случайного пользователя из списка
        //customerLoginPage.selectRndUser();
        //String name = customerLoginPage.getSelecetedUser();
        System.out.println("random user = " + name);

        customerLoginPage.clickButtonLogin();
        // проверяем содержание имени пользователя в заглоовке
        customerMainPage.checkWelcomeHeader(name);
        // открываем список транзакций и сбрасываем значения
        customerMainPage.clickButtonTransaction();
        customerMainPage.clickButtonResetAndBack();

        // выбираем случайный номре счета
        customerMainPage.selectRndAccount();
        // получаем значения выбранного счета
        String selectedAccount = customerMainPage.getSelectedAccountValue();
        // получаем занчения из таблицы
        String currentAccount = customerMainPage.getAccountNumber();
        int balance = Integer.parseInt(customerMainPage.getAccountBalance());
        String currency = customerMainPage.getAccountCurrency();
        // проверяем номер выбранного счета
        assertEquals(selectedAccount, currentAccount);
        // выводит текущие  значения таблицы
        System.out.println("TEST: " + "\n selectedAccount = " + selectedAccount + "\n currentAccount = "
                + currentAccount + "\n balance = " + balance + "\n currency = " + currency);

        // добавляем сумму на счет
        customerMainPage.clickButtonDeposit();
        int summ = (int) (Math.random()*100);
        customerMainPage.setInputAmount(summ+999);
        customerMainPage.clickButtonSubmit();
        // проверка изменения суммы на счету
        int actualBalance = balance + summ+999;
        assertEquals(actualBalance, Integer.parseInt(customerMainPage.getAccountBalance()));
        assertEquals("Deposit Successful", customerMainPage.checkErrMsg());
        // снятие суммы с счета
        customerMainPage.clickButtonDWithdraw();
        sleep(1000);
        customerMainPage.setInputAmount(summ);
        customerMainPage.clickButtonSubmit();
        // проверка изменения суммы на счету
        int actualBalanceAfter = Integer.parseInt(customerMainPage.getAccountBalance());
        //assertEquals(actualBalanceAfter - summ, Integer.parseInt(customerMainPage.getAccountBalance()));
        //assertEquals("Transaction successful", customerMainPage.checkErrMsg());
        sleep(3000);
        // проверка наличия транзакций в таблице
        customerMainPage.clickButtonTransaction();
        // вывод значений таблицы в лог
        System.out.println("Transaction Table test: " +" интервал " + customerMainPage.getEndDate()  + " - " + customerMainPage.getEndDate() );
        System.out.println("Transaction Table test: " +" значения " + customerMainPage.getCellValue(0,1)  + " | "
                + customerMainPage.getCellValue(0,2) + " | "
                + customerMainPage.getCellValue(0,3) + " | ");
        System.out.println("Transaction Table test: " +" значения " + customerMainPage.getCellValue(1,1)  + " | "
                + customerMainPage.getCellValue(1,2) + " | "
                + customerMainPage.getCellValue(1,3) + " | ");
        assertEquals(summ+999, Integer.parseInt(customerMainPage.getCreditValue()));
        assertEquals(summ, Integer.parseInt(customerMainPage.getDebitValue()));
        //выход
        customerMainPage.clickButtonLogout();
        customerLoginPage.clickHomeButton();
    }

    @Test
    public void testManagerPage () {
        // генерация тестовыех данных
        String lastname = generateRandomString(9);
        String firstname = generateRandomString(7);
        String index = String.valueOf((Math.random()*1000));

        mainBankPage.clickButtonManager();
        // добавление нового клиента
        managerMainPage.clickButtonAddCustomer();
        // вводим занчения в поля картчоки клиента
        managerMainPage.setFirstName(firstname);
        managerMainPage.setLastName(lastname);
        managerMainPage.setPostCode(index);
        // нажать кнопку подтверждения
        managerMainPage.clickButtonSubmit();
        // нажать кнопку ок в всплывающем окне с проверкой текста
        managerMainPage.clickOKinNotificationNewCustomer();
        // попытка добавление дубля клиента
        managerMainPage.clickButtonAddCustomer();
        managerMainPage.setFirstName(firstname);
        managerMainPage.setLastName(lastname);
        managerMainPage.setPostCode(index);
        managerMainPage.clickButtonSubmit();
        // нажать кнопку ок в всплывающем окне с проверкой текста
        managerMainPage.clickOKinNotificationDuplicateCustomer();

        // открытие счета добавленному клиенту
        managerMainPage.clickButtonOpenAccount();
        managerMainPage.selectCustomerName(firstname + " " + lastname);
        managerMainPage.selectCurrency("Dollar");
        managerMainPage.clickButtonSubmit();
        managerMainPage.clickOKinNotificationAccountCreate();
        // удаление созданного клиента
        managerMainPage.clickButtonCustomers();
        managerMainPage.setValueAndSearchCustomer(firstname);
        managerMainPage.clickButtonDel();
        // выход
        managerMainPage.clickHomeButton();

    }

    @Test
    public void simpleTestDB() {
        // записываем в переменную результат выполенения SQL запроса

    String resQuery = simpleDB.firstRowQueryDB("SELECT LAST_NAME FROM bank_customers WHERE CUSTOMER_ID = '1109000000001'");
    System.out.println("TEST1 = " + resQuery);

    String resQuery2 = simpleDB.firstRowQueryDB("SELECT LAST_NAME FROM bank_customers WHERE CUSTOMER_ID > '1109000000001'");
    System.out.println("TEST2 = " + resQuery2);

    }

    @Test 
    public void testCreateNewCustomer () {
        // получение случайной строки с тестовыми данными из БД
        String Id = simpleDB.firstRowQueryDB("SELECT * FROM (SELECT CUSTOMER_ID FROM bank_customers ORDER BY dbms_random.value) WHERE rownum < 2 ");
        String lastname = simpleDB.firstRowQueryDB("SELECT LAST_NAME FROM bank_customers WHERE CUSTOMER_ID = '"+ Id + "'");
        String firstname = simpleDB.firstRowQueryDB("SELECT FIRST_NAME FROM bank_customers WHERE CUSTOMER_ID = '"+ Id + "'");
        String index = simpleDB.firstRowQueryDB("SELECT POST_CODE FROM bank_customers WHERE CUSTOMER_ID = '"+ Id + "'");
        System.out.println("Query DB result test data:" + Id + " " + lastname + " " + firstname + " " + index);
        mainBankPage.clickButtonManager();
        // добавление нового клиента
        managerMainPage.clickButtonAddCustomer();
        // вводим занчения в поля картчоки клиента
        managerMainPage.setFirstName(firstname);
        managerMainPage.setLastName(lastname);
        managerMainPage.setPostCode(index);
        // нажать кнопку подтверждения
        managerMainPage.clickButtonSubmit();
        // нажать кнопку ок в всплывающем окне с проверкой текста
        managerMainPage.clickOKinNotificationNewCustomer();
    }
}
