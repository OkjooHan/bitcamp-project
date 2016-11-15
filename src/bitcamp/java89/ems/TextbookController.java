// 모든 예외 처리를 service()에서 수행한다.
// => 이점: doxxx() 메서드에서 예외처리 코드를 작성할 필요가 없다.
// => 단점: 각각의 명령어마다 섬세하게 예외를 다룰 수 없다.
//따라서 예외를 중앙에서 처리할 지 개별적으로 처리할 지
//아니면 섞을지 개발자가 선택하면 된다.
package bitcamp.java89.ems;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.EOFException;

public class TextbookController {
  private ArrayList<Textbook> list;
  private int length;
  private Scanner keyScan;
  private boolean changed;

  public TextbookController(Scanner keyScan, String filePath) {
  // public TextbookController(Scanner keyScan) {
    length = 0;
    this.keyScan = keyScan;
    list = new ArrayList<Textbook>();
    changed = false;
    doLoad(filePath);
  }

  public void service() {
    loop:
    while(true) {
      System.out.println("1. 교재관리");
      System.out.println("[add]:추가 / [list]:보기 / [view]:찾기 / [delete]:삭제 /"
                       + " [update]:변경 / [main]: 메인메뉴");
      System.out.print("명령> ");
      String command = keyScan.nextLine().toLowerCase();
      try {
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
        }
      } catch (IndexOutOfBoundsException e) {
        System.out.println("인덱스가 유효하지 않습니다");
      } catch (Exception e) {
        System.out.println("실행 중 오류가 발생했습니다.");
      }
    }//while
  }

  // 아래 doXXX() 메서드들은 오직 service()에서만 호출하기 때문에
  // private으로 접근을 제한한다.
  private void doAdd() {
    while (true) {
      Textbook book = new Textbook();
      System.out.print("교재명(예:자바프로그래밍)? ");
      book.title = this.keyScan.nextLine();

      System.out.print("저자(예:홍길동)? ");
      book.author = this.keyScan.nextLine();

      System.out.print("출판사(예:비트출판사)? ");
      book.press = this.keyScan.nextLine();

      while (true) {
        try {
          System.out.print("가격(예:30000)? ");
          book.price = Integer.parseInt(this.keyScan.nextLine());
          break;
        } catch (Exception e) {
          System.out.println("정수 값을 입력하세요.");
        }
      }//while

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

      list.add(book);
      changed = true;

      System.out.printf("계속 입력하시겠습니까(y/n)? ");
      if (!this.keyScan.nextLine().equals("y")) {
        break;
      }
    }//while
  }

  private void doList() {
    for (Textbook book : list) {
      System.out.printf(" %s, %s, %s, %d원, %d쪽, %d남음, %s,"
      + " %s, %s\n------------------------------------------------\n",
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
    System.out.print("조회할 교재의 인덱스를 입력하세요: ");
    int index = Integer.parseInt(this.keyScan.nextLine());
    Textbook book = list.get(index);

    System.out.printf("교재명: %s\n", book.title);
    System.out.printf("저자: %s\n", book.author);
    System.out.printf("출판사: %s\n", book.press);
    System.out.printf("가격: %d\n", book.price);
    System.out.printf("쪽수: %d\n", book.pages);
    System.out.printf("재고수량: %d\n", book.stock);
    System.out.printf("강좌명: %s\n", book.className);
    System.out.printf("부록: %b\n", (book.suppl ? "부록있음" : "부록없음"));
    System.out.printf("배부: %s\n", (book.distr ? "배부함" : "배부안함"));
    System.out.println("-----------------------");
  }

  private void doDelete() {
    System.out.printf("삭제할 교재명의 인덱스를 입력하세요: ");
    int index = Integer.parseInt(keyScan.nextLine());

    //LinkedList 클래스를 비롯한 클래스를 생성하면 남이 사용한다는 전제임.
    //LinkedList 클래스에도 인덱스 유효를 체크하지만, 이곳에 동일 내용이 있다고 해서
    //클래스에서 지우면 다른 사용자가 유효체크 없이 아무 값이나 넣어도 유효 체크를 할 수 없다.
    // if (index < 0 || index >= list.size()) {
    //   System.out.println("인덱스가 유효하지 않습니다.");
    //   return;
    // }

    Textbook deleteBook = list.remove(index);
    if (deleteBook == null) {
      return;
    }
    changed = true;
    System.out.printf("%s 교재 정보를 삭제하였습니다.\n", deleteBook.title);
  }

  private void doUpdate() {
    System.out.printf("수정할 교재의 인덱스를 입력하세요: ");
    int index = Integer.parseInt(this.keyScan.nextLine());
    Textbook oldBook = list.get(index);

    Textbook book = new Textbook();

    book.title = oldBook.title;

    System.out.printf("저자(%s)? ", oldBook.author);
    book.author = this.keyScan.nextLine();

    System.out.printf("출판사(%s)? ", oldBook.press);
    book.press = this.keyScan.nextLine();

    while (true) {
      try {
        System.out.printf("가격(%d)? ", oldBook.price);
        book.price = Integer.parseInt(this.keyScan.nextLine());

        break;
      } catch (Exception e) {
        System.out.println("정수 값을 입력하세요.");
      }
    }//while

    System.out.printf("쪽수(%d)? ", oldBook.pages);
    book.pages = Integer.parseInt(this.keyScan.nextLine());

    System.out.printf("재고수량(%d)? ", oldBook.stock);
    book.stock = Integer.parseInt(this.keyScan.nextLine());

    System.out.printf("강좌명(%s)? ", oldBook.className);
    book.className = this.keyScan.nextLine();

    System.out.printf("부록(y/n)? ");
    book.suppl = (this.keyScan.nextLine().equals("y") ? true : false);

    System.out.printf("배부여부(y/n)? ");
    book.distr = (this.keyScan.nextLine().equals("y") ? true : false);

    System.out.printf("저장하시겠습니까(y/n)? ");
    if (!this.keyScan.nextLine().toLowerCase().equals("y")) {
      System.out.println("변경을 취소하였습니다.");
    } else {
      list.set(index, book);
      System.out.println("저장하였습니다.");
      changed = true;
    }
  }

  private void doLoad(String filePath) {
    try {
      File file = new File(filePath);
      if(!file.exists()) {
        file.createNewFile();
      }
    } catch (IOException e){
      System.out.println("파일을 정상적으로 로드하지 못했습니다.");
    }

    FileInputStream fis = null;
    DataInputStream in = null;
    try {
      fis = new FileInputStream(filePath);
      in = new DataInputStream(fis);
      while (true) {
      //while (fis.available() > 0) {
      //while (fis.read() != -1)
        Textbook book = new Textbook();
        book.title = in.readUTF();
        book.author = in.readUTF();
        book.press = in.readUTF();
        book.price = in.readInt();
        book.pages = in.readInt();
        book.stock = in.readInt();
        book.className = in.readUTF();
        book.suppl = in.readBoolean();
        book.distr = in.readBoolean();
        list.add(book);
      }
    } catch (EOFException e) {
      // 파일 생성 후 데이터 없음 or 파일을 모두 읽음
    } catch (IOException e) {
      //e.printStackTrace();
      System.out.println("데이터를 정상적으로 로드하지 못했습니다.");
    } finally {
      try {
        in.close();
        fis.close();
      } catch (Exception e) {}
    }
  }

  public void doSave(String filePath) {
    try {
      FileOutputStream fos = new FileOutputStream(filePath);
      DataOutputStream out = new DataOutputStream(fos);

      for (Textbook book : list) {
        out.writeUTF(book.title);
        out.writeUTF(book.author);
        out.writeUTF(book.press);
        out.writeInt(book.price);
        out.writeInt(book.pages);
        out.writeInt(book.stock);
        out.writeUTF(book.className);
        out.writeBoolean(book.suppl);
        out.writeBoolean(book.distr);
      }
      out.close();
      fos.close();
      changed = false;

      System.out.println("저장하였습니다.");
    } catch (IOException e) {
      System.out.println("데이터를 정상적으로 저장하지 못했습니다.");
    }
  }

  public boolean isChanged() {
    return changed;
  }
}
