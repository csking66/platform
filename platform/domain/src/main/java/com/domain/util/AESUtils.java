package com.domain.util;

import org.apache.shiro.codec.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 加密
 */
public class AESUtils {

	private static Logger logger = LoggerFactory.getLogger(AESUtils.class);

	private final static String ALGORITHM = "AES";

	public static void main(String[] args) throws Exception {

		String data = "123 测试&$#@456";
		String key = "wang!@#$%";
		System.out.println("data: " + data);
		System.out.println("After encrypted: " + encrypt(data, key));
		System.out.println("After decrypted: " + decrypt(encrypt(data, key), key));
	}

	/**
	 * 加密
	 * 
	 * @param content 需要加密的内容
	 * @param password 加密密码
	 * @return
	 */
	public static String encrypt(String content, String password) {

		try {
			KeyGenerator kgen = KeyGenerator.getInstance(ALGORITHM);
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, ALGORITHM);
			Cipher cipher = Cipher.getInstance(ALGORITHM);// 创建密码器
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			return Hex.encodeToString(result); // 加密
		}
		catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage(), e);
		}
		catch (NoSuchPaddingException e) {
			logger.error(e.getMessage(), e);
		}
		catch (InvalidKeyException e) {
			logger.error(e.getMessage(), e);
		}
		catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		}
		catch (IllegalBlockSizeException e) {
			logger.error(e.getMessage(), e);
		}
		catch (BadPaddingException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 解密
	 * 
	 * @param content 待解密内容
	 * @param password 解密密钥
	 * @return
	 */
	public static String decrypt(String content, String password) {

		try {
			KeyGenerator kgen = KeyGenerator.getInstance(ALGORITHM);
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, ALGORITHM);
			Cipher cipher = Cipher.getInstance(ALGORITHM);// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(Hex.decode(content));
			return new String(result); // 加密
		}
		catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage(), e);
		}
		catch (NoSuchPaddingException e) {
			logger.error(e.getMessage(), e);
		}
		catch (InvalidKeyException e) {
			logger.error(e.getMessage(), e);
		}
		catch (IllegalBlockSizeException e) {
			logger.error(e.getMessage(), e);
		}
		catch (BadPaddingException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	
	
	
}
