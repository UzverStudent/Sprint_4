package tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;
import pages.OrderPage;

import java.util.concurrent.TimeUnit;

public class OrderFlow {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/webdrivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Test
    public void orderScooterFromTopButton() {
        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);

        // Закрываем баннер с предупреждением о куках
        mainPage.closeCookieBanner();

        // Нажать кнопку заказа вверху
        mainPage.clickOrderButtonTop();

        // Заполнить форму заказа
        orderPage.fillPersonalData("Иван", "Иванов", "Москва, ул. Пушкина", "Черкизовская", "89001112233");

        // Заполнить форму аренды
        orderPage.fillRentData("среда, 4-е сентября 2024 г.", "двое суток", "чёрный жемчуг", "Побыстрее, пожалуйста");

        //Нажатие на подтверждение заказа
        orderPage.clickToApproveOrder();

        // Проверка появления подтверждающего окна
        orderPage.isOrderSuccessMessageDisplayed();

        driver.quit();
    }

    @Test
    public void orderScooterFromBottomButton() {
        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);

        // Закрываем баннер с предупреждением о куках
        mainPage.closeCookieBanner();

        // Нажать кнопку заказа вверху
        mainPage.clickOrderButtonTop();

        // Заполнить форму заказа
        orderPage.fillPersonalData("Петр", "Петров", "Москва, ул. Ленина", "Лубянка", "89005554433");

        // Заполнить форму аренды
        orderPage.fillRentData("воскресенье, 29-е сентября 2024 г.", "сутки", "серая безысходность", "Без комментариев");

        //Нажатие на подтверждение заказа
        orderPage.clickToApproveOrder();

        // Проверка появления подтверждающего окна
        orderPage.isOrderSuccessMessageDisplayed();

        driver.quit();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}