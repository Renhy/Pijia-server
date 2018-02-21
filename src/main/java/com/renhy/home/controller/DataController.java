package com.renhy.home.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.renhy.home.model.DeviceData;

import lombok.extern.slf4j.Slf4j;

/**
 * Data Controller
 */
@Slf4j
@RestController
@RequestMapping("/api/data")
public class DataController {


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public boolean postDeviceData(@RequestBody DeviceData data) {
        if (data.getTimestamp() == null) {
            data.setTimestamp(System.currentTimeMillis());
        }

        log.info("received data: {}", data.toString());
        return true;
    }

}
