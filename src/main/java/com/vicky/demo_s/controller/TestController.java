package com.vicky.demo_s.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import com.vicky.demo_s.model.Users;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vicky.demo_s.model.Result;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
	
	public static List<Map<String,Object>> list;
	
	@GetMapping("/test")
	public String test(Map<String,Object> map){
		list = new ArrayList<Map<String,Object>>();
		Map<String, Object> mapItem = new HashMap<String,Object>();
		mapItem.put("id", "001");
		mapItem.put("name", "商品001");
		mapItem.put("isShow", "1");
		list.add(mapItem);
		
		mapItem = new HashMap<String,Object>();
		mapItem.put("id", "002");
		mapItem.put("name", "商品002");
		mapItem.put("isShow", "1");
		list.add(mapItem);
		
		mapItem = new HashMap<String,Object>();
		mapItem.put("id", "003");
		mapItem.put("name", "商品003");
		mapItem.put("isShow", "1");
		list.add(mapItem);
		
		map.put("list", list);
		return "index";
	}
	
	@RequestMapping("/changeShowStatus")
	public Result changeShowStatus(@PathParam("id")String id){
		for(Map<String,Object> item:list){
			if(item.get("id").equals(id)){
				item.replace("isShow", false);
			}
		}
		Result result = new Result("200",id);
		return result;
	}

	@GetMapping("/index")
	public String index(){
		return "welcome";
	}

	@GetMapping("/hello")
	public String hello(){
		return "hello world";
	}

	@GetMapping("/update")
	@Secured({"ROLE_sale","ROLE_manager"})
	@PostFilter("filterObject.username='admin1'")
	public String update(){
		ArrayList<Users> users = new ArrayList<>();
		users.add(new Users(1,"admin1","123"));
		users.add(new Users(2,"admin2","123"));
		return "hello update";
	}

	@GetMapping("/getAll")
	@Secured({"ROLE_sale","ROLE_manager"})
	@PostFilter("filterObject.username=='admin1'")  //好像没啥效果？？？
	public ArrayList<Users> getAll(){
		ArrayList<Users> users = new ArrayList<>();
		users.add(new Users(1,"admin1","123"));
		users.add(new Users(2,"admin2","111"));
		System.out.println(users);
		return users;
	}
}
