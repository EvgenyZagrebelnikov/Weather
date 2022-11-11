import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EvgenyZagrebelnikovTest {

    /*
    ТС_1_1 - Тест кейс:
    1. Открыть страницу https://openweathermap.org/
    2. Набрать в строке поиска Paris
    3. Нажать пункт меню Search
    4. Из выпадающего списка выбрать Paris, FR
    5. Подтвердить, что заголовок изменился на "Paris, FR"
     */

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/home/hp14/Downloads/chromedriver");

        //ChromeOptions options = new ChromeOptions();
        //options.addArguments("headless"); // headless -> no browser window. needed for jenkins
        //options.addArguments("disable-infobars"); // disabling infobars
        //options.addArguments("--disable-extensions"); // disabling extensions
        //options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        //options.addArguments("--no-sandbox"); // Bypass OS security model
        //WebDriver driver = new ChromeDriver();

        ChromeOptions options = new ChromeOptions();
        //ChromeOptions.addArguments("--no-sandbox");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        WebDriver driver = new ChromeDriver(options);

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        driver.get(url);
        Thread.sleep(10000);

        WebElement searchCityField = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']")
        );
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']")
        );
        searchButton.click();
        Thread.sleep(9000);

        WebElement parisFRChoiceInDropdownMenu = driver.findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );
        parisFRChoiceInDropdownMenu.click();

        WebElement h2CityContryHeader = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );

        Thread.sleep(5000);

        String actualResult = h2CityContryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);

        //Thread.sleep(9000);


        //Thread.sleep(5000);

        driver.close();

    }

}

/*
Test Template
        @Test
        public void test_name() {
            System.setProperty("webdriver.chrome.driver" , "/Applications/ChromeDriver/chromedriver");
            WebDriver driver = new ChromeDriver();

            driver.quit();
        }
*/