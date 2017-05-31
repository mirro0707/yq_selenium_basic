package com.yq.testng;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * 常用的校验方式
 * Created by YQ on 2017/3/3.
 */
public class AssertTest {
    @Test
    public void assertEquals(){
        String a="wwddc1";
        String b="wwddc1";
        Assert.assertEquals(a,b);
    }
    @Test
    public void assertNotEquals(){
        int a=1;
        int b=1;
        Assert.assertNotEquals(a,b,"a不等于b");//运行失败显示message
    }
    @Test
    public void assertTrue(){
        Boolean a=true;
        Assert.assertTrue(a);
    }

    @Test
    public void assertFalse(){
        Boolean a=true;
        Assert.assertFalse(a);//Assert执行失败后就不会执行
        System.out.println("Assert执行失败后就不会执行");
    }
    @Test
    public void assertNotNull(){
        String a = null;//""这样不是空
        Assert.assertNotNull(a,"a为空");
    }
    @Test
    public void assertNull(){
        String a = "ddd";
        Assert.assertNull(a,"a为空");
    }
}
