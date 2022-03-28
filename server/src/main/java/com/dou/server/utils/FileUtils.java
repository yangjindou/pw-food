package com.dou.server.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author yangjd
 */
public class FileUtils {

	@SuppressWarnings("all")
	public static File upload(MultipartFile file, String targetSrc, String fileName) throws Exception {
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
		file.transferTo(targetFile);

		return targetFile;
	}

	public static File getFile(String fileName, String src) {
		File target = new File(src, fileName);
		if (target.exists()) {
			return target;
		} else {
			return null;
		}
	}
}
