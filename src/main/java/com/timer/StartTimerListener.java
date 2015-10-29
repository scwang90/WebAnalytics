package com.timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 监听器类
 * Created by ChenQi on 2015/10/27 8:55.
 */
public class StartTimerListener implements ServletContextListener{
    TranslateTimer tranTimer = null;

    /**
     * 创建一个初始化监听器对象，一般由容器调用
     */
    public StartTimerListener() {
        super();
    }

    /**
     * 让Web程序运行的时候自动加载Timer
     */
    public void contextInitialized(ServletContextEvent e) {
        System.out.println("-------------StartTimerListener.init-------------");
        tranTimer = new TranslateTimer(); // 启动定时器
        tranTimer.executeTranslateTimer();//执行操作方法
    }

    /**
     * 该方法由容器调用 空实现
     */
    public void contextDestroyed(ServletContextEvent e) {
    }
}
