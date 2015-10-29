package com.timer;

import com.simpletech.webanalytics.service.IpLocationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 定时器类
 * Created by ChenQi on 2015/10/27 8:54.
 */
public class TranslateTimer {
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//    IPTranslator transIP = new IPTranslator(); //操作实现类

    @Autowired
    IpLocationService ipLocationService;
    public void executeTranslateTimer() {

        Runnable task = new Runnable() {

            public void run() {
                System.out.println();
                try{
                    System.out.println("--------------进行ip转换：" + new Date().getTime() + "------------------");
//                    transIP.doTranslate();
//
                }catch (Exception e){
                    e.printStackTrace();
                    System.out.print("数据更新失败");
                }

            }
        };
        if (scheduler.isShutdown()) {
            scheduler = Executors.newScheduledThreadPool(1);
            scheduler.scheduleAtFixedRate(task, 10, 60, TimeUnit.SECONDS);
        } else {
            scheduler.scheduleAtFixedRate(task, 10, 60, TimeUnit.SECONDS); // 延迟10秒，每隔60秒转换一次
        }
    }

    //停止任务，不再提交新任务，已提交任务会继续执行以致完成
    public void stop() {
        scheduler.shutdown();
    }
}
