package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;

public class ThreadTest13 {
	static Horse[] horse = new Horse[]{
		new Horse("쭈녕"),
		new Horse("지뇽"),
		new Horse("밍구"),
		new Horse("병구"),
		new Horse("상구"),
		new Horse("지워니"),
		new Horse("해리비"),
		new Horse("유네"),
		new Horse("미니"),
		new Horse("승서비")
	};

	public static void main(String[] args) {
//		10마리의 말들이 경주하는 경마 프로그램 작성하기
		
//		말은 Horse라는 이름의 클래스로 구성한다.(이 각각의 말들은 하나의 쓰레드가 된다.)
//		이 클래스는 말 이름(String), 등수(int), 말의 현재 위치(int)를 멤버변수로 갖는다
//		그리고, 이 클래스에는 등수를 오름차순으로 처리할 수 있는 내부 정렬기준이 있다. (Comparable인터페이스 구하기)
		
//		경기 구간은 1 ~ 50구간으로 되어있다.
		
//		경기중 중간중간에 각 말들의 위치를 나타내시오
		
//		경기가 끝나면 등수 순으로 출력
		for (Horse hs : horse) {
			hs.start();
		}
		Print print = new Print();
		
		print.setDaemon(true);
		print.start();
		for (Horse hs : horse) {
			try {
				hs.join();
			} catch (Exception e) {
			}
		}
		
		ArrayList<Horse> horseList = new ArrayList<>();
		for(Horse hs : horse){
			horseList.add(hs);
		}
		
		Collections.sort(horseList);
		
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("================================");
		for(Horse hs : horseList){
			System.out.println(hs.rank +  "등\t " + hs.name + " ");
		}
		System.out.println("================================");

		
		
		
	}
}

class Horse extends Thread implements Comparable<Horse>{
	static int globalRank = 1;
	String name;
	int rank;
	int location=0;
	
	public Horse(String name) {
		this.name = name;
		this.rank = rank;
	}
	
	public String getName1() {
		return name;
	}
	
	public void setName1(String name) {
		this.name = name;
	}
	
	public int getRank() {
		return rank;
	}
	
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public int getLocation() {
		return location;
	}
	
	public void setLocation(int location) {
		this.location = location;
	}
	
	@Override
	public void run(){
		for (int i = 1; i <= 50; i++) {
			if(location == 49){
				this.rank = globalRank++;
			}

			this.location++;
			try {
				Thread.sleep((int)(Math.random() * 700));
				
			} catch (Exception e) {
			}
		}
	}

	@Override
	public int compareTo(Horse o) {
		return this.rank - o.rank;
	}
}

class Print extends Thread{
	
	public void print() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("======================================================================");
		for (int k = 0; k < ThreadTest13.horse.length; k++) {
			System.out.print(ThreadTest13.horse[k].name + "\t ");
		
				
				for (int j = 1; j <= 50; j++) {
					
					
					if(j == ThreadTest13.horse[k].location){
						System.out.print("🐎");
					}
					else {
						System.out.print("-");
					}
					
				}
				System.out.println();
		}
		System.out.println("======================================================================");
	}
	@Override
	public void run() {
		super.run();
		while(true){
		try {
			Thread.sleep(200);
			
		} catch (Exception e) {
		}
		
		print();
	}
		}
}