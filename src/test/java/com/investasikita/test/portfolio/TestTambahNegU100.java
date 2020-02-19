/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.investasikita.test.portfolio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 *
 * @author ali
 */
public class TestTambahNegU100 {

    private WebDriver driver;

    @Test
    public void testtambahNEGu100() throws InterruptedException, FileNotFoundException, IOException {

        System.out.println("-----------------------------TEST NEGATIVE PORTFOLIO TAMBAH UNDER 100K !!!!!!!!!!!-----------------------------");
        driver.get(readStream().getProperty("url"));
        Dimension d = new Dimension(411,823);
        //Resize current window to the set dimension
        driver.manage().window().setSize(d);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Thread.sleep(15000); 
        driver.findElement(By.xpath("//header[2]/div[2]/div/div/button/img")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("login-email")).sendKeys(readStream().getProperty("login.email"));
        driver.findElement(By.id("login-password")).sendKeys(readStream().getProperty("login.password"));
        driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//header[2]/div[2]/div/div/button/img")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//a[contains(@href, '#/portfolio')]")).click();
        Thread.sleep(3000);
        //CLICK TAMBAH
        driver.get("http://192.168.2.200:8083/#/portfolio-detail/U2FsdGVkX185bVAelvwT7RfXIx3Z1ZX9ef8mtY7gdiIMl32");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[contains(text(),'Beli')]")).click();
        Thread.sleep(3000);

//        try {
//            WebElement element1 = driver.findElement(By.id("trx-order-totalAmount"));
//            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
//            element1.sendKeys("Wih Iwan");
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//
//        System.out.println("sukses! inputan numeric");
//        Thread.sleep(3000);
        //click tanpa checkbox
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='keterangan ringkas'])[1]/following::button[1]")).click();
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,900)");
        Thread.sleep(3000);

        //CLICK CHECK BOX
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Instruksi pembelian akan otomatis dibatalkan jika tidak ada pembayaran 1x24 jam dari order kamu.'])[1]/following::label[1]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[3]/div/label")).click();
        Thread.sleep(3000);

        WebElement element1 = driver.findElement(By.id("trx-order-totalAmount"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
        element1.sendKeys("150");

        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='keterangan ringkas'])[1]/following::button[1]")).click();
        Thread.sleep(2000);

        element1.clear();
        element1.sendKeys("700000");

        System.out.println("sukses! inputan diatas batas bawah");
        Thread.sleep(2000);

        //CLICK BAYAR
        //CLICK BAYAR
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='keterangan ringkas'])[1]/following::button[1]")).click();
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,900)");
        Thread.sleep(3000);

        driver.findElement(By.xpath("//h3/img")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//div[@id='headingOne']/div/label)[4]")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("(//button[@type='submit'])")).click();
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,900)");
        Thread.sleep(3000);

        driver.findElement(By.xpath("//a[contains(text(),'Kembali ke Beranda')]")).click();
        Thread.sleep(3000);
        System.out.println("-----------------------------end of TEST NEGATIVE PORTFOLIO TAMBAH UNDER 100K!!!!!!!!!!!-----------------------------");
        Thread.interrupted();

    }

    public Properties readStream() {
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream("src/main/resources/application-test.properties")) {
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return prop;
    }

    @BeforeTest
    public void beforeTest() {

        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.cache.disk.enable", false);
        profile.setPreference("browser.cache.memory.enable", false);
        profile.setPreference("browser.cache.offline.enable", false);
        profile.setPreference("network.http.use-cache", false);

        System.setProperty("webdriver.gecko.driver", readStream().getProperty("driver.gecko"));
        driver = new FirefoxDriver();
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}
