package com.itv.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.log4j.Logger;

import java.security.MessageDigest;

/**
 * 加密解密
 * 
 * @author shy.qiu
 * @since http://blog.csdn.net/qiushyfm
 */
public class Crypt {
	private static Logger log = Logger.getLogger(Crypt.class);

	public static byte[] encrypt(String content, String password) {
		try {
			SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// 创建密码器
			byte[] byteContent = content.getBytes("utf-8");
			IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			return result; // 加密
		} catch (Exception e) {
			log.error("Crypt encrypt:", e);
		}
		return null;
	}

	public static byte[] decrypt(byte[] content, String password) {
		try {
			SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// 创建密码器
			IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
			cipher.init(Cipher.DECRYPT_MODE, key, iv);// 初始化
			byte[] result = cipher.doFinal(content);
			return result; // 加密
		} catch (Exception e) {
			log.error("Crypt decrypt", e);
		}
		return null;
	}

	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		try {
			for (int i = 0; i < buf.length; i++) {
				String hex = Integer.toHexString(buf[i] & 0xFF);
				if (hex.length() == 1) {
					hex = '0' + hex;
				}
				sb.append(hex.toUpperCase());
			}
		} catch (Exception e) {
			log.error("二进制转16进制:", e);
		}
		return sb.toString();
	}

	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

    /**
     * md5计算
     * @param inStr
     * @return
     */
    public static String MD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray(); // 将字符串转换为char数组
        byte[] byteArray = new byte[charArray.length]; // 创建一个相同长度的char数组
        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i]; // 将字符转换后的数组付给byteArray
        }
        byte[] md5Bytes = md5.digest(byteArray); // 将md5后的16进值付给md5Bytes
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static void main(String[] args) {
        System.out.println( Crypt.MD5("123456"));
    }
}