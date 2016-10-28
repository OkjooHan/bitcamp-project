package bitcamp.java89.ems;

import java.util.Scanner;

public class EduApp {
  public static void main(String[] args) {
    System.out.println("비트캠프 관리시스템에 오신걸 환영합니다.");

    //1) 사용자로부터 값을 입력받을 떄
    Scanner keyScan = new Scanner(System.in);

    Textbook[] books = new Textbook[5];
    int length = 0;

    Textbook book = null; //주소 없음을 의미한다.
    //while 문 안에서 Textbook book = new Textbook();
    //해도 동일하게 book 하나만 생성하여 사용하지만, while문 밖에서 선언하면
    //while문 실행 후에도 계속 사용할 수 있다.
    //=> while문 내에서만 사용할 거라면 안에서 선언하여 사용하면 된다.
    //=> best practice에서는 가장 작은 범위에서 선언하여 사용하기를 권장한다.
    while(length < books.length) {
      book = new Textbook();
      //Textbook book = new Textbook(); 동일함.

      System.out.print("강좌명(예:자바&DB)? ");
      book.className = keyScan.nextLine();

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

      System.out.print("배부여부(y/n)? ");
      book.distr = (keyScan.nextLine().equals("y") ? true : false);

      books[length++] = book;

      System.out.printf("계속 입력하시겠습니까(y/n)? ");
      if (!keyScan.nextLine().equals("y")) {
        break;
      }
    }
    printBookList(books, length);
  }

  static void printBookList(Textbook[] books, int length) {
    Textbook book = null;
    for (int i = 0; i < length; i++) {
      book = books[i];
      System.out.printf("%s, %s, %s, %s, %d원, %d쪽, %d남음, %s, %s\n",
        book.className,
        book.title,
        book.author,
        book.press,
        book.price,
        book.pages,
        book.stock,
        (book.suppl ? "부록있음" : "부록없음"),
        (book.distr ? "배부함" : "배부안함"));
    }
  }
}
