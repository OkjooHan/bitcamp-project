// 자바 기본도구 사용법
// 입력받은 데이터를 특정타입의
package bitcamp.java89.ems;

public class Lecture {
  //인스턴스 변수
  String title;
  String teacher;
  String curriculum;
  String day;
  String place;
  int openTime;
  int closeTime;
  int period;
  int price;
  //boolean bookMark;

  public Lecture() {}

  public Lecture(String title, String teacher, int openTime, String day) {
    this.title = title;
    this.teacher = teacher;
    this.openTime = openTime;
    this.day = day;
  }

  public static void main(String[] args) {
  }
}
