package com.vicky.demo_s;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.Predicate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication 
@Controller
public class DemoSApplication {
	@RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello world";
    }
	
    public static void main(String[] args) {
        SpringApplication.run(DemoSApplication.class, args);
    }
    
}
