package com.wyy.controller;

import com.wyy.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 *
 */
//@Controller
public class IndexController {
    @RequestMapping(path = {"/", "/index"}, method = RequestMethod.GET)
    @ResponseBody
    public String index() {
        return "Hello World";
    }

    @RequestMapping(path = {"/profile/{groupId}/{userId}"})
    @ResponseBody
    public String profile(@PathVariable("userId") int userId,
                          @PathVariable("groupId") String groupId,
                          @RequestParam(value = "type", defaultValue = "1") int type,
                          @RequestParam(value = "key", defaultValue = "zz", required = false) String key) {
        return String.format("Profile Page of %s/%d,t:%d k:%s", groupId, userId, type, key);
    }

    @RequestMapping(path = {"/vm"}, method = {RequestMethod.GET})
    public String template(Model model) {
        model.addAttribute("value1", "vvvv1");
        List<String> colors = Arrays.asList(new String[]{"Red", "Green", "Blue"});
        model.addAttribute("colors", colors);

        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 4; ++i) {
            map.put(String.valueOf(i), String.valueOf(i * i));
        }
        model.addAttribute("map", map);
        model.addAttribute("user", new User("wyy"));
        return "home";
    }


    @RequestMapping(path = {"/request"}, method = {RequestMethod.GET})
    @ResponseBody
    public String request(Model model, HttpServletResponse response,
                          HttpServletRequest request,
                          HttpSession session,
                          @CookieValue("JSESSIONID") String  sessionId) {
        StringBuilder sb = new StringBuilder();
        sb.append("COOKIEVALUE:"+sessionId);
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String name = headerNames.nextElement();
            sb.append(name + ":" + request.getHeader(name)+"<br>");
        }
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()){
                sb.append("Cookie:"+cookie.getName()+"value:"+cookie.getValue());
            }
        }

        sb.append(request.getMethod() + "<br/>");
        sb.append(request.getQueryString() + "<br/>");
        sb.append(request.getPathInfo() + "<br/>");
        sb.append(request.getRequestURI() + "<br/>");

        response.addHeader("nowcoderId","hello");
        response.addCookie(new Cookie("username","wyy"));
        return sb.toString();
    }
}
