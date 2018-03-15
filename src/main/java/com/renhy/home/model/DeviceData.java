package com.renhy.home.model;

import java.util.Date;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * Data Model For Device
 */
@Data
@Alias("devicedata")
public class DeviceData {

    private int id;
    private String device;
    private String type;
    private String content;
    private Date ts;

}
