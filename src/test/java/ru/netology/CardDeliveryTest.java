package ru.netology;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    public String generateDate(int myDate) {
        return LocalDate.now().plusDays(myDate).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
        Configuration.holdBrowserOpen = true;
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
    }

    @Test
    void shouldSuccessfulCompletionOfTheCardOrderForm1() {
        $("[data-test-id='city'] input").setValue("Омск");
        $("[data-test-id='date'] input").sendKeys(generateDate(3));
        $("[data-test-id='name'] input").setValue("Петрова Анна");
        $("[data-test-id='phone'] input").setValue("+79333333333");
        $("[data-test-id='agreement']").click();
        $(By.className("button")).click();
        $("[data-test-id='notification']").shouldHave(text("Встреча успешно забронирована на " + generateDate(3)), Duration.ofSeconds(15000));
    }

    @Test
    void shouldSuccessfulCompletionOfTheCardOrderForm2() {
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(generateDate(4));
        $("[data-test-id='name'] input").setValue("Мамин-Сибиряк Дмитрий");
        $("[data-test-id='phone'] input").setValue("+79333333333");
        $("[data-test-id='agreement']").click();
        $(By.className("button")).click();
        $("[data-test-id='notification']").shouldHave(text("Встреча успешно забронирована на " + generateDate(4)), Duration.ofSeconds(15000));
    }

    @Test
    void shouldSuccessfulCompletionOfTheCardOrderForm3() {
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(generateDate(5));
        $("[data-test-id='name'] input").setValue("Орудж бей Байат");
        $("[data-test-id='phone'] input").setValue("+79333333333");
        $("[data-test-id='agreement']").click();
        $(By.className("button")).click();
        $("[data-test-id='notification']").shouldHave(text("Встреча успешно забронирована на " + generateDate(5)), Duration.ofSeconds(15000));
    }

    @Test
    void shouldTestUnhappyPathCity1() {
        $("[data-test-id='city'] input").setValue("");
        $("[data-test-id='date'] input").sendKeys(generateDate(3));
        $("[data-test-id='name'] input").setValue("Иванов Имануэль");
        $("[data-test-id='phone'] input").setValue("+79333333333");
        $("[data-test-id='agreement']").click();
        $(By.className("button")).click();
        $("[data-test-id='city'] .input__sub").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestUnhappyPathCity2() {
        $("[data-test-id='city'] input").setValue("Саяногорск");
        $("[data-test-id='date'] input").sendKeys(generateDate(3));
        $("[data-test-id='name'] input").setValue("Иванов Имануэль");
        $("[data-test-id='phone'] input").setValue("+79333333333");
        $("[data-test-id='agreement']").click();
        $(By.className("button")).click();
        $("[data-test-id='city'] .input__sub").shouldHave(text("Доставка в выбранный город недоступна"));
    }

    @Test
    void shouldTestUnhappyPathCity3() {
        $("[data-test-id='city'] input").setValue("Moscow");
        $("[data-test-id='date'] input").sendKeys(generateDate(3));
        $("[data-test-id='name'] input").setValue("Иванов Имануэль");
        $("[data-test-id='phone'] input").setValue("+79333333333");
        $("[data-test-id='agreement']").click();
        $(By.className("button")).click();
        $("[data-test-id='city'] .input__sub").shouldHave(text("Доставка в выбранный город недоступна"));
    }

    @Test
    void shouldTestUnhappyPathCity4() {
        $("[data-test-id='city'] input").setValue("1111");
        $("[data-test-id='date'] input").sendKeys(generateDate(3));
        $("[data-test-id='name'] input").setValue("Иванов Имануэль");
        $("[data-test-id='phone'] input").setValue("+79333333333");
        $("[data-test-id='agreement']").click();
        $(By.className("button")).click();
        $("[data-test-id='city'] .input__sub").shouldHave(text("Доставка в выбранный город недоступна"));
    }

    @Test
    void shouldTestUnhappyPathDate1() {
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(generateDate(0));
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+79333333333");
        $("[data-test-id='agreement']").click();
        $(By.className("button")).click();
        $("[data-test-id='date'] .input__sub").shouldHave(text("Заказ на выбранную дату невозможен"));
    }

    @Test
    void shouldTestUnhappyPathDate2() {
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(generateDate(-10));
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+79333333333");
        $("[data-test-id='agreement']").click();
        $(By.className("button")).click();
        $("[data-test-id='date'] .input__sub").shouldHave(text("Заказ на выбранную дату невозможен"));
    }

    @Test
    void shouldTestUnhappyPathDate3() {
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys("00.00.0000");
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+79333333333");
        $("[data-test-id='agreement']").click();
        $(By.className("button")).click();
        $("[data-test-id='date'] .input__sub").shouldHave(text("Неверно введена дата"));
    }

    @Test
    void shouldTestUnhappyPathName1() {
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(generateDate(3));
        $("[data-test-id='name'] input").setValue("Ivanov Ivan");
        $("[data-test-id='phone'] input").setValue("+79333333333");
        $("[data-test-id='agreement']").click();
        $(By.className("button")).click();
        $("[data-test-id='name'] .input__sub").shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldTestUnhappyPathName2() {
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(generateDate(3));
        $("[data-test-id='name'] input").setValue("");
        $("[data-test-id='phone'] input").setValue("+79333333333");
        $("[data-test-id='agreement']").click();
        $(By.className("button")).click();
        $("[data-test-id='name'] .input__sub").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestUnhappyPathName3() {
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(generateDate(3));
        $("[data-test-id='name'] input").setValue("11111");
        $("[data-test-id='phone'] input").setValue("+79333333333");
        $("[data-test-id='agreement']").click();
        $(By.className("button")).click();
        $("[data-test-id='name'] .input__sub").shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldTestUnhappyPathName4() {
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(generateDate(3));
        $("[data-test-id='name'] input").setValue("1!л -");
        $("[data-test-id='phone'] input").setValue("+79333333333");
        $("[data-test-id='agreement']").click();
        $(By.className("button")).click();
        $("[data-test-id='name'] .input__sub").shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldTestUnhappyPathPhone1() {
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(generateDate(3));
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("79333333333");
        $("[data-test-id='agreement']").click();
        $(By.className("button")).click();
        $("[data-test-id='phone'] .input__sub").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldTestUnhappyPathPhone2() {
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(generateDate(3));
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("");
        $("[data-test-id='agreement']").click();
        $(By.className("button")).click();
        $("[data-test-id='phone'] .input__sub").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestUnhappyPathPhone3() {
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(generateDate(3));
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("793333333333");
        $("[data-test-id='agreement']").click();
        $(By.className("button")).click();
        $("[data-test-id='phone'] .input__sub").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldTestUnhappyPathPhone4() {
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(generateDate(3));
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+793333333333");
        $("[data-test-id='agreement']").click();
        $(By.className("button")).click();
        $("[data-test-id='phone'] .input__sub").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldTestUnhappyPathPhone5() {
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(generateDate(3));
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("д79333333333+");
        $("[data-test-id='agreement']").click();
        $(By.className("button")).click();
        $("[data-test-id='phone'] .input__sub").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldTestUnhappyPathPhone6() {
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(generateDate(3));
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+7933-333-33-33");
        $("[data-test-id='agreement']").click();
        $(By.className("button")).click();
        $("[data-test-id='phone'] .input__sub").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldTestUnhappyPathCheckbox() {
        $("[data-test-id='city'] input").setValue("Омск");
        $("[data-test-id='date'] input").sendKeys(generateDate(3));
        $("[data-test-id='name'] input").setValue("Петрова Анна");
        $("[data-test-id='phone'] input").setValue("+79333333333");
        //$("[data-test-id='agreement']").click();
        $(By.className("button")).click();
        $("[data-test-id='agreement'] .checkbox__text").shouldHave(text("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }
}


