package com.renhy.home.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.renhy.home.model.AqoData;
import com.renhy.home.service.AqoDataService;

/**
 * AQO Data Controller
 */
@RestController
@RequestMapping("api/data/aqo")
public class AqoDataController {

    @Autowired
    private AqoDataService aqoDataService;


    @RequestMapping(value = "/latest", method = RequestMethod.GET)
    public AqoData getLatest(@RequestParam(value = "device", required = false) String device) {
        return aqoDataService.getLatest(device);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public PageInfo<AqoData> list(
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

        return aqoDataService.list(device, s, e, pageSize, pageNum);
    }



}
