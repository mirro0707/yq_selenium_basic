package com.yq.datadriver;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * 如果你测试代码中有需要用到参数，并且参数在xml文件中声明了，那么运行的时候一定通过xml文件来执行测试。
 * 不然参数会找不到，用例会Skips !!!
 * testngParameter.xml
 */
public class ParameterDemo {
    private WebDriver driver;    //声明一个全局变量driver

    @BeforeTest
    public void beforeTest() {
        driver = new FirefoxDriver();
        //设置等待页面完全加载的时间是10秒，如果在10秒内加载完毕，剩余时间不在等待
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();//最大化浏览器
        driver.get("https://www.baidu.com");
    }

    @Test
    @Parameters({"keyword"}) //参数化
    public void parameterTest(String keyword) {
        By inputBox = By.id("kw");//输入框
        By searchButton = By.id("su");//按钮
        intelligentWait(driver, 10, inputBox);//智能等待元素加载出来
        intelligentWait(driver, 10, searchButton);//智能等待元素加载出来
        driver.findElement(inputBox).sendKeys(keyword);//输入内容
        driver.findElement(searchButton).click();//点击查询

        try {
            Thread.sleep(2000);//硬性等待一会儿，便于观察结果
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @AfterTest
    public void afterTest() {
        driver.quit();
    }

    /**
     * 这是智能等待元素加载的方法
     */
    public void intelligentWait(WebDriver driver, int timeOut, final By by) {
        try {
            (new WebDriverWait(driver, timeOut)).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    WebElement element = driver.findElement(by);
                    return element.isDisplayed();
                }
            });
        } catch (TimeoutException e) {
            Assert.fail("超时!! " + timeOut + " 秒之后还没找到元素 [" + by + "]", e);
        }
    }
}
