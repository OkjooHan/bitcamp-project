package bitcamp.java89.ems.server.vo;

import java.io.Serializable;

public class Phonebook implements Serializable {
  private static final long serialVersionUID = 1L;

  private String name;
  private String position;
  private String tel;
  private String email;
  
  
  public Phonebook() {
    super();
  }

  public Phonebook(String name, String position, String tel, String email) {
    super();
    this.name = name;
    this.position = position;
    this.tel = tel;
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
