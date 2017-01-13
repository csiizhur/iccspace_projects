package com.icc.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrUtil {

	private static final String CHECK_EMAIL_REGEX = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	private static final String CHECK_MOBILE_REGEX = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
	private static final String CHECK_USERNAME_REGEX = "[a-zA-Z0-9_\u4e00-\u9fa5]{0,25}$";

	public static boolean isValidity(String str) {
		return str != null && !"".equals(str.trim());
	}

	public static String getStrNoNull(String str) {
		return str == null ? "" : str.toString();
	}

	public static boolean isValidityArray(String[] strArray) {
		return strArray != null && strArray.length != 0;
	}

	public static boolean check(String regex, String value) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(value);
		return m.matches();
	}

	public static boolean checkEmail(String email) {
		return check(CHECK_EMAIL_REGEX, email);
	}

	public static boolean checkMobile(String mobiles) {
		return check(CHECK_MOBILE_REGEX, mobiles);
	}

	public static boolean checkUserName(String name) {
		return check(CHECK_USERNAME_REGEX, name);
	}

	public static boolean isValidityStrBuilder(StringBuilder sb) {
		return sb != null && !"".equals(sb.toString().trim());
	}

	public static boolean ArrayHasValue(String[] strArray) {
		if (strArray == null || strArray.length == 0) {
			return false;
		}
		for (String str : strArray) {
			if (null != str && !"".equals(str.trim())) {
				return true;
			}
		}
		return false;
	}

	public static boolean equals(StringBuilder sb, String str) {
		if (sb == null || str == null)
			return false;
		String sbStr = sb.toString();
		boolean result = str.trim().equals(sbStr.trim());
		sbStr = null;
		return result;
	}

	public static void main(String[] args) {
		// System.out.println(6%4);
		System.out.println(StrUtil.replace("123456789987654321", "=", 3));
	}
	/**
	 * 
	 * @param target 需要替换的字符
	 * @param replacement 以什么符号连接
	 * @param space 以多少位连接
	 * @return
	 */
	public static String replace(String target, String replacement, int space) {
		int len = target.length();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < len; i++) {
			if ((i + 1) % space == 0) {
				sb.append(target.substring(i + 1 - space, i + 1) + replacement);
			}
		}
		if (len % space == 0) {
			sb = new StringBuffer(sb.substring(0, sb.length() - 1));
		} else {
			sb.append(target.substring(len - (len % space)));
		}
		return sb.toString();
	}
}
