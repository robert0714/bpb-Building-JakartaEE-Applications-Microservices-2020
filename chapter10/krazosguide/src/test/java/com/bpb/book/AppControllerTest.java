package com.bpb.book;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static org.junit.Assert.assertEquals;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testcontainers.containers.BrowserWebDriverContainer;

public class AppControllerTest {

    String url = "http://192.168.0.5:8080/krazosguide";
 
  

    @Rule
    public BrowserWebDriverContainer chrome
            = new BrowserWebDriverContainer()
                    .withCapabilities(new ChromeOptions());

    @Test
    public void viewTest() {
        RemoteWebDriver driver = chrome.getWebDriver();
        driver.get(url + "/mvc/app/view");
     
        WebElement element = driver.findElement(By.name("textfield"));
        String resource = element.getAttribute("value");
        assertEquals("view", resource);
        driver.close();
    }

    @Test
    public void cdiTest() {
       
        RemoteWebDriver driver = chrome.getWebDriver();
        driver.get(url + "/mvc/app/cdi");
        WebElement element = driver.findElement(By.id("message"));
        String message = element.getAttribute("innerHTML");
        assertEquals("Welcome from CDI", message);
        

        driver.close();
    }

    @Test
    public void viewable() {
        RemoteWebDriver driver = chrome.getWebDriver();
        driver.get(url + "/mvc/app/viewable");
        WebElement element = driver.findElement(By.id("message"));
        
          //String heading = driver.findElement(By.xpath("/html/body/div/h1")).getText();
        String message = element.getAttribute("innerHTML");
        assertEquals("Welcome from viewable", message);
        driver.close();
    }

}
