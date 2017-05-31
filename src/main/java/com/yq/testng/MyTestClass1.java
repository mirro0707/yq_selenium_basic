package com.yq.testng;

import org.testng.annotations.Test;

/**
 * Created by YQ on 2017/5/24.
 */
public class MyTestClass1 extends AbstractTest {

    @Test
    public void myTestMethod1() {
        System.out.println("myTestMethod1");
    }

    @Test
    public void myTestMethod2() {
        System.out.println("myTestMethod2");
    }
}
