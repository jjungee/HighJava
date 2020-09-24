package kr.or.ddit.util;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

//양방향 암호화 알고리즘인 AES256 암호화를 지원하는 클래스
public class AES256Util {
	//초기화 벡터(Initial Vector) => IV는 암호문이 패턴화 되지 않도록 사용하는 데이터
	private String iv;
	private Key keySpec;
	
	//이 암호화 키는 사용자가 변경해서 사용할 수 있다.
	private String key = "a1b2c3d4e5f6g7h8";	//암호화 키(16자리 이상)
	
	//생성자
	public AES256Util() throws UnsupportedEncodingException {
		this.iv = key.substring(0, 16);
		byte[] keyBytes = new byte[16];
		byte[] b = key.getBytes("UTF-8");
		
		int len = b.length;
		if(len > keyBytes.length){
			len = keyBytes.length;
		}
		System.arraycopy(b, 0, keyBytes, 0, len);
		
		//비밀키 생성
		keySpec = new SecretKeySpec(keyBytes, "AES");
	}
	//AES256방식으로 암호화 하는 메서드
	public String encrypt(String str) throws NoSuchAlgorithmException, 
				GeneralSecurityException, UnsupportedEncodingException{
		//Cipher객체 생성
		//형식) 알고리즘/모드/패딩
		//		CBC : Cipher Block Chaining Mode => 동일한 평문 블록과 암호문 블록 쌍이 발생하지 않도록 
		//		이전 단계의 암복호화한 결과가 현 단계에 영향을 주는 운명 모드를 말한다.
		//		패딩 : Padding => 마지막 블록이 블록의 길이와 항상 딱 맞아 떨어지지 않기 때문에 
		//		부족한 길이만큼 '0'으로 채우거나 임의의 비트로 채워 넣는 것을 의미한다.
		
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		
		byte[] ivBytes = new byte[16];
		System.arraycopy(iv.getBytes(), 0, ivBytes, 0, ivBytes.length);
		
		c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(ivBytes));
		
		//암호문 생성
		byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));
		
		//생성된 암호문을 문자열로 변환해서 반환한다.
		String enStr = new String(Base64.encodeBase64(encrypted));
		return enStr;
	}
	//AES256방식으로 암호화된 문자열을 원래의 문자열로 복원하는 메서드
	public String decrypt(String str) throws NoSuchAlgorithmException, 
	GeneralSecurityException, UnsupportedEncodingException{
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		
		byte[] ivBytes = new byte[16];
		System.arraycopy(iv.getBytes(), 0, ivBytes, 0, ivBytes.length);
		
		c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(ivBytes));	
		
		//암호문을 byte배열로 변환
		byte[] byteStr = Base64.decodeBase64(str.getBytes());
		
		//복원한 데이터를 문자열로 변환하여 반환한다.
		return new String(c.doFinal(byteStr), "UTF-8");
	}
}
