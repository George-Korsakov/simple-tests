package ru.experimental.selenidetest.simpletest;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.hasWebDriverStarted;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Text;

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



}
