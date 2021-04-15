package ru.experimental.selenidetest.simpletest;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.lang.Thread.sleep;


public class test1 {
    @Test
    public void test1() {
        // Configuration.browser ="firefox";
        //Configuration.timeout = 5000;

        open("https://ya.ru");
        $(By.name("text")).shouldBe();
        $(By.name("text")).setValue("Heisenbug 2020").pressEnter();
        //  $$(".search-result .serp-item").shouldHave(CollectionCondition.sizeGreaterThan(5));

        $(By.className("search2__button")).click();

        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
