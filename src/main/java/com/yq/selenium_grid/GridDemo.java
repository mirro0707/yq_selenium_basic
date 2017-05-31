package com.yq.selenium_grid;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 机器键可以ping通
 * 全部本机演示：4444为hub ，5555为chrome，6666配置的为firefox
 *
 * 要想一份脚本既在火狐又在chrome跑怎么办？使用testng的数据驱动
 *
 */
public class GridDemo {
    /**
     *
     * 脚本让hub分配或者让grid分配
     */
    @Test
    public void gridTest() throws MalformedURLException, InterruptedException {
        DesiredCapabilities dc = DesiredCapabilities.firefox();

        //让hub分配
        WebDriver driver = new RemoteWebDriver(new URL("http://192.168.1.104:4444/wd/hub"), dc);
        //  指定某一个node
//        WebDriver driver = new RemoteWebDriver(new URL("http://192.168.1.104:5555/wd/hub"), firefoxDC);

/*        DesiredCapabilities chromeDC = DesiredCapabilities.chrome();
        WebDriver driver = new RemoteWebDriver(new URL("http://192.168.1.104:4444/wd/hub"), chromeDC);*/

        driver.get("http://www.baidu.com");
        Thread.sleep(5000);
        driver.quit();
    }

    /**
     *测试浏览器兼容性
     * 一个case在不同的浏览器的node节点运行
     */
    @DataProvider(name = "data1")
    public Object[][] data1() { //一定有返回值，而且一定是Object[][]
        return new Object[][]{
                {"firefox", "http://192.168.1.104:6666"},
                {"chrome", "http://192.168.1.104:5555"},
        };
    }

    @Test(dataProvider="data1")
    public void data1Test(String browser,String url) throws MalformedURLException, InterruptedException {
        /*
        正式这部分可以放在before里面
         */
        DesiredCapabilities dc=null;
        if (browser.equals("firefox")) {
            dc = DesiredCapabilities.firefox();
        } else if (browser.equals("chrome")) {
            dc = DesiredCapabilities.chrome();
        }else{
            System.out.println("error");
        }

        WebDriver driver = new RemoteWebDriver(new URL(url+"/wd/hub"), dc);//主动分配
        driver.get("http://www.baidu.com");
        Thread.sleep(50000);
        driver.quit();
    }
    /**
     *
     * 同一份脚本分发到hub,让hub分配到指定类型的浏览器运行
     */
    @DataProvider(name = "data2")
    public Object[][] data2() { //一定有返回值，而且一定是Object[][]
        return new Object[][]{
                {"firefox"},
                {"chrome"},
        };
    }

    @Test(dataProvider="data2")
    public void data2Test(String browser) throws MalformedURLException, InterruptedException {
        DesiredCapabilities dc=null;
        if (browser.equals("firefox")) {
            dc = DesiredCapabilities.firefox();
        } else if (browser.equals("chrome")) {
            dc = DesiredCapabilities.chrome();
        }else{
            System.out.println("error");
        }
        //hub进行分配，哪里有火狐分配到哪里，哪里有chrome分配到哪里
        WebDriver driver = new RemoteWebDriver(new URL("http://192.168.1.104:4444/wd/hub"), dc);//主动分配
        driver.get("http://www.baidu.com");
        Thread.sleep(50000);
        driver.quit();
    }

    /**
     * 如果希望脚本跑的时间变短，可以使用testNg的并发
     *
     */
}
