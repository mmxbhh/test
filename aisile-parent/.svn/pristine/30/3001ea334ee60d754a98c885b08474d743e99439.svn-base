package com.aisile.manager.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aisile.common.util.FastDFSClient;
import com.aisile.pojo.entity.Result;

@RestController
public class UploadController {
		@Value("${IMAGE_SERVER_URL}")//通过这个去取路径
		private String IMAGE_SERVER_URLL;//文件服务器地址
		
		
		@RequestMapping("/upload")
		public Result upload( MultipartFile file){
			try {
				//进行文件上传
				//1、取文件的扩展名
				String originalFilename = file.getOriginalFilename();
				// 例如 test2.jpg
				String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
				//2、创建一个 FastDFS 的客户端
				
				FastDFSClient fastDFSClient  = new FastDFSClient("classpath:conf/client.conf");
				
				String url=fastDFSClient.uploadFile(file.getBytes(),extName);
				return new Result(true, IMAGE_SERVER_URLL+url);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new Result(false, "文件上传失败");
			}

			
		}
}
