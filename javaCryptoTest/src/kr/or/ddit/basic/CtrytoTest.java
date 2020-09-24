package kr.or.ddit.basic;

import java.security.NoSuchAlgorithmException;

import kr.or.ddit.util.AES256Util;
import kr.or.ddit.util.CryptoUtil;

public class CtrytoTest {

	public static void main(String[] args) throws Exception {
		String testData = "진성호";
		
		System.out.println("MD5 : " + CryptoUtil.md5(testData));
		System.out.println("SHA-256 : " + CryptoUtil.sha256(testData));
		System.out.println("SHA-512 : " + CryptoUtil.sha512(testData));
		System.out.println("--------------------------------------------");
		
		AES256Util aes256 = new AES256Util();
		String str = aes256.encrypt(testData);
		System.out.println("AES256 암호화하기 전 : " + testData);
		System.out.println("AES256 암호화한 후 : " + str);
		
		String deStr = aes256.decrypt(str);
		System.out.println("AES256 복호화한 후 : " + deStr);
	}

}
