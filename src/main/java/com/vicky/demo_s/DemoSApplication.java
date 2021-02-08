package com.vicky.demo_s;

import java.util.logging.Logger;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vicky.demo_s.config.UserSetting;

@MapperScan("com.vicky.demo_s.mapper")
@SpringBootApplication 
@Controller
public class DemoSApplication {
	
	@Value("${default.user.name}")
	String defaultName;
	@Value("${default.user.age}")
	int defaultAge;
	
	@Autowired
	private UserSetting userSetting;
	
	//通过@Value进行常规属性配置
	@RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello,"+defaultName+", you are "+defaultAge;
    }
	
	//测试基于properties的类型安全的配置
	//（通过@ConfigurationProperties将properties属性和一个Bean及其属性关联）
	@RequestMapping("/hello2")
	@ResponseBody
	public String hello2(){
		return "hello,"+userSetting.getName()+", you are "+userSetting.getAge();
	}
	
    public static void main(String[] args) {
        SpringApplication.run(DemoSApplication.class, args);
    }
    
}
