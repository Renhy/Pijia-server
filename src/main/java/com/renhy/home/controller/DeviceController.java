package com.renhy.home.controller;

import com.renhy.home.model.DeviceData;
import com.renhy.home.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Device Controller
 */
@Slf4j
@RestController
@RequestMapping("/api/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public boolean postDeviceData(@RequestBody DeviceData data) {
        if (data.getTimestamp() == null) {
            data.setTimestamp(System.currentTimeMillis());
        }
        log.info("received data: {}", data.toString());
        return deviceService.saveDeviceData(data) > 0;
    }


}
