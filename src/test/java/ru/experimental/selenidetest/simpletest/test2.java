package ru.experimental.selenidetest.simpletest;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.hasWebDriverStarted;

public class test2 {


    @BeforeAll
    static void setUp() {
        Configuration.fastSetValue = false;
        if (hasWebDriverStarted()) {
            clearBrowserCookies();
            clearBrowserLocalStorage();
            refresh();
        }
        else {
            open("https://todomvc.com/examples/react/#/completed");
        }
    }

    @Test
    public void test2 () {
        $(".new-todo").setValue("Test task 1").pressEnter();
        $(".new-todo").setValue("Test task 2").pressEnter();
        $(".new-todo").setValue("Test task 3").pressEnter();
        $((".footer")).find(".filters").find("All").click();
        sleep(3000);
        $$(".todo-list").shouldHaveSize(3);

    }
    @Test
    public void test3 () {
        // создаем 4 задачи
        $(".new-todo").setValue("Test task 1").pressEnter();
        $(".new-todo").setValue("Test task 2").pressEnter();
        $(".new-todo").setValue("Test task 3").pressEnter();
        $(".new-todo").setValue("Test task 4").pressEnter();
        // выбираем все задачи
        $x("//a[normalize-space()='All']").click();
        sleep(3000);
        // отметить первую задачу
        $x("//li[1]//div[1]//input[1]").click();
        // проверка, что стаутс задачи выполнено
        Assertions.assertTrue($x("//li[@class='completed']").exists());
        // проверяем, что в сиске оставлось 2 активных задачи
        $$(".strong[data-reactid='.0.2.0.0']").contains(3);

    }



}
