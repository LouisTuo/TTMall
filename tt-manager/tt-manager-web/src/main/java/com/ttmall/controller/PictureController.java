package com.ttmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ttmall.service.PictureService;
import com.ttmall.utils.JsonUtils;
import com.ttmall.utils.PictureResult;

/**
 * 图片上传Controller
 * @author Administrator
 *
 */
@Controller
public class PictureController {
		@Autowired
		private PictureService pictureService;
		
		@RequestMapping("/pic/upload")
		@ResponseBody
		public String uploadFile(MultipartFile uploadFile){
			PictureResult result = pictureService.uploadPic(uploadFile);
			//需要把java对象手工转换成json数据--为了解决浏览器的兼容性
			String json = JsonUtils.objectToJson(result);
			return json;
		}
}
