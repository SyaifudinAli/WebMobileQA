/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.investasikita.test.NilaiNabung;

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
public class Test_NilaiOrderTravell {
    private WebDriver driver;

    @Test
    public void testOther() throws InterruptedException, FileNotFoundException, IOException {

        System.out.println("-----------------------------TEST ROBO TRAVELL DETAIL NABUNG!!!!!!!!!!!-----------------------------");
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

        //click travell
        driver.findElement(By.xpath("//a[contains(text(),'1jangan Dihapus Travell')]")).click();
        Thread.sleep(3000);

        //SELECT NABUNG
        driver.findElement(By.xpath("//div[2]/button")).click();
        Thread.sleep(3000);

        //get value nilai 
        WebElement total = driver.findElement(By.xpath("//div/p[3]"));
        System.out.println(total.getText());
        
        String total1 = String.valueOf(total.getText());
        String total2 = total1.replaceAll("Total : Rp", "").replaceAll("\\.", "");
        System.out.println("total = " + total2);
        double a = Double.valueOf(total2);
        System.out.println("total  = " + a);

        driver.findElement(By.xpath("//section/button")).click();
        Thread.sleep(3000);

        //BAYAR
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Virtual Account'])[1]/img[1]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[4]/div/div/label")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//div[3]/div/div/label")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//section/button")).click();
        Thread.sleep(3000);

        //get value biaya nilai 
        WebElement va = driver.findElement(By.xpath("//div[2]/p[2]"));
        System.out.println(va.getText());

        String va1 = String.valueOf(va.getText());
        String va3 = va1.replaceAll("Rp. ", "").replaceAll("\\.", "");
        System.out.println("va = " + va3);
        double b = Double.valueOf(va3);
        System.out.println("va  = " + b);
        
        //get value total nilai 
        WebElement nilai = driver.findElement(By.xpath("//div/p[7]"));
        System.out.println(nilai.getText());
        
        String nilai1 = String.valueOf(nilai.getText());
        String nilai2 = nilai1.replaceAll("Rp. ", "").replaceAll("\\.", "");
        System.out.println("nilai = " + nilai2);
        double c = Double.valueOf(nilai2);
        System.out.println("nilai  = " + c);
        
        
        double jumlahall = a + b ;
        
        if(jumlahall == c ){
            System.out.println("True");
        }else{
            System.out.println("False");
            double selisih = jumlahall - c;
            System.out.println(jumlahall + "-" + c + "= " + selisih);
            System.out.println("tidak sama, selisih sama dengan = " + selisih);
            driver.findElement(By.xpath("// hitungan tidak sesuai!!'")).click();
            
        }
                
        System.out.println("-----------------------------end of TEST ROBO TRAVELL DETAIL NABUNG!!!!!!!!!!!-----------------------------");
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