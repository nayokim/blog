package com.codeup.blog.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {
    @GetMapping("/add/{a}/and/{b}")
    @ResponseBody
    public int add(@PathVariable int a,  @PathVariable int b){
        return a + b;
    }

    @GetMapping("/subtract/{a}/from/{b}")
    @ResponseBody
    public int subtract(@PathVariable int a, @PathVariable int b){
        return b-a;
    }

    @GetMapping("/multiply/{a}/and/{b}")
    @ResponseBody
    public int multiply(@PathVariable int a, @PathVariable int b){
        return a * b;
    }

    @GetMapping("/divide/{a}/by/{b}")
    @ResponseBody
    public int divide(@PathVariable int a, @PathVariable int b){
        return b/a;
    }


}
