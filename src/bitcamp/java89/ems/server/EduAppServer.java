package bitcamp.java89.ems.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import bitcamp.java89.ems.server.controller.PhonebookController;
import bitcamp.java89.ems.server.controller.TextbookController;

public class EduAppServer {
  static Scanner in;
  static PrintStream out;
  static String filePath;
  static TextbookController textbookController;
  static PhonebookController phonebookController;

  public static void main(String[] args) throws Exception {
    ServerSocket ss = new ServerSocket(8888);
    System.out.println("서버 실행 중.....");
    
    while (true) {
      processRequest(ss.accept());
    }
//    ss.close();
  }

  private static void processRequest(Socket socket) throws IOException {
    try {
      in = new Scanner(new BufferedInputStream(socket.getInputStream()), "UTF-8");
      out = new PrintStream(new BufferedOutputStream(socket.getOutputStream()), true);
      
      
      textbookController = new TextbookController(in, out);
      phonebookController = new PhonebookController(in, out);
      
      out.println("비트캠프 관리시스템에 오신걸 환영합니다.");
   
      loop:
      while(true) {
        //클라이어트에게 데이터를 전송한다.
        out.println("명령>");
        out.println(); //보내는 데이터의 끝을 의미한다.
        //클라이언트로부터 명령을 읽는다.
        String command = in.nextLine().toLowerCase();
   
        boolean running = false;
        switch (command) {
        case "menu": doMenu(); break;
        case "go 1": running = textbookController.service(); break;
        case "go 2" : running = phonebookController.service(); break;
   //      case "save": textbookController.doSave(filePath); break;
        case "quit": break loop;
   //        if (doQuit()) {break loop;}
   //        else {break;}
        default:
          out.println("지원하지 않는 명령어입니다.");
          break;
        }
        if (!running) {
          break;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {in.close();} catch (Exception e) {}
      try {out.close();} catch (Exception e) {}
      try {socket.close();} catch (Exception e) {}
    }
  }

  static void doMenu() {
    out.println("[메뉴]");
    out.println("1. 교재관리");
    out.println("2. 연락처관리");
    out.println("메뉴 이동은 'go 메뉴번호'를 입력하세요.");
  }
  

//  static boolean doQuit() {
//    if (textbookController.isChanged()) {
//      out.println("학생 정보가 변경되었습니다. 그래도 종료하시겠습니까?(y/n) ");
//      out.println();
//      if (!in.nextLine().equals("y")) {return false;}
//      else {
//        out.println("학생 정보가 변경된 것을 취소하고 종료합니다.");
//      }
//    }
//    out.println("Good bye!");
//    out.println();
//    return true;
//  }
}
