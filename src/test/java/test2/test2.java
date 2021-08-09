package test2;
import helps.Driver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import helps.Log;
import static locators.Locators.*;
import test2.Before.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class test2 {

    private WebDriver driver = Driver.getChromeDriver();

    @BeforeAll
    public void startUp() throws InterruptedException {
       Log.info("открываем страницу a.testaddressbook.com/sign_in");
        driver.get("http://a.testaddressbook.com/sign_in");
       Log.info("проверяем, что открылась конкретная страница a.testaddressbook.com");
        String pageTitle = driver.getTitle();
        Assertions.assertEquals("Address Book",pageTitle,"--открыта не та страница--");
        Log.info("Пишем в строку логина свой логин");
        driver.findElement(By.id("session_email")).sendKeys("juliet-ka@mail.ru");
        Log.info("Пишем в строку пароля свой пароль");
        driver.findElement(By.name("session[password]")).sendKeys("2202");
        Log.info("Нажимаем на кнопку входжения");
        driver.findElement(By.cssSelector("input[class='btn btn-primary']")).click();
        Log.info("Возвращаемся на главную страницу");
        driver.findElement(By.cssSelector("a[data-test='home']")).click();
        Thread.sleep(5000);
    }

    @Test
    @Order(1)
    @DisplayName("Тест, который добавляет адрес")
    public void testAddress() throws InterruptedException {
        Log.info("Нажимаем на кнопку адресов");
        driver.findElement(ADDRESSES).click();
        Log.info("Нажимаем на кнопку для ввода нового адреса");
        driver.findElement(By.cssSelector("a[href='/addresses/new']")).click();
        Log.info("Вводим данные");
        driver.findElement(By.id("address_first_name")).sendKeys("Julija");
        driver.findElement(By.id("address_last_name")).sendKeys("Yaholovich");
        driver.findElement(By.name("address[address1]")).sendKeys("Belarus");
        driver.findElement(By.name("address[address2]")).sendKeys("-");
        driver.findElement(By.name("address[city]")).sendKeys("Molodechno");
        driver.findElement(By.cssSelector("select[name='address[state]']")).click();
        driver.findElement(By.cssSelector("option[value='FL']")).click();
        driver.findElement(By.name("address[zip_code]")).sendKeys("123456789");
        driver.findElement(By.cssSelector("input[value='us']")).click();
        driver.findElement(By.name("address[birthday]")).sendKeys("22.02/1987");
        driver.findElement(By.name("address[color]")).sendKeys("#123456");
        driver.findElement(By.name("address[age]")).sendKeys("34");
        driver.findElement(By.name("address[website]")).sendKeys("https://proza.ru/");
        driver.findElement(By.name("address[phone]")).sendKeys("1234567890");
        driver.findElement(By.cssSelector("input[id='address_interest_climb']")).click();
        driver.findElement(By.name("address[note]")).sendKeys("-");
        Log.info("Нажать на кнопку добавить");
        driver.findElement(By.cssSelector("input[name='commit']")).click();
        Thread.sleep(5000);
    }
    @Test
    @Order(2)
    @DisplayName("Тест,который изменяет адрес")
    public void testChangesAddress() throws InterruptedException {
        Log.info("нажимаем кнопку адресов");
        driver.findElement(ADDRESSES).click();
        Log.info("нажимаем кнопку для изменения");
        driver.findElement(By.xpath(".//a[text()='Edit']")).click();
        Log.info("меняем данные");
        driver.findElement(By.name("address[address2]")).sendKeys("France");
        Log.info("подтверждаем");
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        Thread.sleep(5000);
    }
    @Test
    @Order(3)
    @DisplayName("Тест,который удаляет адрес")
    public void testDeleteAddress() throws InterruptedException {
        Log.info("нажимаем кнопку адресов");
        driver.findElement(ADDRESSES).click();
        Log.info("нажимаем на кнопку удаления адреса");
        driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[7]/a")).click();
        //driver.findElement(By.cssSelector(" tr:first-child > td:nth-of-type(7)")).click();
        Log.info("нажимаем кнопку подтверждения на всплывающем окне");
        driver.switchTo().alert().accept();
        Thread.sleep(5000);
    }

    @AfterAll
    public void testQuit() {
        Log.info("нажимаем на кнопку выхода");
        driver.findElement(By.cssSelector("a[href='/sign_out']")).click();
        Log.info("Закрываем браузер");
        driver.quit();
    }
}