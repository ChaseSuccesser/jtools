package com.ligx.controller;

import com.ligx.service.ProfilingTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: ligongxing.
 * Date: 2018/09/26.
 */
@RestController
public class ProfilingTestController {

    @Autowired
    private ProfilingTestService profilingTestService;

    @RequestMapping(value = "/profilingTest", method = RequestMethod.GET)
    public String profilingTest() {
        return profilingTestService.profilingTest();
    }

    @RequestMapping(value = "/profilingTestV2", method = RequestMethod.GET)
    public String profilingTestV2() {
        return profilingTestService.profilingTestV2();
    }
}
