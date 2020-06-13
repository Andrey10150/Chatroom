package com.pet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;


import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Controller
public class LoginController {
    @GetMapping("/")
    public ModelAndView login() {
        return new ModelAndView("/login");
    }

    @PostMapping("/index")
    public ModelAndView index(@RequestParam String username, HttpServletRequest request) throws UnknownHostException {
        System.out.println(String.format("Logging in by username [%s]", username));
        if (StringUtils.isEmpty(username)) {
            username = "GUEST" + System.currentTimeMillis();
        }
        ModelAndView chatMav = new ModelAndView("chat");
        chatMav.addObject("username", username);
        chatMav.addObject("webSocketUrl", String.format("ws://%s:%s%s/chat/%s", InetAddress.getLocalHost().getHostAddress(), request.getServerPort(), request.getContextPath(), username));
        return chatMav;
    }
}
