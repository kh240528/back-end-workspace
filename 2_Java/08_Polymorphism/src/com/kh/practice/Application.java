package com.kh.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.kh.practice.controller.BookController;
import com.kh.practice.model.Book;
import com.kh.practice.model.Member;

public class Application {
	
	Scanner sc = new Scanner(System.in);
	BookController bc = new BookController();
	
	ArrayList<Book> books = new ArrayList<>();
	
	{
		books.add(new Book("밥을 지어요", true, 0));
		books.add(new Book("오늘은 아무래도 덮밥", false, 0));
		books.add(new Book("원피스 108", false, 15));
		books.add(new Book("귀멸의 칼날 23", false, 19));
	}
	
	public static void main(String[] args) {
		
		Application app = new Application();
		app.menu();
	}
	
	public void menu() {
		System.out.print("이름 : ");
		String name = sc.nextLine();
		
		System.out.print("나이 : ");
		int age = Integer.parseInt(sc.nextLine());
		
		bc.addMember(name, age);
		
		boolean close = true;
		while(close) {
			System.out.println("==== 메뉴 ====");
			System.out.println("1. 마이페이지");
			System.out.println("2. 도서 대여하기");
			System.out.println("3. 프로그램 종료하기");
			System.out.print("메뉴 번호 : ");
			
			switch(Integer.parseInt(sc.nextLine())) {
			case 1:
				System.out.println(bc.myPage());
				break;
			case 2:
				rent();
				break;
			case 3:
				close = false;
				break;
			}
	
		}
		
	}
	
	public void rent() {
		for(int i = 0; i < books.size(); i++) {
			System.out.println((i+1) + "번 도서 : " + books.get(i));
		}
		System.out.print("대여할 도서 번호 선택 : ");
		int select = Integer.parseInt(sc.nextLine());
		System.out.println(bc.rentBook(books.get(select-1)));
	}
	
	
	
	
	
	

}
