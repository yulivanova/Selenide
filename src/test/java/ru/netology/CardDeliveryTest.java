package ru.netology;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
        Configuration.holdBrowserOpen = true;
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
    }

    @Test
    void shouldRegisterByAccountNumber() {
        String myDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] input").setValue("Омск");
        $("[data-test-id='date'] input").sendKeys(myDate);
        $("[data-test-id='name'] input").setValue("Петрова Анна");
        $("[data-test-id='phone'] input").setValue("+79333333333");
        $("[data-test-id='agreement']").click();
        $(By.className("button")).click();
        $("[data-test-id='notification']").shouldHave(text("Встреча успешно забронирована на " + myDate), Duration.ofSeconds(15000));
    }
}
