package com.yq.datadriver;

import com.yq.example.EmailLogin;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * tesgNG数据驱动（Excel,数据库封装）
 * Created by YQ on 2017/3/6.
 */
public class DataDriverDemo {
    /*
    模拟用户登录；例1
    例如等价类划分，可以用数据驱动减少很多case
     */
    @DataProvider(name = "loginUser")
    public Object[][] data1() { //一定有返回值，而且一定是Object[][]
        //读取XXX文件，数据库等{data}
        return new Object[][]{
                {"user1", "pw1"},
                {"user2", "pw2"},
                {"user3", "pw3"},
        };
    }

    @Test(dataProvider="loginUser")
    public void emailLoginTest(String username, String password) {
        System.out.println(username);
        System.out.println(password);
    }
    /*
    模拟用户登录：例2
     */
    @DataProvider(name = "loginUser2")
    public Object[][] data2() { //一定有返回值，而且一定是Object[][]
        return new Object[][]{
                {"meyoungtester", "meyoung123"},
                {"meyoungtester", "2225"},
                {"dss", "ssaa1"},
        };
    }

    @Test(dataProvider="emailLoginUser2")
    public void emailLoginTest2(String username, String pwd) throws InterruptedException {
        FirefoxDriver driver = new FirefoxDriver();
        driver.get("http://mail/163.com");
        EmailLogin.login(driver,username,pwd);
        Thread.sleep(50000);
        driver.quit();
    }

}
