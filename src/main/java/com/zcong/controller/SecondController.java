package com.zcong.controller;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
@Controller
public class SecondController {
	
    //获取上传的文件夹，具体路径参考application.properties中的配置
    @Value("${web.upload-path}")
    private String uploadPath;
	    /**
	     * GET请求
	     * 上传页面，也将显示已经存在的文件
	     * @param model
	     * @return
	     */
	    @GetMapping(value = "/second")
	    public String second(Model model) {
	        //获取已存在的文件
	 //       File [] files = new File(uploadPath).listFiles();
	//       model.addAttribute("files", files);
	        return "web/second";
	    }
		
	
	@RequestMapping(value="/upload2")//method= RequestMethod.POST
	public @ResponseBody String upload(HttpServletRequest request,MultipartFile file){
		
		try {
				
		/*//上传目录地址
		String rootPath=request.getSession().getServletContext().getRealPath("/upload/");
		//如果目录不存在，自动创建文件夹
		File dir=new File(rootPath);
		if (!dir.exists()) {
			dir.mkdirs();
		} */
		//获取文件后缀名
		String suffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		//上传文件名
		String filename=UUID.randomUUID()+suffix;
		System.out.println(uploadPath);
		//服务器端保存的文件对象
		File serverFile =new File(uploadPath + "/" +filename);
		//将上传的文件写入到服务器端文件内
		file.transferTo(serverFile);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "上传失败";
		}
		
		return "上传成功";	
	}
	

}
