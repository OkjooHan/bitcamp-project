package bitcamp.java89.ems.server.controller;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.omg.Messaging.SyncScopeHelper;

import bitcamp.java89.ems.server.vo.Phonebook;

public class PhonebookController {
  private Scanner in;
  private PrintStream out;
  private ArrayList<Phonebook> list;
  static String filePath;
  static Boolean changed;
  
  static {
    filePath = "./src/bitcamp/java89/ems/Phonebook_v1.6.data";
    changed = false;
  }
  
  public PhonebookController(Scanner in, PrintStream out) {
    list = new ArrayList<Phonebook>();
    this.in = in;
    this.out = out;
    doLoad();
  }
  
  public boolean service() {
    while (true) {
      out.println("[add]:추가 / [list]:보기 / [view]:조회 / [update]:변경 / [delete]:삭제 / [main]:메인메뉴");
      out.println("연락처관리>");
      out.println(); //보내는 데이터의 끝을 의미
      
      String[] command =in.nextLine().split("\\?");
      switch (command[0]) {
      case "add": doAdd(command[1]); break;
      case "list": doList(); break;
      case "view": doView(command[1]); break;
      case "update": doUpdate(command[1]); break;
      case "delete": doDelete(command[1]); break;
//      case "deleteall" : doDeleteAll(); break;
      case "main": doQuit(); return true;
      case "quit": doQuit(); return false;
      default:
        out.println("올바른 명령이 아닙니다.");
      }
    }
  }
  
  private void doQuit() {
    if (isChanged())
      doSave();
//    System.out.println("Good bye!");
  }
  
  @SuppressWarnings("unchecked")
  private void doLoad() {
    try {
      File file = new File(filePath);
      if (!file.exists()) {
        file.createNewFile();
      }
    } catch (Exception e) {
      System.out.println("파일이 정상적으로 로딩되지 않았습니다.");
    }
    
    FileInputStream fis = null;
    ObjectInputStream in = null;
    try {
     fis = new FileInputStream(filePath);
     in = new ObjectInputStream(fis);
     list = (ArrayList<Phonebook>)in.readObject();
    } catch (EOFException e) {
    } catch (Exception e) {
      System.out.println("데이터가 정상적으로 로딩되지 않았습니다.");
    } finally {
      try {
        in.close();
        fis.close();
      } catch (Exception e) {}
    }
  }
  
  public void doSave() {
    FileOutputStream fos = null;
    ObjectOutputStream out = null;
    try {
      fos = new FileOutputStream(filePath);
      out = new ObjectOutputStream(fos);
      out.writeObject(list);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("파일이 정상적으로 저장되지 않았습니다.");
    } finally {
      try {
        out.close();
        fos.close();
        changed = false;
        System.out.println("파일이 저장되었습니다.");
      } catch (Exception e) {}
    }
  }
  
  //클라이언트에서 보낸 데이터 형식
  //=>add?name=홍길동&position=대리&tel=111-1111&email=hong@test.com
  //=>doAdd(name=홍길동&position=대리&tel=111-1111&email=hong@test.com)
  public void doAdd(String params) {
    System.out.println(params);
    String[] values = params.split("&");
    HashMap<String, String> paramMap = new HashMap<>();
    
    for (String value : values) {
      String[] kv = value.split("=");
      paramMap.put(kv[0], kv[1]);
    }
    
    Phonebook book = new Phonebook();
    book.setName(paramMap.get("name"));
    book.setPosition(paramMap.get("position"));
    book.setTel(paramMap.get("tel"));
    book.setEmail(paramMap.get("email"));
    
    boolean save = true;
    if (existEmail(book.getEmail())) {
      out.println("이메일이 이미 존재합니다. 등록을 취소합니다.");
      return;
    }
    list.add(book);
    changed = true;
    out.println("등록하였습니다.");
  }

  private boolean existEmail(String email) {
    for (Phonebook findEmail : list) {
      if (findEmail.getEmail().toLowerCase().equals(email.toLowerCase())) {
        return true;
      }
    }
    return false;
  }
 
  public void doList() {
    for (Phonebook book : list) {
      out.println(String.format("%s, %s, %s, %s", 
          book.getName(),
          book.getEmail(),
          book.getTel(),
          book.getPosition()));
    }
  }

  public void doView(String params) {
    System.out.println(params);
    String[] values = params.split("=");

    for (Phonebook book : list) {
      if (book.getEmail().equals(values[1])) {
        out.println("------------------------------");
        out.println("이름: " + book.getName());
        out.println("직위: " + book.getPosition());
        out.println("전화: " + book.getTel());
        out.println("이메일: " + book.getEmail());
        out.println("------------------------------");
        return;
      }
    }
    out.println("해당 이름의 사람이 없습니다.");
  }
 
  public void doUpdate(String params) {
    System.out.println(params);
    String[] values = params.split("&");
    HashMap<String, String> paramMap = new HashMap<>();
    
    for (String value : values) {
      String[] kv = value.split("=");
      paramMap.put(kv[0], kv[1]);
    }
    
    Phonebook deleteList = searchByName(paramMap.get("email"));
    if (deleteList == null) {
      out.println("입력하신 이메일을 가진 사람이 없습니다.");
      return;
    }
    deleteList.setName(paramMap.get("name"));
    deleteList.setPosition(paramMap.get("position"));
    deleteList.setTel(paramMap.get("tel"));
    
    out.println("변경하였습니다.");
    changed = true;
  }

/* 기존에 내가 작성한 메서드
  public void doDelete() {
    ArrayList<Phonebook> viewList = new ArrayList<>();

    Boolean isFind = false;
    int count = 0;
    
    System.out.print("삭제할 이름? ");
    String name = keyScan.nextLine();
    
    for (Phonebook book : list) {
      if (book.getName().equals(name)) {
        viewList.add(book);
        isFind = true;
      }
    }
    
    if (isFind) {
      for (Phonebook book : viewList) {
        System.out.print(++count + ". " + book.getName() + "(" + book.getEmail() + ")\n");
      }
      
      System.out.println("삭제할 번호? ");
      int inputNum = Integer.parseInt(keyScan.nextLine());
      if (inputNum <= 0 || inputNum > count) {
        System.out.println("해당 번호가 없습니다.");
        return;
      }
      
      for (Phonebook book : list) {
        if (book.equals(viewList.get(inputNum - 1))) {
          list.remove(book);
          changed = true;
          System.out.println("삭제하였습니다.");
          return;
        }
      }
    }
    System.out.println("해당 이름의 사람이 없습니다.");
    return;
  }
*/

  private void doDelete(String params) {
    System.out.println(params);
    String deleteEmail = (params.split("="))[1];
    
    Phonebook deleteList = searchByName(deleteEmail);
    
    if (deleteList == null) {
      out.println("해당 이름의 사람이 없습니다.");
      return;
    }
    
    list.remove(deleteList);
    
    changed = true;
    out.println("삭제하였습니다.");
  }
  
  private Phonebook searchByName(String email) {
    for (Phonebook book : list) {
      if (book.getEmail().toLowerCase().equals(email.toLowerCase())) {
       return book;
      }
    }
    return null;
  }
  

  public Boolean isChanged() {
    return changed;
  }
  public void doDeleteAll() {
    list.clear();
    System.out.println("연락처를 모두 삭제하였습니다.");
  }
}
