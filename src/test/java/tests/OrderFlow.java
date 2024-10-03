package tests;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;
import pages.OrderPage;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class OrderFlow {

    private WebDriver driver;
    private String name;
    private String surname;
    private String address;
    private String metroStation;
    private String phone;
    private String deliveryDate;
    private String rentalPeriod;
    private String color;
    private String comment;
    private boolean isTopButton;

    public OrderFlow(String name, String surname, String address, String metroStation, String phone,
                     String deliveryDate, String rentalPeriod, String color, String comment, boolean isTopButton) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.deliveryDate = deliveryDate;
        this.rentalPeriod = rentalPeriod;
        this.color = color;
        this.comment = comment;
        this.isTopButton = isTopButton;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"Иван", "Иванов", "Москва, ул. Пушкина", "Черкизовская", "89001112233", "среда, 2-е октября 2024 г.", "двое суток", "чёрный жемчуг", "Побыстрее, пожалуйста", true},
                {"Петр", "Петров", "Москва, ул. Ленина", "Лубянка", "89005554433", "воскресенье, 27-е октября 2024 г.", "сутки", "серая безысходность", "Без комментариев", false}
        });
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void orderScooter() {
        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);

        // Закрываем баннер с предупреждением о куках
        mainPage.closeCookieBanner();

        // Выбираем кнопку заказа
        if (isTopButton) {
            mainPage.clickOrderButtonTop();
        } else {
            mainPage.clickOrderButtonBottom();
        }

        // Заполняем форму заказа
        orderPage.fillPersonalData(name, surname, address, metroStation, phone);

        // Заполняем форму аренды
        orderPage.fillRentData(deliveryDate, rentalPeriod, color, comment);

        // Нажатие на подтверждение заказа
        orderPage.clickToApproveOrder();

        // Проверка появления подтверждающего окна
        orderPage.isOrderSuccessMessageDisplayed();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
