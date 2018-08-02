package com.vicky.demo_s.util.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.vicky.demo_s.util.generator.template.hello.HelloDemo;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 简易工具类，基于模板[hello.ftl]生成java文件（可传入变量）
 * @author cgb
 *
 */
public class FreeMarkerDemo {	
	
	public void run(){};
	
	public static void main(String[] args){
		FreeMarkerDemo demo = new HelloDemo();
		demo.run();
		System.out.println("finish");
	}

}
