package com.yq.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * 完成正确账号的登录
 * 完成错误账号的登录
 * 动态id能不能模糊匹配定位？？？？？
 * Created by YQ on 2017/3/5.
 */
public class EmailLogin {
    WebDriver driver;
    @BeforeMethod
    public void openChrome() {
        System.setProperty("webdriver.chrome.driver",".\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);//对于浮框怎么不起作用？？？？！！！！

    }
    @Test
    public void LoginSuccessTest() throws InterruptedException {
        login(driver,"meyoungTester","meyoung123");
        //找不到合适的定位方式，绝对定位
        String text = driver.findElement(By.xpath("html/body/div[2]/div[1]/div/div/div/div[2]/div[2]/span[1]/span[1]")).getText();
        Assert.assertEquals(text,"meyoungtester");

    }


    /**
 * 输入错误密码
 */
    @Test
    public void LoginFailTest() throws InterruptedException {
        login(driver,"meyoungTester","123123");
        //找不到合适的定位方式，绝对定位
        String text = driver.findElement(By.xpath(".//*[@id='nerror']/div[2]")).getText();
        Assert.assertEquals(text,"帐号或密码错误");

    }

    /**
     * 登录163邮箱
     * @param driver
     * @param username
     * @param pwd
     * @throws InterruptedException
     */
    public static void login(WebDriver driver, String username, String pwd) throws InterruptedException {
        driver.get("http://mail.163.com");
        driver.switchTo().frame("x-URS-iframe");//转交给iframe
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(pwd);
        //让验证码出来，手动点击
        driver.findElement(By.id("dologin")).click();
        Thread.sleep(3000);
        //这个等待为什么不行！！！！！！！！！！！！！？？？？？？？？？？？？
//        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.name("checkcode")));
        driver.findElement(By.name("checkcode")).click();//验证码难以获取，手动输入吧！
        Thread.sleep(15000);//手动输入验证码时间

        driver.findElement(By.id("dologin")).click();//登录
        Thread.sleep(2000);
    }


    @AfterMethod
    public void closeChrome() throws InterruptedException {
        Thread.sleep(3000);
//        driver.quit();
    }

}
