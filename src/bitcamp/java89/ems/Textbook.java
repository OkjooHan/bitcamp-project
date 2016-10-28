package bitcamp.java89.ems;

import java.util.Scanner;

public class Textbook{
  //인스턴스 변수
  String title; //교재명
  String author; //저자
  String press; //출판사
  int price; //가격
  int pages; //페이지 수
  int stock; //재고 수, 보유 수
  boolean suppl; //부록 여부
  String className; //교재가 사용되는 강좌명
  boolean distr; //배부 여부

  public Textbook() {}

  public Textbook(String title, int stock, String className, boolean distr) {
    this.title = title;
    this.stock = stock;
    this.className = className;
    this.distr = distr;
  }

  public void inputBookValue() {
    Scanner keyScan = new Scanner(System.in);
    System.out.print("교재명(예:자바프로그래밍)? ");
    this.title = keyScan.nextLine();

    System.out.print("저자(예:홍길동)? ");
    this.author = keyScan.nextLine();

    System.out.print("출판사(예:비트출판사)? ");
    this.press = keyScan.nextLine();

    System.out.print("가격(예:30000)? ");
    this.price = Integer.parseInt(keyScan.nextLine());

    System.out.print("쪽수(예:348)? ");
    this.pages = Integer.parseInt(keyScan.nextLine());

    System.out.print("재고수량(예:32)? ");
    this.stock = Integer.parseInt(keyScan.nextLine());

    System.out.print("부록(y/n)? ");
    this.suppl = (keyScan.nextLine().equals("y") ? true : false);

    System.out.print("강좌명(예:자바&DB)? ");
    this.className = keyScan.nextLine();

    System.out.print("배부여부(y/n)? ");
    this.distr = (keyScan.nextLine().equals("y") ? true : false);
  }

  public void printBook() {
    System.out.printf(" 책이름: %s\n 저자: %s\n 출판사: %s\n 가격: %d원\n"
                    + " 페이지: %d쪽\n 재고수량: %d남음\n 부록여부: %s\n"
                    + " 과정명: %s\n 배부여부: %s\n-------------------------\n",
      this.title,
      this.author,
      this.press,
      this.price,
      this.pages,
      this.stock,
      (this.suppl ? "부록있음" : "부록없음"),
      this.className,
      (this.distr ? "배부함" : "배부안함"));
  }

  // public void printBookList(Textbook[] books, int length) {
  //   for (int i = 0; i < length; i++) {
  //     Textbook book = books[i];
  //     book.printBook();
  //   }
  // }
}
