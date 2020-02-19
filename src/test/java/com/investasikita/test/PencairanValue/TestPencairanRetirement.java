/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.investasikita.test.PencairanValue;

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
public class TestPencairanRetirement {

    private WebDriver driver;

    @Test
    public void testpencairan() throws InterruptedException, FileNotFoundException, IOException {

        System.out.println("-----------------------------TEST ROBO KENDARAAN DETAIL NABUNG!!!!!!!!!!!-----------------------------");
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

        //click RETIREMENT
        driver.findElement(By.xpath("//a[contains(text(),'Jangan Dihapus Pensiun Dini')]")).click();
        Thread.sleep(3000);
        //SELECT PENCAIRAN
        driver.findElement(By.xpath("//div[2]/div[2]/div[2]/div/button")).click();
        Thread.sleep(3000);
        //get value first
        WebElement tabungan1 = driver.findElement(By.xpath("//div[2]/div/div/span"));
        System.out.println(tabungan1.getText());

        //get value second
        WebElement tabungan2 = driver.findElement(By.xpath("//div[3]/div[2]/div/div/span"));
        System.out.println(tabungan2.getText());

        //get value third 
        WebElement tabungan3 = driver.findElement(By.xpath("//div[4]/div[2]/div/div/span"));
        System.out.println(tabungan3.getText());

        //get value TOTAL tabungan saat ini
        WebElement saved = driver.findElement(By.xpath("//div/p[2]"));
        System.out.println(saved.getText());

        //get value fee
        WebElement fee = driver.findElement(By.xpath("//form/section/div/div/p"));
        System.out.println(fee.getText());

        //ganti (,) to (.) 
        //tabungan 1
        String val1 = String.valueOf(tabungan1.getText());
        String val2 = val1.replaceAll("Rp. ", "").replaceAll("\\.", "").replaceAll("\\,00", "");
        double a = Double.valueOf(val2);
        System.out.println("source1 = " + a);

        //tabungan 2
        String val3 = String.valueOf(tabungan2.getText());
        String val4 = val3.replaceAll("Rp. ", "").replaceAll("\\.", "").replaceAll("\\,00", "");
        double b = Double.valueOf(val4);
        System.out.println("source2  = " + val4);

        //tabungan 3
        String val5 = String.valueOf(tabungan3.getText());
        String val6 = val5.replaceAll("Rp. ", "").replaceAll("\\.", "").replaceAll("\\,00", "");
        double c = Double.valueOf(val6);
        System.out.println("source3 = " + c);

        String val7 = String.valueOf(saved.getText());
        System.out.println("val 7 = " + val7);
        String val8 = val7.replaceAll("Total Estimasi Pencairan: Rp. ", "").replaceAll("\\.", "").replaceAll(",00", "");
        double z = Double.valueOf(val8);
        System.out.println("saved = " + z);

        String val9 = String.valueOf(fee.getText());
        String val10 = val9.replaceAll("Total Fee: Rp. ", "").replaceAll("\\.", "").replaceAll(",00", "");
        double e = Double.valueOf(val10);
        System.out.println("fee = " + e);

        double hasil = (a + b + c + e);
        System.out.println(hasil);

        double out = Math.round(hasil * 100.0) / 100.0;
        System.out.println("hasil: " + Math.round(hasil * 100.0) / 100.0);

        if (out == z) {
            System.out.println("true");
            System.out.println(out);
        } else {
            System.out.println("false");
            driver.findElement(By.xpath("//bahaya hitungan tidak sesuai!!'")).click();
        }

        System.out.println("-----------------------------end of TEST ROBO KENDARAAN DETAIL NABUNG!!!!!!!!!!!-----------------------------");
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
