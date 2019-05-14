package com.renhy.home.controller;

import java.util.Date;

import com.github.pagehelper.PageInfo;
import com.renhy.home.model.DeviceData;
import com.renhy.home.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
        if (data.getTs() == null) {
            data.setTs(new Date());
        }
        log.info("received data: {}", data.toString());
        return deviceService.saveDeviceData(data) > 0;
    }

    @RequestMapping(value = "/latest", method = RequestMethod.GET)
    public DeviceData getLatest(@RequestParam(value = "device", required = false) String device) {
        return deviceService.getLatest(device);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public PageInfo<DeviceData> list(
        @RequestParam(value = "device", required = false) String device,
        @RequestParam(value = "start", required = false) Long start,
        @RequestParam(value = "end", required = false) Long end,
        @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
        @RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
        Date s = null;
        if (start != null) {
            s = new Date(start);
        }
        Date e = null;
        if (end != null) {
            e = new Date(end);
        }

        return deviceService.listData(device, s, e, pageSize, pageNum);
    }


}
