package com.vicky.demo_s.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vicky.demo_s.model.Result;

@Controller()
public class TestController {
	
	public static List<Map<String,Object>> list;
	
	@RequestMapping("/index")
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
	@ResponseBody
	public Result changeShowStatus(@PathParam("id")String id){
		for(Map<String,Object> item:list){
			if(item.get("id").equals(id)){
				item.replace("isShow", false);
			}
		}
		Result result = new Result("200",id);
		return result;
	}
}
