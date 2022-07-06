package com.wuzhicheng.pigstore.controller;

import com.wuzhicheng.pigstore.common.R;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 文件得上传和下载(需要先登录)
 * @author wuzhicheng
 * @create 2022-07-05 19:35
 */
@RestController
@RequestMapping("/common")
public class CommonController {
    //从配置文件中获取路径
    @Value("${file.path}")
    private String basePath;

    @PostMapping("/upload")
    //MultipartFile的参数名不能随便取，和前端保持一致
    public R<String> upload(MultipartFile file) throws IOException {
        File dir=new File(basePath);
        //先判断是否存在，没有就创建
        if(!dir.exists()){
            dir.mkdir();
        }
        //将file转存到指定位置，不然临时文件会被删除
        //或者使用UUID来命名，避免发生重复
        file.transferTo(new File(basePath+file.getOriginalFilename()));

        return R.success(file.getOriginalFilename());
    }

    /**
     * 文件下载
     * @param name
     * @param resp
     */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse resp) throws IOException {
        //输入流，从输入流读取文件内容
        FileInputStream fis=new FileInputStream(new File(basePath+name));
        //输出流，写回浏览器
        resp.setContentType("image/jpeg");
        ServletOutputStream os = resp.getOutputStream();
        int len=0;
        byte bytes[]=new byte[1024];
        while((len=fis.read(bytes))!=-1){
            os.write(bytes,0,len);
            os.flush();
        }
        os.close();
        fis.close();
    }
}
