package com.yq.testng;

import org.testng.annotations.Test;

/**
 * 练习注解的顺序2
 * 执行方式：testngCI2.xml
 */
public class MyAnnotationTest extends AbstractAnnotation {

    @Test
    public void myTestMethod1() {
        System.out.println("myTestMethod1");
    }

    @Test
    public void myTestMethod2() {
        System.out.println("myTestMethod2");
    }
}
