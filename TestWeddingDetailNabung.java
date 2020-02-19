/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.investasikita.test.robodet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 *
 * @author ali
 */
public class TestWeddingDetailNabung {

    private WebDriver driver;

    @Test
    public void testWedding() throws InterruptedException, FileNotFoundException, IOException {

        System.out.println("-----------------------------ROBO DASHBOARD WEDDING!!!!!!!!!!!-----------------------------");
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
        driver.findElement(By.xpath("//a[contains(text(),'Tabungan Saya')]")).click();
        Thread.sleep(3000);
               
        //click WEDDING
        driver.findElement(By.xpath("//a[contains(text(),'Jangan Dihapus Nikah')]")).click();
        Thread.sleep(3000);

        //SELECT NABUNG
        driver.findElement(By.xpath("//div[2]/button")).click();
        Thread.sleep(3000);
        //SELECT 
        driver.findElement(By.xpath("//h3/img")).click();
        Thread.sleep(3000);
        
        driver.findElement(By.xpath("//section/button")).click();
        Thread.sleep(3000);

        //BAYAR
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Virtual Account'])[1]/img[1]")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//div[2]/div/div/div/div/label")).click();
        Thread.sleep(3000);
        
        driver.findElement(By.xpath("//section/button")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Informasi Rekening Pembayaran'])[1]/following::button[1]")).click();
        Thread.sleep(3000);
        
        //cek virtual account 
        WebElement va = driver.findElement(By.xpath("//div/div/div/div[2]/p"));
        System.out.println(va.getText());

        String val1 = String.valueOf(va.getText());
        String val2 = val1.replaceAll("Virtual Account", "").replaceAll("[()]", "");
        System.out.println("val 1 = " + val1);
        System.out.println("val 2 = " + val2);
        double a = Double.valueOf(val2);
        System.out.println("va = " + a);

        double real = 8890822145516795.0;

        if (real == a) {
            System.out.println("virtual Account tersedia = " + a);
        } else {
            System.out.println("false");
            driver.findElement(By.xpath("//Virtual account tidak tersedia!!'")).click();
        }  

         System.out.println("-----------------------------end of ROBO DASHBOARD WEDDING!!!!!!!!!!!-----------------------------");
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
        System.setProperty("webdriver.gecko.driver", readStream().getProperty("driver.gecko"));
        driver = new FirefoxDriver();
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }

}
