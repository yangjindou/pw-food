package com.dou.server.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * httpContext工具类
 * @author yangjd
 */
public class HttpContextUtils {

	/**
	 * excel请求头
	 * @param fileName 文件名
	 * @return
	 */
	public static HttpHeaders excelHeaders(String fileName) {
		HttpHeaders headers = new HttpHeaders();
		//将数据表这几个中文的字转码 防止导出后乱码
		headers.setContentDispositionFormData("attachment",
			new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
		headers.setContentType(MediaType.parseMediaType("application/x-msdownload"));
		return headers;
	}

	/**
	 * 获取request
	 * @return httpServletRequest
	 */
	public static HttpServletRequest getHttpServletRequest() {
		return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
	}

	/**
	 * 获取domain
	 * @return domain名
	 */
	public static String getDomain() {
		HttpServletRequest request = getHttpServletRequest();
		StringBuffer url = request.getRequestURL();
		return url.delete(url.length() - request.getRequestURI().length(), url.length()).toString();
	}
}