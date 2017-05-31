package com.yq.po.cases;

import com.yq.po.pages.LoginPage;
import com.yq.po.service.UtilsMail;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * po-case层（业务层）
 *
 * 使用页面库的定位方式
 * Created by YQ on 2017/3/8.
 * 业务层
 */
public class Login163 {
    WebDriver driver;
    @BeforeMethod
    public void openChrome() {
        System.setProperty("webdriver.chrome.driver",".\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);//对于浮框怎么不起作用？？？？！！！！

    }
    @Test
    public void login() throws InterruptedException {
        driver.get("http://mail.163.com");
        UtilsMail.login(driver,"meyoungTester","meyoung123");


        driver.findElement(LoginPage.loginButton).click();//登录
        //找不到合适的定位方式，绝对定位
        String text = driver.findElement(By.xpath("html/body/div[2]/div[1]/div/div/div/div[2]/div[2]/span[1]/span[1]")).getText();
        Assert.assertEquals(text,"meyoungtester");
    }


    @AfterMethod
    public void closeChrome() throws InterruptedException {
        Thread.sleep(3000);
//        driver.quit();
    }
}
