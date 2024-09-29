package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {

            // Локаторы для формы с личными данными

    // Локатор для поля "Имя"
    private By firstNameField = By.xpath("//input[@placeholder='* Имя']");

    // Локатор для поля "Фамилия"
    private By lastNameField = By.xpath("//input[@placeholder='* Фамилия']");

    // Локатор для поля "Адрес"
    private By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");

    // Локатор для раскрытия списка "Станция метро"
    private By metroStationButton = By.xpath("//input[@placeholder='* Станция метро']");

    // Локатор для списка "Станция метро"
    private By metroStationDropdownOption = By.xpath("//div[@class='select-search__select']//li");

    // Локатор для поля "Телефон"
    private By phoneNumberField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");

    // Локатор для кнопки "Далее"
    private By nextButton = By.xpath("//button[text()='Далее']");

            //Локаторы для формы аренды

    // Локатор для открытия календаря "Дата доставки"
    private By deliveryDateButton = By.xpath("//input[@placeholder='* Когда привезти самокат']");

    // Локатор для выбора даты в календаре
    private String dayInCalendar = "//div[contains(@aria-label, 'Choose %s')]";

    // Локатор для списка "Срок аренды"
    private By rentDurationDropdown = By.xpath("//div[@class='Dropdown-control']");

    // Локатор для поля "Комментарий для курьера"
    private By commentForСourier = By.xpath("//input[@placeholder='Комментарий для курьера']");

    // Локатор для кнопки отправки заказа "Заказать"
    private By orderButton = By.xpath("//button[contains(@class, 'Button_Button__ra12g') and contains(@class, 'Button_Middle__1CSJM') and (text()='Заказать')]");

    // Локатор для кнопки подтверждения заказа "Да"
    private By approveOrderButton = By.xpath("//button[text()='Да']");

    // Локатор для текста на странице с подтверждением заказа "Заказ оформлен"
    private By orderIsApproved = By.xpath("//div[contains(text(),'Заказ оформлен')]");



    private WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // Метод для заполнения формы с личными данными
    public void fillPersonalData(String firstName, String lastName, String address, String metroStation, String phone) {
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(addressField).sendKeys(address);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.findElement(metroStationButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(metroStationDropdownOption));
        for (WebElement option : driver.findElements(metroStationDropdownOption)) {
            if (option.getText().contains(metroStation)) {
                option.click();
                break;
            }
        }
        driver.findElement(phoneNumberField).sendKeys(phone);
        driver.findElement(nextButton).click();
    }

    // Метод для заполнения формы аренды
    public void fillRentData(String deliveryDate, String rentDuration, String color, String comment) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.findElement(deliveryDateButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("react-datepicker")));
        String formattedLocator = String.format(dayInCalendar, deliveryDate);
        WebElement dateElement = driver.findElement(By.xpath(formattedLocator));
        dateElement.click();
        driver.findElement(rentDurationDropdown).click();
        driver.findElement(By.xpath("//div[text()='" + rentDuration + "']")).click();
        driver.findElement(By.xpath("//label[text()='" + color + "']")).click();
        driver.findElement(commentForСourier).sendKeys(comment);
        driver.findElement(orderButton).click();
    }

    // Метод для проверки успешного создания заказа
    public boolean isOrderSuccessMessageDisplayed() {
        return driver.findElement(orderIsApproved).isDisplayed();
    }

    // Метод для нажатия кнопки подтверждения заказа
    public void clickToApproveOrder(){
        driver.findElement(approveOrderButton).click();
    }
}