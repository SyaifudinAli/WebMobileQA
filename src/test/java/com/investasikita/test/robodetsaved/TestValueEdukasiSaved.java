/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.investasikita.test.robodetsaved;

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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 *
 * @author ali
 */
public class TestValueEdukasiSaved {
    private WebDriver driver;

    @Test
    public void testvalue() throws InterruptedException, FileNotFoundException, IOException {
        System.out.println("-----------------------------TEST ROBO VALUE edukasi Saved !!!!!!!!!!!-----------------------------");

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

        //click edukasi
        driver.findElement(By.xpath("//a[contains(text(),'Jangan Dihapus Edukasi')]")).click();
        Thread.sleep(3000);

        try {
            long lastHeight = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");

            while (true) {
                ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
                Thread.sleep(2000);

                long newHeight = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");
                if (newHeight == lastHeight) {
                    break;
                }
                lastHeight = newHeight;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread.sleep(2000);

        //get value first
        WebElement tabungan1 = driver.findElement(By.xpath("//p[8]/span"));
        System.out.println(tabungan1.getText());

        //get value second
        WebElement tabungan2 = driver.findElement(By.xpath("//section[2]/p[8]/span"));
        System.out.println(tabungan2.getText());

        //get value third 
        WebElement tabungan3 = driver.findElement(By.xpath("//section[3]/p[8]/span"));
        System.out.println(tabungan3.getText());

        //get value TOTAL tabungan saat ini
        WebElement saved = driver.findElement(By.xpath("//div[2]/div[2]/p"));
        System.out.println(saved.getText());

        //ganti (,) to (.) 
        //tabungan 1
        String val1 = String.valueOf(tabungan1.getText());
        String val2 = val1.replaceAll("\\,", "").replaceAll("\\.00", "");
        double a = Double.valueOf(val2);
        System.out.println("source1 = " + a);
 
        //tabungan 2
        String val3 = String.valueOf(tabungan2.getText());
        String val4 = val3.replaceAll("\\,", "").replaceAll("\\.00", "");
        double b = Double.valueOf(val4);
        System.out.println("source2  = " + val4);

        //tabungan 3
        String val5 = String.valueOf(tabungan3.getText());
        String val6 = val5.replaceAll("\\,", "").replaceAll("\\.00", "");
        double c = Double.valueOf(val6);
        System.out.println("source3 = " + c);

        String val7 = String.valueOf(saved.getText());
        String val8 = val7.replaceAll("\\.", "").replaceAll(",00", "");
        double f = Double.valueOf(val8);
        System.out.println("saved = " + f);

        double hasil = (a + b + c);
        System.out.println(hasil);
        
        double out = Math.round(hasil * 100.0) / 100.0;
        System.out.println("hasil: " + Math.round(hasil * 100.0) / 100.0);

        if (out == f) {
            System.out.println("true");
            System.out.println(out);
        } else {
            System.out.println("false");
            double selisih = out - f ;
            System.out.println("selisih = " + selisih);
            driver.findElement(By.xpath("//bahaya hitungan tidak sesuai!!'")).click();
        }
        System.out.println("-----------------------------end of TEST ROBO VALUE edukasi Saved!!!!!!!!!!!-----------------------------");
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
//        driver.quit();
    }           
}
