package com.jq.springboot.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-07-26 15:37
 */
@Controller

public class ThController {




    //第四章
    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request) {
        HttpSession session = request.getSession();

        ModelAndView modelAndView = new ModelAndView("Varilble");
        ModelMap modelMap = modelAndView.getModelMap();
//        modelMap.addAttribute("name", "jq");
        modelAndView.addObject("name", "ctx");
        session.setAttribute("name","session");
        request.setAttribute("name","request");
        modelAndView.addObject("order", JavaData.getORDER());
        modelAndView.addObject("bool", true);
        modelAndView.addObject("none", null);
        return modelAndView;
    }

    //第五章
    @RequestMapping("five")
    public String five(ModelMap modelMap) {


        return "five";
    }

}
