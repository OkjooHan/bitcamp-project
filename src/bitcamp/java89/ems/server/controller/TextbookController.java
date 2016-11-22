/* 작업내용 : 직렬화 적용
 */
package bitcamp.java89.ems.server.controller;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import bitcamp.java89.ems.server.vo.Phonebook;
import bitcamp.java89.ems.server.vo.Textbook;

public class TextbookController {
  private Scanner in;
  private PrintStream out;
  private ArrayList<Textbook> list;
//  private int length;
  private boolean changed;
  String filePath = "./src/bitcamp/java89/ems/Textbook_v1.6.data";

  public TextbookController(Scanner in, PrintStream out) {
    this.in = in;
    this.out = out;
    list = new ArrayList<Textbook>();
    changed = false;
    doLoad();
  }

  public boolean service() {
    while(true) {
      out.println("1. 교재관리");
      out.println("[add]:추가 / [list]:보기 / [view]:찾기 / [delete]:삭제 /"
                       + " [update]:변경 / [main]: 메인메뉴");
      out.println("명령> ");
      out.println();
      String command[] = in.nextLine().toLowerCase().split("\\?");
      try {
        switch (command[0]) {
        case "add": this.doAdd(command[1]); break;
        case "list": this.doList(); break;
        case "view": this.doView(command[1]); break;
        case "delete": this.doDelete(command[1]); break;
        case "update" : this.doUpdate(command[1]); break;
        case "main": doQuit(); return true;
        case "quit": doQuit(); return false;
        default:
          System.out.println("지원하지 않는 명령어입니다.");
        }
      } catch (IndexOutOfBoundsException e) {
        e.printStackTrace();
        System.out.println("인덱스가 유효하지 않습니다");
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println("실행 중 오류가 발생했습니다.");
      }
    }//while
  }
  
  private void doQuit() {
    if (isChanged())
      doSave();
//    System.out.println("Good bye!");
  }

  // 아래 doXXX() 메서드들은 오직 service()에서만 호출하기 때문에
  // private으로 접근을 제한한다.
  //add?title=javaprogramming&author=aaa&press=bitpress&price=30000&pages=200&stock=3&className=bit89&suppl=y&distr=n
  //add?title=db study&author=bbb&press=bitpress&price=10000&pages=100&stock=1&className=bit89&suppl=y&distr=n
  private void doAdd(String params) {
    System.out.println(params);
    String[] values = params.split("&");
    HashMap<String, String> paramMap = new HashMap<>();
    
    for (String value : values) {
      String[] kv = value.split("=");
      paramMap.put(kv[0], kv[1]);
    }
    
    Textbook book = new Textbook();
    book.setTitle(paramMap.get("title"));
    book.setAuthor(paramMap.get("author"));
    book.setPress(paramMap.get("press"));
    book.setPrice(Integer.parseInt(paramMap.get("price")));
    book.setPages(Integer.parseInt(paramMap.get("pages")));
    book.setStock(Integer.parseInt(paramMap.get("stock")));
    book.setClassName(paramMap.get("className"));
    book.setSuppl(paramMap.get("suppl").equals("y")? true : false);
    book.setDistr(paramMap.get("distr").equals("y")? true : false);
    
    if (existTitle(book.getTitle()) != null) {
      out.println("이메일이 이미 존재합니다. 등록을 취소합니다.");
      return;
    }
    list.add(book);
    changed = true;
    out.println("등록하였습니다.");
  }
  
  private Textbook existTitle(String title) {
    for (Textbook book : list) {
      if (book.getTitle().toLowerCase().equals(title.toLowerCase())) {
        return book;
      }
    }
    return null;
  }

  private void doList() {
    for (Textbook book : list) {
      out.println(String.format("%s, %s, %s, %d원, %d쪽, %d남음, %s,"
          + " %s, %s",
          book.getTitle(),
          book.getAuthor(),
          book.getPress(),
          book.getPrice(),
          book.getPages(),
          book.getStock(),
          (book.isSuppl() ? "부록있음" : "부록없음"),
          book.getClassName(),
          (book.isDistr() ? "배부함" : "배부안함")));
    }
  }

  private void doView(String params) {
    System.out.println(params);
    String[] values = params.split("=");

    for (Textbook book : list) {
      if (book.getTitle().equals(values[1])) {
        out.println("-----------------------");
        out.println("교재명: " + book.getTitle());
        out.println("저자: " + book.getAuthor());
        out.println("출판사: " + book.getPress());
        out.println("가격: " + book.getPrice());
        out.println("쪽수: " + book.getPages());
        out.println("재고수량: " + book.getStock());
        out.println("강좌명: " + book.getClassName());
        out.println("부록: " + (book.isSuppl() ? "부록있음" : "부록없음"));
        out.println("배부: " + (book.isDistr() ? "배부함" : "배부안함"));
        out.println("-----------------------");
        return;
      }
    }
    out.println("해당 이름의 교재가 없습니다.");
  }

  private void doDelete(String params) {
    System.out.println(params);
    String deleteTitle = (params.split("="))[1];
    
    Textbook deleteBook = existTitle(deleteTitle);
    
    if (deleteBook == null) {
      out.println("해당 이름의 교재가 없습니다.");
      return;
    }
    
    list.remove(deleteBook);
    
    changed = true;
    out.println("삭제하였습니다.");
  }

  //update?title=db study&author=ccc&press=bitpress&price=10000&pages=400&stock=4&className=bit89&suppl=y&distr=n
  private void doUpdate(String params) {
    System.out.println(params);
    String[] values = params.split("&");
    HashMap<String, String> paramMap = new HashMap<>();
    
    for (String value : values) {
      String[] kv = value.split("=");
      paramMap.put(kv[0], kv[1]);
    }
    
    Textbook book = existTitle(paramMap.get("title"));
    if (book == null) {
      out.println("해당 이름의 교재가 없습니다.");
      return;
    }
    book.setTitle(paramMap.get("title"));
    book.setAuthor(paramMap.get("author"));
    book.setPress(paramMap.get("press"));
    book.setPrice(Integer.parseInt(paramMap.get("price")));
    book.setPages(Integer.parseInt(paramMap.get("pages")));
    book.setStock(Integer.parseInt(paramMap.get("stock")));
    book.setClassName(paramMap.get("className"));
    book.setSuppl(paramMap.get("suppl").equals("y")? true : false);
    book.setDistr(paramMap.get("distr").equals("y")? true : false);
    
    out.println("변경하였습니다.");
    changed = true;
  }
    
  @SuppressWarnings("unchecked")
  private void doLoad() {
    try {
      File file = new File(filePath);
      if(!file.exists()) {
        file.createNewFile();
      }
    } catch (IOException e){
      System.out.println("파일을 정상적으로 로드하지 못했습니다.");
    }

    FileInputStream fis = null;
    ObjectInputStream in = null;
    try {
      fis = new FileInputStream(filePath);
      in = new ObjectInputStream(fis);
      
      list = (ArrayList<Textbook>)in.readObject();
    } catch (EOFException e) {
      // 파일 생성 후 데이터 없음 or 파일을 모두 읽음
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("데이터를 정상적으로 로드하지 못했습니다.");
    } finally {
      try {
        in.close();
        fis.close();
      } catch (Exception e) {}
    }
  }

  public void doSave() {
    try {
      FileOutputStream fos = new FileOutputStream(filePath);
      ObjectOutputStream out = new ObjectOutputStream(fos);

      out.writeObject(list);
      
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
