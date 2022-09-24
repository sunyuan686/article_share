/**
 * Copyright 2022 json.cn
 */
package com.suny.feishulianaiguanjia.feishu;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Auto-generated: 2022-09-23 20:40:40
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
@Data
public class Forecasts {

    private String city;
    private String adcode;
    private String province;
    private Date reporttime;
    private List<Casts> casts;

}
