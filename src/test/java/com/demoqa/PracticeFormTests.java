package com.demoqa;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.codeborne.selenide.Configuration;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;


public class PracticeFormTests {
    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";

    }
    @Test
    void successTest() {

        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        $("#firstName").setValue("Иван");
        $("#lastName").setValue("Иванов");
        $("#userEmail").setValue("Ivan@ov.ru");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("1233210023");
        $(".react-datepicker-wrapper").click();
        $(".react-datepicker__month-select").click();
        // дата в календаре
        $(".react-datepicker__month-select").$((byValue("4"))).click();
        $(".react-datepicker__year-select").$((byValue("1983"))).click();
        $(".react-datepicker__day--011").click();
        // далее
        $("#subjectsInput").setValue("ma").pressEnter();
        $(byText("Sports")).click();
        //Picture
        $(by("type", "file")).uploadFile(new File("src/test/resources/Picture.png"));
        // далее
        $("#currentAddress").setValue("Адрес");
        $("#react-select-3-input").setValue("r").pressEnter();
        $("#react-select-4-input").setValue("r").pressEnter();
        $("#submit").click();

        $(".table-responsive").shouldHave(text("Иван Иванов"));
        $(".table-responsive").shouldHave(text("Ivan@ov.ru"));
        $(".table-responsive").shouldHave(text("Male"));
        $(".table-responsive").shouldHave(text("1233210023"));
        $(".table-responsive").shouldHave(text("11 May,1983"));
        $(".table-responsive").shouldHave(text("Maths"));
        $(".table-responsive").shouldHave(text("Sports"));
        $(".table-responsive").shouldHave(text("Picture.png"));
        $(".table-responsive").shouldHave(text("Адрес"));
        $(".table-responsive").shouldHave(text("NCR Gurgaon"));


    }
}
