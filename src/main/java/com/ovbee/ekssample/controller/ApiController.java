package com.ovbee.ekssample.controller;

import com.ovbee.ekssample.model.IpInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@RestController
@RequestMapping("/api")
public class ApiController {
    private static final Logger logger = LogManager.getLogger(ApiController.class);

    @RequestMapping(value = "/ipinfo", method = RequestMethod.GET)
    public IpInfo ipInformation(HttpServletRequest request) {
        String remoteAddress = request.getRemoteAddr();
        String localAddress = request.getLocalAddr();
        String headerIp = request.getHeader("X-FORWARDED-FOR");
        logger.info("---headerIp---" + headerIp);
        logger.info("---remoteAddress---" + remoteAddress);
        logger.info("---localAddress---" + localAddress);
        IpInfo info = new IpInfo();
        info.setClientIp(remoteAddress);
        info.setRemoteIp(localAddress);

        String ips = request.getHeader("X-Forwarded-For");
        if (StringUtils.isEmpty(ips)) {
            ips = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ips)) {
            ips = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ips)) {
            ips = request.getRemoteAddr();
        }
        String ip = Arrays.stream(ips.split(",")).findFirst().get();
        logger.info("---ips---" + ips);
        info.setClientIp(remoteAddress + "#" + ips);
        return info;
    }
}
