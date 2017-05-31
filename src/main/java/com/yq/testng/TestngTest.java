package com.yq.testng;

import org.testng.annotations.*;

/**
 * 多个test根据ACSII码值的顺序来执行的
 * case与case之间要保持它的原子性，不要相互依赖
 * Created by YQ on 2017/3/3.
 */
public class TestngTest {
    @BeforeTest
    public void test2(){
        System.out.println("这是有个@BeforeTest");
    }

    /**
     * 每个test执行之前需要的操作可以放在这里
     */
    @BeforeMethod
    public void test3(){
        System.out.println("这是有个@BeforeMethod");
    }

    @Test
    public void test1(){
        System.out.println("这是一个@Test_2");
    }

    @Test
    public void test4(){
        System.out.println("这是一个@Test_4");
    }

    @AfterMethod
    public void test6(){
        System.out.println("这是一个@AfterMethod");
    }

    @AfterTest
    public void test5(){
        System.out.println("这是一个@AfterTest");
    }
}
