import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;


public class YourNickOnGitHubTest {

//  / TC_11_03
//1.  Открыть базовую ссылку
//2. Подтвердить, что внизу страницы есть панель с текстом “We use cookies which are essential for the
// site to work. We also use non-essential cookies to help us improve our services.
// Any data collected is anonymised. You can allow all cookies or manage them individually.”
//3. Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all” и “Manage cookies”

    @Test
    public void testCookiesPanel_Footer_WhenOpenWebsite() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/elenura/Downloads/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";

        String expectedResultPanelText = "We use cookies which are essential for the site to work. " +
                "We also use non-essential cookies to help us improve our services. Any data collected " +
                "is anonymised. You can allow all cookies or manage them individually.";

        String expectedResultAllowAll = "Allow all";
        String expectedResultManageCookies = "Manage cookies";
        
        driver.get(url);
        Thread.sleep(5000);

        WebElement cookies = driver.findElement(
                By.xpath("//div/p[@class='stick-footer-panel__description']")
//              By.className("stick-footer-panel__description")   более верный xpath
        );

        String actualResultPanelText = cookies.getText();
        Assert.assertEquals(actualResultPanelText, expectedResultPanelText);

        WebElement allowAllButton = driver.findElement(
                By.xpath("//div/button[@type='button']")
        );

        String actualResultAllowAll = allowAllButton.getText();
        Assert.assertEquals(actualResultAllowAll, expectedResultAllowAll);

        WebElement manageCookies = driver.findElement(
                By.xpath("//div/a[@href='/cookies-settings']")
        );

        String actualResultManageCookies = manageCookies.getText();
        Assert.assertEquals(actualResultManageCookies, expectedResultManageCookies);

        driver.quit();

    }

//  TC_11_01
//1.  Открыть базовую ссылку
//2.  Нажать на пункт меню Guide
//3.  Подтвердить, что вы перешли на страницу со ссылкой https://openweathermap.org/guide
//    и что title этой страницы OpenWeatherMap API guide - OpenWeatherMap
    @Test

 public void testTitleOpenWeatherMapAPIGuide() throws InterruptedException {

     System.setProperty("webdriver.chrome.driver", "/Users/elenura/Downloads/chromedriver");
     WebDriver driver = new ChromeDriver();

     String url = "https://openweathermap.org/";

     String expectedResultOpenPage = "https://openweathermap.org/guide";

     String expectedResultTitle = "OpenWeatherMap API guide - OpenWeatherMap";

     driver.get(url);
     Thread.sleep(5000);

     WebElement Title = driver.findElement(
             By.xpath("//div/ul/li/a[@href='/guide']")
     );
     Title.click();
     Thread.sleep(3000);

     String actualResultOpenPage = driver.getCurrentUrl();
     Assert.assertEquals(actualResultOpenPage,expectedResultOpenPage);

     String actualResultTitle = driver.getTitle();
     Assert.assertEquals(actualResultTitle,expectedResultTitle);

     driver.quit();

 }

// TC_11_02
//1.  Открыть базовую ссылку
//2.  Нажать на единицы измерения Imperial: °F, mph
// 3.  Подтвердить, что температура для города показана в Фарингейтах

@Test

    public void testUnitOfMeasureFahrenheit () throws InterruptedException {

    System.setProperty("webdriver.chrome.driver", "/Users/elenura/Downloads/chromedriver");
    WebDriver driver = new ChromeDriver();

    String url = "https://openweathermap.org/";

    boolean expectedResultFahrenheit = true;

    driver.get(url);
    Thread.sleep(5000);

    WebElement tempUnit = driver.findElement(
            By.xpath("//div[text()='Imperial: °F, mph']")
    );
    tempUnit.click();
    Thread.sleep(4000);

    WebElement tempUnitHeading = driver.findElement(
            By.xpath("//div[@class='current-temp']/span")
    );

    boolean actualResultFahrenheit = tempUnitHeading.getText().contains("°F");
    Assert.assertEquals(actualResultFahrenheit,expectedResultFahrenheit);

    driver.quit();
}

// TC_11_04
//1.  Открыть базовую ссылку
//2.  Подтвердить, что в меню Support есть 3 подменю с названиями “FAQ”, “How to start” и “Ask a question”

    @Test
    public void testSupportDropdownMenu() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/Users/elenura/Downloads/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";

        String expectedResult1 = "FAQ";
        String expectedResult2 = "How to start";
        String expectedResult3 = "Ask a question";

        driver.get(url);
        Thread.sleep(5000);

        WebElement Support = driver.findElement(
                By.xpath("//div[@id='support-dropdown']")
        );
        Support.click();
        Thread.sleep(4000);

        WebElement FAQ = driver.findElement(
                By.xpath("//ul[@class='dropdown-menu dropdown-visible']//a[@href='/faq']")
        );
        String actualResult1 = FAQ.getText();
        Assert.assertEquals(actualResult1,expectedResult1);

        WebElement howToStart = driver.findElement(
                By.xpath("//ul[@class='dropdown-menu dropdown-visible']//a[@href='/appid']")
        );
        String actualResult2 = howToStart.getText();
        Assert.assertEquals(actualResult2,expectedResult2);

        WebElement askQuestion = driver.findElement(
                By.xpath("//ul[@class='dropdown-menu dropdown-visible']//a[@href='https://home.openweathermap.org/questions']")
        );
        String actualResult3 = askQuestion.getText();
        Assert.assertEquals(actualResult3,expectedResult3);

        driver.quit();
    }

//    TC_11_05
//1. Открыть базовую ссылку
//2. Нажать пункт меню Support → Ask a question
//3. Заполнить поля Email, Subject, Message
//4. Не подтвердив CAPTCHA, нажать кнопку Submit
//5. Подтвердить, что пользователю будет показана ошибка “reCAPTCHA verification failed, please try again.”

    @Test
    public void testError_WhenSubmitInSupportWithoutCaptcha() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String eMail = "tester@test.com";
        String subject = "Other";
        String message = "lalala";
        String expectedResult = "reCAPTCHA verification failed, please try again.";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(   3000);

        WebElement menuSupport = driver.findElement(By.xpath("//div[@id='support-dropdown']"));
        menuSupport.click();
        Thread.sleep(500);

        WebElement dropDownAskAQuestion = driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']/li/a[@href='https://home.openweathermap.org/questions']"));
        dropDownAskAQuestion.click();
        Thread.sleep(5000);

        String originalWindow = driver.getWindowHandle();

        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.equals(windowHandle)) {
                driver.switchTo().window(windowHandle);
//                break;
            }
        }
        Thread.sleep(3000);

        WebElement emailField = driver.findElement(By.id("question_form_email"));
        emailField.click();
        emailField.sendKeys(eMail);

        WebElement subjectField = driver.findElement(By.id("question_form_subject"));
        subjectField.click();
        subjectField.sendKeys(subject);

        WebElement messageField = driver.findElement(By.id("question_form_message"));
        messageField.click();
        messageField.sendKeys(message);

        WebElement submitButton = driver.findElement(By.xpath("//div/input[@type='submit']"));
        submitButton.click();

        WebElement errorText = driver.findElement(By.xpath("//div[@class = 'help-block']"));

        String actualResult = errorText.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.close();
        driver.quit();
    }

//    TC_11_06
//1.  Открыть базовую ссылку
//2.  Нажать пункт меню Support → Ask a question
//3.  Оставить значение по умолчанию в checkbox Are you an OpenWeather user?
//4. Оставить пустым поле Email
//5. Заполнить поля  Subject, Message
//6. Подтвердить CAPTCHA
//7. Нажать кнопку Submit
//8. Подтвердить, что в поле Email пользователю будет показана ошибка “can't be blank”

    @Test
    public void testError_WhenSubmitInSupportAskAQuestionWithoutFillEmail() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String subject = "Other";
        String message = "lalala";
        String expectedResult = "can't be blank";

        driver.manage().window().maximize();
        driver.get(url);
        Thread.sleep(   3000);

        WebElement menuSupport = driver.findElement(By.xpath("//div[@id='support-dropdown']"));
        menuSupport.click();
        Thread.sleep(500);

        WebElement dropDownAskAQuestion = driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']/li/a[@href='https://home.openweathermap.org/questions']"));

        dropDownAskAQuestion.click();
        Thread.sleep(5000);

        String originalWindow = driver.getWindowHandle();

        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.equals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        Thread.sleep(3000);

        WebElement subjectField = driver.findElement(By.id("question_form_subject"));
        subjectField.click();
        subjectField.sendKeys(subject);

        WebElement messageField = driver.findElement(By.id("question_form_message"));
        messageField.click();
        messageField.sendKeys(message);

//        WebDriverWait wait = new WebDriverWait(driver, 20);

//        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
//                By.xpath("//div/iframe[@title='reCAPTCHA']")));

        WebElement captchaFrame = driver.findElement(By.xpath("//div/iframe[@title='reCAPTCHA']"));
        driver.switchTo().frame(captchaFrame);
        Thread.sleep(2000);

        WebElement captchaBox = driver.findElement(By.xpath("//div[@class='recaptcha-checkbox-border']"));
        captchaBox.click();
        Thread.sleep(15000);

        driver.switchTo().defaultContent();

        WebElement submitButton = driver.findElement(By.xpath("//div/input[@type='submit']"));
        Thread.sleep(2000);
        submitButton.click();

        WebElement errorEmail = driver.findElement(By.xpath("//div/span[@class='help-block']"));
        String actualResult = errorEmail.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.close();
        driver.quit();
    }

//    TC_11_07
//1.  Открыть базовую ссылку
//2.  Нажать на единицы измерения Imperial: °F, mph
//3.  Нажать на единицы измерения Metric: °C, m/s
//4.  Подтвердить, что в результате этих действий, единицы измерения температуры изменились с F на С

    @Test
    public void testUnitOfMeasureFtoC() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/Users/elenura/Downloads/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";

        boolean expectedResultFahrenheit = true;

        driver.get(url);
        Thread.sleep(5000);

        WebElement tempUnitF = driver.findElement(
                By.xpath("//div[text()='Imperial: °F, mph']")
        );
        tempUnitF.click();
        Thread.sleep(4000);

        WebElement tempUnitC = driver.findElement(
                By.xpath("//div[text()='Metric: °C, m/s']")
        );
        tempUnitC.click();
        Thread.sleep(4000);

        WebElement tempUnitHeadingC = driver.findElement(
                By.xpath("//div[@class='current-temp']/span")
        );

        boolean actualResultFahrenheit = tempUnitHeadingC.getText().contains("°C");

        Assert.assertEquals(actualResultFahrenheit,expectedResultFahrenheit);

        driver.quit();
    }

// TC_11_08
//1.  Открыть базовую ссылку
//2.  Нажать на лого компании
//3.  Дождаться, когда произойдет перезагрузка сайта, и подтвердить, что текущая ссылка не изменилась

    @Test
    public void  testLogoNotChangeURL() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/Users/elenura/Downloads/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";

        String expectedResult = "https://openweathermap.org/";

        driver.get(url);
        Thread.sleep(5000);

        WebElement logo = driver.findElement(
                By.xpath("//img[@src = '/themes/openweathermap/assets/img/logo_white_cropped.png']")
        );
////  //li[@class = 'logo']//img[@src = '/themes/openweathermap/assets/img/logo_white_cropped.png']
        logo.click();
        Thread.sleep(5000);

        String actualResult = driver.getCurrentUrl();
        Assert.assertEquals(actualResult,expectedResult);

        driver.quit();
    }

// TC_11_09
//1.  Открыть базовую ссылку
//2.  В строке поиска в навигационной панели набрать “Rome”
//3.  Нажать клавишу Enter
//4.  Подтвердить, что вы перешли на страницу в ссылке которой содержатся слова “find” и “Rome”
//5.  Подтвердить, что в строке поиска на новой странице вписано слово “Rome”

    @Test
    public void testWeatherInYourCityField() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/Users/elenura/Downloads/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String cityName = "Rome";
        boolean expectedResult = true;

//        String expectedResult = "https://openweathermap.org/find?q=Rome";
        String expectedResult2 = "Rome";

        driver.get(url);
        Thread.sleep(5000);

        WebElement weatherInYourCityField = driver.findElement(
                By.xpath("//div[@id = 'desktop-menu']//input[@placeholder='Weather in your city']")
        );
        weatherInYourCityField.click();
        weatherInYourCityField.sendKeys(cityName);
        weatherInYourCityField.sendKeys(Keys.ENTER);

        boolean actualResult = driver.getCurrentUrl().contains("find");
        Assert.assertEquals(actualResult, expectedResult);

        WebElement searchField = driver.findElement(
                By.xpath("//div[@class='form-group']/input[@class='form-control border-color col-sm-12']")
//             By.xpath("/html/body/main/div[2]/div/div/div/form/div/input"


        );
        String actualResult2 = searchField.getAttribute("value");
        Assert.assertEquals(actualResult2, expectedResult2);

        driver.quit();
    }

//  TC_11_10
//1.  Открыть базовую ссылку
//2.  Нажать на пункт меню API
//3.  Подтвердить, что на открывшейся странице пользователь видит 30 оранжевых кнопок
    @Test

    public void testNumberOfOrangeButtons() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/Users/elenura/Downloads/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";

        driver.get(url);
        Thread.sleep(5000);

        WebElement menuAPI = driver.findElement(
                By.xpath("//li/a[@href='/api']")
        );
        menuAPI.click();
        Thread.sleep(2000);

        List<WebElement> orangeButtons = driver.findElements(
                By.xpath("//a[@type='button' and contains(@class,'orange') or contains(@class, 'btn-orange')]")
        );
        int actualResult = orangeButtons.size();
        int expectedResult = 30;
        Assert.assertEquals(actualResult,expectedResult);

        driver.quit();

//  //input[@name='ololo' and contains (@value, 'tololo')]  xpath with multiple attributes 

    }






}
