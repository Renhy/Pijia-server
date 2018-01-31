package com.renhy.home.model;

import java.util.Date;

import lombok.Data;

/**
 * Data Model For Device
 */
@Data
public class DeviceData {

    private int id;
    private String device;
    private String type;
    private String content;
    private Date timestamp;

}
