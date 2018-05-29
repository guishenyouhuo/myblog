package com.wf.blog.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.wf.blog.controller.base.BaseController;

@Controller
public class UploadController extends BaseController {

	@Value("${web.upload.path}")
    private String uploadPath;
	
	@RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
	public void uploadFile(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "editormd-image-file", required = false) MultipartFile attach) {
		try {
			request.setCharacterEncoding("utf-8");
			response.setHeader("Content-Type", "text/html");
			/**
			 * 文件路径不存在则需要创建文件路径
			 */
			File filePath = new File(uploadPath);
			if (!filePath.exists()) {
				filePath.mkdirs();
			}

			// 最终文件名
			File realFile = new File(uploadPath + File.separator + attach.getOriginalFilename());
			FileUtils.copyInputStreamToFile(attach.getInputStream(), realFile);

			// 下面response返回的json格式是editor.md所限制的，规范输出就OK
			response.setCharacterEncoding("utf-8");
			response.getWriter().write("{\"success\": 1, \"message\":\"上传成功\",\"url\":\"" + request.getContextPath() + "/showPic/" + attach.getOriginalFilename() + "\"}");
		} catch (Exception e) {
			try {
				response.getWriter().write("{\"success\":0}");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	/*
	 * @RequestMapping(value="/showPic/{fileName:.+}", method=RequestMethod.GET)
	 * public void showPic(HttpServletRequest request, HttpServletResponse
	 * response, @PathVariable String fileName){ String uploadUrl =
	 * request.getSession
	 * ().getServletContext().getRealPath("/resources/upload/"); String
	 * fileUrl=uploadUrl + fileName; try { File filePath = new File(fileUrl);
	 * if(filePath.exists()){ //读图片 FileInputStream inputStream = new
	 * FileInputStream(filePath); int available = inputStream.available();
	 * byte[] data = new byte[available]; inputStream.read(data);
	 * inputStream.close(); //写图片 response.setContentType("image/" + fileName);
	 * response.setCharacterEncoding("UTF-8"); OutputStream stream = new
	 * BufferedOutputStream(response.getOutputStream()); stream.write(data);
	 * stream.flush(); stream.close(); } } catch (Exception e) {
	 * e.printStackTrace(); } }
	 */
}
