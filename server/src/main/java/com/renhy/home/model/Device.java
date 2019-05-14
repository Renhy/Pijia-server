package com.renhy.home.model;

import org.apache.ibatis.type.Alias;

import lombok.Data;

/**
 * Device Model
 */
@Data
@Alias("device")
public class Device {

    private Integer id;
    private String name;
    private String type;
    private String location;

}
