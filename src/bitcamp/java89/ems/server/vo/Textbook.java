package bitcamp.java89.ems.server.vo;

import java.io.Serializable;

public class Textbook  implements Serializable {
  private static final long serialVersionUID = 1L;
  
  //인스턴스 변수
  protected String title; //교재명
  protected String author; //저자
  protected String press; //출판사
  protected int price; //가격
  protected int pages; //페이지 수
  protected int stock; //재고 수, 보유 수
  protected String className; //교재가 사용되는 강좌명
  protected boolean suppl; //부록 여부
  protected boolean distr; //배부 여부

  public Textbook() {}

  public Textbook(String title, int stock, String className, boolean distr) {
    this.title = title;
    this.stock = stock;
    this.className = className;
    this.distr = distr;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getPress() {
    return press;
  }

  public void setPress(String press) {
    this.press = press;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public int getPages() {
    return pages;
  }

  public void setPages(int pages) {
    this.pages = pages;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public boolean isSuppl() {
    return suppl;
  }

  public void setSuppl(boolean suppl) {
    this.suppl = suppl;
  }

  public boolean isDistr() {
    return distr;
  }

  public void setDistr(boolean distr) {
    this.distr = distr;
  }

  @Override
  public String toString() {
    return "Textbook [title=" + title + ", author=" + author + ", press=" + press + ", price=" + price + ", pages="
        + pages + ", stock=" + stock + ", className=" + className + ", suppl=" + suppl + ", distr=" + distr + "]";
  }
  
  
}
