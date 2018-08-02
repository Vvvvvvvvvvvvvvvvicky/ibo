package com.vicky.demo_s.util.generator.template.hello;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.vicky.demo_s.util.generator.FreeMarkerDemo;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 简易工具类，基于模板[hello.ftl]生成java文件（可传入变量）
 * @author cgb
 *
 */
public class HelloDemo extends FreeMarkerDemo {
	
	private static final String TEMPLATE_PATH = "src/main/java/com/vicky/demo_s/util/generator/template/hello";
	private static final String CLASS_PATH = "src/main/java/com/vicky/demo_s/util/generator/template/hello/result";
	
	public void run(){
		//1.创建freeMarker配置实例
		Configuration configuration = new Configuration();
		Writer out = null;
		try{
			//2.获取模板路径
			configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
			//3.创建数据模型
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("classPath", CLASS_PATH.substring(14, CLASS_PATH.length()).replaceAll("/", "."));
			dataMap.put("className", "AutoCodeDemo");
			dataMap.put("helloWorld", "通过简单的 <代码自动生产程序> 演示 FreeMarker的HelloWorld！");
			//4.加载模板文件
			Template template = configuration.getTemplate("hello.ftl");
			//5.生成数据
			File docFile = new File(CLASS_PATH+"\\"+"AutoCodeDemo.java");
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
			//6.输出文件
			template.process(dataMap, out);
			System.out.println("------------------AutoCodeDemo.java 文件创建成功！-----------------------");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(out != null){
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
