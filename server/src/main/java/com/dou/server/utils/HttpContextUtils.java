package com.dou.server.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
	 * @throws UnsupportedEncodingException
	 */
	public static HttpHeaders excelHeaders(String fileName) throws UnsupportedEncodingException {
		HttpHeaders headers = new HttpHeaders();
		fileName = URLEncoder.encode(fileName, "utf-8");
		fileName = fileName.replace("+", "%20");
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		headers.add("charset", "utf-8");
		headers.add("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
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