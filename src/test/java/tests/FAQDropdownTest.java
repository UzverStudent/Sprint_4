package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FAQDropdownTest {

    private WebDriver driver;
    private MainPage mainPage;

    private int questionIndex; // Индекс вопроса
    private String expectedQuestion; // Ожидаемый текст вопроса
    private String expectedAnswer; // Ожидаемый текст ответа

    public FAQDropdownTest(int questionIndex, String expectedQuestion, String expectedAnswer) {
        this.questionIndex = questionIndex;
        this.expectedQuestion = expectedQuestion;
        this.expectedAnswer = expectedAnswer;
    }

    // Наборы данных для теста
    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {0, "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        });
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/"); // Задай нужный URL
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        mainPage = new MainPage(driver);
    }

    @Test
    public void testFAQDropdown() {
        // Закрываем баннер с предупреждением о куках
        mainPage.closeCookieBanner();

        // Прокручиваем страницу до блока с вопросами
        mainPage.scrollToFAQ();

        // Проверяем текст вопроса
        String actualQuestion = mainPage.getQuestionText(questionIndex);
        assertEquals("Текст вопроса не соответствует ожидаемому", expectedQuestion, actualQuestion);

        // Нажимаем на стрелочку рядом с вопросом
        mainPage.clickDropdownArrow(questionIndex);

        // Получаем текст ответа и проверяем его
        String actualAnswer = mainPage.getAnswerText(questionIndex);
        assertEquals("Текст ответа не соответствует ожидаемому", expectedAnswer, actualAnswer);

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}