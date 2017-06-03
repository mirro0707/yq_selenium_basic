package com.yq.testng;

import org.testng.annotations.Test;

/**
 * 练习注解的顺序2
 * 执行方式：testngCI2.xml
 */
public class MyAnnotationTest2 extends AbstractAnnotation {

    @Test
    public void myTestMethod3() {
        System.out.println("myTestMethod3");
    }

    @Test
    public void myTestMethod4() {
        System.out.println("myTestMethod4");
    }
}
