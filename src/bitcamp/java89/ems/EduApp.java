package bitcamp.java89.ems;

import java.util.Scanner;

public class EduApp {
  public static void main(String[] args) {
    System.out.println("비트캠프 관리시스템에 오신걸 환영합니다.");

    //1) 사용자로부터 값을 입력받을 떄
    Scanner keyScan = new Scanner(System.in);

    Textbook book = new Textbook();

    System.out.print("교재번호(예:1)? ");
    book.no = Integer.parseInt(keyScan.nextLine());

    System.out.print("교재명(예:자바프로그래밍)? ");
    book.title = keyScan.nextLine();

    System.out.print("저자(예:홍길동)? ");
    book.author = keyScan.nextLine();

    System.out.print("출판사(예:비트출판사)? ");
    book.press = keyScan.nextLine();

    System.out.print("가격(예:30000)? ");
    book.price = Integer.parseInt(keyScan.nextLine());

    System.out.print("쪽수(예:348)? ");
    book.pages = Integer.parseInt(keyScan.nextLine());

    System.out.print("재고수량(예:32)? ");
    book.stock = Integer.parseInt(keyScan.nextLine());

    System.out.print("부록(y/n)? ");
    book.suppl = (keyScan.nextLine().equals("y") ? true : false);

    System.out.print("강좌명(예:자바&DB)? ");
    book.className = keyScan.nextLine();

    System.out.print("배부여부(y/n)? ");
    book.distr = (keyScan.nextLine().equals("y") ? true : false);

    System.out.println("-----------------------------");
    System.out.printf("교재번호: %d\n", book.no);
    System.out.printf("교재명: %s\n", book.title);
    System.out.printf("저자: %s\n", book.author);
    System.out.printf("출판사: %s\n", book.press);
    System.out.printf("가격: %d 원\n", book.price);
    System.out.printf("쪽수: %d 쪽\n", book.pages);
    System.out.printf("재고수량: %d 권\n", book.stock);
    System.out.printf("부록여부: %s\n", (book.suppl) ? "부록있음" : "부록없음");
    System.out.printf("강좌명: %s\n", book.className);
    System.out.printf("배부여부: %s\n", (book.distr) ? "배부함" : "배부안함");
  }
}
