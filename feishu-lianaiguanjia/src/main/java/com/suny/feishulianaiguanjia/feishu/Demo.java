package com.suny.feishulianaiguanjia.feishu;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author sunyuan
 * @date 2022/9/1 22:10
 * 飞书 
 */
@Slf4j
@RestController
public class Demo {

    private static final String SUCCESS = "1";


    @RequestMapping("/timingAt8")
    public void timingAt8() {

        log.info("定时任务" + DateUtil.formatDateTime(new Date()));
        // 在一起时间
        String beginDate = "2022-05-19";
        Date date1 = DateUtil.parse(beginDate);
        Date now = new Date();
        long betweenDay = DateUtil.between(date1, now, DateUnit.DAY);
        System.out.println("今天是和XXX在一起的第" + betweenDay + "天");


        // 高德地图API  查询天气情况
        // https://restapi.amap.com/v3/weather/weatherInfo?city=自己地区编码&key=高德地图的key&extensions=all"
        String tunLiuUrl = "https://restapi.amap.com/v3/weather/weatherInfo?city=110000&key=150ecc8f5e61785215fb113XXXc8b1ec&extensions=all";

        String result2 = HttpUtil.get(tunLiuUrl, CharsetUtil.CHARSET_UTF_8);

        // 字符串转JSON
        GaodeResult gaodeResult = JSONUtil.toBean(result2, GaodeResult.class);

        if (SUCCESS.equals(gaodeResult.getStatus())) {
            System.out.println("查询成功--");
            List<Forecasts> forecasts = gaodeResult.getForecasts();
            List<Casts> casts = forecasts.get(0).getCasts();
            String province = forecasts.get(0).getProvince();
            String city = forecasts.get(0).getCity();
            Date reporttime = forecasts.get(0).getReporttime();
            String formatReporttime = DateUtil.format(reporttime, "yyyy.MM.dd HH:mm:ss");


            System.out.println("今天天气---");

            Casts live = casts.get(0);
            Date date = live.getDate();
            String formatDate = DateUtil.format(date, "yyyy-MM-dd");
            System.out.println("----------" + formatDate);
            String week = "星期" + live.getWeek();

            String dayWeather = live.getDayweather();
            String nightWeather = live.getNightweather();

            String dayTemp = live.getDaytemp() + "度";
            String nightTemp = live.getNighttemp() + "度";

            String dayWind = live.getDaywind();
            String nightWind = live.getNightwind();

            String daypower = live.getDaypower();
            String nightPower = live.getNightpower();


            System.out.println("今天是 :" + formatDate + "  " + week);
            System.out.println("今天是和臭宝在一起的第" + betweenDay + "天");
            System.out.println("今天天气 :" + "白天 " + dayWeather + "  " + "晚上 " + nightWeather);
            System.out.println("今天温度 :" + "白天 " + dayTemp + "  " + "晚上 " + nightTemp);
            System.out.println("今天风向 :" + "白天 " + dayWind + "  " + "晚上 " + nightWind);


            // 飞书卡片
            String json = "{\n" +
                    "    \"msg_type\": \"interactive\",\n" +
                    "    \"card\": {\n" +
                    "\n" +
                    "  \"config\": {\n" +
                    "    \"wide_screen_mode\": true\n" +
                    "  },\n" +
                    "  \"header\": {\n" +
                    "    \"template\": \"red\",\n" +
                    "    \"title\": {\n" +
                    "      \"content\": \"\uD83D\uDD14 mua～亲爱的臭宝贝 \uD83C\uDF81\",\n" +
                    "      \"tag\": \"plain_text\"\n" +
                    "    }\n" +
                    "  },\n" +
                    "  \"i18n_elements\": {\n" +
                    "    \"zh_cn\": [\n" +
                    "      {\n" +
                    "        \"tag\": \"div\",\n" +
                    "        \"text\": {\n" +
                    "          \"content\": \"**\uD83C\uDF84 今天是：**" + formatDate + "  " + week + "\\n\\n**\uD83C\uDF81 今天是我们在一起的第：" + betweenDay + "天" + "**\",\n" +
                    "          \"tag\": \"lark_md\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"tag\": \"div\",\n" +
                    "        \"text\": {\n" +
                    "          \"content\": \" " + formatDate + "  " + province + " " + city + "  \\n数据发布的时间 :" + formatReporttime + " \\n今天天气 : " + "白天 " + dayWeather + "  " + "晚上 " + nightWeather + "\\n今天温度 :" + nightTemp + " ~ " + dayTemp + "\\n今天风向 : " + "白天 " + dayWind + "  " + "晚上 " + nightWind + "\\n\\n**祝我的臭宝每天开心,每天爱我的臭宝多一点点 ！**\",\n" +
                    "          \"tag\": \"lark_md\"\n" +
                    "        }\n" +
                    "      }\n" +
                    "    ]\n" +
                    "  }\n" +
                    "}\n" +
                    "}";

            String feishuUrl = "https://open.feishu.cn/open-apis/bot/v2/hook/5371db79-7cb2-45ef-bac1-161e2a71XXXX";
            String result3 = HttpRequest
                    .post(feishuUrl)
                    .body(json)
                    .execute().body();


        }
    }

}



    