package bitcamp.java89.ems;

import java.util.Scanner;

public class EduApp {
  public static void main(String[] args) {
    System.out.println("=== 비트캠프 관리시스템에 오신걸 환영합니다 ===");
    Textbook[] books = new Textbook[2];
    Scanner keyScan = new Scanner(System.in);
    int length = 0;

    loop:
    while(true){
      System.out.printf("## 명령어를 입력해주세요.\n"
                      + "   (추가: add, 리스트보기: list, 상세정보보기:view, 종료:exit)\n");
      switch(keyScan.nextLine()) {
      case "add":
        if(length >= books.length) {
          System.out.println("배열이 가득찼습니다.");
          break;
        }
        while(length < books.length) {
          Textbook book = new Textbook();
          inputBookValue(book);
          books[length++] = book;

          if(length >= books.length) {
            System.out.println("배열이 가득찼습니다.");
            break;
          } else {
            System.out.printf("계속 입력하시겠습니까(y/n)? ");
            if (!keyScan.nextLine().equals("y")) {
              break;
            }
          }
        }
        break;
      case "list":
        printBookList(books, length);
        break;
      case "view":
        boolean nofound = false;
        System.out.println("찾으실 책이름을 입력하세요 : ");
        String searchTitle = keyScan.nextLine();
        for (int i = 0; i < length; i++) {
          if (books[i].title.equals(searchTitle)){
            printBook(books[i]);
            nofound = true;
          }
        }
        if (!nofound) {System.out.println("결과 값이 없습니다.");}
        break;
      case "exit":
        System.out.print("종료하시겠습니까(y/n)? ");
        if(keyScan.nextLine().equals("y")) {break loop;}
        else {break;}
      default:
        System.out.println("올바른 명령어가 아닙니다. 다시 입력해주세요.");
      }
    }
  }

  static void printBookList(Textbook[] books, int length) {
    for (int i = 0; i < length; i++) {
      Textbook book = books[i];
      printBook(book);
    }
  }

  static void printBook(Textbook book) {
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

  static void inputBookValue(Textbook book) {
    Scanner keyScan = new Scanner(System.in);
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
  }
}
