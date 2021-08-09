package test2;

import helps.Log;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import helps.Driver;
public class Before {
    protected WebDriver driver = Driver.getChromeDriver();

    @BeforeAll
    public void startUp() throws InterruptedException {
        //driver.get("http://a.testaddressbook.com/sign_in");
       // String currentURL = driver.getCurrentUrl();
        //Assertions.assertEquals("http://a.testaddressbook.com/sign_in", currentURL, "Открыта не та страница");
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
}