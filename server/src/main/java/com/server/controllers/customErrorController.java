package com.server.controllers;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class customErrorController {
    @GetMapping("/error")
    @ResponseBody
    public  String HandleError(){
        return "PAGE NOT FOUND";
    }

    public String getErrorPath(){
        return  "/error";
    }
}
