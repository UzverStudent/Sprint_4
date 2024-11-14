package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage {

    private WebDriver driver;

    // Локатор для кнопки аккордеона FAQ
    private String dropdownArrowTemplate = "(//div[contains(@class, 'accordion__button')])[%d]";

    // Локатор для текста ответа
    private String answerTextTemplate = "(//div[contains(@class, 'accordion__panel')])[%d]";

    // Локатор для текста вопроса
    private String questionTextTemplate = "(//div[contains(@class, 'accordion__heading')])[%d]";

    //Локатор для прокрутки страницы до блока с вопросами
    private By questionBoard = By.xpath("(//div[(@class = 'Home_FourPart__1uthg')])");

    //Локатор для прокрутки страницы до нижней кнопки "Заказать"
    private By bottomOrderButtonLocation = By.xpath("(//div[(@class = 'Home_RoadMap__2tal_')])");

    //Локатор для кнопки согласия с куками
    private By cookieButton = By.className("App_CookieButton__3cvqF");

    //Локатор верхней кнопки "Заказать"
    private By orderButtonTop = By.xpath("//button[text()='Заказать' and @class='Button_Button__ra12g']");

    //Локатор нижней кнопки "Заказать"
    private By orderButtonBottom = By.xpath("//button[contains(@class, 'Button_Button__ra12g') and contains(@class, 'Button_Middle__1CSJM')]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Метод для нажатия на кнопку аккордеона FAQ
    public void clickDropdownArrow(int index) {
        By dropdownArrow = By.xpath(String.format(dropdownArrowTemplate, index + 1));
        driver.findElement(dropdownArrow).click();
    }

    // Метод для получения текста ответа по индексу
    public String getAnswerText(int index) {
        By answerText = By.xpath(String.format(answerTextTemplate, index + 1));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement answerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(answerText));
        return answerElement.getText();

    }
    // Метод для получения текста вопроса по индексу
    public String getQuestionText(int index) {
        By questionText = By.xpath(String.format(questionTextTemplate, index + 1));
        return driver.findElement(questionText).getText();
    }
    //Метод для прокрутки страницы до FAQ
    public void scrollToFAQ() {
        WebElement element = driver.findElement(questionBoard);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }
    //Метод для закрытия баннера о применении куков
    public void closeCookieBanner() {
        driver.findElement(cookieButton).click();
    }
    //Метод для клика на верхнюю кнопку "Заказать"
    public void clickOrderButtonTop() {
        driver.findElement(orderButtonTop).click();
    }
    //Метод для клика на нижнюю кнопку "Заказать"

    public void scrollToBottomOrderButton(){
        WebElement element = driver.findElement(bottomOrderButtonLocation);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void clickOrderButtonBottom() {
        scrollToBottomOrderButton();
        driver.findElement(orderButtonBottom).click();
    }
}