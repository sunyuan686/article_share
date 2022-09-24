/**
 * Copyright 2022 json.cn
 */
package com.suny.feishulianaiguanjia.feishu;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Auto-generated: 2022-09-23 20:40:40
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
@Data
public class Casts {

    @JsonFormat(pattern = "", timezone = "")
    private Date date;
    private String week;
    private String dayweather;
    private String nightweather;
    private String daytemp;
    private String nighttemp;
    private String daywind;
    private String nightwind;
    private String daypower;
    private String nightpower;

}
