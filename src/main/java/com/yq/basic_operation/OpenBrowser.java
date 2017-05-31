package com.yq.basic_operation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * CTRL+SHIFT+ENTER自动补全
 * WebDriver在启动浏览器时，都是启动一个干净的没有任务插件及cookies信息的浏览器
 * 查看dirver.exe版本，直接双击打开即可
 */
public class OpenBrowser {
    WebDriver driver;

    /**
     * 火狐浏览器（默认安装时候），48+才需要下载geckodriver
     * https://github.com/mozilla/geckodriver/releases
      */
    @Test
    public void  openFireFoxbyDefault(){

        driver = new FirefoxDriver();//启动浏览器
    }

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
     * IE浏览器
     * 版本没有太大要求，和selenium对应即可！！建议用32位，还没出现过什么问题
     * http://selenium-release.storage.googleapis.com/index.html?path=2.51/
     */
    @Test
    public void  openIE(){

        System.setProperty("webdriver.ie.driver",".\\drivers\\IEDriverServer.exe");//设置IE的实际安装路径
        driver = new InternetExplorerDriver();
    }

    /**
     * edge浏览器（win10才有）
     * https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/
     */

    @Test
    public void  openEdge(){

        System.setProperty("webdriver.edge.driver",".\\drivers\\MicrosoftWebDriver.exe");//设置edge的实际安装路径
        driver = new EdgeDriver();
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
