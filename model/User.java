package model;

public class User {
  private String ip;
  private String name;

  public User(String ip) {
    this.ip = ip;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
