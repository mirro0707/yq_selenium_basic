package com.yq.CI;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * 此类用于jenkins集成测试
 */
public class OpenBrowserTest {
    WebDriver driver;

    /**
     * 火狐浏览器（非默认安装时候），需要设置实际安装路径
     */
    @Test
    public void  openFireFoxbyDirectory(){

        System.setProperty("webdriver.firefox.bin","C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
        driver = new FirefoxDriver();
    }

    /**
     * chrome浏览器
     * 下载chrome driver(根据本地版本下载）
     * https://sites.google.com/a/chromium.org/chromedriver/downloads
     *
     */
    @Test
    public void  openChrome(){

        System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");//设置chrome的实际安装路径
        driver = new ChromeDriver();
    }


    /**
     * AfterMethod 在每一个@Test之后都会执行
     * close和quit有什么区别？
     * 1）driver.quit()会把chromedriver进程关闭--这是真正的关闭浏览器
     * 2）driver.close()不会关闭chromedriver进程
     */
    @AfterMethod
    public void close() throws InterruptedException {

//        Thread.sleep(5000);
        driver.quit();//关闭所有窗口并退出
//        driver.close();
    }
}
