package bitcamp.java89.ems;

public class Textbook{
  //인스턴스 변수
  int no; //교재 번호(여러권, 순서가 있는 경우)
  String title; //교재명
  String author; //저자
  String press; //출판사
  int price; //가격
  int pages; //페이지 수
  int stock; //재고 수, 보유 수
  boolean suppl; //부록 여부
  String className; //교재가 사용되는 강좌명
  boolean distr; //배부 여부

  public Textbook() {}

  public Textbook(String title, int stock, String className, boolean distr) {
    this.title = title;
    this.stock = stock;
    this.className = className;
    this.distr = distr;
  }
}
