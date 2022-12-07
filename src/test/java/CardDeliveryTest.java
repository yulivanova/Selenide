import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    @BeforeEach
    void setUp() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
    }

    @Test
    void shouldRegisterByAccountNumber() {
        $$(".tab-item").last().click();
        $("[name='number']").setValue("40550100012346138564");
        $("[name='phone']").setValue("+792000000000");
        $("button").click(); // кликаем на первой попавшейся кнопке :-)    // TODO: }
}
}
