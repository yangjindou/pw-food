package com.dou.server.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Map;
import java.util.Random;

/**
 * 通用工具类
 * @author yangjd
 */
public class CommonUtils {

	public static String randomStr(int length) {
		String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<length; i++) {
			int number = random.nextInt(62);
			sb.append(str.charAt(number));
		}
		return sb.toString();
	}

	public static boolean varIsBlank(Object... vars) {
		boolean isBlank = false;
		if (null == vars || vars.length == 0) {
			return true;
		}
		for (Object var : vars) {
			if (null == var) {
				isBlank = true;
				break;
			} else if (var instanceof String) {
				String str = var.toString();
				if (StringUtils.isBlank(str) || "null".equals(str) || "undefined".equals(str)) {
					isBlank = true;
					break;
				}
			} else if (var instanceof Collection) {
				if (CollectionUtils.isEmpty((Collection<?>) var)) {
					isBlank = true;
					break;
				}
			} else if (var instanceof Map) {
				if (MapUtils.isEmpty((Map<?,?>) var)) {
					isBlank = true;
					break;
				}
			}
		}
		return isBlank;
	}

	public static Integer integerRandom(Integer min, Integer max) {
		Random random = new Random();
		return random.nextInt(max)%(max-min+1) + min;
	}

	/**
	 * 获取IP地址
	 * @param request HttpServletRequest
	 * @return String
	 */
	public static String getIpAddress(HttpServletRequest request){
		String ipAddress = request.getHeader("x-forwarded-for");
		if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)){
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)){
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)){
			ipAddress = request.getRemoteAddr();

			if ("127.0.0.1".equals(ipAddress) || "0:0:0:0:0:0:0:1".equals(ipAddress)){
				InetAddress inetAddress = null;
				try {
					inetAddress = InetAddress.getLocalHost();
				} catch (UnknownHostException e){
					e.printStackTrace();
				}
				assert inetAddress != null;
				ipAddress = inetAddress.getHostAddress();
			}
		}
		if (null != ipAddress && ipAddress.length() > 15){
			if(ipAddress.indexOf(",") > 0){
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}
}
