package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import utils.GetData;

public class Message {
  private String message;
  private User user;
  private Boolean read;
  private LocalDateTime dateTime;

  public Message(String message, User user, Boolean read, LocalDateTime dateTime) {
    this.message = message;
    this.user = user;
    this.read = false;
    this.dateTime = dateTime;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public User getUser() {
    return this.user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Boolean isRead() {
    return read;
  }

  public void setRead(Boolean read) {
    this.read = read;
  }

  public LocalDateTime getDateTime() {
    return dateTime;
  }

  public void setDateTime(LocalDateTime dateTime) {
    this.dateTime = dateTime;
  }

  public String getTime() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    String formattedTime = dateTime.format(formatter);
    return formattedTime;
  }

  public static Boolean isValidMessageFromServer(String data) {

    String method = data.split(" ")[0];

    if (!method.equalsIgnoreCase("SEND")) {
      return false;
    }

    String util = GetData.getUtil(data);

    if (util.split(":").length != 3) {
      return false;
    }

    return true;
  }

  public static Message buildFromServer(String data) {
    String util = GetData.getUtil(data);

    String[] parts = util.split(":");

    User user = new User(parts[1]);
    Message message = new Message(parts[2], user, false, LocalDateTime.now());

    return message;
  }
}
