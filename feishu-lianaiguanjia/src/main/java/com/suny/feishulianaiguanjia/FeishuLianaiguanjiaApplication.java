package com.suny.feishulianaiguanjia;

import cn.hutool.core.lang.Console;
import cn.hutool.cron.CronUtil;
import cn.hutool.cron.task.Task;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FeishuLianaiguanjiaApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeishuLianaiguanjiaApplication.class, args);

        //动态的添加定时任务每5秒执行一次

        //CronUtil.schedule("*/5 * * * * *", new Task() {
        //    @Override
        //    public void execute() {
        //        Console.log("动态定时任务，每5秒执行一次");
        //    }
        //});



        //支持秒级
        CronUtil.setMatchSecond(true);
        //开启定时任务
        CronUtil.start(true);
    }

}
