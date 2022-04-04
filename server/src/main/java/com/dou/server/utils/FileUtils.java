package com.dou.server.utils;

import com.dou.server.exception.LogicException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUtils {

	public static File upload(MultipartFile file, String targetSrc, String fileNewName) {
		if (file == null || file.isEmpty()) throw new LogicException("无效空文件！");
		try {
			String fileOldName= file.getOriginalFilename();
			if (fileOldName == null || fileOldName.lastIndexOf(".") == -1)
				throw new LogicException("文件名错误！");
			String fileType = fileOldName.substring(fileOldName.lastIndexOf("."));
			File targetFile = FileUtils.initDir(targetSrc, fileNewName + fileType);
			file.transferTo(targetFile);
			return targetFile;
		} catch (IOException e) {
			e.printStackTrace();
			throw new LogicException("文件保存错误！");
		}
	}

	public static File initDir(String targetSrc, String fileName) {
		// 判断文件夹是否存在
		File targetDir = new File(targetSrc);
		if (!targetDir.exists()) {
			// 创建文件夹
			targetDir.mkdirs();
		}
		File targetFile = new File(targetSrc, fileName);
		// 如果文件已经存在，则删除原有文件
		if (targetFile.exists()) {
			targetFile.delete();
		}
		return targetFile;
	}
}
