package com.vicky.demo_s.controller;

import com.vicky.demo_s.model.Result;
import com.vicky.demo_s.model.ResultCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class FileUploadController{
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/");

    @PostMapping("/upload")
//    @RequestMapping(value="upload",method= RequestMethod.GET)
    public String upload(@RequestParam("uploadFile") MultipartFile file, HttpServletRequest req){
        //判断文件为空，防止空指针
        if (file == null){
            return "上传文件为空！";
        }
        //创建文件路径
        String realPath = req.getSession().getServletContext().getRealPath("/uploadFile/");
        String format = this.simpleDateFormat.format(new Date());
        File forder = new File(realPath + format);
        if(!forder.isDirectory()){
           forder.mkdirs();
        }
        //文件重命名
        String originalFilename = file.getOriginalFilename();
        String newname = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."),originalFilename.length());
        //返回文件新路径
        try {
            file.transferTo(new File(forder , newname));
            String filePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/uploadFile/" + format + newname;
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }

    @PostMapping("/uploadList")
//    @RequestMapping(value="upload",method= RequestMethod.GET)
    public Result upload(@RequestParam("uploadFiles") List<MultipartFile> files, HttpServletRequest req){
        //判断文件为空，防止空指针
        if (files == null || files.size()==0){
            return new Result(ResultCode.ERROR,"上传文件为空！",null);
        }
        //创建文件路径
        String realPath = req.getSession().getServletContext().getRealPath("/uploadFile/");
        String format = this.simpleDateFormat.format(new Date());
        File forder = new File(realPath + format);
        if(!forder.isDirectory()){
            forder.mkdirs();
        }

        List<String> filePathList =  new ArrayList<String>();
        for(MultipartFile file:files){
            //文件重命名
            String originalFilename = file.getOriginalFilename();
            String newname = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."),originalFilename.length());
            //返回文件新路径
            try {
                file.transferTo(new File(forder , newname));
                String filePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/uploadFile/" + format + newname;
                filePathList.add(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(filePathList.size()!=0){
            return new Result(ResultCode.SUCCESS,null,filePathList);
        }
        return new Result(ResultCode.ERROR,"上传失败",null);
    }
}
