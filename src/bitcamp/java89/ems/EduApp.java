package bitcamp.java89.ems;

import java.util.Scanner;

public class EduApp {
  public static void main(String[] args) {
    System.out.println("=== 비트캠프 관리시스템에 오신걸 환영합니다 ===");
    Textbook[] books = new Textbook[5];
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
          book.inputBookValue();
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
        //printBookList(books, length);
        for (int i = 0; i < length; i++) {
          books[i].printBook();
        }
        break;
      case "view":
        boolean nofound = false;
        System.out.println("## 찾으실 책이름을 입력하세요 : ");
        String searchTitle = keyScan.nextLine();
        for (int i = 0; i < length; i++) {
          if (books[i].title.equals(searchTitle)){
            books[i].printBook();
            nofound = true;
          }
        }
        if (!nofound) {System.out.println("결과 값이 없습니다.");}
        break;
      case "exit":
        System.out.print("## 종료하시겠습니까(y/n)? ");
        if(keyScan.nextLine().equals("y")) {break loop;}
        else {break;}
      default:
        System.out.println("올바른 명령어가 아닙니다. 다시 입력해주세요.");
      }
    }
  }
}
