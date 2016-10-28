package bitcamp.java89.ems;

public class Textbook{
  //인스턴스 변수
  String className; //교재가 사용되는 강좌명
  String title; //교재명
  String author; //저자
  String press; //출판사
  int price; //가격
  int pages; //페이지 수
  int stock; //재고 수, 보유 수
  boolean suppl; //부록 여부
  boolean distr; //배부 여부

  public Textbook() {}

  public Textbook(String className, String title, int stock, boolean distr) {
    this.className = className;
    this.title = title;
    this.stock = stock;
    this.distr = distr;
  }
}
