// 자바 기본도구 사용법
// 입력받은 데이터를 특정타입의
package bitcamp.java89.ems;

import java.util.Scanner;

public class LectureController {
  private Box head;
  private Box tail;
  private int length;
  private Scanner keyScan;
  private LinkedList list;


  public LectureController (Scanner keyScan) {
    tail = new Box();
    head = tail;
    length = 0;
    this.keyScan = keyScan;
    list = new LinkedList();
  }


  public void service() {
    loop:
    while (true) {
      System.out.println("-----------------------------------------------------");
      System.out.println("강의추가:add / 강의전체리스트:list / 강의검색:view / 삭제:delete / 업데이트:update / 메인:main");
      System.out.print("강의관리> ");
      String command = keyScan.nextLine().toLowerCase();

      switch (command) {
      case "add": this.doAdd(); break;
      case "list" : this.doList(); break;
      case "view" : this.doView(); break;
      case "update" : this.doUpdate(); break;
      case "delete" : this.doDelete(); break;
      case "main" : break loop;
      default : System.out.println("지원하지 않는 명령어입니다.");
      }
    }
  }

  private void doAdd() {
    while (true) {
      Lecture lecture = new Lecture();
      System.out.print("강의제목? ");
      lecture.title = this.keyScan.nextLine();

      System.out.print("강사? ");
      lecture.teacher = this.keyScan.nextLine();

      System.out.print("커리큘럼? ");
      lecture.curriculum = this.keyScan.nextLine();

      System.out.print("요일? ");
      lecture.day = this.keyScan.nextLine();

      System.out.print("장소? ");
      lecture.place = keyScan.nextLine();

      System.out.print("오픈시간? ");
      lecture.openTime = Integer.parseInt(this.keyScan.nextLine());

      System.out.print("종료시간? ");
      lecture.closeTime = Integer.parseInt(this.keyScan.nextLine());

      System.out.print("기간? ");
      lecture.period = Integer.parseInt(this.keyScan.nextLine());

      System.out.print("교육비? ");
      lecture.price = Integer.parseInt(this.keyScan.nextLine());

      list.add(lecture);

      System.out.print("계속 입력하시겠습니까(y/n)?");
      if (!this.keyScan.nextLine().equals("y"))
        break;
    }
  }

  private void doList() {
    for (int i = 0; i < list.size(); i++) {
      Lecture lecture = (Lecture)list.get(i);
      System.out.println("---------------------------------------------");
      System.out.printf("강의제목 : %s\n강사 : %s\n커리큘럼 : %s\n요일 : %s\n장소 : %s\n오픈시간 : %d\n종료시간 : %d\n기간 : %d\n교육비 : %d\n",
        lecture.title,
        lecture.teacher,
        lecture.curriculum,
        lecture.day,
        lecture.place,
        lecture.openTime,
        lecture.closeTime,
        lecture.period,
        lecture.price);
    }
  }

  private void doView() {
    System.out.println("조회할 강좌의 인덱스 입력 : ");
    int index = Integer.parseInt(keyScan.nextLine());
    Lecture lecture = (Lecture)list.get(index);
    if (lecture == null) {
      return;
    }

    System.out.printf("강의제목 : %s\n", lecture.title);
    System.out.printf("강사 : %s\n", lecture.teacher);
    System.out.printf("커리큘럼 : %s\n", lecture.curriculum);
    System.out.printf("요일 : %s\n", lecture.day);
    System.out.printf("장소 : %s\n", lecture.place);
    System.out.printf("오픈시간 : %s\n", lecture.openTime);
    System.out.printf("종료시간 : %s\n", lecture.closeTime);
    System.out.printf("기간 : %s\n", lecture.period);
    System.out.printf("교육비 : %s\n", lecture.price);
  }

  private void doDelete() {
    System.out.println("삭제할 강좌의 인덱스 입력 : ");
    int index = Integer.parseInt(keyScan.nextLine());

    if (index < 0 || index >= list.size()) {
      System.out.println("인덱스가 유효하지 않습니다.");
      return;
    }
    Lecture deletedLecture = (Lecture)list.remove(index);
    System.out.printf("%s 강좌를 삭제하였습니다.\n", deletedLecture.title);
  }

  private void doUpdate() {
    System.out.println("변경할 강좌의 인덱스 입력 : ");
    int index = Integer.parseInt(keyScan.nextLine());

    Lecture oldLecture = (Lecture)list.get(index);
    if (oldLecture == null) {
      return;
    }

    Lecture lecture = new Lecture();
    lecture.title = oldLecture.title;
    System.out.printf("강사 : %s >> ", oldLecture.teacher);
    lecture.teacher = this.keyScan.nextLine();
    System.out.printf("커리큘럼 : %s >> ", oldLecture.curriculum);
    lecture.curriculum = this.keyScan.nextLine();
    System.out.printf("요일 : %s >> ", oldLecture.day);
    lecture.day = this.keyScan.nextLine();
    System.out.printf("장소 : %s >> ", oldLecture.place);
    lecture.place = this.keyScan.nextLine();
    System.out.printf("오픈시간 : %s >> ", oldLecture.openTime);
    lecture.openTime = Integer.parseInt(this.keyScan.nextLine());
    System.out.printf("종료시간 : %s >> ", oldLecture.closeTime);
    lecture.closeTime = Integer.parseInt(this.keyScan.nextLine());
    System.out.printf("기간 : %s >> ", oldLecture.period);
    lecture.period = Integer.parseInt(this.keyScan.nextLine());
    System.out.printf("교육비 : %s >> ", oldLecture.price);
    lecture.price = Integer.parseInt(this.keyScan.nextLine());

    System.out.print("저장하시겠습니까((y/n)?");
    if (this.keyScan.nextLine().equals("y")) {
      list.set(index, lecture);
      System.out.printf("%s의 내용을 저장하였습니다.\n", oldLecture.title);
    } else {
      System.out.printf("%s의 변경을 취소하였습니다.\n", oldLecture.title);
    }
    return;
  }
}
