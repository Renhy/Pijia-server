package com.renhy.home.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

/**
 * AQO Data Model
 */
@Data
@Alias("aqodata")
public class AqoData {

    private int id;
    private String device;
    private Date ts;

    private Double tem;
    private Double hum;
    private Double ch2o;
    private Double tvoc;
    private Double co2;
    private Double pm10;
    private Double pm25;

}
