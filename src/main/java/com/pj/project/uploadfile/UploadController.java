package com.pj.project.uploadfile;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.pj.utils.sg.AjaxJson;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * 文件上传控制器 (基于应用服务器的文件上传)
 * @author kong
 *
 */
@RestController
@RequestMapping("/upload/")
@Slf4j
public class UploadController {

	/** 上传图片 */
	@RequestMapping("image")
	public AjaxJson image(MultipartFile file){
		// 验证文件大小 -> 验证后缀 -> 保存到硬盘 -> 地址返回给前端 
		UploadUtil.checkFileSize(file); 						
		UploadUtil.checkSubffix(file.getOriginalFilename(), UploadUtil.uploadConfig.imageSuffix); 	
		String httpUrl = UploadUtil.saveFile(file, UploadUtil.uploadConfig.imageFolder);
		return AjaxJson.getSuccessData(httpUrl);
	}

	/** 上传视频  */
	@RequestMapping("video")
	public AjaxJson video(MultipartFile file){
		// 验证文件大小 -> 验证后缀 -> 保存到硬盘 -> 地址返回给前端 
		UploadUtil.checkFileSize(file); 			
		UploadUtil.checkSubffix(file.getOriginalFilename(), UploadUtil.uploadConfig.videoSuffix); 
		String httpUrl = UploadUtil.saveFile(file, UploadUtil.uploadConfig.videoFolder);	
		return AjaxJson.getSuccessData(httpUrl);
	}
	
	/** 上传音频   */
	@RequestMapping("audio")
	public AjaxJson audio(MultipartFile file){
		// 验证文件大小 -> 验证后缀 -> 保存到硬盘 -> 地址返回给前端 
		UploadUtil.checkFileSize(file); 	
		UploadUtil.checkSubffix(file.getOriginalFilename(), UploadUtil.uploadConfig.audioSuffix); 	
		String httpUrl = UploadUtil.saveFile(file, UploadUtil.uploadConfig.audioFolder);			
		return AjaxJson.getSuccessData(httpUrl);
	}
	
	/** 上传apk   */
	@RequestMapping("apk")
	public AjaxJson apk(MultipartFile file){
		// 验证文件大小 -> 验证后缀 -> 保存到硬盘 -> 地址返回给前端 
		UploadUtil.checkFileSize(file); 						
		UploadUtil.checkSubffix(file.getOriginalFilename(), UploadUtil.uploadConfig.apkSuffix); 	
		String httpUrl = UploadUtil.saveFile(file, UploadUtil.uploadConfig.apkFolder);		
		return AjaxJson.getSuccessData(httpUrl);
	}
	
	/** 上传任意文件   */
	@RequestMapping("file")
	public AjaxJson file(MultipartFile file){
		// 验证文件大小 -> 验证后缀 -> 保存到硬盘 -> 地址返回给前端 
		UploadUtil.checkFileSize(file); 					
		UploadUtil.checkSubffix(file.getOriginalFilename(), UploadUtil.uploadConfig.fileSuffix); 	
		String httpUrl = UploadUtil.saveFile(file, UploadUtil.uploadConfig.fileFolder);			
		return AjaxJson.getSuccessData(httpUrl);
	}

	@GetMapping(value = "download/{fileName}")
	public void download(@PathVariable(value = "fileName") String fileName, HttpServletResponse response) throws Exception {
		String filePath = "./file/"+fileName; // 文件路径
//		String filePath = "./file/ams-1.0.0 Setup.exe"; // 文件路径
		File file = new File(filePath);
		if (file.exists()) {
			response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
			response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
			response.setHeader("Connection", "keep-alive");
			response.setContentLength((int) file.length());
			InputStream inputStream = new FileInputStream(file);
			OutputStream outputStream = response.getOutputStream();
			try {
				byte[] buffer = new byte[1024];
				int length;
				while ((length = inputStream.read(buffer)) > 0) {
					outputStream.write(buffer, 0, length);
				}
				outputStream.flush();
			} catch (IOException e) {
				log.error("Download file error: {}", e.getMessage());
			} finally {
//				if (!response.isCommitted()) { // 判断是否已经提交响应
//					response.reset(); // 清空响应头和响应体，防止重复写入响应体
//				}
				try {
					if (outputStream != null) {
						outputStream.close();
					}
				} catch (IOException e) {
					log.error("Close output stream error: {}", e.getMessage());
				}
			}
		} else {
			log.warn("读取不到文件");
			response.setStatus(HttpStatus.NOT_FOUND.value());
			return;
		}
		log.info("下载完毕！");
	}


}
