package com.ttmall.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;
import com.ttmall.service.PictureService;
import com.ttmall.utils.FastDFSClient;
import com.ttmall.utils.PictureResult;

/**
 * 图片上传service
 * @author Administrator
 *
 */
@Service
public class PictureServiceImpl implements PictureService {

	@Value("${IMAGE_SERVER_BASE_URL}")
	private String IMAGE_SERVER_BASE_URL  ;
	
	@Override
	public PictureResult uploadPic(MultipartFile picFile) {
		PictureResult result = new PictureResult();
		//判断图片是否为空
		if(picFile.isEmpty()){
			result.setError(1);
			result.setMessage("图片为空");
			return result;
		}
		//图片上传到服务器
		try {
			// 取图片扩展名
			String originalFilename = picFile.getOriginalFilename();
			String extName = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
			
			FastDFSClient client = new FastDFSClient("classpath:properties/client.conf");
			String url = client.uploadFile(picFile.getBytes(), extName);
			//把URL响应给客户端
			result.setError(0);
			//拼接图片服务器的IP
			result.setUrl(IMAGE_SERVER_BASE_URL+url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setError(1);
			result.setMessage("图片上传失败");
		}
		return result;
	}

}
