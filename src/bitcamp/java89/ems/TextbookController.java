package bitcamp.java89.ems;

import java.util.Scanner;

public class TextbookController {
  // 아래 인스턴스 변수들은 외부에서 사용할 일이 없기 때문에
  // private으로 접근을 제한한다.
  private Textbook[] books = new Textbook[100];
  private int length = 0;
  private Scanner keyScan;

  public TextbookController(Scanner keyScan) {
    this.keyScan = keyScan;
  }

  public void service() {
    loop:
    while(true) {
      System.out.println("1. 교재관리");
      System.out.println("[add]:추가, [list]:보기, [view]:찾기, [delete]:삭제,"
                       + " [update]:변경, [main]: 메인메뉴");
      System.out.print("명령> ");
      String command = keyScan.nextLine().toLowerCase();

      switch (command) {
      case "add": this.doAdd(); break;
      case "list": this.doList(); break;
      case "view": this.doView(); break;
      case "delete": this.doDelete(); break;
      case "update" : this.doUpdate(); break;
      case "main":
        break loop;
      default:
        System.out.println("지원하지 않는 명령어입니다.");
        break;
      }
    }
  }

  // 아래 doXXX() 메서드들은 오직 service()에서만 호출하기 때문에
  // private으로 접근을 제한한다.
  private void doAdd() {
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

  private void doList() {
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

  private void doView() {
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

  private void doDelete() {
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

  private void doUpdate() {
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
