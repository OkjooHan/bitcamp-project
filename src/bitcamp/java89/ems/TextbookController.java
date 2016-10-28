package bitcamp.java89.ems;

import java.util.Scanner;

public class TextbookController {
  Textbook[] books = new Textbook[100];
  int length = 0;
  Scanner keyScan;

  public TextbookController(Scanner keyScan) {
    this.keyScan = keyScan;
  }

  public void doAdd() {
    while (length < this.books.length) {
      Textbook book = new Textbook();
      System.out.print("교재명(예:자바프로그래밍)? ");
      book.title = this.keyScan.nextLine();

      System.out.print("저자(예:홍길동)? ");
      book.author = this.keyScan.nextLine();

      System.out.print("출판사(예:비트출판사)? ");
      book.press = this.keyScan.nextLine();

      System.out.print("가격(예:30000)? ");
      book.price = Integer.parseInt(this.keyScan.nextLine());

      System.out.print("쪽수(예:348)? ");
      book.pages = Integer.parseInt(this.keyScan.nextLine());

      System.out.print("재고수량(예:32)? ");
      book.stock = Integer.parseInt(this.keyScan.nextLine());

      System.out.print("강좌명(예:자바&DB)? ");
      book.className = this.keyScan.nextLine();

      System.out.print("부록(y/n)? ");
      book.suppl = (this.keyScan.nextLine().equals("y") ? true : false);

      System.out.print("배부여부(y/n)? ");
      book.distr = (this.keyScan.nextLine().equals("y") ? true : false);

      this.books[this.length++] = book;

      System.out.printf("계속 입력하시겠습니까(y/n)? ");
      if (!this.keyScan.nextLine().equals("y")) {
        break;
      }
    }
  }

  public void doList() {
    for (int i = 0; i < length; i++) {
      Textbook book = this.books[i];
      System.out.printf(" 책이름: %s\n 저자: %s\n 출판사: %s\n 가격: %d원\n"
      + " 페이지: %d쪽\n 재고수량: %d남음\n 부록여부: %s\n"
      + " 과정명: %s\n 배부여부: %s\n-------------------------\n",
      book.title,
      book.author,
      book.press,
      book.price,
      book.pages,
      book.stock,
      (book.suppl ? "부록있음" : "부록없음"),
      book.className,
      (book.distr ? "배부함" : "배부안함"));
    }
  }

  public void doView() {
    System.out.printf("조회할 교재명을 입력하세요: ");
    String title = this.keyScan.nextLine().toLowerCase();
    for (int i = 0; i < this.length; i++) {
      if (this.books[i].title.toLowerCase().equals(title)) {
        System.out.printf("교재명: %s\n", this.books[i].title);
        System.out.printf("저자: %s\n", this.books[i].author);
        System.out.printf("출판사: %s\n", this.books[i].press);
        System.out.printf("가격: %d\n", this.books[i].price);
        System.out.printf("쪽수: %d\n", this.books[i].pages);
        System.out.printf("재고수량: %d\n", this.books[i].stock);
        System.out.printf("강좌명: %s\n", this.books[i].className);
        System.out.printf("부록: %b\n", (this.books[i].suppl ? "부록있음" : "부록없음"));
        System.out.printf("배부: %s\n", (this.books[i].distr ? "배부함" : "배부안함"));
        return;
      }
    }
    System.out.printf("%s 교재 정보를 찾을 수 없습니다.\n", title);
  }

  public void doDelete() {
    System.out.printf("삭제할 교재명을 입력하세요: ");
    String title = this.keyScan.nextLine().toLowerCase();
    for (int i = 0; i < this.length; i++) {
      if (this.books[i].title.toLowerCase().equals(title)) {
        for (int x = i + 1; x < this.length; x++, i++) {
          this.books[i] = this.books[x];
        }
        this.books[--this.length] = null;
        System.out.printf("%s 교재 정보를 삭제하였습니다.\n", title);
        return; //함수 실행을 종료한다.
      }
    }
    System.out.printf("%s 교재 정보를 찾을 수 없습니다.\n", title);
  }

  public void doUpdate() {
    System.out.printf("수정할 교재명을 입력하세요: ");
    String title = this.keyScan.nextLine().toLowerCase();
    for (int i = 0; i < this.length; i++) {
      if (this.books[i].title.toLowerCase().equals(title)) {
        Textbook book = new Textbook();
        book.title = this.books[i].title;

        System.out.print("저자(예:홍길동)? ");
        book.author = this.keyScan.nextLine();

        System.out.print("출판사(예:비트출판사)? ");
        book.press = this.keyScan.nextLine();

        System.out.print("가격(예:30000)? ");
        book.price = Integer.parseInt(this.keyScan.nextLine());

        System.out.print("쪽수(예:348)? ");
        book.pages = Integer.parseInt(this.keyScan.nextLine());

        System.out.print("재고수량(예:32)? ");
        book.stock = Integer.parseInt(this.keyScan.nextLine());

        System.out.print("강좌명(예:자바&DB)? ");
        book.className = this.keyScan.nextLine();

        System.out.print("부록(y/n)? ");
        book.suppl = (this.keyScan.nextLine().equals("y") ? true : false);

        System.out.print("배부여부(y/n)? ");
        book.distr = (this.keyScan.nextLine().equals("y") ? true : false);

        System.out.printf("저장하시겠습니까(y/n)? ");
        if (!this.keyScan.nextLine().toLowerCase().equals("y")) {
          System.out.println("변경을 취소하였습니다.");
        } else {
          this.books[i] = book;
          System.out.println("저장하였습니다.");
        }
        return;
      }
    }
    System.out.printf("%s 교재 정보를 찾을 수 없습니다.\n", title);
  }
}
