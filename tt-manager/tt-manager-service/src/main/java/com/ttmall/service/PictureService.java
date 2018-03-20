package com.ttmall.service;

import org.springframework.web.multipart.MultipartFile;

import com.ttmall.utils.PictureResult;


public interface PictureService {
	
	PictureResult uploadPic(MultipartFile picFile);
}
