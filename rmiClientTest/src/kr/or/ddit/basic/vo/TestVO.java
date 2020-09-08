package kr.or.ddit.basic.vo;

import java.io.Serializable;

//이 클래스는 RMI에서 데이터 전달용으로 사용할 클래스임.
//RMI에서 데이터 전달용으로 사용되는 객체는 네트워크를 통해서
//전달되어야 하기 때문에 직렬화가 필요하다. 그래서 Serializable을 구현해야한다.

public class TestVO implements Serializable{
	private int number;
	private String name;
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
