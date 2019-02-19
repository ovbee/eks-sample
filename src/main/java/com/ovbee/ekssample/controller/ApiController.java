package com.ovbee.ekssample.controller;

import com.ovbee.ekssample.model.IpInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class ApiController {
    private static final Logger logger = LogManager.getLogger(ApiController.class);

    @RequestMapping(value = "/ipinfo", method = RequestMethod.GET)
    public IpInfo ipInformation(Model model, HttpServletRequest request) {
        String remoteAddress = request.getRemoteAddr();
        String localAddress = request.getLocalAddr();
        logger.info("---remoteAddress---" + remoteAddress);
        logger.info("---localAddress---" + localAddress);
        IpInfo info = new IpInfo();
        info.setClientIp(remoteAddress);
        info.setRemoteIp(localAddress);
        return info;
    }
}
