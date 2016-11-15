package bitcamp.java89.ems;

import java.util.Scanner;

public class EduApp {
  static Scanner keyScan;
  static String filePath;
  static TextbookController textbookController;

  static {
    keyScan = new Scanner(System.in);
    filePath = "./src/bitcamp/java89/ems/TextbookData.data";
    textbookController = new TextbookController(keyScan, filePath);
  }

  public static void main(String[] args) {
    // TextbookController textbookController = new TextbookController(keyScan);
    // textbookController.doLoad(filePath);

    System.out.println("비트캠프 관리시스템에 오신걸 환영합니다.");

    loop:
    while(true) {
      System.out.println("-----------------------------------------------------");
      System.out.println("[menu]:메뉴확인하기/[save]:데이터저장");
      System.out.print("명령> ");
      String command = keyScan.nextLine().toLowerCase();

      switch (command) {
      case "menu": doMenu(); break;
      case "go 1": textbookController.service(); break;
      case "save": textbookController.doSave(filePath); break;
      case "quit": 
        if (doQuit()) {break loop;}
        else {break;}
      default:
        System.out.println("지원하지 않는 명령어입니다.");
        break;
      }
    }
  }

  static void doMenu() {
    System.out.println("[메뉴]");
    System.out.println("1. 교재관리");
    System.out.println("메뉴 이동은 'go 메뉴번호'를 입력하세요.");
  }

  static boolean doQuit() {
    if (textbookController.isChanged()) {
      System.out.print("학생 정보가 변경되었습니다. 그래도 종료하시겠습니까?(y/n) ");
      if (!keyScan.nextLine().equals("y")) {return false;}
      else {
        System.out.println("학생 정보가 변경된 것을 취소하고 종료합니다.");
      }
    }
    System.out.println("Good bye!");
    return true;
  }
}
