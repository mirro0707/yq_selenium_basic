package com.yq.po.cases;

import com.yq.po.pages.RegisterPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * po-case层（业务层）（实际可以放在test的java代码中，其它层放在main的java中）
 *
 * 完成163邮箱的注册
 * 别人的产品，验证码无法处理
 * Created by YQ on 2017/3/5.
 */
public class EmailRegister {
    WebDriver driver;

    @BeforeMethod
    public void openChrome() {
        System.setProperty("webdriver.chrome.driver",".\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }


    @Test
    public void registerTest() {
        long time = System.currentTimeMillis();
        driver.get("http://mail.163.com");
        driver.switchTo().frame("x-URS-iframe");//转交给iframe
        driver.findElement(RegisterPage.regiterButton).click();//注册按钮
        String Handle1 = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            if (handle.equals(Handle1)) {
                continue;
            } else {
                driver.switchTo().window(handle);//转交给新窗口
            }
        }

        driver.findElement(RegisterPage.registerMail).click();//注册字母邮箱
        driver.findElement(RegisterPage.mailAddr).sendKeys('e'+String.valueOf(time));//邮件地址
        driver.findElement(RegisterPage.pwd).sendKeys("yq1234");//密码
        driver.findElement(RegisterPage.rePwd).sendKeys("yq1234");//确认密码
        driver.findElement(RegisterPage.phone).sendKeys(String.valueOf(time/100));//手机号

        driver.findElement(RegisterPage.captcha).sendKeys("45lh5");//验证码
        driver.findElement(RegisterPage.smsCaptcha).sendKeys("123123");//短信验证码

        driver.findElement(RegisterPage.regiterOnce).click();//立即注册
        String text = driver.findElement(By.xpath(".//*[@id='m_mainAcode']/span")).getText();
        Assert.assertEquals(text, "  手机验证码不正确，请重新填写");
    }

    @AfterMethod
    public void closeChrome() throws InterruptedException {
        Thread.sleep(3000);
//        driver.quit();
    }

}
