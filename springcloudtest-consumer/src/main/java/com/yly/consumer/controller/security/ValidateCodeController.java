package com.yly.consumer.controller.security;

import com.yly.consumer.utils.ValidImage.ImageCode;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@RestController
public class ValidateCodeController {
    public static final String SEESION_KEY = "SESSION_KEY_IMAGE_CODE";
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    @GetMapping("/code/image")  //注意要将该url 加入免验证列表
    public void createCode(HttpServletRequest req, HttpServletResponse rsp) throws IOException {
        ImageCode imageCode = ImageCode.createImage();
        sessionStrategy.setAttribute(new ServletWebRequest(req),SEESION_KEY,imageCode ); //将随机数存入Session
        saveImage(imageCode.getImage());
        ImageIO.write(imageCode.getImage(),"JPG",rsp.getOutputStream()); //将生成的图片写到接口的响应中
    }
    //将图片写入 磁盘 检查图片是否生成成功
    private  void saveImage(BufferedImage img) throws  IOException {
        //这里为了测试将生成的图片放入f盘，在实际的项目开发中是需要将生成的图片写入客户端的:ImageIO.write(img,"JPEG",response.getOutputStream());

        ImageIO.write(img, "JPEG", new FileOutputStream("F:\\a.jpg"));

    }
}
