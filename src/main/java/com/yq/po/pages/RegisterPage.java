package com.yq.po.pages;

import org.openqa.selenium.By;

/**
 * page层
 *
 * 注册页面元素
 * Created by YQ on 2017/3/8.
 */
public class RegisterPage {
    //注册 按钮
    public static By regiterButton = By.id("changepage");
    //注册字母邮箱 按钮
    public static By registerMail = By.xpath(".//*[@id='tabsUl']/li[1]/a");

    //邮箱地址 文本
    public static By mailAddr = By.id("nameIpt");

    //密码 文本
    public static By pwd = By.id("mainPwdIpt");

    //确认密码  文本
    public static By rePwd = By.id("mainCfmPwdIpt");


    //手机号  文本
    public static By phone = By.id("mainMobileIpt");

    //验证码  文本
    public static By captcha = By.id("vcodeIpt");

    //短信验证码 文本
    public static By smsCaptcha = By.id("mainAcodeIpt");

    //立即注册 按钮
    public static By regiterOnce = By.id("mainRegA");



}
