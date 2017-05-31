package com.yq.po.service;

import com.yq.po.pageobject.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * po-逻辑层
 * 公共的方法抽取出来
 * Created by YQ on 2017/3/8.
 * 逻辑层
 */
public class UtilsMail {
    public static void login(WebDriver driver, String username, String pwd) throws InterruptedException {
        driver.switchTo().frame("x-URS-iframe");//转交给iframe
        driver.findElement(LoginPage.emailTextField).clear();
        //输入用户和密码
        driver.findElement(LoginPage.emailTextField).sendKeys(username);
        driver.findElement(LoginPage.pwdTextField).sendKeys(pwd);
        //让验证码出来，手动点击
        driver.findElement(By.id("dologin")).click();
        Thread.sleep(3000);
        //这个等待为什么不行！！！！！！！！！！！！！？？？？？？？？？？？？
//        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.name("checkcode")));
        driver.findElement(By.name("checkcode")).click();//验证码难以获取，手动输入吧！
        Thread.sleep(10000);//手动输入验证码时间
    }
}
