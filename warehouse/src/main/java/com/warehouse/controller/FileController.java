package com.warehouse.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @author promefire
 * @date 2024/5/5 15:03
 * description: 文件上传
 */
@RestController
public class FileController {


    @PostMapping("/upload")
    public String up(String name, MultipartFile photo, HttpServletRequest request) throws IOException{
        System.out.println("文件名称" + photo.getOriginalFilename()) ;
        System.out.println("文件大小" + photo.getSize());

        System.out.println(photo.getContentType());

//        请求上下文（web服务器） 获取web服务器的运行目录
        String path = request.getServletContext().getRealPath("/upload/");
        System.out.println(path);
        saveFile(photo,path);

        return "上传成功";
    }
    public void saveFile(MultipartFile photo, String path) throws IOException {
        File dir = new File(path);
        if(!dir.exists()){
            dir.mkdir();
        }

        File file = new File(path+photo.getOriginalFilename());
//        把网络中file文件存储到file路径中
        photo.transferTo(file);
    }


}
