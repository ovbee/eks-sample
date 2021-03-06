package com.ovbee.ekssample.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
    private static final Logger logger = LogManager.getLogger(HomeController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model, HttpServletRequest request) {
        String remoteAddress = request.getRemoteAddr();
        String localAddress = request.getLocalAddr();
        logger.info("---remoteAddress---" + remoteAddress);
        logger.info("---localAddress---" + localAddress);
        model.addAttribute("clientIp", remoteAddress);
        model.addAttribute("remoteIp", localAddress);
        String headerIp = request.getHeader("X-FORWARDED-FOR");
        logger.info("---headerIp---" + headerIp);
        return "/home";
    }
}
