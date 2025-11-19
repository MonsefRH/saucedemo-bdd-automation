package com.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.ObjectUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.List;

public class SeleniumTest {

    WebDriver driver;

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testGoogleTitle() {
        String url = "https://www.saucedemo.com/";
        driver.get(url);
        String title = driver.getTitle();
        List<WebElement> elements = driver.findElements(By.tagName("input"));
        for (WebElement element : elements) {
            element.sendKeys("yeees");
        }
        for (WebElement e : elements){
            String type = e.getAttribute("type");
            if (type == null || !type.equalsIgnoreCase("submit")){
                String text = e.getAttribute("value");
                System.out.println(text);
                assert text.equals("yeees");
            }
        }
    }

    @AfterTest
    public void tearDown() throws InterruptedException {
        if (driver != null) {
            Thread.sleep(3000);
            driver.quit();
        }
    }
}
